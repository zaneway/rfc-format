package com.github.zaneway.format.rfc.parser.model;

import java.util.List;

/**
 * 一次确定性解析得到的 RFC 文档聚合根，位于解析流水线的最终产出层。
 *
 * <p>由 {@link com.github.zaneway.format.rfc.parser.RfcDocumentParser} 实现类
 * （默认 {@link com.github.zaneway.format.rfc.parser.RfcTextParser}）在单次 {@code parse} 调用中
 * 一次性组装；下游入库、向量化或 JSON 序列化均以此 record 为边界，不再二次改写正文。</p>
 *
 * <p>字段职责:</p>
 * <ul>
 *   <li>{@code documentId} / {@code rfcNumber} — 文档唯一标识，{@code documentId} 形如 {@code rfc-9999}</li>
 *   <li>{@code title} / {@code category} / {@code publicationDate} — 头部元数据</li>
 *   <li>{@code source} — 文件级溯源（文件名 + SHA-256），用于变更检测</li>
 *   <li>{@code sections} — 章节树（扁平存储，通过 {@link RfcSection#parentId()} / {@link RfcSection#path()} 还原层级）</li>
 *   <li>{@code units} — 检索级知识单元；引用与规则抽取的文本来源</li>
 *   <li>{@code relations} — 引用边（{@code cites} / {@code cites_section} / {@code section_ref}）与 {@code defines} 边；
 *       审计证据（原文切片、偏移、occurrence、resolutionStatus 等）挂在 {@link RfcRelation#attributes()}</li>
 *   <li>{@code objects} — 从明确语法抽取的 OID、字段、ABNF 规则与状态/错误码对象</li>
 * </ul>
 */
public record RfcDocument(
    /** 文档唯一 ID,格式 "rfc-{number}"。 */
    String documentId,
    /** RFC 编号,纯数字字符串。 */
    String rfcNumber,
    /** RFC 标题。 */
    String title,
    /** 类别 (例如 "Standards Track"、"Informational"、"Experimental")。 */
    String category,
    /** 发布日期,原始字符串 (例如 "July 2024"),可能为空串。 */
    String publicationDate,
    /** 文件溯源信息。 */
    RfcSource source,
    /** 章节列表（扁平;用 parentId / path 还原树）。 */
    List<RfcSection> sections,
    /** 知识单元列表。 */
    List<RfcUnit> units,
    /** 关系列表（引用 + defines；详见类注释）。 */
    List<RfcRelation> relations,
    /** 可独立审计的规则对象列表。 */
    List<RfcExtractedObject> objects) {

}
