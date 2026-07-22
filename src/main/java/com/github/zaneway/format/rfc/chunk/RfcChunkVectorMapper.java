package com.github.zaneway.format.rfc.chunk;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 将已拆分 RFC 数据集记录映射为 Qdrant 向量文档。
 */
@Component
public class RfcChunkVectorMapper {

  static final String SCHEMA_VERSION = "rfc-chunk-v2";
  static final String UNIT_TYPE = "rfc_chunk";

  /**
   * 将一个逻辑 chunk 映射为一个向量文档，不再按字符数拆分原文。
   *
   * @param chunk 已通过入口校验的 RFC chunk
   * @return 保留完整正文的向量文档
   */
  public RfcVectorDocument toVectorDocument(RfcChunk chunk) {
    String prefix = embeddingPrefix(chunk);
    String parentChunkId = parentChunkId(chunk);
    LinkedHashMap<String, Object> metadata = new LinkedHashMap<>();
    metadata.put("rfc_number", Integer.toString(chunk.rfcNumber()));
    metadata.put("document_id", "rfc-" + chunk.rfcNumber());
    metadata.put("unit_type", UNIT_TYPE);
    metadata.put("schema_version", SCHEMA_VERSION);
    metadata.put("parent_chunk_id", parentChunkId);
    metadata.put("section_id", nullToEmpty(chunk.sectionId()));
    metadata.put("section_title", nullToEmpty(chunk.sectionTitle()));
    metadata.put("section_path", nullToEmpty(chunk.sectionPath()));
    metadata.put("depth", chunk.depth());
    metadata.put("references", List.copyOf(chunk.references()));
    metadata.put("source_file", chunk.sourceFile());
    metadata.put("source_start_line", chunk.startLine());
    metadata.put("source_end_line", chunk.endLine());
    // metadata 在写入前冻结，避免后续流程误修改造成同批数据不一致。
    return new RfcVectorDocument(parentChunkId, prefix + chunk.text(), Map.copyOf(metadata));
  }

  private static String embeddingPrefix(RfcChunk chunk) {
    String section = nullToEmpty(chunk.sectionId()).trim();
    String path = nullToEmpty(chunk.sectionPath()).trim();
    StringBuilder prefix = new StringBuilder("RFC ").append(chunk.rfcNumber());
    if (!section.isEmpty()) {
      prefix.append(" | Section ").append(section);
    }
    if (!path.isEmpty()) {
      prefix.append(" | ").append(path);
    }
    // 追加空行，将“定位上下文”和“正文内容”明确分隔。
    return prefix.append("\n\n").toString();
  }

  private static String parentChunkId(RfcChunk chunk) {
    return "rfc-%d:%s:%d-%d".formatted(
        chunk.rfcNumber(), chunk.sourceFile(), chunk.startLine(), chunk.endLine());
  }

  private static String nullToEmpty(String value) {
    return value == null ? "" : value;
  }
}
