package com.github.zaneway.format.rfc.llm;

/** 访问 LLM 并取得 RFC 结构化 JSON 的端口。 */
@FunctionalInterface
public interface RfcStructuredLlmClient {

  /**
   * @param request 受调用方控制的 RFC 原文及来源信息
   * @return 模型原始响应；必须由 {@link StructuredRfcJsonParser} 校验
   */
  String parse(RfcStructuredLlmRequest request);
}
