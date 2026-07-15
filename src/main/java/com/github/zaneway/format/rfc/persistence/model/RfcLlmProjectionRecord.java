package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcLlmProjectionRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746678+08:00", comments="Source field: rfc_llm_projection.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746698+08:00", comments="Source field: rfc_llm_projection.status")
    private String status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746713+08:00", comments="Source field: rfc_llm_projection.vector_count")
    private Integer vectorCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746727+08:00", comments="Source field: rfc_llm_projection.error_message")
    private String errorMessage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746742+08:00", comments="Source field: rfc_llm_projection.attempted_at")
    private LocalDateTime attemptedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746764+08:00", comments="Source field: rfc_llm_projection.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746781+08:00", comments="Source Table: rfc_llm_projection")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746687+08:00", comments="Source field: rfc_llm_projection.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746694+08:00", comments="Source field: rfc_llm_projection.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746704+08:00", comments="Source field: rfc_llm_projection.status")
    public String getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74671+08:00", comments="Source field: rfc_llm_projection.status")
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746718+08:00", comments="Source field: rfc_llm_projection.vector_count")
    public Integer getVectorCount() {
        return vectorCount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746723+08:00", comments="Source field: rfc_llm_projection.vector_count")
    public void setVectorCount(Integer vectorCount) {
        this.vectorCount = vectorCount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746732+08:00", comments="Source field: rfc_llm_projection.error_message")
    public String getErrorMessage() {
        return errorMessage;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746737+08:00", comments="Source field: rfc_llm_projection.error_message")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746751+08:00", comments="Source field: rfc_llm_projection.attempted_at")
    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746757+08:00", comments="Source field: rfc_llm_projection.attempted_at")
    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746771+08:00", comments="Source field: rfc_llm_projection.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746776+08:00", comments="Source field: rfc_llm_projection.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}