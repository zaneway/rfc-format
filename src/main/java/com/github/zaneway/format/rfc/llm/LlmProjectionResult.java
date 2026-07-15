package com.github.zaneway.format.rfc.llm;

/** Qdrant 投影结果。 */
public record LlmProjectionResult(LlmProjectionStatus status, int vectorCount, String errorMessage) {

  /** @param vectorCount 成功写入的向量数量 */
  public static LlmProjectionResult succeeded(int vectorCount) {
    return new LlmProjectionResult(LlmProjectionStatus.SUCCEEDED, vectorCount, null);
  }

  /** @param errorMessage Qdrant 或投影状态持久化失败原因 */
  public static LlmProjectionResult skipped(String errorMessage) {
    return new LlmProjectionResult(LlmProjectionStatus.SKIPPED, 0, errorMessage);
  }
}
