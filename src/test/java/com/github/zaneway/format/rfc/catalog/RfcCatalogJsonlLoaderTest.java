package com.github.zaneway.format.rfc.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RfcCatalogJsonlLoaderTest {

  @TempDir
  Path tempDir;

  @Test
  void loadsSummariesAndKeepsLastDuplicateRfcNumber() throws Exception {
    Path jsonl = tempDir.resolve("rfc-summaries.jsonl");
    Files.writeString(jsonl, """
        {"rfc_number":"2119","title":"First","category":"Informational","publication_date":"March 1997","obsoletes":"","updates":"","abstract":"A","summary":"s","source_file":"rfc2119.txt","extract_method":"abstract","warning":""}
        {"rfc_number":"5280","title":"X509","category":"Standards Track","publication_date":"May 2008","obsoletes":"3280","updates":"","abstract":"B","summary":"s","source_file":"rfc5280.txt","extract_method":"abstract","warning":""}
        {"rfc_number":"2119","title":"Key words","category":"Best Current Practice","publication_date":"March 1997","obsoletes":"","updates":"","abstract":"C","summary":"s","source_file":"rfc2119.txt","extract_method":"abstract","warning":""}
        """);

    RfcCatalogJsonlLoader loader = new RfcCatalogJsonlLoader(new ObjectMapper());
    List<RfcCatalogSummary> summaries = loader.load(jsonl);

    assertThat(summaries).hasSize(2);
    assertThat(summaries.get(0).rfcNumber()).isEqualTo("2119");
    assertThat(summaries.get(0).title()).isEqualTo("Key words");
    assertThat(summaries.get(0).abstractText()).isEqualTo("C");
    assertThat(summaries.get(1).rfcNumber()).isEqualTo("5280");
  }
}
