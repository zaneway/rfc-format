package com.github.zaneway.format.rfc.llm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class LlmRfcIngestionServiceTest {

  @Test
  void projectsOnlyAfterMysqlStorageCompletesAndSkipsQdrantFailure() {
    List<String> events = new ArrayList<>();
    LlmRfcIngestionService service = new LlmRfcIngestionService(
        request -> { events.add("llm"); return response(); },
        new StructuredRfcJsonParser(new ObjectMapper()),
        (document, sourceText, rawResponse) -> { events.add("store"); return new StoredLlmRfcDocument(7L); },
        (stored, document) -> { events.add("project"); return LlmProjectionResult.skipped("Qdrant unavailable"); });

    LlmRfcIngestionResult result = service.ingest("rfc9999.txt", "RFC text");

    assertThat(events).containsExactly("llm", "store", "project");
    assertThat(result.documentId()).isEqualTo(7L);
    assertThat(result.projection().status()).isEqualTo(LlmProjectionStatus.SKIPPED);
  }

  @Test
  void doesNotProjectWhenMysqlStorageFails() {
    List<String> events = new ArrayList<>();
    LlmRfcIngestionService service = new LlmRfcIngestionService(
        request -> { events.add("llm"); return response(); },
        new StructuredRfcJsonParser(new ObjectMapper()),
        (document, sourceText, rawResponse) -> { events.add("store"); throw new IllegalStateException("MySQL unavailable"); },
        (stored, document) -> { events.add("project"); return LlmProjectionResult.succeeded(1); });

    assertThatThrownBy(() -> service.ingest("rfc9999.txt", "RFC text"))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("MySQL unavailable");
    assertThat(events).containsExactly("llm", "store");
  }

  private static String response() {
    return """
        {"document":{"title":"Example RFC","source_type":"txt","source_file":"rfc9999.txt","language":"en","standard_id":"RFC9999","doc_kind":"rfc","published":"","status":"","authors":[],"relations":{"replaces":[],"replaced_by":[],"updates":[],"updated_by":[],"obsoletes":[],"obsoleted_by":[]},"extensions":{"rfc_number":"9999","category":"","stream":""}},"units":[{"unit_id":"unit-001","clause_id":null,"heading_path":[],"content_type":"text","content":"Example content.","source_location":{"start":null,"end":null},"extensions":{}}]}
        """;
  }
}
