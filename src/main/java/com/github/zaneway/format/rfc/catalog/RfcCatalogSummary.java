package com.github.zaneway.format.rfc.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RFC 摘要目录条目，对应 {@code rfc-summaries.jsonl} 单行。
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RfcCatalogSummary(
    @JsonProperty("rfc_number") String rfcNumber,
    String title,
    String category,
    @JsonProperty("publication_date") String publicationDate,
    String obsoletes,
    String updates,
    @JsonProperty("abstract") String abstractText,
    String summary,
    @JsonProperty("source_file") String sourceFile,
    @JsonProperty("extract_method") String extractMethod,
    String warning) {
}
