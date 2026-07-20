package com.github.zaneway.format.rfc.chunk;

/**
 * RFC chunk 流式读取统计。
 *
 * @param chunkCount 有效 JSONL 记录数
 * @param rfcCount   连续 RFC 分组数
 */
public record RfcChunkReadResult(int chunkCount, int rfcCount) {
}
