package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcDocumentRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.721023+08:00", comments="Source field: rfc_document.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722513+08:00", comments="Source field: rfc_document.document_key")
    private String documentKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722693+08:00", comments="Source field: rfc_document.rfc_number")
    private String rfcNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722779+08:00", comments="Source field: rfc_document.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722863+08:00", comments="Source field: rfc_document.publication_date")
    private String publicationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722942+08:00", comments="Source field: rfc_document.status")
    private String status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723029+08:00", comments="Source field: rfc_document.category")
    private String category;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723112+08:00", comments="Source field: rfc_document.source_file")
    private String sourceFile;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723192+08:00", comments="Source field: rfc_document.source_uri")
    private String sourceUri;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723271+08:00", comments="Source field: rfc_document.source_sha256")
    private String sourceSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723346+08:00", comments="Source field: rfc_document.parser_version")
    private String parserVersion;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.72343+08:00", comments="Source field: rfc_document.cleaning_policy_version")
    private String cleaningPolicyVersion;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.72351+08:00", comments="Source field: rfc_document.chunking_policy_version")
    private String chunkingPolicyVersion;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.72359+08:00", comments="Source field: rfc_document.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723671+08:00", comments="Source field: rfc_document.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723748+08:00", comments="Source field: rfc_document.obsoletes_json")
    private String obsoletesJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723818+08:00", comments="Source field: rfc_document.updates_json")
    private String updatesJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723961+08:00", comments="Source Table: rfc_document")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722304+08:00", comments="Source field: rfc_document.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.72248+08:00", comments="Source field: rfc_document.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722548+08:00", comments="Source field: rfc_document.document_key")
    public String getDocumentKey() {
        return documentKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722655+08:00", comments="Source field: rfc_document.document_key")
    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey == null ? null : documentKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.72272+08:00", comments="Source field: rfc_document.rfc_number")
    public String getRfcNumber() {
        return rfcNumber;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722751+08:00", comments="Source field: rfc_document.rfc_number")
    public void setRfcNumber(String rfcNumber) {
        this.rfcNumber = rfcNumber == null ? null : rfcNumber.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722807+08:00", comments="Source field: rfc_document.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722834+08:00", comments="Source field: rfc_document.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722888+08:00", comments="Source field: rfc_document.publication_date")
    public String getPublicationDate() {
        return publicationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.72292+08:00", comments="Source field: rfc_document.publication_date")
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate == null ? null : publicationDate.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.722975+08:00", comments="Source field: rfc_document.status")
    public String getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723007+08:00", comments="Source field: rfc_document.status")
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723058+08:00", comments="Source field: rfc_document.category")
    public String getCategory() {
        return category;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723084+08:00", comments="Source field: rfc_document.category")
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723139+08:00", comments="Source field: rfc_document.source_file")
    public String getSourceFile() {
        return sourceFile;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723168+08:00", comments="Source field: rfc_document.source_file")
    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile == null ? null : sourceFile.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723217+08:00", comments="Source field: rfc_document.source_uri")
    public String getSourceUri() {
        return sourceUri;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723247+08:00", comments="Source field: rfc_document.source_uri")
    public void setSourceUri(String sourceUri) {
        this.sourceUri = sourceUri == null ? null : sourceUri.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723297+08:00", comments="Source field: rfc_document.source_sha256")
    public String getSourceSha256() {
        return sourceSha256;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723322+08:00", comments="Source field: rfc_document.source_sha256")
    public void setSourceSha256(String sourceSha256) {
        this.sourceSha256 = sourceSha256 == null ? null : sourceSha256.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723374+08:00", comments="Source field: rfc_document.parser_version")
    public String getParserVersion() {
        return parserVersion;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723401+08:00", comments="Source field: rfc_document.parser_version")
    public void setParserVersion(String parserVersion) {
        this.parserVersion = parserVersion == null ? null : parserVersion.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723457+08:00", comments="Source field: rfc_document.cleaning_policy_version")
    public String getCleaningPolicyVersion() {
        return cleaningPolicyVersion;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723485+08:00", comments="Source field: rfc_document.cleaning_policy_version")
    public void setCleaningPolicyVersion(String cleaningPolicyVersion) {
        this.cleaningPolicyVersion = cleaningPolicyVersion == null ? null : cleaningPolicyVersion.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723535+08:00", comments="Source field: rfc_document.chunking_policy_version")
    public String getChunkingPolicyVersion() {
        return chunkingPolicyVersion;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723568+08:00", comments="Source field: rfc_document.chunking_policy_version")
    public void setChunkingPolicyVersion(String chunkingPolicyVersion) {
        this.chunkingPolicyVersion = chunkingPolicyVersion == null ? null : chunkingPolicyVersion.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723621+08:00", comments="Source field: rfc_document.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723648+08:00", comments="Source field: rfc_document.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723699+08:00", comments="Source field: rfc_document.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723727+08:00", comments="Source field: rfc_document.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723771+08:00", comments="Source field: rfc_document.obsoletes_json")
    public String getObsoletesJson() {
        return obsoletesJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723795+08:00", comments="Source field: rfc_document.obsoletes_json")
    public void setObsoletesJson(String obsoletesJson) {
        this.obsoletesJson = obsoletesJson == null ? null : obsoletesJson.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723844+08:00", comments="Source field: rfc_document.updates_json")
    public String getUpdatesJson() {
        return updatesJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.723868+08:00", comments="Source field: rfc_document.updates_json")
    public void setUpdatesJson(String updatesJson) {
        this.updatesJson = updatesJson == null ? null : updatesJson.trim();
    }
}