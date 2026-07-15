package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcSectionRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735418+08:00", comments="Source field: rfc_section.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73546+08:00", comments="Source field: rfc_section.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735489+08:00", comments="Source field: rfc_section.section_key")
    private String sectionKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735515+08:00", comments="Source field: rfc_section.parent_section_key")
    private String parentSectionKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735542+08:00", comments="Source field: rfc_section.title")
    private String title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735566+08:00", comments="Source field: rfc_section.section_type")
    private String sectionType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735595+08:00", comments="Source field: rfc_section.source_start_line")
    private Integer sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735618+08:00", comments="Source field: rfc_section.source_end_line")
    private Integer sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735642+08:00", comments="Source field: rfc_section.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735672+08:00", comments="Source field: rfc_section.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735701+08:00", comments="Source field: rfc_section.section_path_json")
    private String sectionPathJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73573+08:00", comments="Source Table: rfc_section")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735435+08:00", comments="Source field: rfc_section.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735449+08:00", comments="Source field: rfc_section.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735471+08:00", comments="Source field: rfc_section.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735481+08:00", comments="Source field: rfc_section.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735498+08:00", comments="Source field: rfc_section.section_key")
    public String getSectionKey() {
        return sectionKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735508+08:00", comments="Source field: rfc_section.section_key")
    public void setSectionKey(String sectionKey) {
        this.sectionKey = sectionKey == null ? null : sectionKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735523+08:00", comments="Source field: rfc_section.parent_section_key")
    public String getParentSectionKey() {
        return parentSectionKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735535+08:00", comments="Source field: rfc_section.parent_section_key")
    public void setParentSectionKey(String parentSectionKey) {
        this.parentSectionKey = parentSectionKey == null ? null : parentSectionKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73555+08:00", comments="Source field: rfc_section.title")
    public String getTitle() {
        return title;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735559+08:00", comments="Source field: rfc_section.title")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735577+08:00", comments="Source field: rfc_section.section_type")
    public String getSectionType() {
        return sectionType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735587+08:00", comments="Source field: rfc_section.section_type")
    public void setSectionType(String sectionType) {
        this.sectionType = sectionType == null ? null : sectionType.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735603+08:00", comments="Source field: rfc_section.source_start_line")
    public Integer getSourceStartLine() {
        return sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735611+08:00", comments="Source field: rfc_section.source_start_line")
    public void setSourceStartLine(Integer sourceStartLine) {
        this.sourceStartLine = sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735626+08:00", comments="Source field: rfc_section.source_end_line")
    public Integer getSourceEndLine() {
        return sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735634+08:00", comments="Source field: rfc_section.source_end_line")
    public void setSourceEndLine(Integer sourceEndLine) {
        this.sourceEndLine = sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735657+08:00", comments="Source field: rfc_section.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735666+08:00", comments="Source field: rfc_section.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735684+08:00", comments="Source field: rfc_section.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735693+08:00", comments="Source field: rfc_section.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735708+08:00", comments="Source field: rfc_section.section_path_json")
    public String getSectionPathJson() {
        return sectionPathJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73572+08:00", comments="Source field: rfc_section.section_path_json")
    public void setSectionPathJson(String sectionPathJson) {
        this.sectionPathJson = sectionPathJson == null ? null : sectionPathJson.trim();
    }
}