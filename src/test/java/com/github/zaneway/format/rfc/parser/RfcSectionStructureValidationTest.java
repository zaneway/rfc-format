package com.github.zaneway.format.rfc.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcSection;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/** RFC TXT 章节边界清洗与结构不变量的回归测试。 */
class RfcSectionStructureValidationTest {

  private final RfcTextParser parser = new RfcTextParser();

  @Test
  void skipsAllPagesOfTableOfContentsBeforeRecognizingBodySections() throws Exception {
    RfcDocument document = parser.parse(fixturePath("rfc9100-multipage-toc.txt"));

    assertThat(document.sections()).extracting(RfcSection::id)
        .containsExactly("1", "4.2.1.15", "2")
        .doesNotHaveDuplicates();
  }

  @Test
  void doesNotRecognizeIndentedNumberedListItemAsSectionHeading() throws Exception {
    RfcDocument document = parser.parse(fixturePath("rfc9101-indented-numbered-list.txt"));

    assertThat(document.sections()).extracting(RfcSection::id)
        .containsExactly("1", "2");
  }

  @Test
  void rejectsRepeatedBodySectionWithBothPhysicalLineNumbers() {
    assertThatThrownBy(() -> parser.parse(fixturePath("rfc9102-duplicate-body-section.txt")))
        .isInstanceOf(IOException.class)
        .hasMessageContaining("Duplicate section id '2'")
        .hasMessageContaining("lines 15 and 19");
  }

  private Path fixturePath(String fileName) throws URISyntaxException {
    return Path.of(getClass().getResource("/rfc/" + fileName).toURI());
  }
}
