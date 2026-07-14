package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 章节表（rfc_section）的持久化记录。
 *
 * <p>对应解析模型 {@code RfcSection}，保存章节树扁平化后的节点、层级路径与源行号；
 * 为单元归属（{@code parent_section_key}）和关系溯源提供锚点。</p>
 */
public class RfcSectionRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209771+08:00", comments = "Source field: rfc_section.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209818+08:00", comments = "Source field: rfc_section.document_id")
  private Long documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.20986+08:00", comments = "Source field: rfc_section.section_key")
  private String sectionKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209888+08:00", comments = "Source field: rfc_section.parent_section_key")
  private String parentSectionKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209917+08:00", comments = "Source field: rfc_section.title")
  private String title;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209944+08:00", comments = "Source field: rfc_section.section_type")
  private String sectionType;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209972+08:00", comments = "Source field: rfc_section.source_start_line")
  private Integer sourceStartLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209998+08:00", comments = "Source field: rfc_section.source_end_line")
  private Integer sourceEndLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210022+08:00", comments = "Source field: rfc_section.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210054+08:00", comments = "Source field: rfc_section.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210083+08:00", comments = "Source field: rfc_section.section_path_json")
  private String sectionPathJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21012+08:00", comments = "Source Table: rfc_section")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209791+08:00", comments = "Source field: rfc_section.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209806+08:00", comments = "Source field: rfc_section.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209831+08:00", comments = "Source field: rfc_section.document_id")
  public Long getDocumentId() {
    return documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.20985+08:00", comments = "Source field: rfc_section.document_id")
  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.20987+08:00", comments = "Source field: rfc_section.section_key")
  public String getSectionKey() {
    return sectionKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209881+08:00", comments = "Source field: rfc_section.section_key")
  public void setSectionKey(String sectionKey) {
    this.sectionKey = sectionKey == null ? null : sectionKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209897+08:00", comments = "Source field: rfc_section.parent_section_key")
  public String getParentSectionKey() {
    return parentSectionKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209908+08:00", comments = "Source field: rfc_section.parent_section_key")
  public void setParentSectionKey(String parentSectionKey) {
    this.parentSectionKey = parentSectionKey == null ? null : parentSectionKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209926+08:00", comments = "Source field: rfc_section.title")
  public String getTitle() {
    return title;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209936+08:00", comments = "Source field: rfc_section.title")
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209953+08:00", comments = "Source field: rfc_section.section_type")
  public String getSectionType() {
    return sectionType;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209964+08:00", comments = "Source field: rfc_section.section_type")
  public void setSectionType(String sectionType) {
    this.sectionType = sectionType == null ? null : sectionType.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.209982+08:00", comments = "Source field: rfc_section.source_start_line")
  public Integer getSourceStartLine() {
    return sourceStartLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.20999+08:00", comments = "Source field: rfc_section.source_start_line")
  public void setSourceStartLine(Integer sourceStartLine) {
    this.sourceStartLine = sourceStartLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210007+08:00", comments = "Source field: rfc_section.source_end_line")
  public Integer getSourceEndLine() {
    return sourceEndLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210015+08:00", comments = "Source field: rfc_section.source_end_line")
  public void setSourceEndLine(Integer sourceEndLine) {
    this.sourceEndLine = sourceEndLine;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210037+08:00", comments = "Source field: rfc_section.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210047+08:00", comments = "Source field: rfc_section.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210063+08:00", comments = "Source field: rfc_section.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210072+08:00", comments = "Source field: rfc_section.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210092+08:00", comments = "Source field: rfc_section.section_path_json")
  public String getSectionPathJson() {
    return sectionPathJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210103+08:00", comments = "Source field: rfc_section.section_path_json")
  public void setSectionPathJson(String sectionPathJson) {
    this.sectionPathJson = sectionPathJson == null ? null : sectionPathJson.trim();
  }
}