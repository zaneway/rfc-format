package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 向量投影状态表（rfc_vector_projection）的持久化记录。
 *
 * <p>跟踪文档向 Qdrant 的投影进度（状态、尝试次数、完成时间与错误信息）；
 * 以 {@code document_id} + {@code projection_name} + {@code source_sha256} 判定是否需要重新投影。</p>
 */
public class RfcVectorProjectionRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216076+08:00", comments = "Source field: rfc_vector_projection.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216109+08:00", comments = "Source field: rfc_vector_projection.document_id")
  private Long documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216131+08:00", comments = "Source field: rfc_vector_projection.projection_name")
  private String projectionName;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216155+08:00", comments = "Source field: rfc_vector_projection.source_sha256")
  private String sourceSha256;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216177+08:00", comments = "Source field: rfc_vector_projection.status")
  private String status;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216197+08:00", comments = "Source field: rfc_vector_projection.attempt_count")
  private Integer attemptCount;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216216+08:00", comments = "Source field: rfc_vector_projection.completed_at")
  private LocalDateTime completedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216245+08:00", comments = "Source field: rfc_vector_projection.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216268+08:00", comments = "Source field: rfc_vector_projection.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216287+08:00", comments = "Source field: rfc_vector_projection.error_message")
  private String errorMessage;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216312+08:00", comments = "Source Table: rfc_vector_projection")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21609+08:00", comments = "Source field: rfc_vector_projection.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216101+08:00", comments = "Source field: rfc_vector_projection.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216117+08:00", comments = "Source field: rfc_vector_projection.document_id")
  public Long getDocumentId() {
    return documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216125+08:00", comments = "Source field: rfc_vector_projection.document_id")
  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216138+08:00", comments = "Source field: rfc_vector_projection.projection_name")
  public String getProjectionName() {
    return projectionName;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216149+08:00", comments = "Source field: rfc_vector_projection.projection_name")
  public void setProjectionName(String projectionName) {
    this.projectionName = projectionName == null ? null : projectionName.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216161+08:00", comments = "Source field: rfc_vector_projection.source_sha256")
  public String getSourceSha256() {
    return sourceSha256;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216171+08:00", comments = "Source field: rfc_vector_projection.source_sha256")
  public void setSourceSha256(String sourceSha256) {
    this.sourceSha256 = sourceSha256 == null ? null : sourceSha256.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216183+08:00", comments = "Source field: rfc_vector_projection.status")
  public String getStatus() {
    return status;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216191+08:00", comments = "Source field: rfc_vector_projection.status")
  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216204+08:00", comments = "Source field: rfc_vector_projection.attempt_count")
  public Integer getAttemptCount() {
    return attemptCount;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21621+08:00", comments = "Source field: rfc_vector_projection.attempt_count")
  public void setAttemptCount(Integer attemptCount) {
    this.attemptCount = attemptCount;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216231+08:00", comments = "Source field: rfc_vector_projection.completed_at")
  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216239+08:00", comments = "Source field: rfc_vector_projection.completed_at")
  public void setCompletedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216255+08:00", comments = "Source field: rfc_vector_projection.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216262+08:00", comments = "Source field: rfc_vector_projection.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216275+08:00", comments = "Source field: rfc_vector_projection.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216282+08:00", comments = "Source field: rfc_vector_projection.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216295+08:00", comments = "Source field: rfc_vector_projection.error_message")
  public String getErrorMessage() {
    return errorMessage;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216303+08:00", comments = "Source field: rfc_vector_projection.error_message")
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage == null ? null : errorMessage.trim();
  }
}