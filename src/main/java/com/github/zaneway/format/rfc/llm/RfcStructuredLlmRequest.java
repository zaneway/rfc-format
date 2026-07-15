package com.github.zaneway.format.rfc.llm;

/** LLM RFC 结构化解析请求。 */
public record RfcStructuredLlmRequest(String sourceFile, String documentText) {
}
