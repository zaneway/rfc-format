package com.github.zaneway.format.rfc.llm;

/** MySQL 事实层存储端口。 */
@FunctionalInterface
public interface LlmRfcDocumentStorage {

  /**
   * 该方法返回时必须已完成 MySQL 事务提交；调用方据此决定是否允许 Qdrant 投影。
   *
   * @param document 已校验的结构化 JSON
   * @param sourceText 原始 RFC 文本，用于审计和内容哈希
   * @param rawResponse 原始模型 JSON，用于可回放审计
   * @return 已提交记录的主键
   */
  StoredLlmRfcDocument store(StructuredRfcDocument document, String sourceText,
      String rawResponse);
}
