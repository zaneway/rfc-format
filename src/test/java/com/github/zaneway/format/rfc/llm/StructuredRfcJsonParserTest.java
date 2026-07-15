package com.github.zaneway.format.rfc.llm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class StructuredRfcJsonParserTest {

  private final StructuredRfcJsonParser parser = new StructuredRfcJsonParser(new ObjectMapper());

  @Test
  void parsesPromptContractAndPreservesSourceLocations() {
    StructuredRfcDocument document = parser.parse(validResponse());

    assertThat(document.document().standardId()).isEqualTo("RFC9999");
    assertThat(document.units()).singleElement().satisfies(unit -> {
      assertThat(unit.unitId()).isEqualTo("unit-001");
      assertThat(unit.contentType()).isEqualTo("requirement");
      assertThat(unit.sourceLocation().start()).isEqualTo(10);
      assertThat(unit.sourceLocation().end()).isEqualTo(12);
    });
  }

  @Test
  void rejectsFieldsOutsideThePromptContract() {
    assertThatThrownBy(() -> parser.parse(validResponse().replace("\n  \"units\"", "\n  \"warnings\": [],\n  \"units\"")))
        .isInstanceOf(StructuredRfcValidationException.class)
        .hasMessageContaining("Unknown JSON field");
  }

  private static String validResponse() {
    return """
        {
          "document": {
            "title": "Example RFC",
            "source_type": "txt",
            "source_file": "rfc9999.txt",
            "language": "en",
            "standard_id": "RFC9999",
            "doc_kind": "rfc",
            "published": "July 2026",
            "status": "Informational",
            "authors": ["Example Author"],
            "relations": {
              "replaces": [],
              "replaced_by": [],
              "updates": [],
              "updated_by": [],
              "obsoletes": [],
              "obsoleted_by": []
            },
            "extensions": {
              "rfc_number": "9999",
              "category": "Informational",
              "stream": "IETF"
            }
          },
          "units": [
            {
              "unit_id": "unit-001",
              "clause_id": "1",
              "heading_path": [{"level": 1, "title": "Introduction"}],
              "content_type": "requirement",
              "content": "The client MUST validate input.",
              "source_location": {"start": 10, "end": 12},
              "extensions": {}
            }
          ]
        }
        """;
  }
}
