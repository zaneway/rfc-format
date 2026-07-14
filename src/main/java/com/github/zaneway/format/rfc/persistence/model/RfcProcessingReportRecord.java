package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 处理报告表（rfc_processing_report）的持久化记录。
 *
 * <p>对应解析流水线的 {@code processing-report.json} 产物，记录各结构计数、质量检查与警告；
 * 与 {@code RfcDocument} 一一关联，用于入库后质量审计与幂等校验（{@code source_sha256}）。</p>
 */
public class RfcProcessingReportRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218581+08:00", comments = "Source field: rfc_processing_report.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218607+08:00", comments = "Source field: rfc_processing_report.document_id")
  private Long documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218625+08:00", comments = "Source field: rfc_processing_report.source_sha256")
  private String sourceSha256;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218669+08:00", comments = "Source field: rfc_processing_report.schema_version")
  private String schemaVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21869+08:00", comments = "Source field: rfc_processing_report.parser_version")
  private String parserVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218706+08:00", comments = "Source field: rfc_processing_report.report_status")
  private String reportStatus;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218722+08:00", comments = "Source field: rfc_processing_report.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218742+08:00", comments = "Source field: rfc_processing_report.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218756+08:00", comments = "Source field: rfc_processing_report.counts_json")
  private String countsJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218772+08:00", comments = "Source field: rfc_processing_report.quality_checks_json")
  private String qualityChecksJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218789+08:00", comments = "Source field: rfc_processing_report.warnings_json")
  private String warningsJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218813+08:00", comments = "Source Table: rfc_processing_report")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218592+08:00", comments = "Source field: rfc_processing_report.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.2186+08:00", comments = "Source field: rfc_processing_report.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218613+08:00", comments = "Source field: rfc_processing_report.document_id")
  public Long getDocumentId() {
    return documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21862+08:00", comments = "Source field: rfc_processing_report.document_id")
  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21863+08:00", comments = "Source field: rfc_processing_report.source_sha256")
  public String getSourceSha256() {
    return sourceSha256;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218658+08:00", comments = "Source field: rfc_processing_report.source_sha256")
  public void setSourceSha256(String sourceSha256) {
    this.sourceSha256 = sourceSha256 == null ? null : sourceSha256.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218678+08:00", comments = "Source field: rfc_processing_report.schema_version")
  public String getSchemaVersion() {
    return schemaVersion;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218685+08:00", comments = "Source field: rfc_processing_report.schema_version")
  public void setSchemaVersion(String schemaVersion) {
    this.schemaVersion = schemaVersion == null ? null : schemaVersion.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218695+08:00", comments = "Source field: rfc_processing_report.parser_version")
  public String getParserVersion() {
    return parserVersion;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218701+08:00", comments = "Source field: rfc_processing_report.parser_version")
  public void setParserVersion(String parserVersion) {
    this.parserVersion = parserVersion == null ? null : parserVersion.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218711+08:00", comments = "Source field: rfc_processing_report.report_status")
  public String getReportStatus() {
    return reportStatus;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218717+08:00", comments = "Source field: rfc_processing_report.report_status")
  public void setReportStatus(String reportStatus) {
    this.reportStatus = reportStatus == null ? null : reportStatus.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218731+08:00", comments = "Source field: rfc_processing_report.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218736+08:00", comments = "Source field: rfc_processing_report.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218747+08:00", comments = "Source field: rfc_processing_report.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218752+08:00", comments = "Source field: rfc_processing_report.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218761+08:00", comments = "Source field: rfc_processing_report.counts_json")
  public String getCountsJson() {
    return countsJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218768+08:00", comments = "Source field: rfc_processing_report.counts_json")
  public void setCountsJson(String countsJson) {
    this.countsJson = countsJson == null ? null : countsJson.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218777+08:00", comments = "Source field: rfc_processing_report.quality_checks_json")
  public String getQualityChecksJson() {
    return qualityChecksJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218785+08:00", comments = "Source field: rfc_processing_report.quality_checks_json")
  public void setQualityChecksJson(String qualityChecksJson) {
    this.qualityChecksJson = qualityChecksJson == null ? null : qualityChecksJson.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218795+08:00", comments = "Source field: rfc_processing_report.warnings_json")
  public String getWarningsJson() {
    return warningsJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218803+08:00", comments = "Source field: rfc_processing_report.warnings_json")
  public void setWarningsJson(String warningsJson) {
    this.warningsJson = warningsJson == null ? null : warningsJson.trim();
  }
}