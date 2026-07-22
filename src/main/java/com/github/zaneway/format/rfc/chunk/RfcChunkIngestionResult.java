package com.github.zaneway.format.rfc.chunk;

/**
 * RFC chunk 聚合文件入库结果。
 *
 * @param readChunkCount    读取的逻辑 chunk 数
 * @param writtenPointCount 写入 Qdrant 的向量点数
 * @param skippedChunkCount embedding 失败而跳过的逻辑 chunk 数
 * @param rfcCount          读取的 RFC 数量
 * @param durationMillis    总耗时毫秒数
 */
public record RfcChunkIngestionResult(
    int readChunkCount,
    int writtenPointCount,
    int skippedChunkCount,
    int rfcCount,
    long durationMillis) {
}
