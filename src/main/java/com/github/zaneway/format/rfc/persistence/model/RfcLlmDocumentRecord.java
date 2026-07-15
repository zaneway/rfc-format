package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcLlmDocumentRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744899+08:00", comments="Source field: rfc_llm_document.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744924+08:00", comments="Source field: rfc_llm_document.source_file")
    private String sourceFile;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744943+08:00", comments="Source field: rfc_llm_document.source_sha256")
    private String sourceSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74496+08:00", comments="Source field: rfc_llm_document.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744975+08:00", comments="Source field: rfc_llm_document.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744991+08:00", comments="Source field: rfc_llm_document.document_json")
    private String documentJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745008+08:00", comments="Source field: rfc_llm_document.structured_json")
    private String structuredJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745029+08:00", comments="Source Table: rfc_llm_document")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74491+08:00", comments="Source field: rfc_llm_document.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744918+08:00", comments="Source field: rfc_llm_document.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744929+08:00", comments="Source field: rfc_llm_document.source_file")
    public String getSourceFile() {
        return sourceFile;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744939+08:00", comments="Source field: rfc_llm_document.source_file")
    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile == null ? null : sourceFile.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744948+08:00", comments="Source field: rfc_llm_document.source_sha256")
    public String getSourceSha256() {
        return sourceSha256;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744954+08:00", comments="Source field: rfc_llm_document.source_sha256")
    public void setSourceSha256(String sourceSha256) {
        this.sourceSha256 = sourceSha256 == null ? null : sourceSha256.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744965+08:00", comments="Source field: rfc_llm_document.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744971+08:00", comments="Source field: rfc_llm_document.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744982+08:00", comments="Source field: rfc_llm_document.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744987+08:00", comments="Source field: rfc_llm_document.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744996+08:00", comments="Source field: rfc_llm_document.document_json")
    public String getDocumentJson() {
        return documentJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745003+08:00", comments="Source field: rfc_llm_document.document_json")
    public void setDocumentJson(String documentJson) {
        this.documentJson = documentJson == null ? null : documentJson.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745012+08:00", comments="Source field: rfc_llm_document.structured_json")
    public String getStructuredJson() {
        return structuredJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745021+08:00", comments="Source field: rfc_llm_document.structured_json")
    public void setStructuredJson(String structuredJson) {
        this.structuredJson = structuredJson == null ? null : structuredJson.trim();
    }
}