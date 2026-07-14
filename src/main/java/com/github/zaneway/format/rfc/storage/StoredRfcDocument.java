package com.github.zaneway.format.rfc.storage;

/**
 * 已写入 MySQL 的 RFC 文档身份，供向量投影与导入审计引用。
 *
 * @param documentId   数据库主键，投影状态表的外键
 * @param documentKey  解析器生成的稳定文档标识（如 rfc-5280）
 * @param rfcNumber    RFC 编号字符串，用于 Qdrant 按 RFC 范围替换向量
 * @param sourceSha256 源文本 SHA-256，用于检测重导入与投影版本对齐
 */
public record StoredRfcDocument(long documentId, String documentKey, String rfcNumber,
                                String sourceSha256) {

}
