package com.github.zaneway.format.rfc.parser.model;

import java.util.Map;

/**
 * 面向检索的最小知识单元；content 保持清洗后的 RFC 原文，不由模型改写。
 *
 * 使用约束:
 * - {@code content} 必须来自 RFC 原文,不得被 LLM 二次改写,以保证可追溯;
 * - {@code embeddingText} 在 content 基础上拼接了来源标记,用于写入向量库;
 * - {@code semantic} 携带与该单元相关的弱结构化元数据 (如 language / entityName / normativeKeywords)。
 */
public record RfcUnit(
        /** 单元唯一 ID,格式 "rfc-{number}:{sectionId}:{unitType}:{suffix}"。 */
        String id,
        /** 单元类型,例如 "asn1_definition" / "abnf_definition" / "section_content" / "normative_rule"。 */
        String unitType,
        /** 所属章节 ID。 */
        String parentSectionId,
        /** 清洗后的 RFC 原文片段 (段落级),保持原始换行。 */
        String content,
        /** 写入向量库时使用的文本,包含来源标记。 */
        String embeddingText,
        /** 起始行号 (1-based)。 */
        int startLine,
        /** 结束行号 (1-based)。 */
        int endLine,
        /** 语义元数据,键值含义取决于 unitType。 */
        Map<String, Object> semantic) {
}
