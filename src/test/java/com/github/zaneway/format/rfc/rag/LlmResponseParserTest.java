package com.github.zaneway.format.rfc.rag;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/** 模型响应中的推理块不得进入 RFC 问答最终答案。 */
class LlmResponseParserTest {

  @Test
  void removesLeadingThinkBlockAndKeepsFinalAnswer() {
    LlmResponseParser.ParsedResponse response = LlmResponseParser.parse("""
        <think>
        Internal reasoning that must not reach the caller.
        </think>

        RFC 5280 section 4.2.1.15 defines the Freshest CRL extension.
        """);

    assertThat(response.thinking()).contains("Internal reasoning");
    assertThat(response.answer())
        .isEqualTo("RFC 5280 section 4.2.1.15 defines the Freshest CRL extension.");
  }

  @Test
  void preservesResponseWithoutThinkBlock() {
    LlmResponseParser.ParsedResponse response = LlmResponseParser.parse("RFC 5280 is the source.");

    assertThat(response.thinking()).isEmpty();
    assertThat(response.answer()).isEqualTo("RFC 5280 is the source.");
  }

  @Test
  void withholdsUnclosedLeadingThinkBlock() {
    LlmResponseParser.ParsedResponse response = LlmResponseParser.parse("""
        <think>unfinished internal reasoning
        RFC 5280 section 4.2.1.15 defines the extension.
        """);

    assertThat(response.thinking()).contains("unfinished internal reasoning");
    assertThat(response.answer()).isEmpty();
  }
}
