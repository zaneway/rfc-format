package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 知识单元表（rfc_unit）的持久化记录。
 *
 * <p>对应解析模型 {@code RfcUnit}，承载检索级正文、嵌入文本与语义元数据；
 * 是 Qdrant 向量投影（{@code RfcVectorDocument}）的主要数据来源。</p>
 */
public class RfcUnitRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211366+08:00", comments = "Source field: rfc_unit.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211417+08:00", comments = "Source field: rfc_unit.document_id")
  private Long documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211444+08:00", comments = "Source field: rfc_unit.unit_key")
  private String unitKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211474+08:00", comments = "Source field: rfc_unit.parent_section_key")
  private String parentSectionKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211499+08:00", comments = "Source field: rfc_unit.unit_type")
  private String unitType;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211524+08:00", comments = "Source field: rfc_unit.source_start_line")
  private Integer sourceStartLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211547+08:00", comments = "Source field: rfc_unit.source_end_line")
  private Integer sourceEndLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211573+08:00", comments = "Source field: rfc_unit.language")
  private String language;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211603+08:00", comments = "Source field: rfc_unit.entity_type")
  private String entityType;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211627+08:00", comments = "Source field: rfc_unit.entity_name")
  private String entityName;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211651+08:00", comments = "Source field: rfc_unit.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211676+08:00", comments = "Source field: rfc_unit.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211702+08:00", comments = "Source field: rfc_unit.content")
  private String content;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211726+08:00", comments = "Source field: rfc_unit.embedding_text")
  private String embeddingText;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211757+08:00", comments = "Source field: rfc_unit.semantic_json")
  private String semanticJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21178+08:00", comments = "Source field: rfc_unit.metadata_json")
  private String metadataJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21181+08:00", comments = "Source Table: rfc_unit")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211392+08:00", comments = "Source field: rfc_unit.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211405+08:00", comments = "Source field: rfc_unit.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211427+08:00", comments = "Source field: rfc_unit.document_id")
  public Long getDocumentId() {
    return documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211437+08:00", comments = "Source field: rfc_unit.document_id")
  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211452+08:00", comments = "Source field: rfc_unit.unit_key")
  public String getUnitKey() {
    return unitKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211467+08:00", comments = "Source field: rfc_unit.unit_key")
  public void setUnitKey(String unitKey) {
    this.unitKey = unitKey == null ? null : unitKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211483+08:00", comments = "Source field: rfc_unit.parent_section_key")
  public String getParentSectionKey() {
    return parentSectionKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211492+08:00", comments = "Source field: rfc_unit.parent_section_key")
  public void setParentSectionKey(String parentSectionKey) {
    this.parentSectionKey = parentSectionKey == null ? null : parentSectionKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211508+08:00", comments = "Source field: rfc_unit.unit_type")
  public String getUnitType() {
    return unitType;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211518+08:00", comments = "Source field: rfc_unit.unit_type")
  public void setUnitType(String unitType) {
    this.unitType = unitType == null ? null : unitType.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211532+08:00", comments = "Source field: rfc_unit.source_start_line")
  public Integer getSourceStartLine() {
    return sourceStartLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21154+08:00", comments = "Source field: rfc_unit.source_start_line")
  public void setSourceStartLine(Integer sourceStartLine) {
    this.sourceStartLine = sourceStartLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211554+08:00", comments = "Source field: rfc_unit.source_end_line")
  public Integer getSourceEndLine() {
    return sourceEndLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211565+08:00", comments = "Source field: rfc_unit.source_end_line")
  public void setSourceEndLine(Integer sourceEndLine) {
    this.sourceEndLine = sourceEndLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211584+08:00", comments = "Source field: rfc_unit.language")
  public String getLanguage() {
    return language;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211596+08:00", comments = "Source field: rfc_unit.language")
  public void setLanguage(String language) {
    this.language = language == null ? null : language.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211611+08:00", comments = "Source field: rfc_unit.entity_type")
  public String getEntityType() {
    return entityType;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21162+08:00", comments = "Source field: rfc_unit.entity_type")
  public void setEntityType(String entityType) {
    this.entityType = entityType == null ? null : entityType.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211635+08:00", comments = "Source field: rfc_unit.entity_name")
  public String getEntityName() {
    return entityName;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211644+08:00", comments = "Source field: rfc_unit.entity_name")
  public void setEntityName(String entityName) {
    this.entityName = entityName == null ? null : entityName.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211662+08:00", comments = "Source field: rfc_unit.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21167+08:00", comments = "Source field: rfc_unit.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211685+08:00", comments = "Source field: rfc_unit.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211695+08:00", comments = "Source field: rfc_unit.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211709+08:00", comments = "Source field: rfc_unit.content")
  public String getContent() {
    return content;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211719+08:00", comments = "Source field: rfc_unit.content")
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211741+08:00", comments = "Source field: rfc_unit.embedding_text")
  public String getEmbeddingText() {
    return embeddingText;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211751+08:00", comments = "Source field: rfc_unit.embedding_text")
  public void setEmbeddingText(String embeddingText) {
    this.embeddingText = embeddingText == null ? null : embeddingText.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211765+08:00", comments = "Source field: rfc_unit.semantic_json")
  public String getSemanticJson() {
    return semanticJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211773+08:00", comments = "Source field: rfc_unit.semantic_json")
  public void setSemanticJson(String semanticJson) {
    this.semanticJson = semanticJson == null ? null : semanticJson.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211787+08:00", comments = "Source field: rfc_unit.metadata_json")
  public String getMetadataJson() {
    return metadataJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211796+08:00", comments = "Source field: rfc_unit.metadata_json")
  public void setMetadataJson(String metadataJson) {
    this.metadataJson = metadataJson == null ? null : metadataJson.trim();
  }
}