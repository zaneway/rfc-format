package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcRelationRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73846+08:00", comments="Source field: rfc_relation.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738511+08:00", comments="Source field: rfc_relation.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738541+08:00", comments="Source field: rfc_relation.relation_key")
    private String relationKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738562+08:00", comments="Source field: rfc_relation.relation_type")
    private String relationType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738589+08:00", comments="Source field: rfc_relation.from_kind")
    private String fromKind;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738777+08:00", comments="Source field: rfc_relation.from_identifier")
    private String fromIdentifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73897+08:00", comments="Source field: rfc_relation.to_kind")
    private String toKind;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739058+08:00", comments="Source field: rfc_relation.to_identifier")
    private String toIdentifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739091+08:00", comments="Source field: rfc_relation.source_section_key")
    private String sourceSectionKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739118+08:00", comments="Source field: rfc_relation.source_start_line")
    private Integer sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739159+08:00", comments="Source field: rfc_relation.source_end_line")
    private Integer sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739183+08:00", comments="Source field: rfc_relation.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739208+08:00", comments="Source field: rfc_relation.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739238+08:00", comments="Source field: rfc_relation.attributes_json")
    private String attributesJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739265+08:00", comments="Source Table: rfc_relation")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738491+08:00", comments="Source field: rfc_relation.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738503+08:00", comments="Source field: rfc_relation.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738524+08:00", comments="Source field: rfc_relation.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738534+08:00", comments="Source field: rfc_relation.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738547+08:00", comments="Source field: rfc_relation.relation_key")
    public String getRelationKey() {
        return relationKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738557+08:00", comments="Source field: rfc_relation.relation_key")
    public void setRelationKey(String relationKey) {
        this.relationKey = relationKey == null ? null : relationKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738573+08:00", comments="Source field: rfc_relation.relation_type")
    public String getRelationType() {
        return relationType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738582+08:00", comments="Source field: rfc_relation.relation_type")
    public void setRelationType(String relationType) {
        this.relationType = relationType == null ? null : relationType.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738623+08:00", comments="Source field: rfc_relation.from_kind")
    public String getFromKind() {
        return fromKind;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738651+08:00", comments="Source field: rfc_relation.from_kind")
    public void setFromKind(String fromKind) {
        this.fromKind = fromKind == null ? null : fromKind.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738879+08:00", comments="Source field: rfc_relation.from_identifier")
    public String getFromIdentifier() {
        return fromIdentifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738937+08:00", comments="Source field: rfc_relation.from_identifier")
    public void setFromIdentifier(String fromIdentifier) {
        this.fromIdentifier = fromIdentifier == null ? null : fromIdentifier.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739003+08:00", comments="Source field: rfc_relation.to_kind")
    public String getToKind() {
        return toKind;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739044+08:00", comments="Source field: rfc_relation.to_kind")
    public void setToKind(String toKind) {
        this.toKind = toKind == null ? null : toKind.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73907+08:00", comments="Source field: rfc_relation.to_identifier")
    public String getToIdentifier() {
        return toIdentifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739084+08:00", comments="Source field: rfc_relation.to_identifier")
    public void setToIdentifier(String toIdentifier) {
        this.toIdentifier = toIdentifier == null ? null : toIdentifier.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.7391+08:00", comments="Source field: rfc_relation.source_section_key")
    public String getSourceSectionKey() {
        return sourceSectionKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73911+08:00", comments="Source field: rfc_relation.source_section_key")
    public void setSourceSectionKey(String sourceSectionKey) {
        this.sourceSectionKey = sourceSectionKey == null ? null : sourceSectionKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739126+08:00", comments="Source field: rfc_relation.source_start_line")
    public Integer getSourceStartLine() {
        return sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739152+08:00", comments="Source field: rfc_relation.source_start_line")
    public void setSourceStartLine(Integer sourceStartLine) {
        this.sourceStartLine = sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739169+08:00", comments="Source field: rfc_relation.source_end_line")
    public Integer getSourceEndLine() {
        return sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739176+08:00", comments="Source field: rfc_relation.source_end_line")
    public void setSourceEndLine(Integer sourceEndLine) {
        this.sourceEndLine = sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739191+08:00", comments="Source field: rfc_relation.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739201+08:00", comments="Source field: rfc_relation.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73922+08:00", comments="Source field: rfc_relation.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73923+08:00", comments="Source field: rfc_relation.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739245+08:00", comments="Source field: rfc_relation.attributes_json")
    public String getAttributesJson() {
        return attributesJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739254+08:00", comments="Source field: rfc_relation.attributes_json")
    public void setAttributesJson(String attributesJson) {
        this.attributesJson = attributesJson == null ? null : attributesJson.trim();
    }
}