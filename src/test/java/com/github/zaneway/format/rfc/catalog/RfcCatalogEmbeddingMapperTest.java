package com.github.zaneway.format.rfc.catalog;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RfcCatalogEmbeddingMapperTest {

  private final RfcCatalogEmbeddingMapper mapper = new RfcCatalogEmbeddingMapper();

  @Test
  void buildsStableIdEmbeddingTextAndFilterableMetadata() {
    RfcCatalogSummary summary = new RfcCatalogSummary(
        "5280",
        "Internet X.509 Public Key Infrastructure Certificate and Certificate Revocation List (CRL) Profile",
        "Standards Track",
        "May 2008",
        "3280, 4325, 4630",
        "",
        "This memo profiles the X.509 v3 certificate.",
        "title: unused summary field",
        "rfc5280.txt",
        "abstract",
        "");

    RfcVectorDocument document = mapper.toVectorDocument(summary);

    assertThat(document.id()).isEqualTo("rfc-5280:catalog:summary");
    assertThat(document.text()).isEqualTo("""
        RFC 5280: Internet X.509 Public Key Infrastructure Certificate and Certificate Revocation List (CRL) Profile
        Category: Standards Track
        Published: May 2008
        Obsoletes: 3280, 4325, 4630
        Updates:\s

        This memo profiles the X.509 v3 certificate.""");
    assertThat(document.metadata())
        .containsEntry("rfc_number", "5280")
        .containsEntry("document_id", "rfc-5280")
        .containsEntry("unit_type", "catalog_summary")
        .containsEntry("schema_version", "rfc-catalog-v1")
        .containsEntry("title", summary.title())
        .containsEntry("category", "Standards Track")
        .containsEntry("publication_date", "May 2008")
        .containsEntry("source_file", "rfc5280.txt")
        .containsEntry("extract_method", "abstract")
        .containsEntry("obsoletes", List.of("3280", "4325", "4630"))
        .containsEntry("updates", List.of())
        .doesNotContainKey("summary")
        .doesNotContainKey("abstract");
  }

  @Test
  void normalizesKnownCategoryAliasesAndKeepsDirtyValueAsRaw() {
    RfcCatalogSummary dirty = new RfcCatalogSummary(
        "2550", "Joke", "Stinkards Track", "April 1999", "", "",
        "Abstract text.", "", "rfc2550.txt", "abstract", "");

    Map<String, Object> metadata = mapper.toVectorDocument(dirty).metadata();

    assertThat(metadata).containsEntry("category", "")
        .containsEntry("category_raw", "Stinkards Track");
  }

  @Test
  void mapsStandardTrackAliasToStandardsTrack() {
    RfcCatalogSummary alias = new RfcCatalogSummary(
        "1", "Host Software", "Standard Track", "April 1969", "", "",
        "Abstract.", "", "rfc1.txt", "abstract", "");

    assertThat(mapper.toVectorDocument(alias).metadata())
        .containsEntry("category", "Standards Track");
  }
}
