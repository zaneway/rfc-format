package com.github.zaneway.format.rfc.llm;

/** LLM 输出不符合 RFC 结构化 JSON 契约时抛出。 */
public final class StructuredRfcValidationException extends RuntimeException {

  /**
   * @param message 面向调用方的结构化输出校验失败原因
   * @param cause Jackson 或底层解析异常；可为空
   */
  public StructuredRfcValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message 面向调用方的结构化输出校验失败原因
   */
  public StructuredRfcValidationException(String message) {
    super(message);
  }
}
