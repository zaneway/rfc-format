package com.github.zaneway.format.rfc.llm;

/** Qdrant 投影端口。 */
@FunctionalInterface
public interface RfcStructuredVectorProjector {

  /**
   * @param storedDocument MySQL 已提交的文档身份
   * @param document 用于生成向量文档的结构化内容
   * @return Qdrant 失败时返回 SKIPPED，禁止向上回滚 MySQL
   */
  LlmProjectionResult project(StoredLlmRfcDocument storedDocument,
      StructuredRfcDocument document);
}
