package com.github.zaneway.format.rfc.llm;

/** RFC LLM 导入的最终结果。 */
public record LlmRfcIngestionResult(long documentId, LlmProjectionResult projection) {
}
