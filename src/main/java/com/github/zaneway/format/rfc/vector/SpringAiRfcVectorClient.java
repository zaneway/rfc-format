package com.github.zaneway.format.rfc.vector;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Spring AI {@link VectorStore} 到 Qdrant 的适配器。
 *
 * <p>通过 metadata 中的 {@code rfc_number} 字段实现按 RFC 范围的删除-再-写入替换，
 * 避免全量清空集合影响其他 RFC 的向量数据。</p>
 */
@Component
public class SpringAiRfcVectorClient implements RfcVectorClient {

  private static final Logger log = LoggerFactory.getLogger(SpringAiRfcVectorClient.class);

  private final VectorStore vectorStore;
  private final int batchSize;

  /**
   * @param vectorStore RFC chunk collection（默认 {@code rfc-data}）
   * @param batchSize   单次 embedding 与 Qdrant 写入的最大文档数
   */
  public SpringAiRfcVectorClient(@Qualifier("vectorStore") VectorStore vectorStore,
      @Value("${rfc.vector.write-batch-size:32}") int batchSize) {
    if (batchSize <= 0) {
      throw new IllegalArgumentException("rfc.vector.write-batch-size must be positive");
    }
    this.vectorStore = vectorStore;
    this.batchSize = batchSize;
  }

  /**
   * {@inheritDoc}
   *
   * <p>先按 {@code rfc_number} 过滤删除，再批量 add；两步均非事务性，
   * 删除成功但 add 失败时该 RFC 向量暂时为空，需通过重试恢复。</p>
   */
  @Override
  public void replace(String rfcNumber, List<RfcVectorDocument> documents) {
    long startedAt = System.nanoTime();
    log.info("开始调用 Qdrant 替换 RFC 向量，rfc={}，向量点={}，批大小={}",
        rfcNumber, documents.size(), batchSize);
    // 仅删除本 RFC 的向量点，保留集合内其他文档；删除与后续写入不是跨系统事务。
    vectorStore.delete(new FilterExpressionBuilder().eq("rfc_number", rfcNumber).build());
    List<Document> batch = new ArrayList<>(Math.min(batchSize, documents.size()));
    int written = 0;
    for (RfcVectorDocument document : documents) {
      batch.add(toSpringAiDocument(rfcNumber, document));
      if (batch.size() == batchSize) {
        vectorStore.add(List.copyOf(batch));
        written += batch.size();
        batch.clear();
      }
    }
    if (!batch.isEmpty()) {
      vectorStore.add(List.copyOf(batch));
      written += batch.size();
    }
    log.info("Qdrant RFC 向量替换完成，rfc={}，写入={}，耗时毫秒={}",
        rfcNumber, written, (System.nanoTime() - startedAt) / 1_000_000);
  }

  /**
   * Qdrant point ID 只能使用 UUID 或无符号整数；业务单元 ID 保留在 metadata 中。
   */
  private static Document toSpringAiDocument(String rfcNumber, RfcVectorDocument document) {
    LinkedHashMap<String, Object> metadata = new LinkedHashMap<>(document.metadata());
    metadata.putIfAbsent("unit_id", document.id());
    String pointId = UUID.nameUUIDFromBytes(
        (rfcNumber + "\0" + document.id()).getBytes(StandardCharsets.UTF_8)).toString();
    return new Document(pointId, document.text(), metadata);
  }
}
