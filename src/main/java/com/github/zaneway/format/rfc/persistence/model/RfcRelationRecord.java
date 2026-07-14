package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 关系边表（rfc_relation）的持久化记录。
 *
 * <p>对应解析模型 {@code RfcRelation}，存储引用（cites）、章节引用与 defines 等有向边；
 * {@code attributes_json} 保留原文切片、偏移与解析状态等审计证据。</p>
 */
public class RfcRelationRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213119+08:00", comments = "Source field: rfc_relation.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213155+08:00", comments = "Source field: rfc_relation.document_id")
  private Long documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21318+08:00", comments = "Source field: rfc_relation.relation_key")
  private String relationKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213203+08:00", comments = "Source field: rfc_relation.relation_type")
  private String relationType;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213226+08:00", comments = "Source field: rfc_relation.from_kind")
  private String fromKind;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213247+08:00", comments = "Source field: rfc_relation.from_identifier")
  private String fromIdentifier;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213269+08:00", comments = "Source field: rfc_relation.to_kind")
  private String toKind;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21329+08:00", comments = "Source field: rfc_relation.to_identifier")
  private String toIdentifier;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213312+08:00", comments = "Source field: rfc_relation.source_section_key")
  private String sourceSectionKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213332+08:00", comments = "Source field: rfc_relation.source_start_line")
  private Integer sourceStartLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213353+08:00", comments = "Source field: rfc_relation.source_end_line")
  private Integer sourceEndLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213373+08:00", comments = "Source field: rfc_relation.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213395+08:00", comments = "Source field: rfc_relation.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213416+08:00", comments = "Source field: rfc_relation.attributes_json")
  private String attributesJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213442+08:00", comments = "Source Table: rfc_relation")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213135+08:00", comments = "Source field: rfc_relation.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213147+08:00", comments = "Source field: rfc_relation.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213165+08:00", comments = "Source field: rfc_relation.document_id")
  public Long getDocumentId() {
    return documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213174+08:00", comments = "Source field: rfc_relation.document_id")
  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213188+08:00", comments = "Source field: rfc_relation.relation_key")
  public String getRelationKey() {
    return relationKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213197+08:00", comments = "Source field: rfc_relation.relation_key")
  public void setRelationKey(String relationKey) {
    this.relationKey = relationKey == null ? null : relationKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213211+08:00", comments = "Source field: rfc_relation.relation_type")
  public String getRelationType() {
    return relationType;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21322+08:00", comments = "Source field: rfc_relation.relation_type")
  public void setRelationType(String relationType) {
    this.relationType = relationType == null ? null : relationType.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213233+08:00", comments = "Source field: rfc_relation.from_kind")
  public String getFromKind() {
    return fromKind;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213241+08:00", comments = "Source field: rfc_relation.from_kind")
  public void setFromKind(String fromKind) {
    this.fromKind = fromKind == null ? null : fromKind.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213254+08:00", comments = "Source field: rfc_relation.from_identifier")
  public String getFromIdentifier() {
    return fromIdentifier;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213264+08:00", comments = "Source field: rfc_relation.from_identifier")
  public void setFromIdentifier(String fromIdentifier) {
    this.fromIdentifier = fromIdentifier == null ? null : fromIdentifier.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213276+08:00", comments = "Source field: rfc_relation.to_kind")
  public String getToKind() {
    return toKind;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213284+08:00", comments = "Source field: rfc_relation.to_kind")
  public void setToKind(String toKind) {
    this.toKind = toKind == null ? null : toKind.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213298+08:00", comments = "Source field: rfc_relation.to_identifier")
  public String getToIdentifier() {
    return toIdentifier;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213306+08:00", comments = "Source field: rfc_relation.to_identifier")
  public void setToIdentifier(String toIdentifier) {
    this.toIdentifier = toIdentifier == null ? null : toIdentifier.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213319+08:00", comments = "Source field: rfc_relation.source_section_key")
  public String getSourceSectionKey() {
    return sourceSectionKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213327+08:00", comments = "Source field: rfc_relation.source_section_key")
  public void setSourceSectionKey(String sourceSectionKey) {
    this.sourceSectionKey = sourceSectionKey == null ? null : sourceSectionKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213339+08:00", comments = "Source field: rfc_relation.source_start_line")
  public Integer getSourceStartLine() {
    return sourceStartLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213348+08:00", comments = "Source field: rfc_relation.source_start_line")
  public void setSourceStartLine(Integer sourceStartLine) {
    this.sourceStartLine = sourceStartLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21336+08:00", comments = "Source field: rfc_relation.source_end_line")
  public Integer getSourceEndLine() {
    return sourceEndLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213367+08:00", comments = "Source field: rfc_relation.source_end_line")
  public void setSourceEndLine(Integer sourceEndLine) {
    this.sourceEndLine = sourceEndLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213381+08:00", comments = "Source field: rfc_relation.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213388+08:00", comments = "Source field: rfc_relation.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213402+08:00", comments = "Source field: rfc_relation.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21341+08:00", comments = "Source field: rfc_relation.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213423+08:00", comments = "Source field: rfc_relation.attributes_json")
  public String getAttributesJson() {
    return attributesJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213431+08:00", comments = "Source field: rfc_relation.attributes_json")
  public void setAttributesJson(String attributesJson) {
    this.attributesJson = attributesJson == null ? null : attributesJson.trim();
  }
}