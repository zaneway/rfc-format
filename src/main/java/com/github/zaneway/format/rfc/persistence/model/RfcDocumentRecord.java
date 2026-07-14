package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * RFC 文档主表（rfc_document）的持久化记录。
 *
 * <p>对应解析产物 {@code RfcDocument} 的文档级元数据与源溯源字段；
 * 是章节/单元/关系等子表的外键锚点，也是向量投影的主键依据。</p>
 */
public class RfcDocumentRecord implements Serializable {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.193931+08:00", comments = "Source field: rfc_document.id")
  private Long id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195665+08:00", comments = "Source field: rfc_document.document_key")
  private String documentKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195842+08:00", comments = "Source field: rfc_document.rfc_number")
  private String rfcNumber;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195941+08:00", comments = "Source field: rfc_document.title")
  private String title;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196025+08:00", comments = "Source field: rfc_document.publication_date")
  private String publicationDate;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196101+08:00", comments = "Source field: rfc_document.status")
  private String status;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196188+08:00", comments = "Source field: rfc_document.category")
  private String category;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196269+08:00", comments = "Source field: rfc_document.source_file")
  private String sourceFile;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.19635+08:00", comments = "Source field: rfc_document.source_uri")
  private String sourceUri;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196426+08:00", comments = "Source field: rfc_document.source_sha256")
  private String sourceSha256;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196507+08:00", comments = "Source field: rfc_document.parser_version")
  private String parserVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196581+08:00", comments = "Source field: rfc_document.cleaning_policy_version")
  private String cleaningPolicyVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196662+08:00", comments = "Source field: rfc_document.chunking_policy_version")
  private String chunkingPolicyVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196735+08:00", comments = "Source field: rfc_document.created_at")
  private LocalDateTime createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196815+08:00", comments = "Source field: rfc_document.updated_at")
  private LocalDateTime updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196899+08:00", comments = "Source field: rfc_document.obsoletes_json")
  private String obsoletesJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196979+08:00", comments = "Source field: rfc_document.updates_json")
  private String updatesJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.197138+08:00", comments = "Source Table: rfc_document")
  private static final long serialVersionUID = 1L;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195429+08:00", comments = "Source field: rfc_document.id")
  public Long getId() {
    return id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195632+08:00", comments = "Source field: rfc_document.id")
  public void setId(Long id) {
    this.id = id;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195699+08:00", comments = "Source field: rfc_document.document_key")
  public String getDocumentKey() {
    return documentKey;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195811+08:00", comments = "Source field: rfc_document.document_key")
  public void setDocumentKey(String documentKey) {
    this.documentKey = documentKey == null ? null : documentKey.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195879+08:00", comments = "Source field: rfc_document.rfc_number")
  public String getRfcNumber() {
    return rfcNumber;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195915+08:00", comments = "Source field: rfc_document.rfc_number")
  public void setRfcNumber(String rfcNumber) {
    this.rfcNumber = rfcNumber == null ? null : rfcNumber.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195972+08:00", comments = "Source field: rfc_document.title")
  public String getTitle() {
    return title;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.195999+08:00", comments = "Source field: rfc_document.title")
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.19605+08:00", comments = "Source field: rfc_document.publication_date")
  public String getPublicationDate() {
    return publicationDate;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196079+08:00", comments = "Source field: rfc_document.publication_date")
  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate == null ? null : publicationDate.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196138+08:00", comments = "Source field: rfc_document.status")
  public String getStatus() {
    return status;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196167+08:00", comments = "Source field: rfc_document.status")
  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196217+08:00", comments = "Source field: rfc_document.category")
  public String getCategory() {
    return category;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196244+08:00", comments = "Source field: rfc_document.category")
  public void setCategory(String category) {
    this.category = category == null ? null : category.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196298+08:00", comments = "Source field: rfc_document.source_file")
  public String getSourceFile() {
    return sourceFile;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196328+08:00", comments = "Source field: rfc_document.source_file")
  public void setSourceFile(String sourceFile) {
    this.sourceFile = sourceFile == null ? null : sourceFile.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196377+08:00", comments = "Source field: rfc_document.source_uri")
  public String getSourceUri() {
    return sourceUri;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196403+08:00", comments = "Source field: rfc_document.source_uri")
  public void setSourceUri(String sourceUri) {
    this.sourceUri = sourceUri == null ? null : sourceUri.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196453+08:00", comments = "Source field: rfc_document.source_sha256")
  public String getSourceSha256() {
    return sourceSha256;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196485+08:00", comments = "Source field: rfc_document.source_sha256")
  public void setSourceSha256(String sourceSha256) {
    this.sourceSha256 = sourceSha256 == null ? null : sourceSha256.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196533+08:00", comments = "Source field: rfc_document.parser_version")
  public String getParserVersion() {
    return parserVersion;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196559+08:00", comments = "Source field: rfc_document.parser_version")
  public void setParserVersion(String parserVersion) {
    this.parserVersion = parserVersion == null ? null : parserVersion.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196605+08:00", comments = "Source field: rfc_document.cleaning_policy_version")
  public String getCleaningPolicyVersion() {
    return cleaningPolicyVersion;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196633+08:00", comments = "Source field: rfc_document.cleaning_policy_version")
  public void setCleaningPolicyVersion(String cleaningPolicyVersion) {
    this.cleaningPolicyVersion =
        cleaningPolicyVersion == null ? null : cleaningPolicyVersion.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196687+08:00", comments = "Source field: rfc_document.chunking_policy_version")
  public String getChunkingPolicyVersion() {
    return chunkingPolicyVersion;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196714+08:00", comments = "Source field: rfc_document.chunking_policy_version")
  public void setChunkingPolicyVersion(String chunkingPolicyVersion) {
    this.chunkingPolicyVersion =
        chunkingPolicyVersion == null ? null : chunkingPolicyVersion.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196769+08:00", comments = "Source field: rfc_document.created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196796+08:00", comments = "Source field: rfc_document.created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196851+08:00", comments = "Source field: rfc_document.updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196877+08:00", comments = "Source field: rfc_document.updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196922+08:00", comments = "Source field: rfc_document.obsoletes_json")
  public String getObsoletesJson() {
    return obsoletesJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.196947+08:00", comments = "Source field: rfc_document.obsoletes_json")
  public void setObsoletesJson(String obsoletesJson) {
    this.obsoletesJson = obsoletesJson == null ? null : obsoletesJson.trim();
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.197008+08:00", comments = "Source field: rfc_document.updates_json")
  public String getUpdatesJson() {
    return updatesJson;
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.197034+08:00", comments = "Source field: rfc_document.updates_json")
  public void setUpdatesJson(String updatesJson) {
    this.updatesJson = updatesJson == null ? null : updatesJson.trim();
  }
}