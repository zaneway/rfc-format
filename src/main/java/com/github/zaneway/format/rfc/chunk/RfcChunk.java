package com.github.zaneway.format.rfc.chunk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * {@code docs/rfc/all_chunks.jsonl} 的单行数据契约。
 *
 * @param rfcNumber    RFC 编号，必须为正整数
 * @param sectionId    章节标识；早期未分节 RFC 允许为空
 * @param sectionTitle 章节标题；早期未分节 RFC 允许为空
 * @param sectionPath  章节完整路径；早期未分节 RFC 允许为空
 * @param depth        章节深度，当前数据范围为 0 到 3
 * @param text         RFC 原文块，不允许为空
 * @param references   当前块引用的 RFC 编号
 * @param sourceFile   原始 RFC 文本文件名
 * @param startLine    原文起始行（包含）
 * @param endLine      原文结束行（包含）
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RfcChunk(
    @JsonProperty("rfc_number") int rfcNumber,
    @JsonProperty("section_id") String sectionId,
    @JsonProperty("section_title") String sectionTitle,
    @JsonProperty("section_path") String sectionPath,
    int depth,
    String text,
    List<Integer> references,
    @JsonProperty("source_file") String sourceFile,
    @JsonProperty("start_line") int startLine,
    @JsonProperty("end_line") int endLine) {
}
