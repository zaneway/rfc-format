package com.github.zaneway.format.rfc.llm;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import com.github.zaneway.format.rfc.persistence.mapper.RfcLlmProjectionRecordMapper;
import org.springframework.stereotype.Service;

/** MySQL 已提交后将结构化 unit 同步投影到 Qdrant。 */
@Service
public final class QdrantStructuredRfcVectorProjector implements RfcStructuredVectorProjector {
  private final VectorStore vectorStore;
  private final RfcLlmProjectionRecordMapper projectionMapper;

  public QdrantStructuredRfcVectorProjector(VectorStore vectorStore, RfcLlmProjectionRecordMapper projectionMapper) {
    this.vectorStore = vectorStore;
    this.projectionMapper = projectionMapper;
  }

  /** Qdrant 异常仅更新 SKIPPED 状态，确保 MySQL 事实层永不回滚。 */
  @Override
  public LlmProjectionResult project(StoredLlmRfcDocument stored, StructuredRfcDocument document) {
    try {
      update(stored.documentId(), "RUNNING", null, null);
      vectorStore.delete(new Filter.Expression(Filter.ExpressionType.EQ,
          new Filter.Key("document_id"), new Filter.Value(stored.documentId())));
      List<Document> vectors = document.units().stream().map(unit -> vector(stored.documentId(), document, unit)).toList();
      vectorStore.add(vectors);
      update(stored.documentId(), "SUCCEEDED", vectors.size(), null);
      return LlmProjectionResult.succeeded(vectors.size());
    } catch (RuntimeException exception) {
      String error = error(exception);
      try {
        update(stored.documentId(), "SKIPPED", null, error);
      } catch (RuntimeException ignored) { }
      return LlmProjectionResult.skipped(error);
    }
  }

  private void update(long documentId, String statusValue, Integer vectorCountValue, String errorValue) {
    projectionMapper.update(c -> c.set(com.github.zaneway.format.rfc.persistence.mapper.RfcLlmProjectionRecordDynamicSqlSupport.status).equalTo(statusValue)
        .set(com.github.zaneway.format.rfc.persistence.mapper.RfcLlmProjectionRecordDynamicSqlSupport.vectorCount).equalToWhenPresent(() -> vectorCountValue)
        .set(com.github.zaneway.format.rfc.persistence.mapper.RfcLlmProjectionRecordDynamicSqlSupport.errorMessage).equalToWhenPresent(() -> errorValue)
        .where(com.github.zaneway.format.rfc.persistence.mapper.RfcLlmProjectionRecordDynamicSqlSupport.documentId, org.mybatis.dynamic.sql.SqlBuilder.isEqualTo(documentId)));
  }

  private static Document vector(long documentId, StructuredRfcDocument document, StructuredRfcDocument.Unit unit) {
    LinkedHashMap<String, Object> metadata = new LinkedHashMap<>();
    metadata.put("document_id", documentId);
    metadata.put("standard_id", document.document().standardId());
    metadata.put("rfc_number", document.document().extensions().rfcNumber());
    metadata.put("unit_id", unit.unitId());
    metadata.put("content_type", unit.contentType());
    metadata.put("clause_id", unit.clauseId());
    metadata.put("heading_path", unit.headingPath());
    return new Document(UUID.nameUUIDFromBytes((documentId + "\u0000" + unit.unitId()).getBytes(StandardCharsets.UTF_8)).toString(), unit.content(), metadata);
  }

  private static String error(RuntimeException exception) {
    String value = exception.getMessage();
    if (value == null || value.isBlank()) value = exception.getClass().getSimpleName();
    return value.length() > 4000 ? value.substring(0, 4000) : value;
  }
}
