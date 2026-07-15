package com.github.zaneway.format.rfc.catalog;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Spring AI VectorStore 适配器：写入独立的 RFC catalog collection。
 */
@Component
public class SpringAiRfcCatalogVectorClient implements RfcCatalogVectorClient {

  private static final Logger log = LoggerFactory.getLogger(SpringAiRfcCatalogVectorClient.class);

  private final VectorStore catalogVectorStore;

  public SpringAiRfcCatalogVectorClient(
      @Qualifier("rfcCatalogVectorStore") VectorStore catalogVectorStore) {
    this.catalogVectorStore = catalogVectorStore;
  }

  @Override
  public void replaceAll(List<RfcVectorDocument> documents) {
    log.info("开始全量替换 catalog 向量，待写入条数={}", documents.size());
    catalogVectorStore.delete(new FilterExpressionBuilder()
        .eq("unit_type", RfcCatalogEmbeddingMapper.UNIT_TYPE)
        .build());
    log.info("旧 catalog 向量删除完成");
    catalogVectorStore.add(documents.stream()
        .map(SpringAiRfcCatalogVectorClient::toSpringAiDocument)
        .toList());
    log.info("新 catalog 向量写入完成，条数={}", documents.size());
  }

  private static Document toSpringAiDocument(RfcVectorDocument document) {
    LinkedHashMap<String, Object> metadata = new LinkedHashMap<>(document.metadata());
    metadata.putIfAbsent("unit_id", document.id());
    String rfcNumber = String.valueOf(metadata.getOrDefault("rfc_number", ""));
    return new Document(qdrantPointId(rfcNumber), document.text(), metadata);
  }

  private static String qdrantPointId(String rfcNumber) {
    return UUID.nameUUIDFromBytes(("catalog\0" + rfcNumber).getBytes(StandardCharsets.UTF_8))
        .toString();
  }
}
