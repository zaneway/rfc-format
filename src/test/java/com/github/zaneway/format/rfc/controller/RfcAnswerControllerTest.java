package com.github.zaneway.format.rfc.controller;

import com.github.zaneway.format.rfc.rag.RfcAnswerRequest;
import com.github.zaneway.format.rfc.rag.RfcAnswer;
import com.github.zaneway.format.rfc.rag.RfcAnswerResponse;
import com.github.zaneway.format.rfc.rag.RfcAnswerSource;
import com.github.zaneway.format.rfc.rag.RfcQuestionAnswerService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** RFC 问答 HTTP 契约测试。 */
class RfcAnswerControllerTest {

  @Test
  void acceptsQuestionAndReturnsGroundedAnswerWithSources() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new RfcAnswerController(
        new RecordingAnswerService())).build();

    mockMvc.perform(post("/rfc/answers")
            .contentType("application/json")
            .content("{\"question\":\"What is Freshest CRL?\",\"topK\":3}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.answer.result").value(true))
        .andExpect(jsonPath("$.answer.data").value("grounded answer"))
        .andExpect(jsonPath("$.sources[0].unitId")
            .value("rfc-5280:4.2.1.15:section_content:01"));
  }

  private static final class RecordingAnswerService extends RfcQuestionAnswerService {

    private RecordingAnswerService() {
      super(null, null);
    }

    @Override
    public RfcAnswerResponse answer(RfcAnswerRequest request) {
      return new RfcAnswerResponse(new RfcAnswer(true, "grounded answer"), List.of(new RfcAnswerSource(
          "rfc-5280:4.2.1.15:section_content:01", "5280", "4.2.1.15", 2671, 2690)));
    }
  }
}
