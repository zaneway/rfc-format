package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 导入运行审计表（rfc_ingestion_run）的持久化记录。
 *
 * <p>记录每次 {@code RfcIngestionService} 导入尝试的生命周期：输入路径、哈希、状态与时间戳；
 * 关联已入库的 {@code document_id}，用于运维排障与重复导入检测。</p>
 */
public class RfcIngestionRunRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217269+08:00", comments = "Source field: rfc_ingestion_run.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217301+08:00", comments = "Source field: rfc_ingestion_run.run_key")
  private String runKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217399+08:00", comments = "Source field: rfc_ingestion_run.document_id")
  private Long documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217421+08:00", comments = "Source field: rfc_ingestion_run.input_path")
  private String inputPath;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217443+08:00", comments = "Source field: rfc_ingestion_run.input_sha256")
  private String inputSha256;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217466+08:00", comments = "Source field: rfc_ingestion_run.status")
  private String status;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217485+08:00", comments = "Source field: rfc_ingestion_run.started_at")
  private LocalDateTime startedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217506+08:00", comments = "Source field: rfc_ingestion_run.finished_at")
  private LocalDateTime finishedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217525+08:00", comments = "Source field: rfc_ingestion_run.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217542+08:00", comments = "Source field: rfc_ingestion_run.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21756+08:00", comments = "Source field: rfc_ingestion_run.statistics_json")
  private String statisticsJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217578+08:00", comments = "Source field: rfc_ingestion_run.error_message")
  private String errorMessage;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217599+08:00", comments = "Source Table: rfc_ingestion_run")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217281+08:00", comments = "Source field: rfc_ingestion_run.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21729+08:00", comments = "Source field: rfc_ingestion_run.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217327+08:00", comments = "Source field: rfc_ingestion_run.run_key")
  public String getRunKey() {
    return runKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217389+08:00", comments = "Source field: rfc_ingestion_run.run_key")
  public void setRunKey(String runKey) {
    this.runKey = runKey == null ? null : runKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217407+08:00", comments = "Source field: rfc_ingestion_run.document_id")
  public Long getDocumentId() {
    return documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217415+08:00", comments = "Source field: rfc_ingestion_run.document_id")
  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217428+08:00", comments = "Source field: rfc_ingestion_run.input_path")
  public String getInputPath() {
    return inputPath;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217437+08:00", comments = "Source field: rfc_ingestion_run.input_path")
  public void setInputPath(String inputPath) {
    this.inputPath = inputPath == null ? null : inputPath.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217452+08:00", comments = "Source field: rfc_ingestion_run.input_sha256")
  public String getInputSha256() {
    return inputSha256;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21746+08:00", comments = "Source field: rfc_ingestion_run.input_sha256")
  public void setInputSha256(String inputSha256) {
    this.inputSha256 = inputSha256 == null ? null : inputSha256.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217472+08:00", comments = "Source field: rfc_ingestion_run.status")
  public String getStatus() {
    return status;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21748+08:00", comments = "Source field: rfc_ingestion_run.status")
  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217493+08:00", comments = "Source field: rfc_ingestion_run.started_at")
  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217499+08:00", comments = "Source field: rfc_ingestion_run.started_at")
  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217513+08:00", comments = "Source field: rfc_ingestion_run.finished_at")
  public LocalDateTime getFinishedAt() {
    return finishedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21752+08:00", comments = "Source field: rfc_ingestion_run.finished_at")
  public void setFinishedAt(LocalDateTime finishedAt) {
    this.finishedAt = finishedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217531+08:00", comments = "Source field: rfc_ingestion_run.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217537+08:00", comments = "Source field: rfc_ingestion_run.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217549+08:00", comments = "Source field: rfc_ingestion_run.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217555+08:00", comments = "Source field: rfc_ingestion_run.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217566+08:00", comments = "Source field: rfc_ingestion_run.statistics_json")
  public String getStatisticsJson() {
    return statisticsJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217573+08:00", comments = "Source field: rfc_ingestion_run.statistics_json")
  public void setStatisticsJson(String statisticsJson) {
    this.statisticsJson = statisticsJson == null ? null : statisticsJson.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217584+08:00", comments = "Source field: rfc_ingestion_run.error_message")
  public String getErrorMessage() {
    return errorMessage;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217591+08:00", comments = "Source field: rfc_ingestion_run.error_message")
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage == null ? null : errorMessage.trim();
  }
}