package com.github.zaneway.format.rfc.vector;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Ollama embedding 与 Qdrant point store 的 RFC 向量编排器。
 *
 * <p>单个文档 embedding 失败时记录并跳过；Qdrant 删除或写入失败仍向上抛出，
 * 避免基础设施故障被误判为可忽略的数据问题。</p>
 */
@Component
public class SpringAiRfcVectorClient implements RfcVectorClient {

  private static final Logger log = LoggerFactory.getLogger(SpringAiRfcVectorClient.class);

  private final EmbeddingModel embeddingModel;
  private final RfcPointStore pointStore;
  private final int batchSize;

  /**
   * @param embeddingModel Ollama embedding 调用边界
   * @param pointStore     Qdrant point 删除与写入边界
   * @param batchSize      单次 embedding 与 Qdrant upsert 的最大文档数
   */
  public SpringAiRfcVectorClient(EmbeddingModel embeddingModel, RfcPointStore pointStore,
      @Value("${rfc.vector.write-batch-size:32}") int batchSize) {
    if (batchSize <= 0) {
      throw new IllegalArgumentException("rfc.vector.write-batch-size must be positive");
    }
    this.embeddingModel = embeddingModel;
    this.pointStore = pointStore;
    this.batchSize = batchSize;
  }

  /**
   * {@inheritDoc}
   *
   * <p>先生成当前 RFC 的可用 embedding，再删除旧点并分批 upsert。批量 embedding
   * 失败时使用二分法定位失败文档，避免一个超长 chunk 连带丢弃同批正常文档。
   * 若整个 RFC 均失败，则保留其旧向量，防止 Ollama 整体故障清空线上数据。</p>
   */
  @Override
  public RfcVectorWriteResult replace(String rfcNumber, List<RfcVectorDocument> documents) {
    long startedAt = System.nanoTime();
    log.info("开始生成并替换 RFC 向量，rfc={}，候选={}，批大小={}",
        rfcNumber, documents.size(), batchSize);

    List<List<RfcEmbeddedDocument>> batches = new ArrayList<>();
    int skipped = 0;
    for (int start = 0; start < documents.size(); start += batchSize) {
      int end = Math.min(start + batchSize, documents.size());
      EmbeddingBatchResult result = embedWithIsolation(
          rfcNumber, List.copyOf(documents.subList(start, end)));
      if (!result.documents().isEmpty()) {
        batches.add(result.documents());
      }
      skipped += result.skippedCount();
    }

    if (!documents.isEmpty() && batches.isEmpty()) {
      log.error("RFC 所有 embedding 均失败，跳过本次替换并保留旧向量，rfc={}，跳过={}",
          rfcNumber, skipped);
      return new RfcVectorWriteResult(0, skipped);
    }

    // embedding 成功后才删除旧点，避免 Ollama 整体故障先清空当前 RFC。
    pointStore.deleteByRfc(rfcNumber);
    int written = 0;
    for (List<RfcEmbeddedDocument> batch : batches) {
      pointStore.upsert(rfcNumber, batch);
      written += batch.size();
    }
    log.info("RFC 向量替换完成，rfc={}，写入={}，跳过={}，耗时毫秒={}",
        rfcNumber, written, skipped, elapsedMillis(startedAt));
    return new RfcVectorWriteResult(written, skipped);
  }

  /** 批量调用失败后递归二分，直至定位单个失败文档；成功子批仍保留批量吞吐。 */
  private EmbeddingBatchResult embedWithIsolation(String rfcNumber,
      List<RfcVectorDocument> documents) {
    List<float[]> embeddings;
    long startedAt = System.nanoTime();
    try {
      log.debug("开始调用 Ollama embedding，rfc={}，文档数={}", rfcNumber, documents.size());
      embeddings = embeddingModel.embed(documents.stream().map(RfcVectorDocument::text).toList());
      if (embeddings.size() != documents.size()) {
        throw new IllegalStateException("Embedding count does not match document count");
      }
      log.debug("Ollama embedding 完成，rfc={}，文档数={}，耗时毫秒={}",
          rfcNumber, documents.size(), elapsedMillis(startedAt));
    } catch (RuntimeException exception) {
      if (documents.size() == 1) {
        RfcVectorDocument document = documents.getFirst();
        log.error("跳过 embedding 失败的 RFC chunk，rfc={}，unitId={}，文本字符数={}，耗时毫秒={}",
            rfcNumber, document.id(), document.text().length(), elapsedMillis(startedAt), exception);
        return new EmbeddingBatchResult(List.of(), 1);
      }
      int midpoint = documents.size() / 2;
      log.warn("批量 embedding 失败，二分定位问题 chunk，rfc={}，文档数={}，左批={}，右批={}，异常={}",
          rfcNumber, documents.size(), midpoint, documents.size() - midpoint,
          exception.toString());
      EmbeddingBatchResult left = embedWithIsolation(rfcNumber,
          List.copyOf(documents.subList(0, midpoint)));
      EmbeddingBatchResult right = embedWithIsolation(rfcNumber,
          List.copyOf(documents.subList(midpoint, documents.size())));
      List<RfcEmbeddedDocument> embedded = new ArrayList<>(
          left.documents().size() + right.documents().size());
      embedded.addAll(left.documents());
      embedded.addAll(right.documents());
      return new EmbeddingBatchResult(List.copyOf(embedded),
          left.skippedCount() + right.skippedCount());
    }

    List<RfcEmbeddedDocument> embedded = new ArrayList<>(documents.size());
    for (int index = 0; index < documents.size(); index++) {
      embedded.add(new RfcEmbeddedDocument(documents.get(index), embeddings.get(index)));
    }
    return new EmbeddingBatchResult(List.copyOf(embedded), 0);
  }

  private static long elapsedMillis(long startedAt) {
    return (System.nanoTime() - startedAt) / 1_000_000;
  }

  private record EmbeddingBatchResult(List<RfcEmbeddedDocument> documents, int skippedCount) {
  }
}
