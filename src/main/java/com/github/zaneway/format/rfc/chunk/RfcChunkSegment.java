package com.github.zaneway.format.rfc.chunk;

/**
 * 为控制 embedding 上下文长度而从一个逻辑 RFC chunk 派生的物理向量段。
 *
 * @param text      保持原始顺序的文本片段
 * @param startLine 片段在 RFC 原文中的近似起始行
 * @param endLine   片段在 RFC 原文中的近似结束行
 * @param index     从 0 开始的片段序号
 * @param count     当前逻辑 chunk 的片段总数
 */
public record RfcChunkSegment(String text, int startLine, int endLine, int index, int count) {
}
