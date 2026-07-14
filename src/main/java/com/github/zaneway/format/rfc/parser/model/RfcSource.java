package com.github.zaneway.format.rfc.parser.model;

/**
 * RFC 原始输入文件的可追溯信息，在解析入口 {@code parse} 读盘时一并计算。
 *
 * <p>嵌入 {@link RfcDocument#source()}，用于回答「这份结构化结果对应哪份物理文件、
 * 内容是否被替换」。入库或重跑解析时，可用 {@code sha256} 判断源 TXT 是否变更， 从而决定是否需要增量更新向量投影。</p>
 */
public record RfcSource(
    /** 原始文件名 (不包含路径)。 */
    String fileName,
    /** 原始文件内容的 SHA-256 哈希 (hex,64 字符)。 */
    String sha256) {

}
