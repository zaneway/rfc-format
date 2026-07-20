package com.github.zaneway.format.rfc.chunk;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RfcChunkSegmenterTest {

  @Test
  void splitsWithoutLosingCharactersAndKeepsLineLocations() {
    String text = "alpha\n".repeat(70) + "omega";
    RfcChunk chunk = chunk(text);

    List<RfcChunkSegment> segments = new RfcChunkSegmenter().split(chunk, 256);

    assertThat(segments).hasSizeGreaterThan(1);
    assertThat(segments).allSatisfy(segment -> assertThat(segment.text()).hasSizeLessThanOrEqualTo(256));
    assertThat(segments.stream().map(RfcChunkSegment::text).reduce("", String::concat))
        .isEqualTo(text);
    assertThat(segments.getFirst().startLine()).isEqualTo(10);
    assertThat(segments.getLast().endLine()).isEqualTo(80);
    assertThat(segments).allSatisfy(segment -> assertThat(segment.count()).isEqualTo(segments.size()));
  }

  private static RfcChunk chunk(String text) {
    return new RfcChunk(9999, "1", "Title", "1 Title", 1, text, List.of(2119),
        "rfc9999.txt", 10, 80);
  }
}
