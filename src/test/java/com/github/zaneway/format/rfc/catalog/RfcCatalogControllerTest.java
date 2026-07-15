package com.github.zaneway.format.rfc.catalog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.zaneway.format.rfc.controller.RfcCatalogController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class RfcCatalogControllerTest {

  @TempDir
  Path tempDir;

  @Test
  void rejectsMissingFile() {
    RfcCatalogController controller = new RfcCatalogController(unusedService());

    assertThatThrownBy(() -> controller.ingest(tempDir.resolve("missing.jsonl").toString()))
        .isInstanceOf(ResponseStatusException.class)
        .extracting(ex -> ((ResponseStatusException) ex).getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void ingestsExistingJsonlPath() throws Exception {
    Path jsonl = tempDir.resolve("rfc-summaries.jsonl");
    Files.writeString(jsonl, "{}\n");
    AtomicReference<Path> ingested = new AtomicReference<>();
    RfcCatalogIngestionService service = new RfcCatalogIngestionService(null, null, null) {
      @Override
      public RfcCatalogIngestionResult ingest(Path jsonlPath) {
        ingested.set(jsonlPath);
        return new RfcCatalogIngestionResult(1, 1, 0);
      }
    };
    RfcCatalogController controller = new RfcCatalogController(service);

    RfcCatalogIngestionResult result = controller.ingest(jsonl.toString());

    assertThat(result.writtenCount()).isEqualTo(1);
    assertThat(ingested.get()).isEqualTo(jsonl.toAbsolutePath().normalize());
  }

  private static RfcCatalogIngestionService unusedService() {
    return new RfcCatalogIngestionService(null, null, null) {
      @Override
      public RfcCatalogIngestionResult ingest(Path jsonlPath) throws IOException {
        throw new AssertionError("should not ingest");
      }
    };
  }
}
