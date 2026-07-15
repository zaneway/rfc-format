package com.github.zaneway.format.rfc.rag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

/** 查询规划必须提供重整问题、检索语句及目标证据维度。 */
class LlmQueryPlanParserTest {

  private final LlmQueryPlanParser parser = new LlmQueryPlanParser(new ObjectMapper());

  @Test
  void parsesStructuredInitialQueryPlan() {
    RfcQueryPlan plan = parser.parse("""
        {"question":"Which RFC requirements govern Freshest CRL processing?",
        "query":"Freshest CRL processing requirements RFC 5280",
        "requiredInformation":"extension semantics and processing requirements"}
        """);

    assertThat(plan.question()).contains("Freshest CRL");
    assertThat(plan.query()).contains("RFC 5280");
    assertThat(plan.requiredInformation()).contains("processing");
  }

  @Test
  void rejectsPlanWithoutRetrievalQuery() {
    assertThatThrownBy(() -> parser.parse("""
        {"question":"What is Freshest CRL?","requiredInformation":"definition"}
        """))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("query");
  }
}
