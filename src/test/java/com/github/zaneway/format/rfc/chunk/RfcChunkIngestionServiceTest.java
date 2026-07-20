package com.github.zaneway.format.rfc.chunk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.zaneway.format.rfc.vector.RfcVectorClient;
import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import tools.jackson.databind.ObjectMapper;

class RfcChunkIngestionServiceTest {

  @TempDir
  Path tempDir;

  @Test
  void replacesEachRfcOnceAndReturnsLogicalAndPhysicalCounts() throws Exception {
    Path input = writeInput();
    RecordingClient client = new RecordingClient(-1);
    RfcChunkIngestionService service = service(client);

    RfcChunkIngestionResult result = service.ingest(input);

    assertThat(result.readChunkCount()).isEqualTo(3);
    assertThat(result.writtenPointCount()).isEqualTo(3);
    assertThat(result.rfcCount()).isEqualTo(2);
    assertThat(client.rfcs).containsExactly("1", "2");
    assertThat(client.documentCounts).containsExactly(2, 1);
  }

  @Test
  void addsRfcContextWhenQdrantReplacementFails() throws Exception {
    RfcChunkIngestionService service = service(new RecordingClient(2));

    assertThatThrownBy(() -> service.ingest(writeInput()))
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("RFC 2")
        .hasCauseInstanceOf(IllegalArgumentException.class);
  }

  private RfcChunkIngestionService service(RfcVectorClient client) {
    return new RfcChunkIngestionService(
        new RfcChunkJsonlReader(new ObjectMapper()), new RfcChunkVectorMapper(512), client);
  }

  private Path writeInput() throws Exception {
    Path input = tempDir.resolve("all_chunks.jsonl");
    Files.writeString(input, line(1, 1, 2, "one") + "\n"
        + line(1, 3, 4, "two") + "\n"
        + line(2, 1, 2, "three") + "\n");
    return input;
  }

  private static String line(int rfc, int start, int end, String text) {
    return """
        {"rfc_number":%d,"section_id":"1","section_title":"Title","section_path":"1 Title","depth":1,"text":"%s","references":[],"source_file":"rfc%d.txt","start_line":%d,"end_line":%d}"""
        .formatted(rfc, text, rfc, start, end);
  }

  private static final class RecordingClient implements RfcVectorClient {
    private final int failingRfc;
    private final List<String> rfcs = new ArrayList<>();
    private final List<Integer> documentCounts = new ArrayList<>();

    private RecordingClient(int failingRfc) {
      this.failingRfc = failingRfc;
    }

    @Override
    public void replace(String rfcNumber, List<RfcVectorDocument> documents) {
      if (Integer.parseInt(rfcNumber) == failingRfc) {
        throw new IllegalArgumentException("qdrant unavailable");
      }
      rfcs.add(rfcNumber);
      documentCounts.add(documents.size());
    }
  }
}
