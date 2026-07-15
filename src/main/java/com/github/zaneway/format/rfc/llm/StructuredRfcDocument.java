package com.github.zaneway.format.rfc.llm;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * 与 RFC 结构化解析提示词一一对应的 JSON 聚合根。
 *
 * <p>该对象只描述模型已经确认的内容；调用方不应依据 RFC 常识补全缺失字段。</p>
 */
public record StructuredRfcDocument(DocumentMetadata document, List<Unit> units) {

  /** RFC 级元数据。 */
  public record DocumentMetadata(
      String title,
      @JsonProperty("source_type") String sourceType,
      @JsonProperty("source_file") String sourceFile,
      String language,
      @JsonProperty("standard_id") String standardId,
      @JsonProperty("doc_kind") String docKind,
      String published,
      String status,
      List<String> authors,
      Relations relations,
      Extensions extensions) {
  }

  /** RFC 关系集合。 */
  public record Relations(
      List<String> replaces,
      @JsonProperty("replaced_by") List<String> replacedBy,
      List<String> updates,
      @JsonProperty("updated_by") List<String> updatedBy,
      List<String> obsoletes,
      @JsonProperty("obsoleted_by") List<String> obsoletedBy) {
  }

  /** RFC 扩展元数据。 */
  public record Extensions(
      @JsonProperty("rfc_number") String rfcNumber,
      String category,
      String stream) {
  }

  /** 可独立检索的正文单元。 */
  public record Unit(
      @JsonProperty("unit_id") String unitId,
      @JsonProperty("clause_id") String clauseId,
      @JsonProperty("heading_path") List<Heading> headingPath,
      @JsonProperty("content_type") String contentType,
      String content,
      @JsonProperty("source_location") SourceLocation sourceLocation,
      Map<String, Object> extensions) {
  }

  /** 标题路径节点。 */
  public record Heading(int level, String title) {
  }

  /** 调用方提供的原始行号范围；没有行号时两个字段均为 null。 */
  public record SourceLocation(Integer start, Integer end) {
  }
}
