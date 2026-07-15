package com.github.zaneway.format.rfc.ingestion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.zaneway.format.rfc.parser.RfcDocumentParser;
import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcSource;
import com.github.zaneway.format.rfc.storage.RfcDocumentStorage;
import com.github.zaneway.format.rfc.storage.StoredRfcDocument;
import com.github.zaneway.format.rfc.vector.ProjectionResult;
import com.github.zaneway.format.rfc.vector.ProjectionStatus;
import com.github.zaneway.format.rfc.vector.RfcVectorProjector;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class RfcIngestionServiceTest {

  @Test
  void storesBeforeProjecting() throws IOException {
    List<String> events = new ArrayList<>();
    RfcIngestionService service = new RfcIngestionService(parser(events), storage(events, false),
        projector(events), audit(events));

    RfcIngestionResult result = service.ingest(Path.of("rfc9999.txt"));

    assertThat(events).containsExactly("parse", "store", "project");
    assertThat(result.projection().status()).isEqualTo(ProjectionStatus.SUCCEEDED);
    assertThat(result.storedDocument().documentId()).isEqualTo(42L);
  }

  @Test
  void doesNotProjectWhenMysqlStorageFails() {
    List<String> events = new ArrayList<>();
    RfcIngestionService service = new RfcIngestionService(parser(events), storage(events, true),
        projector(events), audit(events));

    assertThatThrownBy(() -> service.ingest(Path.of("rfc9999.txt"))).isInstanceOf(
            IllegalStateException.class)
        .hasMessage("MySQL unavailable");

    assertThat(events).containsExactly("parse", "store", "audit");
  }

  private static RfcDocumentParser parser(List<String> events) {
    return path -> {
      events.add("parse");
      return new RfcDocument("rfc-9999", "9999", "Test RFC", "Informational", "July 2026",
          new RfcSource(path.getFileName().toString(), "a".repeat(64)), List.of(), List.of(),
          List.of(), List.of());
    };
  }

  private static RfcDocumentStorage storage(List<String> events, boolean fail) {
    return (document, inputPath) -> {
      events.add("store");
      if (fail) {
        throw new IllegalStateException("MySQL unavailable");
      }
      return new StoredRfcDocument(42L, document.documentId(), document.rfcNumber(),
          document.source().sha256());
    };
  }

  private static RfcVectorProjector projector(List<String> events) {
    return (storedDocument, document) -> {
      events.add("project");
      return ProjectionResult.succeeded(0);
    };
  }

  private static RfcIngestionAuditStore audit(List<String> events) {
    return (inputPath, inputSha256, failure) -> events.add("audit");
  }
}
