package com.github.zaneway.format.rfc.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcRelation;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * 单元级 RFC 引用抽取契约测试。
 *
 * <p>夹具 {@code rfc9001-cross-references.txt} 故意包含：未入库的目标 RFC、同一目标的重复出现、
 * 以及 Normative / Informative 分组。解析器应记录全部引用事实，不要求目标已导入，也不合并 相同目标的多次出现（用 occurrence 区分）。</p>
 */
class RfcCrossReferenceParserTest {

  /**
   * 无状态解析器实例，测试间可安全复用。
   */
  private final RfcTextParser parser = new RfcTextParser();

  /**
   * 验证正文单元中的每条引用都携带: - 来自参考文献分组的 citationType（normative / informative / unspecified）; - 原始
   * citationText 与 1-based occurrence; - 指向原 RFC TXT 物理行的 startLine / endLine; 同时覆盖子章节与 Normative
   * References 分组内的引用。
   */
  @Test
  void extractsEveryBodyCitationWithReferenceListClassificationAndSourceLocation()
      throws Exception {
    RfcDocument document = parser.parse(fixturePath());

    // 选取第 1 节中含 [RFC1000] 的正文单元作为主断言对象
    RfcUnit bodyUnit = document.units().stream()
        .filter(unit -> unit.parentSectionId().equals("1"))
        .filter(unit -> unit.content().contains("[RFC1000]"))
        .findFirst()
        .orElseThrow();
    List<RfcRelation> bodyRelations = document.relations().stream()
        .filter(relation -> relation.fromIdentifier().equals(bodyUnit.id()))
        .toList();

    // 本夹具只覆盖普通 cites（unit→document），不含 section_ref / defines
    assertThat(document.relations())
        .allMatch(relation -> relation.relationType().equals("cites"))
        .allMatch(relation -> relation.fromKind().equals("unit"))
        .allMatch(relation -> relation.toKind().equals("document"));

    // 按出现顺序逐条核对分类、原文与物理行号；同一目标 RFC1000 出现两次时 occurrence 递增
    assertThat(bodyRelations).hasSize(6).satisfiesExactly(
        relation -> assertCitation(relation, bodyUnit, "rfc-1000", "normative", "[RFC1000]", 1, 14),
        relation -> assertCitation(relation, bodyUnit, "rfc-2000", "informative", "[RFC2000]", 1,
            15),
        relation -> assertCitation(relation, bodyUnit, "rfc-3000", "unspecified", "[RFC3000]", 1,
            15),
        relation -> assertCitation(relation, bodyUnit, "rfc-1000", "normative", "[RFC1000]", 2, 16),
        relation -> assertCitation(relation, bodyUnit, "rfc-4000", "unspecified", "[RFC4000]", 1,
            16),
        relation -> assertCitation(relation, bodyUnit, "rfc-5000", "normative", "[RFC5000]", 1,
            16));
    assertThat(bodyRelations).extracting(RfcRelation::id).doesNotHaveDuplicates();

    // 第 2 节正文同样可产出对 RFC1000 的引用
    assertThat(document.relations()).anySatisfy(relation -> {
      assertThat(relation.fromIdentifier()).startsWith("rfc-9001:2:");
      assertThat(relation.toIdentifier()).isEqualTo("rfc-1000");
      assertThat(relation.sourceSectionId()).isEqualTo("2");
      assertThat(relation.attributes()).containsEntry("citationType", "normative")
          .containsEntry("citationText", "[RFC1000]")
          .containsEntry("occurrence", 1);
    });

    // 参考文献分组标题被识别为独立章节，分组内条目也可产出 cites
    assertThat(document.sections()).extracting(section -> section.id())
        .contains("Normative References");
    assertThat(document.relations()).anySatisfy(relation -> {
      assertThat(relation.fromIdentifier()).startsWith("rfc-9001:Normative References:");
      assertThat(relation.toIdentifier()).isEqualTo("rfc-5000");
      assertThat(relation.sourceSectionId()).isEqualTo("Normative References");
      assertThat(relation.attributes()).containsEntry("citationType", "normative")
          .containsEntry("citationText", "[RFC5000]")
          .containsEntry("occurrence", 1);
    });
  }

  /**
   * 断言单条 cites 关系的来源、目标、分类、原文、出现次序与物理行号。
   *
   * @param relation     待校验关系
   * @param source       期望的来源单元
   * @param target       目标文档 ID，例如 "rfc-1000"
   * @param citationType normative / informative / unspecified
   * @param citationText 原始匹配文本
   * @param occurrence   同一来源与目标的 1-based 出现次序
   * @param sourceLine   期望的物理行号
   */
  private static void assertCitation(RfcRelation relation, RfcUnit source, String target,
      String citationType,
      String citationText, int occurrence, int sourceLine) {
    assertThat(relation.fromIdentifier()).isEqualTo(source.id());
    assertThat(relation.toIdentifier()).isEqualTo(target);
    assertThat(relation.sourceSectionId()).isEqualTo(source.parentSectionId());
    assertThat(relation.startLine()).isEqualTo(sourceLine);
    assertThat(relation.endLine()).isEqualTo(sourceLine);
    assertThat(relation.attributes()).containsEntry("citationType", citationType)
        .containsEntry("citationText", citationText)
        .containsEntry("occurrence", occurrence);
  }

  /**
   * 加载跨引用夹具。
   */
  private Path fixturePath() throws URISyntaxException {
    return Path.of(getClass().getResource("/rfc/rfc9001-cross-references.txt").toURI());
  }
}
