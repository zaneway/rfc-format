package com.github.zaneway.format.rfc.llm;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcLlmDocumentRecordDynamicSqlSupport.sourceSha256;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcLlmDocumentRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcLlmProjectionRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcLlmUnitRecordMapper;
import com.github.zaneway.format.rfc.persistence.model.RfcLlmDocumentRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcLlmProjectionRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcLlmUnitRecord;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/** 通过 MyBatis Generator 生成的 Mapper 写入 LLM RFC 事实层。 */
@Repository
public final class MybatisLlmRfcDocumentStorage implements LlmRfcDocumentStorage {
  private final RfcLlmDocumentRecordMapper documentMapper;
  private final RfcLlmUnitRecordMapper unitMapper;
  private final RfcLlmProjectionRecordMapper projectionMapper;
  private final ObjectMapper objectMapper;

  public MybatisLlmRfcDocumentStorage(RfcLlmDocumentRecordMapper documentMapper,
      RfcLlmUnitRecordMapper unitMapper, RfcLlmProjectionRecordMapper projectionMapper,
      ObjectMapper objectMapper) {
    this.documentMapper = documentMapper;
    this.unitMapper = unitMapper;
    this.projectionMapper = projectionMapper;
    this.objectMapper = objectMapper;
  }

  /** 同一事务内插入文档、units 和 PENDING 投影记录；返回时事务才允许提交。 */
  @Override
  @Transactional
  public StoredLlmRfcDocument store(StructuredRfcDocument document, String sourceText,
      String rawResponse) {
    String hash = sha256(sourceText);
    var existing = documentMapper.selectOne(c -> c.where(sourceSha256, isEqualTo(hash)));
    if (existing.isPresent()) return new StoredLlmRfcDocument(existing.orElseThrow().getId());

    RfcLlmDocumentRecord record = new RfcLlmDocumentRecord();
    record.setSourceFile(document.document().sourceFile());
    record.setSourceSha256(hash);
    record.setDocumentJson(json(document.document()));
    record.setStructuredJson(rawResponse);
    documentMapper.insertSelective(record);
    long documentId = documentMapper.selectOne(c -> c.where(sourceSha256, isEqualTo(hash)))
        .map(RfcLlmDocumentRecord::getId)
        .orElseThrow(() -> new IllegalStateException("MyBatis insert did not return RFC document"));

    var units = document.units().stream().map(unit -> unit(documentId, unit)).toList();
    if (!units.isEmpty()) unitMapper.insertMultiple(units);
    RfcLlmProjectionRecord projection = new RfcLlmProjectionRecord();
    projection.setDocumentId(documentId);
    projection.setStatus("PENDING");
    projectionMapper.insertSelective(projection);
    return new StoredLlmRfcDocument(documentId);
  }

  private RfcLlmUnitRecord unit(long documentId, StructuredRfcDocument.Unit unit) {
    RfcLlmUnitRecord record = new RfcLlmUnitRecord();
    record.setDocumentId(documentId); record.setUnitId(unit.unitId()); record.setClauseId(unit.clauseId());
    record.setHeadingPathJson(json(unit.headingPath())); record.setContentType(unit.contentType());
    record.setContent(unit.content()); record.setSourceStartLine(unit.sourceLocation().start());
    record.setSourceEndLine(unit.sourceLocation().end()); record.setExtensionsJson(json(unit.extensions()));
    return record;
  }

  private String json(Object value) {
    try { return objectMapper.writeValueAsString(value); }
    catch (JsonProcessingException exception) { throw new IllegalStateException("Cannot serialize RFC JSON", exception); }
  }

  private static String sha256(String value) {
    try { return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8))); }
    catch (NoSuchAlgorithmException exception) { throw new IllegalStateException("SHA-256 is unavailable", exception); }
  }
}
