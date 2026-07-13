package com.github.zaneway.format.rfc.parser.model;

/**
 * 从 RFC 头部、References 或结构定义中提取的可追溯关系。
 *
 * 关系语义:
 * - 当前主要产出 "references" 关系 (来自 References 章节对其它 RFC 的引用);
 * - 通过 fromKind / toKind 区分主体类型 ("document" / "unit" 等),
 *   后续可扩展为 "defines"、"depends_on" 等关系。
 */
public record RfcRelation(
        /** 关系唯一 ID。 */
        String id,
        /** 关系类型,例如 "references"。 */
        String relationType,
        /** 起始实体种类 ("document"、"unit" 等)。 */
        String fromKind,
        /** 起始实体标识。 */
        String fromIdentifier,
        /** 目标实体种类。 */
        String toKind,
        /** 目标实体标识。 */
        String toIdentifier,
        /** 关系来源章节 ID (例如 "References")。 */
        String sourceSectionId,
        /** 起始行号 (1-based)。 */
        int startLine,
        /** 结束行号 (1-based)。 */
        int endLine) {
}
