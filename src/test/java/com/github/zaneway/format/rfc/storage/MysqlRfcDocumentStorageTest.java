package com.github.zaneway.format.rfc.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcExtractedObject;
import com.github.zaneway.format.rfc.parser.model.RfcRelation;
import com.github.zaneway.format.rfc.parser.model.RfcSection;
import com.github.zaneway.format.rfc.parser.model.RfcSource;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import com.github.zaneway.format.rfc.persistence.mapper.RfcDocumentRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcIngestionRunRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcObjectRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcProcessingReportRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcRelationRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcSectionRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcUnitRecordMapper;
import com.github.zaneway.format.rfc.persistence.model.RfcDocumentRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcObjectRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcRelationRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcSectionRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcUnitRecord;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MysqlRfcDocumentStorageTest {

  @Test
  void replacesDerivedLayersThroughGeneratedMapperMethods() {
    List<String> operations = new ArrayList<>();
    List<List<?>> insertedBatches = new ArrayList<>();
    RfcDocumentRecord existing = document(42L);
    MysqlRfcDocumentStorage storage = storage(operations, List.of(existing), insertedBatches);

    StoredRfcDocument stored = storage.store(sampleDocument(), "/tmp/rfc9999.txt");

    assertThat(stored.documentId()).isEqualTo(42L);
    assertThat(stored.documentKey()).isEqualTo("rfc-9999");
    assertThat(operations).containsSubsequence(
        "document.selectOne", "document.updateByPrimaryKeySelective",
        "relation.delete", "object.delete", "unit.delete", "section.delete",
        "section.insertMultiple", "unit.insertMultiple", "relation.insertMultiple",
        "object.insertMultiple",
        "ingestion.insertSelective", "report.selectOne", "report.insertSelective");
    assertThat(insertedBatches).flatExtracting(batch -> batch).allSatisfy(record ->
        assertThat(createdAt(record)).as("Generator insertMultiple record audit time").isNotNull());
  }

  @Test
  void insertsDocumentWhenDocumentKeyHasNoExistingRecord() {
    List<String> operations = new ArrayList<>();
    MysqlRfcDocumentStorage storage = storage(operations,
        new ArrayList<>(Arrays.asList(null, document(43L))), new ArrayList<>());

    StoredRfcDocument stored = storage.store(sampleDocument(), "/tmp/rfc9999.txt");

    assertThat(stored.documentId()).isEqualTo(43L);
    assertThat(operations).containsSubsequence("document.selectOne", "document.insertSelective",
        "document.selectOne");
  }

  private static MysqlRfcDocumentStorage storage(List<String> operations,
      List<RfcDocumentRecord> selectResults,
      List<List<?>> insertedBatches) {
    RfcDocumentRecordMapper documentMapper = mapper(RfcDocumentRecordMapper.class, "document",
        operations, selectResults, insertedBatches);
    return new MysqlRfcDocumentStorage(documentMapper,
        mapper(RfcSectionRecordMapper.class, "section", operations, List.of(), insertedBatches),
        mapper(RfcUnitRecordMapper.class, "unit", operations, List.of(), insertedBatches),
        mapper(RfcRelationRecordMapper.class, "relation", operations, List.of(), insertedBatches),
        mapper(RfcObjectRecordMapper.class, "object", operations, List.of(), insertedBatches),
        mapper(RfcIngestionRunRecordMapper.class, "ingestion", operations, List.of(),
            insertedBatches),
        mapper(RfcProcessingReportRecordMapper.class, "report", operations, List.of(),
            insertedBatches), new ObjectMapper());
  }

  @SuppressWarnings("unchecked")
  private static <T> T mapper(Class<T> mapperType, String name, List<String> operations,
      List<RfcDocumentRecord> selectResults,
      List<List<?>> insertedBatches) {
    List<RfcDocumentRecord> results = new ArrayList<>(selectResults);
    return (T) Proxy.newProxyInstance(mapperType.getClassLoader(), new Class[]{mapperType},
        (proxy, method, arguments) -> {
          if (method.getDeclaringClass() == Object.class) {
            return switch (method.getName()) {
              case "toString" -> name + "Mapper";
              case "hashCode" -> System.identityHashCode(proxy);
              case "equals" -> proxy == arguments[0];
              default -> null;
            };
          }
          operations.add(name + "." + method.getName());
          if (method.getName().equals("insertMultiple")) {
            insertedBatches.add((List<?>) arguments[0]);
          }
          if (method.getName().equals("selectOne")) {
            return results.isEmpty() ? Optional.empty()
                : Optional.ofNullable(results.removeFirst());
          }
          if (method.getReturnType() == int.class) {
            return 1;
          }
          if (method.getReturnType() == long.class) {
            return 0L;
          }
          return null;
        });
  }

  private static java.time.LocalDateTime createdAt(Object record) {
    return switch (record) {
      case RfcSectionRecord value -> value.getCreatedAt();
      case RfcUnitRecord value -> value.getCreatedAt();
      case RfcRelationRecord value -> value.getCreatedAt();
      case RfcObjectRecord value -> value.getCreatedAt();
      default ->
          throw new IllegalArgumentException("Unexpected batch record type: " + record.getClass());
    };
  }

  private static RfcDocumentRecord document(long id) {
    RfcDocumentRecord record = new RfcDocumentRecord();
    record.setId(id);
    return record;
  }

  private static RfcDocument sampleDocument() {
    RfcSection section = new RfcSection("1", "Scope", "body", null, List.of("1"), 10, 20);
    RfcUnit unit = new RfcUnit("rfc-9999:1:section_content:1", "section_content", "1", "MUST test",
        "[RFC 9999 §1]\nMUST test", 11, 12, Map.of("normativeKeywords", List.of("MUST")));
    RfcRelation relation = new RfcRelation("rfc-9999:rel:1", "cites", "unit", unit.id(), "document",
        "rfc-2119", "1", 11, 11, Map.of("citationText", "RFC 2119", "startOffset", 0));
    RfcExtractedObject object = new RfcExtractedObject("rfc-9999:1:abnf_rule:token:1", "abnf_rule",
        "token",
        "", unit.id(), 11, 11, 0, 5, Map.of("notation", "ABNF"));
    return new RfcDocument("rfc-9999", "9999", "Test RFC", "Informational", "July 2026",
        new RfcSource("rfc9999.txt", "a".repeat(64)), List.of(section), List.of(unit),
        List.of(relation), List.of(object));
  }
}
