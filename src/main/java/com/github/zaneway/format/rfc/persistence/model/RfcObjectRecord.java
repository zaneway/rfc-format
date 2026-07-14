package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 抽取对象表（rfc_object）的持久化记录。
 *
 * <p>对应解析模型 {@code RfcExtractedObject}，保存 OID、ABNF 规则、字段与状态码等结构化对象；
 * 通过 {@code source_unit_key} 回溯到产生该对象的 {@code RfcUnit}。</p>
 */
public class RfcObjectRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21467+08:00", comments = "Source field: rfc_object.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214709+08:00", comments = "Source field: rfc_object.document_id")
  private Long documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214733+08:00", comments = "Source field: rfc_object.object_key")
  private String objectKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214757+08:00", comments = "Source field: rfc_object.object_type")
  private String objectType;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214779+08:00", comments = "Source field: rfc_object.object_name")
  private String objectName;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21481+08:00", comments = "Source field: rfc_object.normalized_value")
  private String normalizedValue;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214832+08:00", comments = "Source field: rfc_object.source_unit_key")
  private String sourceUnitKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214854+08:00", comments = "Source field: rfc_object.source_start_line")
  private Integer sourceStartLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214874+08:00", comments = "Source field: rfc_object.source_end_line")
  private Integer sourceEndLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214893+08:00", comments = "Source field: rfc_object.source_start_offset")
  private Integer sourceStartOffset;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21492+08:00", comments = "Source field: rfc_object.source_end_offset")
  private Integer sourceEndOffset;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21494+08:00", comments = "Source field: rfc_object.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214966+08:00", comments = "Source field: rfc_object.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214986+08:00", comments = "Source field: rfc_object.attributes_json")
  private String attributesJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.215009+08:00", comments = "Source Table: rfc_object")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214686+08:00", comments = "Source field: rfc_object.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214701+08:00", comments = "Source field: rfc_object.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214718+08:00", comments = "Source field: rfc_object.document_id")
  public Long getDocumentId() {
    return documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214727+08:00", comments = "Source field: rfc_object.document_id")
  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214741+08:00", comments = "Source field: rfc_object.object_key")
  public String getObjectKey() {
    return objectKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214751+08:00", comments = "Source field: rfc_object.object_key")
  public void setObjectKey(String objectKey) {
    this.objectKey = objectKey == null ? null : objectKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214764+08:00", comments = "Source field: rfc_object.object_type")
  public String getObjectType() {
    return objectType;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214772+08:00", comments = "Source field: rfc_object.object_type")
  public void setObjectType(String objectType) {
    this.objectType = objectType == null ? null : objectType.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214787+08:00", comments = "Source field: rfc_object.object_name")
  public String getObjectName() {
    return objectName;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214797+08:00", comments = "Source field: rfc_object.object_name")
  public void setObjectName(String objectName) {
    this.objectName = objectName == null ? null : objectName.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214818+08:00", comments = "Source field: rfc_object.normalized_value")
  public String getNormalizedValue() {
    return normalizedValue;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214826+08:00", comments = "Source field: rfc_object.normalized_value")
  public void setNormalizedValue(String normalizedValue) {
    this.normalizedValue = normalizedValue == null ? null : normalizedValue.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214839+08:00", comments = "Source field: rfc_object.source_unit_key")
  public String getSourceUnitKey() {
    return sourceUnitKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214847+08:00", comments = "Source field: rfc_object.source_unit_key")
  public void setSourceUnitKey(String sourceUnitKey) {
    this.sourceUnitKey = sourceUnitKey == null ? null : sourceUnitKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214861+08:00", comments = "Source field: rfc_object.source_start_line")
  public Integer getSourceStartLine() {
    return sourceStartLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214868+08:00", comments = "Source field: rfc_object.source_start_line")
  public void setSourceStartLine(Integer sourceStartLine) {
    this.sourceStartLine = sourceStartLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21488+08:00", comments = "Source field: rfc_object.source_end_line")
  public Integer getSourceEndLine() {
    return sourceEndLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214887+08:00", comments = "Source field: rfc_object.source_end_line")
  public void setSourceEndLine(Integer sourceEndLine) {
    this.sourceEndLine = sourceEndLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214899+08:00", comments = "Source field: rfc_object.source_start_offset")
  public Integer getSourceStartOffset() {
    return sourceStartOffset;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214906+08:00", comments = "Source field: rfc_object.source_start_offset")
  public void setSourceStartOffset(Integer sourceStartOffset) {
    this.sourceStartOffset = sourceStartOffset;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214928+08:00", comments = "Source field: rfc_object.source_end_offset")
  public Integer getSourceEndOffset() {
    return sourceEndOffset;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214935+08:00", comments = "Source field: rfc_object.source_end_offset")
  public void setSourceEndOffset(Integer sourceEndOffset) {
    this.sourceEndOffset = sourceEndOffset;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214948+08:00", comments = "Source field: rfc_object.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21496+08:00", comments = "Source field: rfc_object.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214973+08:00", comments = "Source field: rfc_object.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21498+08:00", comments = "Source field: rfc_object.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214992+08:00", comments = "Source field: rfc_object.attributes_json")
  public String getAttributesJson() {
    return attributesJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.215001+08:00", comments = "Source field: rfc_object.attributes_json")
  public void setAttributesJson(String attributesJson) {
    this.attributesJson = attributesJson == null ? null : attributesJson.trim();
  }
}