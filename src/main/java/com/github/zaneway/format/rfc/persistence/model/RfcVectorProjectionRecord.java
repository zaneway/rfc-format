package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcVectorProjectionRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741779+08:00", comments="Source field: rfc_vector_projection.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741808+08:00", comments="Source field: rfc_vector_projection.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741834+08:00", comments="Source field: rfc_vector_projection.projection_name")
    private String projectionName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741855+08:00", comments="Source field: rfc_vector_projection.source_sha256")
    private String sourceSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741874+08:00", comments="Source field: rfc_vector_projection.status")
    private String status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741893+08:00", comments="Source field: rfc_vector_projection.attempt_count")
    private Integer attemptCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741911+08:00", comments="Source field: rfc_vector_projection.completed_at")
    private LocalDateTime completedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74193+08:00", comments="Source field: rfc_vector_projection.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741948+08:00", comments="Source field: rfc_vector_projection.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741971+08:00", comments="Source field: rfc_vector_projection.error_message")
    private String errorMessage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741992+08:00", comments="Source Table: rfc_vector_projection")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741792+08:00", comments="Source field: rfc_vector_projection.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741802+08:00", comments="Source field: rfc_vector_projection.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741815+08:00", comments="Source field: rfc_vector_projection.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741828+08:00", comments="Source field: rfc_vector_projection.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74184+08:00", comments="Source field: rfc_vector_projection.projection_name")
    public String getProjectionName() {
        return projectionName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741848+08:00", comments="Source field: rfc_vector_projection.projection_name")
    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName == null ? null : projectionName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741861+08:00", comments="Source field: rfc_vector_projection.source_sha256")
    public String getSourceSha256() {
        return sourceSha256;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741869+08:00", comments="Source field: rfc_vector_projection.source_sha256")
    public void setSourceSha256(String sourceSha256) {
        this.sourceSha256 = sourceSha256 == null ? null : sourceSha256.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741881+08:00", comments="Source field: rfc_vector_projection.status")
    public String getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741887+08:00", comments="Source field: rfc_vector_projection.status")
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741898+08:00", comments="Source field: rfc_vector_projection.attempt_count")
    public Integer getAttemptCount() {
        return attemptCount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741905+08:00", comments="Source field: rfc_vector_projection.attempt_count")
    public void setAttemptCount(Integer attemptCount) {
        this.attemptCount = attemptCount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741918+08:00", comments="Source field: rfc_vector_projection.completed_at")
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741924+08:00", comments="Source field: rfc_vector_projection.completed_at")
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741937+08:00", comments="Source field: rfc_vector_projection.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741943+08:00", comments="Source field: rfc_vector_projection.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74196+08:00", comments="Source field: rfc_vector_projection.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741966+08:00", comments="Source field: rfc_vector_projection.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741977+08:00", comments="Source field: rfc_vector_projection.error_message")
    public String getErrorMessage() {
        return errorMessage;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741985+08:00", comments="Source field: rfc_vector_projection.error_message")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }
}