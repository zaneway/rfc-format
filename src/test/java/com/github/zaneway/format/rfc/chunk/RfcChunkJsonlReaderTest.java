package com.github.zaneway.format.rfc.chunk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import tools.jackson.databind.ObjectMapper;

class RfcChunkJsonlReaderTest {

  @TempDir
  Path tempDir;

  @Test
  void streamsContiguousRfcGroupsAndReturnsStatistics() throws Exception {
    Path input = tempDir.resolve("chunks.jsonl");
    Files.writeString(input, validLine(1, 1, 3, "first") + "\n\n"
        + validLine(1, 4, 6, "second") + "\n"
        + validLine(2, 1, 2, "third") + "\n");
    List<Integer> rfcs = new ArrayList<>();
    List<Integer> groupSizes = new ArrayList<>();

    RfcChunkReadResult result = new RfcChunkJsonlReader(new ObjectMapper())
        .readGrouped(input, (rfc, chunks) -> {
          rfcs.add(rfc);
          groupSizes.add(chunks.size());
        });

    assertThat(result).isEqualTo(new RfcChunkReadResult(3, 2));
    assertThat(rfcs).containsExactly(1, 2);
    assertThat(groupSizes).containsExactly(2, 1);
  }

  @Test
  void reportsThePhysicalLineContainingInvalidJson() throws Exception {
    Path input = tempDir.resolve("invalid.jsonl");
    Files.writeString(input, validLine(1, 1, 2, "valid") + "\n{broken\n");

    assertThatThrownBy(() -> new RfcChunkJsonlReader(new ObjectMapper())
        .readGrouped(input, (rfc, chunks) -> { }))
        .isInstanceOf(IOException.class)
        .hasMessageContaining("line 2")
        .hasMessageContaining(input.toString());
  }

  @Test
  void rejectsAnRfcThatReappearsAfterAnotherGroup() throws Exception {
    Path input = tempDir.resolve("reopened.jsonl");
    Files.writeString(input, validLine(1, 1, 2, "one") + "\n"
        + validLine(2, 1, 2, "two") + "\n"
        + validLine(1, 3, 4, "one again") + "\n");

    assertThatThrownBy(() -> new RfcChunkJsonlReader(new ObjectMapper())
        .readGrouped(input, (rfc, chunks) -> { }))
        .isInstanceOf(IOException.class)
        .hasMessageContaining("not stored in one contiguous group");
  }

  private static String validLine(int rfc, int startLine, int endLine, String text) {
    return """
        {"rfc_number":%d,"section_id":"1","section_title":"Title","section_path":"1 Title","depth":1,"text":"%s","references":[],"source_file":"rfc%d.txt","start_line":%d,"end_line":%d}"""
        .formatted(rfc, text, rfc, startLine, endLine);
  }
}
