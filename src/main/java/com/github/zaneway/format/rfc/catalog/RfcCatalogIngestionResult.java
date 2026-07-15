package com.github.zaneway.format.rfc.catalog;

/**
 * RFC 摘要目录导入结果。
 *
 * @param readCount             JSONL 原始有效行数（去重前）
 * @param writtenCount          实际写入向量库的点数
 * @param duplicateSkippedCount 因 rfc_number 重复被覆盖而未单独写入的行数
 */
public record RfcCatalogIngestionResult(int readCount, int writtenCount, int duplicateSkippedCount) {
}
