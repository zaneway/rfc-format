package com.github.zaneway.format.rfc.vector;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcSource;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import com.github.zaneway.format.rfc.storage.StoredRfcDocument;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class QdrantRfcVectorProjectorTest {

  @Test
  void projectsOnlyCanonicalUnitsAndMarksProjectionSuccessful() {
    RecordingVectorClient client = new RecordingVectorClient(false);
    RecordingProjectionState state = new RecordingProjectionState();
    QdrantRfcVectorProjector projector = new QdrantRfcVectorProjector(client, state);

    ProjectionResult result = projector.project(storedDocument(),
        documentWithCanonicalAndOverlayUnits());

    assertThat(result.status()).isEqualTo(ProjectionStatus.SUCCEEDED);
    assertThat(client.documents).extracting(RfcVectorDocument::id)
        .containsExactly("rfc-9999:1:section_content:1", "rfc-9999:1:abnf_definition:1");
    assertThat(client.documents).extracting(RfcVectorDocument::text)
        .containsExactly("section embedding text", "abnf embedding text");
    assertThat(state.events).containsExactly("started", "succeeded");
  }

  @Test
  void recordsFailedProjectionWithoutThrowingAwayStoredDocument() {
    RecordingVectorClient client = new RecordingVectorClient(true);
    RecordingProjectionState state = new RecordingProjectionState();
    QdrantRfcVectorProjector projector = new QdrantRfcVectorProjector(client, state);

    ProjectionResult result = projector.project(storedDocument(),
        documentWithCanonicalAndOverlayUnits());

    assertThat(result.status()).isEqualTo(ProjectionStatus.FAILED);
    assertThat(result.errorMessage()).contains("Qdrant unavailable");
    assertThat(state.events).containsExactly("started", "failed:Qdrant unavailable");
  }

  private static StoredRfcDocument storedDocument() {
    return new StoredRfcDocument(42L, "rfc-9999", "9999", "a".repeat(64));
  }

  private static RfcDocument documentWithCanonicalAndOverlayUnits() {
    RfcUnit section = new RfcUnit("rfc-9999:1:section_content:1", "section_content", "1",
        "section content",
        "section embedding text", 10, 11, Map.of("normativeKeywords", List.of("MUST")));
    RfcUnit abnf = new RfcUnit("rfc-9999:1:abnf_definition:1", "abnf_definition", "1",
        "token = 1*ALPHA",
        "abnf embedding text", 12, 12, Map.of());
    RfcUnit overlay = new RfcUnit("rfc-9999:1:normative_rule:1", "normative_rule", "1", "MUST test",
        "overlay embedding text", 10, 10, Map.of());
    return new RfcDocument("rfc-9999", "9999", "Test RFC", "Informational", "July 2026",
        new RfcSource("rfc9999.txt", "a".repeat(64)), List.of(), List.of(section, abnf, overlay),
        List.of(), List.of());
  }

  private static final class RecordingVectorClient implements RfcVectorClient {

    private final boolean fail;
    private final List<RfcVectorDocument> documents = new ArrayList<>();

    private RecordingVectorClient(boolean fail) {
      this.fail = fail;
    }

    @Override
    public void replace(String rfcNumber, List<RfcVectorDocument> documents) {
      if (fail) {
        throw new IllegalStateException("Qdrant unavailable");
      }
      this.documents.addAll(documents);
    }
  }

  private static final class RecordingProjectionState implements RfcVectorProjectionStateStore {

    private final List<String> events = new ArrayList<>();

    @Override
    public void markStarted(StoredRfcDocument document) {
      events.add("started");
    }

    @Override
    public void markSucceeded(StoredRfcDocument document) {
      events.add("succeeded");
    }

    @Override
    public void markFailed(StoredRfcDocument document, String errorMessage) {
      events.add("failed:" + errorMessage);
    }
  }
}
