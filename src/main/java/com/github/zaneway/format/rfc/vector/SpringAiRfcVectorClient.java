package com.github.zaneway.format.rfc.vector;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Spring AI {@link VectorStore} 到 Qdrant 的适配器。
 *
 * <p>通过 metadata 中的 {@code rfc_number} 字段实现按 RFC 范围的删除-再-写入替换，
 * 避免全量清空集合影响其他 RFC 的向量数据。</p>
 */
@Component
public class SpringAiRfcVectorClient implements RfcVectorClient {

  private final VectorStore vectorStore;

  /**
   * @param vectorStore Spring AI 自动配置的向量存储实例（底层对接 Qdrant）
   */
  public SpringAiRfcVectorClient(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
  }

  /**
   * {@inheritDoc}
   *
   * <p>先按 {@code rfc_number} 过滤删除，再批量 add；两步均非事务性，
   * 删除成功但 add 失败时该 RFC 向量暂时为空，需通过重试恢复。</p>
   */
  @Override
    public void replace(String rfcNumber, List<RfcVectorDocument> documents) {
        // 步骤 1：仅删除本 RFC 的向量点，保留集合内其他文档，避免全量清空
        vectorStore.delete(new FilterExpressionBuilder().eq("rfc_number", rfcNumber).build());
        // 步骤 2：写入新快照。两步非事务：若此处失败，该 RFC 在 Qdrant 中暂时为空，依赖状态表 FAILED + 重试恢复
        vectorStore.add(documents.stream()
                .map(document -> new Document(document.id(), document.text(), document.metadata()))
                .toList());
    }
}
