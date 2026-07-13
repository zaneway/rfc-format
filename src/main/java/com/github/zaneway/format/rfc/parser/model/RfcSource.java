package com.github.zaneway.format.rfc.parser.model;

/**
 * RFC 原始文件的可追溯信息。
 *
 * 用于在 {@link RfcDocument} 中保留"这份 RFC 内容到底来自哪个文件"的事实,
 * 一旦后续怀疑解析结果与原始 RFC 不一致,可凭 SHA-256 验证内容是否被篡改。
 */
public record RfcSource(
        /** 原始文件名 (不包含路径)。 */
        String fileName,
        /** 原始文件内容的 SHA-256 哈希 (hex,64 字符)。 */
        String sha256) {
}
