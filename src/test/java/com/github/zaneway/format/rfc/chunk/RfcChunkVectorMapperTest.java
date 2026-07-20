package com.github.zaneway.format.rfc.chunk;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import java.util.List;
import org.junit.jupiter.api.Test;

class RfcChunkVectorMapperTest {

  @Test
  void mapsSourceFieldsAndBoundsEveryEmbeddingDocument() {
    String text = "requirement line\n\n".repeat(80);
    RfcChunk chunk = new RfcChunk(8446, "4.1", "Handshake Protocol",
        "4 Handshake > 4.1 Handshake Protocol", 2, text, List.of(2119, 5246),
        "rfc8446.txt", 100, 259);

    List<RfcVectorDocument> documents = new RfcChunkVectorMapper(512)
        .toVectorDocuments(chunk);

    assertThat(documents).hasSizeGreaterThan(1);
    assertThat(documents).allSatisfy(document -> {
      assertThat(document.text()).hasSizeLessThanOrEqualTo(512);
      assertThat(document.metadata())
          .containsEntry("rfc_number", "8446")
          .containsEntry("section_id", "4.1")
          .containsEntry("source_file", "rfc8446.txt")
          .containsEntry("schema_version", "rfc-chunk-v1")
          .containsEntry("unit_type", "rfc_chunk");
      assertThat(document.id()).startsWith("rfc-8446:rfc8446.txt:100-259:segment-");
    });
    assertThat(documents.getFirst().metadata()).containsEntry("segment_index", 0);
    assertThat(documents.getLast().metadata())
        .containsEntry("segment_count", documents.size());
  }
}
