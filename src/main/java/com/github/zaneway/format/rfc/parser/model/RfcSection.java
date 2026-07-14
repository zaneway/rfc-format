package com.github.zaneway.format.rfc.parser.model;

import java.util.List;

/**
 * RFC 章节树中的一个确定性节点，由解析器在「章节切分」阶段产出。
 *
 * <p>在流水线中的位置：{@code findSectionRanges} 识别区间 → {@code toSections} 填充
 * parent/path 并映射物理行号 → 写入 {@link RfcDocument#sections()}，供单元归属与引用解析使用。</p>
 *
 * <p>设计要点:</p>
 * <ul>
 *   <li>通过 {@code parentId} 与 {@code path} 同时表达层级：O(1) 查父、可完整回溯路径；</li>
 *   <li>{@code sectionType} 取值 {@code body} / {@code appendix} / {@code references}，
 *       其中 {@code references} 章节还驱动 Normative/Informative 引用分类目录；</li>
 *   <li>{@code startLine}/{@code endLine} 一律为原 RFC TXT 的 1-based 物理行号
 *       （解析内部清洗索引会在产出前映射回去，对外不暴露清洗后行号）。</li>
 * </ul>
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
    /** 起始行号 (1-based,原 RFC TXT 物理行)。 */
    int startLine,
    /** 结束行号 (1-based,包含,原 RFC TXT 物理行)。 */
    int endLine) {

}
