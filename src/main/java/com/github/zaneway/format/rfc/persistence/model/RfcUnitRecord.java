package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcUnitRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736868+08:00", comments="Source field: rfc_unit.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736907+08:00", comments="Source field: rfc_unit.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736933+08:00", comments="Source field: rfc_unit.unit_key")
    private String unitKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736984+08:00", comments="Source field: rfc_unit.parent_section_key")
    private String parentSectionKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737007+08:00", comments="Source field: rfc_unit.unit_type")
    private String unitType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737035+08:00", comments="Source field: rfc_unit.source_start_line")
    private Integer sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737058+08:00", comments="Source field: rfc_unit.source_end_line")
    private Integer sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73708+08:00", comments="Source field: rfc_unit.language")
    private String language;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737104+08:00", comments="Source field: rfc_unit.entity_type")
    private String entityType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737127+08:00", comments="Source field: rfc_unit.entity_name")
    private String entityName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73715+08:00", comments="Source field: rfc_unit.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737177+08:00", comments="Source field: rfc_unit.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.7372+08:00", comments="Source field: rfc_unit.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737227+08:00", comments="Source field: rfc_unit.embedding_text")
    private String embeddingText;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737249+08:00", comments="Source field: rfc_unit.semantic_json")
    private String semanticJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737271+08:00", comments="Source field: rfc_unit.metadata_json")
    private String metadataJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737302+08:00", comments="Source Table: rfc_unit")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736884+08:00", comments="Source field: rfc_unit.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736897+08:00", comments="Source field: rfc_unit.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736915+08:00", comments="Source field: rfc_unit.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736926+08:00", comments="Source field: rfc_unit.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736964+08:00", comments="Source field: rfc_unit.unit_key")
    public String getUnitKey() {
        return unitKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736975+08:00", comments="Source field: rfc_unit.unit_key")
    public void setUnitKey(String unitKey) {
        this.unitKey = unitKey == null ? null : unitKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.736992+08:00", comments="Source field: rfc_unit.parent_section_key")
    public String getParentSectionKey() {
        return parentSectionKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737001+08:00", comments="Source field: rfc_unit.parent_section_key")
    public void setParentSectionKey(String parentSectionKey) {
        this.parentSectionKey = parentSectionKey == null ? null : parentSectionKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737016+08:00", comments="Source field: rfc_unit.unit_type")
    public String getUnitType() {
        return unitType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737024+08:00", comments="Source field: rfc_unit.unit_type")
    public void setUnitType(String unitType) {
        this.unitType = unitType == null ? null : unitType.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737043+08:00", comments="Source field: rfc_unit.source_start_line")
    public Integer getSourceStartLine() {
        return sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737051+08:00", comments="Source field: rfc_unit.source_start_line")
    public void setSourceStartLine(Integer sourceStartLine) {
        this.sourceStartLine = sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737066+08:00", comments="Source field: rfc_unit.source_end_line")
    public Integer getSourceEndLine() {
        return sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737074+08:00", comments="Source field: rfc_unit.source_end_line")
    public void setSourceEndLine(Integer sourceEndLine) {
        this.sourceEndLine = sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737089+08:00", comments="Source field: rfc_unit.language")
    public String getLanguage() {
        return language;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737097+08:00", comments="Source field: rfc_unit.language")
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737111+08:00", comments="Source field: rfc_unit.entity_type")
    public String getEntityType() {
        return entityType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737121+08:00", comments="Source field: rfc_unit.entity_type")
    public void setEntityType(String entityType) {
        this.entityType = entityType == null ? null : entityType.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737135+08:00", comments="Source field: rfc_unit.entity_name")
    public String getEntityName() {
        return entityName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737143+08:00", comments="Source field: rfc_unit.entity_name")
    public void setEntityName(String entityName) {
        this.entityName = entityName == null ? null : entityName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73716+08:00", comments="Source field: rfc_unit.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737168+08:00", comments="Source field: rfc_unit.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737185+08:00", comments="Source field: rfc_unit.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737194+08:00", comments="Source field: rfc_unit.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737207+08:00", comments="Source field: rfc_unit.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737215+08:00", comments="Source field: rfc_unit.content")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737234+08:00", comments="Source field: rfc_unit.embedding_text")
    public String getEmbeddingText() {
        return embeddingText;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737243+08:00", comments="Source field: rfc_unit.embedding_text")
    public void setEmbeddingText(String embeddingText) {
        this.embeddingText = embeddingText == null ? null : embeddingText.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737257+08:00", comments="Source field: rfc_unit.semantic_json")
    public String getSemanticJson() {
        return semanticJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737265+08:00", comments="Source field: rfc_unit.semantic_json")
    public void setSemanticJson(String semanticJson) {
        this.semanticJson = semanticJson == null ? null : semanticJson.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737278+08:00", comments="Source field: rfc_unit.metadata_json")
    public String getMetadataJson() {
        return metadataJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737288+08:00", comments="Source field: rfc_unit.metadata_json")
    public void setMetadataJson(String metadataJson) {
        this.metadataJson = metadataJson == null ? null : metadataJson.trim();
    }
}