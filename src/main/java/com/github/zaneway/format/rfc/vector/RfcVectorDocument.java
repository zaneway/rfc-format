package com.github.zaneway.format.rfc.vector;

import java.util.Map;

/**
 * 写入向量库的稳定 RFC 单元投影。
 *
 * @param id       单元稳定标识，作为 Qdrant point id
 * @param text     用于嵌入的文本（通常为 embeddingText）
 * @param metadata 检索过滤用的结构化元数据（rfc_number、section_id、unit_type 等）
 */
public record RfcVectorDocument(String id, String text, Map<String, Object> metadata) {

}
