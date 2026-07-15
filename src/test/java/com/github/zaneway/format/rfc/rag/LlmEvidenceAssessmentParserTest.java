package com.github.zaneway.format.rfc.rag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

/** 证据判断只负责返回是否充分及缺失信息，查询规划由独立阶段承担。 */
class LlmEvidenceAssessmentParserTest {

  private final LlmEvidenceAssessmentParser parser = new LlmEvidenceAssessmentParser(new ObjectMapper());

  @Test
  void parsesInsufficientEvidenceAssessment() {
    RfcEvidenceAssessment assessment = parser.parse("""
        {"result":false,"data":"Freshest CRL processing details are missing"}
        """);

    assertThat(assessment.result()).isFalse();
    assertThat(assessment.missingInformation()).contains("Freshest CRL processing");
  }

  @Test
  void rejectsAssessmentWithoutMissingInformation() {
    assertThatThrownBy(() -> parser.parse("""
        {"result":false}
        """))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("data");
  }
}
