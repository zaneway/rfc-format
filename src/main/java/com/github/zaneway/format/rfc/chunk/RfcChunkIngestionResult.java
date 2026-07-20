package com.github.zaneway.format.rfc.chunk;

/**
 * RFC chunk 聚合文件入库结果。
 *
 * @param readChunkCount    读取的逻辑 chunk 数
 * @param writtenPointCount 写入 Qdrant 的物理向量点数
 * @param rfcCount          成功替换的 RFC 数量
 * @param splitPointCount   因超长切分而额外增加的向量点数
 * @param durationMillis    总耗时毫秒数
 */
public record RfcChunkIngestionResult(
    int readChunkCount,
    int writtenPointCount,
    int rfcCount,
    int splitPointCount,
    long durationMillis) {
}
