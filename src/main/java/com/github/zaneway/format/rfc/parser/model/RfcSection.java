package com.github.zaneway.format.rfc.parser.model;

import java.util.List;

/**
 * RFC 章节树中的一个确定性节点，行号均基于原始文件且从 1 开始计数。
 *
 * 设计要点:
 * - 章节通过 {@code parentId} 与 {@code path} 同时表达层级关系,既支持 O(1) 父节点查询
 *   也支持自顶向下的完整路径回溯;
 * - {@code sectionType} 区分 "body" / "appendix" / "references"。
 */
public record RfcSection(
        /** 章节 ID,例如 "1"、"2.3"、"Appendix A"、"References"。 */
        String id,
        /** 章节标题 (去除前导编号后的纯文本)。 */
        String title,
        /** 章节类型:"body" / "appendix" / "references"。 */
        String sectionType,
        /** 父章节 ID,顶级章节为 null。 */
        String parentId,
        /** 从顶级到当前章节的完整路径,例如 ["2", "2.1", "2.1.3"]。 */
        List<String> path,
        /** 起始行号 (1-based,基于清洗后的正文行)。 */
        int startLine,
        /** 结束行号 (1-based,包含)。 */
        int endLine) {
}
