package com.github.zaneway.format.rfc.chunk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.zaneway.format.rfc.controller.RfcChunkIngestionController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class RfcChunkIngestionControllerTest {

  @TempDir
  Path tempDir;

  @Test
  void rejectsUnreadableInputBeforeCallingTheService() {
    RfcChunkIngestionController controller = new RfcChunkIngestionController(unusedService());

    assertThatThrownBy(() -> controller.ingest(tempDir.resolve("missing.jsonl").toString()))
        .isInstanceOf(ResponseStatusException.class)
        .extracting(exception -> ((ResponseStatusException) exception).getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void normalizesThePathAndReturnsIngestionStatistics() throws Exception {
    Path input = tempDir.resolve("all_chunks.jsonl");
    Files.writeString(input, "{}\n");
    AtomicReference<Path> receivedPath = new AtomicReference<>();
    RfcChunkIngestionService service = new RfcChunkIngestionService(null, null, null) {
      @Override
      public RfcChunkIngestionResult ingest(Path jsonlPath) {
        receivedPath.set(jsonlPath);
        return new RfcChunkIngestionResult(1, 1, 1, 0, 10);
      }
    };

    RfcChunkIngestionResult result = new RfcChunkIngestionController(service)
        .ingest(input.toString());

    assertThat(result.readChunkCount()).isEqualTo(1);
    assertThat(receivedPath.get()).isEqualTo(input.toAbsolutePath().normalize());
  }

  private static RfcChunkIngestionService unusedService() {
    return new RfcChunkIngestionService(null, null, null) {
      @Override
      public RfcChunkIngestionResult ingest(Path jsonlPath) throws IOException {
        throw new AssertionError("service must not be called");
      }
    };
  }
}
