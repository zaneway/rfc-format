package com.github.zaneway.format.rfc.parser.model;

import java.util.Map;

/**
 * 从 RFC 正文、References 章节或结构定义中提取的可追溯关系边。
 *
 * <p>在流水线中的位置：引用抽取（{@code extractRelations}）与规则对象抽取（{@code extractRuleObjects}
 * 产出的 {@code defines} 边）合并写入 {@link RfcDocument#relations()}。 行号与 {@code attributes}
 * 中的偏移共同支持无需重扫正文即可审计。</p>
 *
 * <p>关系语义:</p>
 * <ul>
 *   <li>{@code cites} — 来源单元对其它 RFC 的普通引用；</li>
 *   <li>{@code cites_section} — 来源单元对其它 RFC 指定章节的外部引用；</li>
 *   <li>{@code section_ref} — 来源单元对本 RFC 内部章节的引用；</li>
 *   <li>{@code defines} — 来源单元到规则对象（{@link RfcExtractedObject}）的定义边；</li>
 *   <li>{@code fromKind} / {@code toKind} 区分主体类型（{@code unit} / {@code document} / {@code section} / {@code object}）。</li>
 * </ul>
 */
public record RfcRelation(
    /** 关系唯一 ID。 */
    String id,
    /** 关系类型,例如 "cites" / "cites_section" / "section_ref" / "defines"。 */
    String relationType,
    /** 起始实体种类 ("unit"、"document" 等)。 */
    String fromKind,
    /** 起始实体标识。 */
    String fromIdentifier,
    /** 目标实体种类 ("document"、"section"、"object" 等)。 */
    String toKind,
    /** 目标实体标识。 */
    String toIdentifier,
    /** 关系来源章节 ID (通常为产出该关系的单元所属章节)。 */
    String sourceSectionId,
    /** 起始行号 (1-based,基于原 RFC TXT 物理行)。 */
    int startLine,
    /** 结束行号 (1-based,基于原 RFC TXT 物理行)。 */
    int endLine,
    /**
     * 与关系实例绑定的不可变属性。
     *
     * <p>引用类关系 ({@code cites} / {@code cites_section} / {@code section_ref}) 稳定提供：
     * {@code citationType}（{@code normative} / {@code informative} / {@code unspecified}）、
     * {@code citationText}（原始匹配文本）、{@code occurrence}（同一来源与目标的 1-based 次序）、
     * {@code startOffset} / {@code endOffset}、{@code citationScope}、{@code resolutionStatus} 等。</p>
     *
     * <p>{@code defines} 关系则提供 {@code objectType}、{@code objectName} 以及对象在单元内的偏移。</p>
     */
    Map<String, Object> attributes) {

  /**
   * 防御性复制属性，确保关系作为值对象在解析和持久化边界均不可被外部修改。
   */
  public RfcRelation {
    attributes = Map.copyOf(attributes);
  }
}
