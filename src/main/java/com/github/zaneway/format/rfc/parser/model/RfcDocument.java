package com.github.zaneway.format.rfc.parser.model;

import java.util.List;

/**
 * 一次确定性解析得到的 RFC 文档聚合根。
 *
 * 字段职责:
 * - documentId / rfcNumber : 文档唯一标识,documentId 形如 "rfc-9999"
 * - title / category / publicationDate : 头部元数据
 * - source : 文件级溯源信息 (文件名 + SHA-256)
 * - sections : 章节树 (扁平存储,通过 parentId / path 还原层级)
 * - units : 检索级知识单元集合
 * - relations : 文档间 / 文档内引用关系
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
        /** 章节列表。 */
        List<RfcSection> sections,
        /** 知识单元列表。 */
        List<RfcUnit> units,
        /** 关系列表。 */
        List<RfcRelation> relations) {
}
