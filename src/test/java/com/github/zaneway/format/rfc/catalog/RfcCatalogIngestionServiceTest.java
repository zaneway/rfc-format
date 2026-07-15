package com.github.zaneway.format.rfc.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RfcCatalogIngestionServiceTest {

  @TempDir
  Path tempDir;

  @Test
  void replacesCatalogCollectionWithMappedDocuments() throws Exception {
    Path jsonl = tempDir.resolve("rfc-summaries.jsonl");
    Files.writeString(jsonl, """
        {"rfc_number":"8446","title":"TLS 1.3","category":"Standards Track","publication_date":"August 2018","obsoletes":"5246","updates":"5705","abstract":"TLS 1.3 protocol.","summary":"s","source_file":"rfc8446.txt","extract_method":"abstract","warning":""}
        """);

    RecordingCatalogVectorClient client = new RecordingCatalogVectorClient();
    RfcCatalogIngestionService service = new RfcCatalogIngestionService(
        new RfcCatalogJsonlLoader(new ObjectMapper()),
        new RfcCatalogEmbeddingMapper(),
        client);

    RfcCatalogIngestionResult result = service.ingest(jsonl);

    assertThat(result.readCount()).isEqualTo(1);
    assertThat(result.writtenCount()).isEqualTo(1);
    assertThat(result.duplicateSkippedCount()).isEqualTo(0);
    assertThat(client.replaced).hasSize(1);
    assertThat(client.replaced.getFirst().id()).isEqualTo("rfc-8446:catalog:summary");
    assertThat(client.replaced.getFirst().metadata()).containsEntry("unit_type", "catalog_summary");
  }

  private static final class RecordingCatalogVectorClient implements RfcCatalogVectorClient {
    private final List<RfcVectorDocument> replaced = new ArrayList<>();

    @Override
    public void replaceAll(List<RfcVectorDocument> documents) {
      replaced.clear();
      replaced.addAll(documents);
    }
  }
}
