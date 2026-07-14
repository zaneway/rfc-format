package com.github.zaneway.format.rfc.parser.model;

import java.util.Map;

/**
 * 面向检索的最小知识单元，由解析器在「单元构建」阶段从章节段落切分产出。
 *
 * <p>{@code content} 保持清洗后的 RFC 原文片段，不由 LLM 改写；{@code embeddingText} 供向量库写入，
 * {@code semantic} 供过滤与展示。引用关系（{@link RfcRelation}）与规则对象（{@link RfcExtractedObject}） 均锚定到 canonical
 * 单元，而非 {@code normative_rule} 覆盖层。</p>
 *
 * <p>使用约束:</p>
 * <ul>
 *   <li>{@code content} 必须来自 RFC 原文，不得被 LLM 二次改写，以保证可追溯；</li>
 *   <li>{@code embeddingText} 在 content 基础上拼接 {@code [RFC N §section title]} 来源标记；</li>
 *   <li>{@code semantic} 携带弱结构化元数据（如 {@code language} / {@code entityName} / {@code normativeKeywords}）；</li>
 *   <li>{@code startLine}/{@code endLine} 为原 RFC TXT 的 1-based 物理行号；</li>
 *   <li>{@code normative_rule} 是同一段正文的检索覆盖层，引用 / 规则对象只从
 *       {@code section_content} / {@code asn1_definition} / {@code abnf_definition} 抽取，避免重复边。</li>
 * </ul>
 */
public record RfcUnit(
    /**
     * 单元唯一 ID，基础格式为 {@code rfc-{number}:{sectionId}:{unitType}:{suffix}}。
     * 同一 RFC 内基础 ID 首次出现时保持该格式；后续碰撞按解析顺序追加 {@code ~2}、{@code ~3} 等后缀。
     */
    String id,
    /** 单元类型,例如 "asn1_definition" / "abnf_definition" / "section_content" / "normative_rule"。 */
    String unitType,
    /** 所属章节 ID。 */
    String parentSectionId,
    /** 清洗后的 RFC 原文片段 (段落级),保持原始换行。 */
    String content,
    /** 写入向量库时使用的文本,包含来源标记。 */
    String embeddingText,
    /** 起始行号 (1-based,原 RFC TXT 物理行)。 */
    int startLine,
    /** 结束行号 (1-based,包含,原 RFC TXT 物理行)。 */
    int endLine,
    /** 语义元数据,键值含义取决于 unitType。 */
    Map<String, Object> semantic) {

}
