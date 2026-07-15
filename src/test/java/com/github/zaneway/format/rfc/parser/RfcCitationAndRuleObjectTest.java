package com.github.zaneway.format.rfc.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcExtractedObject;
import com.github.zaneway.format.rfc.parser.model.RfcRelation;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * RFC 引用变体和高精度规则对象的解析契约。
 *
 * <p>夹具 {@code rfc9004-citations-and-objects.txt} 只使用可由确定性规则识别的语法，确保本测试
 * 不会把自然语言推断误当作解析能力。所有偏移都必须可以回放到来源 {@link RfcUnit#content()}。</p>
 *
 * <p>覆盖范围：外部章节引用、普通 RFC 引用、内部章节引用、OID / 字段 / ABNF / 状态码 / 错误码对象，
 * 以及 unit → object 的 defines 边。</p>
 */
class RfcCitationAndRuleObjectTest {

  /**
   * 无状态解析器实例，测试间可安全复用。
   */
  private final RfcTextParser parser = new RfcTextParser();

  /**
   * 端到端校验夹具中的全部引用变体与规则对象: 1. 外部章节、普通 RFC、内部章节引用语义正确; 2. 引用偏移可切回单元 content 原文; 3.
   * 五种对象类型均被抽取，且每个对象恰好有一条 defines 边。
   */
  @Test
  void extractsAuditableCitationVariantsAndRuleObjects() throws Exception {
    RfcDocument document = parser.parse(fixturePath());

    // —— 引用变体 ——
    assertExternalSectionCitation(document, "3000", "4.2", "Section 4.2 of RFC 3000");
    assertExternalSectionCitation(document, "4000", "5", "RFC 4000, Section 5");
    assertDirectRfcCitation(document, "1000", "[RFC1000]");
    assertDirectRfcCitation(document, "2000", "RFC 2000");
    assertInternalCitation(document, "rfc-9004:2.1", "Section 2.1");
    assertInternalCitation(document, "rfc-9004:Appendix A", "Appendix A");
    assertCitationOffsetsRoundTripToSourceContent(document);

    // —— 规则对象：类型覆盖 ——
    assertThat(document.objects()).extracting(RfcExtractedObject::objectType)
        .contains("oid_definition", "field_definition", "abnf_rule", "error_code", "status_code");
    // —— 规则对象：关键名称应出现 ——
    assertThat(document.objects()).extracting(RfcExtractedObject::name)
        .contains("serialNumber", "critical", "token", "BAD_REQUEST", "OK");
    // —— 规则对象：OID 规范化值（空格弧 → 点分） ——
    assertThat(document.objects()).anySatisfy(object -> {
      assertThat(object.objectType()).isEqualTo("oid_definition");
      assertThat(object.name()).isEqualTo("id-example");
      assertThat(object.normalizedValue()).isEqualTo("1.2.3.4");
    });
    // —— 每个对象可回放，且恰好一条 defines ——
    assertObjectsAreAuditableAndDefinedExactlyOnce(document);
  }

  /**
   * 验证外部章节引用不会退化为普通 RFC 引用，且不依赖目标文档已入库。
   */
  private static void assertExternalSectionCitation(RfcDocument document, String targetRfc,
      String targetSection,
      String citationText) {
    assertThat(citationRelations(document)).anySatisfy(relation -> {
      assertThat(relation.relationType()).isEqualTo("cites_section");
      assertThat(relation.toKind()).isEqualTo("document");
      assertThat(relation.toIdentifier()).isEqualTo("rfc-" + targetRfc);
      assertThat(relation.attributes()).containsEntry("citationText", citationText)
          .containsEntry("citationScope", "external_section")
          .containsEntry("targetRfcNumber", targetRfc)
          .containsEntry("targetSectionId", targetSection)
          .containsEntry("resolutionStatus", "unresolved");
    });
  }

  /**
   * 验证方括号形式与空格形式的普通 RFC 引用均保留原始标记。
   */
  private static void assertDirectRfcCitation(RfcDocument document, String targetRfc,
      String citationText) {
    assertThat(citationRelations(document)).anySatisfy(relation -> {
      assertThat(relation.relationType()).isEqualTo("cites");
      assertThat(relation.toKind()).isEqualTo("document");
      assertThat(relation.toIdentifier()).isEqualTo("rfc-" + targetRfc);
      assertThat(relation.attributes()).containsEntry("citationText", citationText)
          .containsEntry("citationScope", "rfc")
          .containsEntry("targetRfcNumber", targetRfc)
          .containsEntry("resolutionStatus", "unresolved");
    });
  }

  /**
   * 验证编号章节与附录均可在当前文档章节树中解析为已解决的内部目标。
   */
  private static void assertInternalCitation(RfcDocument document, String targetSection,
      String citationText) {
    assertThat(citationRelations(document)).anySatisfy(relation -> {
      assertThat(relation.relationType()).isEqualTo("section_ref");
      assertThat(relation.toKind()).isEqualTo("section");
      assertThat(relation.toIdentifier()).isEqualTo(targetSection);
      assertThat(relation.attributes()).containsEntry("citationText", citationText)
          .containsEntry("citationScope", "internal_section")
          .containsEntry("resolutionStatus", "resolved");
    });
  }

  /**
   * 每个引用的 UTF-16 偏移必须精确切回单位内容中的原始引文，且行号落在来源单元区间内。
   */
  private static void assertCitationOffsetsRoundTripToSourceContent(RfcDocument document) {
    for (RfcRelation relation : citationRelations(document)) {
      RfcUnit source = findSourceUnit(document, relation.fromIdentifier());
      int startOffset = (Integer) relation.attributes().get("startOffset");
      int endOffset = (Integer) relation.attributes().get("endOffset");
      String citationText = (String) relation.attributes().get("citationText");

      assertThat(startOffset).isGreaterThanOrEqualTo(0);
      assertThat(endOffset).isGreaterThan(startOffset)
          .isLessThanOrEqualTo(source.content().length());
      // 偏移切回的子串必须与记录的 citationText 完全一致
      assertThat(source.content().substring(startOffset, endOffset)).isEqualTo(citationText);
      assertThat(relation.startLine()).isBetween(source.startLine(), source.endLine());
      assertThat(relation.endLine()).isBetween(relation.startLine(), source.endLine());
    }
  }

  /**
   * 每个对象都必须带有可回放位置，并由唯一的来源单元 defines。
   */
  private static void assertObjectsAreAuditableAndDefinedExactlyOnce(RfcDocument document) {
    List<RfcRelation> definesRelations = document.relations().stream()
        .filter(relation -> relation.relationType().equals("defines"))
        .toList();
    // 1) defines 边数量与对象一一对应
    assertThat(definesRelations).hasSize(document.objects().size());

    for (RfcExtractedObject object : document.objects()) {
      RfcUnit source = findSourceUnit(document, object.sourceUnitId());
      // 2) 行号与 UTF-16 偏移均可回放到来源单元
      assertThat(object.startLine()).isBetween(source.startLine(), source.endLine());
      assertThat(object.endLine()).isBetween(object.startLine(), source.endLine());
      assertThat(object.startOffset()).isGreaterThanOrEqualTo(0);
      assertThat(object.endOffset()).isGreaterThan(object.startOffset())
          .isLessThanOrEqualTo(source.content().length());
      // 3) 恰好一条 from=来源单元、to=该对象 的 defines 边
      assertThat(definesRelations).filteredOn(
              relation -> relation.toIdentifier().equals(object.id()))
          .singleElement()
          .satisfies(relation -> {
            assertThat(relation.fromKind()).isEqualTo("unit");
            assertThat(relation.fromIdentifier()).isEqualTo(object.sourceUnitId());
            assertThat(relation.toKind()).isEqualTo("object");
          });
    }
  }

  /**
   * 过滤出引用类关系（cites / cites_section / section_ref），排除 defines。
   */
  private static List<RfcRelation> citationRelations(RfcDocument document) {
    return document.relations().stream()
        .filter(relation -> relation.relationType().equals("cites")
            || relation.relationType().equals("cites_section")
            || relation.relationType().equals("section_ref"))
        .toList();
  }

  /**
   * 根据关系或对象中的来源 ID 查找 canonical 单元，避免依赖 units 的输出顺序。
   */
  private static RfcUnit findSourceUnit(RfcDocument document, String unitId) {
    return document.units().stream()
        .filter(unit -> unit.id().equals(unitId))
        .findFirst()
        .orElseThrow(() -> new AssertionError("Missing source unit: " + unitId));
  }

  /**
   * 加载引用变体与规则对象夹具。
   */
  private Path fixturePath() throws URISyntaxException {
    return Path.of(getClass().getResource("/rfc/rfc9004-citations-and-objects.txt").toURI());
  }
}
