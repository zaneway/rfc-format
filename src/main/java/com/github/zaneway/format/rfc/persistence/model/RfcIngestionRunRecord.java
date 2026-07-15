package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcIngestionRunRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742811+08:00", comments="Source field: rfc_ingestion_run.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742845+08:00", comments="Source field: rfc_ingestion_run.run_key")
    private String runKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742945+08:00", comments="Source field: rfc_ingestion_run.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742968+08:00", comments="Source field: rfc_ingestion_run.input_path")
    private String inputPath;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742988+08:00", comments="Source field: rfc_ingestion_run.input_sha256")
    private String inputSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743006+08:00", comments="Source field: rfc_ingestion_run.status")
    private String status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743027+08:00", comments="Source field: rfc_ingestion_run.started_at")
    private LocalDateTime startedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743046+08:00", comments="Source field: rfc_ingestion_run.finished_at")
    private LocalDateTime finishedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743064+08:00", comments="Source field: rfc_ingestion_run.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743081+08:00", comments="Source field: rfc_ingestion_run.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743099+08:00", comments="Source field: rfc_ingestion_run.statistics_json")
    private String statisticsJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743118+08:00", comments="Source field: rfc_ingestion_run.error_message")
    private String errorMessage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743139+08:00", comments="Source Table: rfc_ingestion_run")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742822+08:00", comments="Source field: rfc_ingestion_run.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742833+08:00", comments="Source field: rfc_ingestion_run.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742863+08:00", comments="Source field: rfc_ingestion_run.run_key")
    public String getRunKey() {
        return runKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742935+08:00", comments="Source field: rfc_ingestion_run.run_key")
    public void setRunKey(String runKey) {
        this.runKey = runKey == null ? null : runKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742954+08:00", comments="Source field: rfc_ingestion_run.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742962+08:00", comments="Source field: rfc_ingestion_run.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742973+08:00", comments="Source field: rfc_ingestion_run.input_path")
    public String getInputPath() {
        return inputPath;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742981+08:00", comments="Source field: rfc_ingestion_run.input_path")
    public void setInputPath(String inputPath) {
        this.inputPath = inputPath == null ? null : inputPath.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742994+08:00", comments="Source field: rfc_ingestion_run.input_sha256")
    public String getInputSha256() {
        return inputSha256;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743001+08:00", comments="Source field: rfc_ingestion_run.input_sha256")
    public void setInputSha256(String inputSha256) {
        this.inputSha256 = inputSha256 == null ? null : inputSha256.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743013+08:00", comments="Source field: rfc_ingestion_run.status")
    public String getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743021+08:00", comments="Source field: rfc_ingestion_run.status")
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743033+08:00", comments="Source field: rfc_ingestion_run.started_at")
    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743041+08:00", comments="Source field: rfc_ingestion_run.started_at")
    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743052+08:00", comments="Source field: rfc_ingestion_run.finished_at")
    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743057+08:00", comments="Source field: rfc_ingestion_run.finished_at")
    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74307+08:00", comments="Source field: rfc_ingestion_run.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743076+08:00", comments="Source field: rfc_ingestion_run.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743088+08:00", comments="Source field: rfc_ingestion_run.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743094+08:00", comments="Source field: rfc_ingestion_run.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743105+08:00", comments="Source field: rfc_ingestion_run.statistics_json")
    public String getStatisticsJson() {
        return statisticsJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743113+08:00", comments="Source field: rfc_ingestion_run.statistics_json")
    public void setStatisticsJson(String statisticsJson) {
        this.statisticsJson = statisticsJson == null ? null : statisticsJson.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743123+08:00", comments="Source field: rfc_ingestion_run.error_message")
    public String getErrorMessage() {
        return errorMessage;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74313+08:00", comments="Source field: rfc_ingestion_run.error_message")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }
}