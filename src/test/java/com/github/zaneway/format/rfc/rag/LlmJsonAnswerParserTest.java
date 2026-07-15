package com.github.zaneway.format.rfc.rag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

/** LLM 最终回答必须遵循 RFC 问答 JSON 契约。 */
class LlmJsonAnswerParserTest {

  private final LlmJsonAnswerParser parser = new LlmJsonAnswerParser(new ObjectMapper());

  @Test
  void parsesJsonWrappedInMarkdownFence() {
    RfcAnswer answer = parser.parse("""
        ```json
        {"result": true, "data": "RFC 5280 section 4.2.1.15 defines Freshest CRL."}
        ```
        """);

    assertThat(answer.result()).isTrue();
    assertThat(answer.data()).isEqualTo("RFC 5280 section 4.2.1.15 defines Freshest CRL.");
  }

  @Test
  void rejectsJsonWithoutRequiredBooleanResult() {
    assertThatThrownBy(() -> parser.parse("{\"data\":\"missing result\"}"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("result");
  }
}
