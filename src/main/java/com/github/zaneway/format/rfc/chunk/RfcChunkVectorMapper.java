package com.github.zaneway.format.rfc.chunk;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 将已拆分 RFC 数据集记录映射为 Qdrant 向量文档。
 */
@Component
public class RfcChunkVectorMapper {

  static final String SCHEMA_VERSION = "rfc-chunk-v1";
  static final String UNIT_TYPE = "rfc_chunk";

  private final int maxEmbeddingChars;
  private final RfcChunkSegmenter segmenter;

  public RfcChunkVectorMapper(
      @Value("${rfc.chunks.max-embedding-chars:12000}") int maxEmbeddingChars) {
    if (maxEmbeddingChars < 512) {
      throw new IllegalArgumentException("rfc.chunks.max-embedding-chars must be at least 512");
    }
    this.maxEmbeddingChars = maxEmbeddingChars;
    this.segmenter = new RfcChunkSegmenter();
  }

  /**
   * 映射一个逻辑 chunk；超限正文会确定性拆成多个物理向量点。
   *
   * @param chunk 已通过入口校验的 RFC chunk
   * @return 一个或多个向量文档，embedding 文本均不超过配置上限
   */
  public List<RfcVectorDocument> toVectorDocuments(RfcChunk chunk) {
    String prefix = embeddingPrefix(chunk);
    int maxBodyChars = maxEmbeddingChars - prefix.length();
    List<RfcChunkSegment> segments = segmenter.split(chunk, maxBodyChars);
    List<RfcVectorDocument> documents = new ArrayList<>(segments.size());
    String parentChunkId = parentChunkId(chunk);
    for (RfcChunkSegment segment : segments) {
      String unitId = parentChunkId + ":segment-" + (segment.index() + 1);
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
      metadata.put("source_start_line", segment.startLine());
      metadata.put("source_end_line", segment.endLine());
      metadata.put("segment_index", segment.index());
      metadata.put("segment_count", segment.count());
      documents.add(new RfcVectorDocument(unitId, prefix + segment.text(), Map.copyOf(metadata)));
    }
    return List.copyOf(documents);
  }

  private static String embeddingPrefix(RfcChunk chunk) {
    String section = nullToEmpty(chunk.sectionId()).trim();
    String title = nullToEmpty(chunk.sectionTitle()).trim();
    StringBuilder prefix = new StringBuilder("RFC ").append(chunk.rfcNumber());
    if (!section.isEmpty()) {
      prefix.append(" | Section ").append(section);
    }
    if (!title.isEmpty()) {
      prefix.append(" | ").append(title);
    }
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
