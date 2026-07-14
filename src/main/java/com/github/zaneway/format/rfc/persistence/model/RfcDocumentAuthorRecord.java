package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 文档作者表（rfc_document_author）的持久化记录。
 *
 * <p>存储 {@code RfcDocument} 头部解析出的作者列表，按 {@code author_order} 保序；
 * 通过 {@code document_id} 关联文档主表，不参与向量投影。</p>
 */
public class RfcDocumentAuthorRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.20809+08:00", comments = "Source field: rfc_document_author.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208171+08:00", comments = "Source field: rfc_document_author.document_id")
  private Long documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208209+08:00", comments = "Source field: rfc_document_author.author_order")
  private Integer authorOrder;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208246+08:00", comments = "Source field: rfc_document_author.author_name")
  private String authorName;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208284+08:00", comments = "Source field: rfc_document_author.organization")
  private String organization;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208323+08:00", comments = "Source field: rfc_document_author.email")
  private String email;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.20836+08:00", comments = "Source field: rfc_document_author.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208397+08:00", comments = "Source field: rfc_document_author.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208437+08:00", comments = "Source Table: rfc_document_author")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208134+08:00", comments = "Source field: rfc_document_author.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208157+08:00", comments = "Source field: rfc_document_author.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208186+08:00", comments = "Source field: rfc_document_author.document_id")
  public Long getDocumentId() {
    return documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208198+08:00", comments = "Source field: rfc_document_author.document_id")
  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208222+08:00", comments = "Source field: rfc_document_author.author_order")
  public Integer getAuthorOrder() {
    return authorOrder;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208235+08:00", comments = "Source field: rfc_document_author.author_order")
  public void setAuthorOrder(Integer authorOrder) {
    this.authorOrder = authorOrder;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208258+08:00", comments = "Source field: rfc_document_author.author_name")
  public String getAuthorName() {
    return authorName;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208274+08:00", comments = "Source field: rfc_document_author.author_name")
  public void setAuthorName(String authorName) {
    this.authorName = authorName == null ? null : authorName.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208298+08:00", comments = "Source field: rfc_document_author.organization")
  public String getOrganization() {
    return organization;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208312+08:00", comments = "Source field: rfc_document_author.organization")
  public void setOrganization(String organization) {
    this.organization = organization == null ? null : organization.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208336+08:00", comments = "Source field: rfc_document_author.email")
  public String getEmail() {
    return email;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208349+08:00", comments = "Source field: rfc_document_author.email")
  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208374+08:00", comments = "Source field: rfc_document_author.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208387+08:00", comments = "Source field: rfc_document_author.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.208409+08:00", comments = "Source field: rfc_document_author.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.20842+08:00", comments = "Source field: rfc_document_author.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}