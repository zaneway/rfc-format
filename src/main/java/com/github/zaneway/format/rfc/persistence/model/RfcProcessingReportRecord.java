package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcProcessingReportRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743918+08:00", comments="Source field: rfc_processing_report.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743944+08:00", comments="Source field: rfc_processing_report.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743964+08:00", comments="Source field: rfc_processing_report.source_sha256")
    private String sourceSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743984+08:00", comments="Source field: rfc_processing_report.schema_version")
    private String schemaVersion;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744005+08:00", comments="Source field: rfc_processing_report.parser_version")
    private String parserVersion;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744022+08:00", comments="Source field: rfc_processing_report.report_status")
    private String reportStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744041+08:00", comments="Source field: rfc_processing_report.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74406+08:00", comments="Source field: rfc_processing_report.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744079+08:00", comments="Source field: rfc_processing_report.counts_json")
    private String countsJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744133+08:00", comments="Source field: rfc_processing_report.quality_checks_json")
    private String qualityChecksJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74415+08:00", comments="Source field: rfc_processing_report.warnings_json")
    private String warningsJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744176+08:00", comments="Source Table: rfc_processing_report")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743928+08:00", comments="Source field: rfc_processing_report.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743936+08:00", comments="Source field: rfc_processing_report.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74395+08:00", comments="Source field: rfc_processing_report.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743957+08:00", comments="Source field: rfc_processing_report.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74397+08:00", comments="Source field: rfc_processing_report.source_sha256")
    public String getSourceSha256() {
        return sourceSha256;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743979+08:00", comments="Source field: rfc_processing_report.source_sha256")
    public void setSourceSha256(String sourceSha256) {
        this.sourceSha256 = sourceSha256 == null ? null : sourceSha256.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743989+08:00", comments="Source field: rfc_processing_report.schema_version")
    public String getSchemaVersion() {
        return schemaVersion;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743997+08:00", comments="Source field: rfc_processing_report.schema_version")
    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion == null ? null : schemaVersion.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74401+08:00", comments="Source field: rfc_processing_report.parser_version")
    public String getParserVersion() {
        return parserVersion;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744017+08:00", comments="Source field: rfc_processing_report.parser_version")
    public void setParserVersion(String parserVersion) {
        this.parserVersion = parserVersion == null ? null : parserVersion.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744029+08:00", comments="Source field: rfc_processing_report.report_status")
    public String getReportStatus() {
        return reportStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744036+08:00", comments="Source field: rfc_processing_report.report_status")
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus == null ? null : reportStatus.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744047+08:00", comments="Source field: rfc_processing_report.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744054+08:00", comments="Source field: rfc_processing_report.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744067+08:00", comments="Source field: rfc_processing_report.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744073+08:00", comments="Source field: rfc_processing_report.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744084+08:00", comments="Source field: rfc_processing_report.counts_json")
    public String getCountsJson() {
        return countsJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744105+08:00", comments="Source field: rfc_processing_report.counts_json")
    public void setCountsJson(String countsJson) {
        this.countsJson = countsJson == null ? null : countsJson.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74414+08:00", comments="Source field: rfc_processing_report.quality_checks_json")
    public String getQualityChecksJson() {
        return qualityChecksJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744146+08:00", comments="Source field: rfc_processing_report.quality_checks_json")
    public void setQualityChecksJson(String qualityChecksJson) {
        this.qualityChecksJson = qualityChecksJson == null ? null : qualityChecksJson.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744156+08:00", comments="Source field: rfc_processing_report.warnings_json")
    public String getWarningsJson() {
        return warningsJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744169+08:00", comments="Source field: rfc_processing_report.warnings_json")
    public void setWarningsJson(String warningsJson) {
        this.warningsJson = warningsJson == null ? null : warningsJson.trim();
    }
}