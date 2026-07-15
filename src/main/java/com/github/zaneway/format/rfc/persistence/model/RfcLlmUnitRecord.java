package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcLlmUnitRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745691+08:00", comments="Source field: rfc_llm_unit.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745711+08:00", comments="Source field: rfc_llm_unit.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745726+08:00", comments="Source field: rfc_llm_unit.unit_id")
    private String unitId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74574+08:00", comments="Source field: rfc_llm_unit.clause_id")
    private String clauseId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745755+08:00", comments="Source field: rfc_llm_unit.content_type")
    private String contentType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745769+08:00", comments="Source field: rfc_llm_unit.source_start_line")
    private Integer sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745788+08:00", comments="Source field: rfc_llm_unit.source_end_line")
    private Integer sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745802+08:00", comments="Source field: rfc_llm_unit.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745824+08:00", comments="Source field: rfc_llm_unit.heading_path_json")
    private String headingPathJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745861+08:00", comments="Source field: rfc_llm_unit.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745876+08:00", comments="Source field: rfc_llm_unit.extensions_json")
    private String extensionsJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745893+08:00", comments="Source Table: rfc_llm_unit")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.7457+08:00", comments="Source field: rfc_llm_unit.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745707+08:00", comments="Source field: rfc_llm_unit.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745715+08:00", comments="Source field: rfc_llm_unit.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745721+08:00", comments="Source field: rfc_llm_unit.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74573+08:00", comments="Source field: rfc_llm_unit.unit_id")
    public String getUnitId() {
        return unitId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745736+08:00", comments="Source field: rfc_llm_unit.unit_id")
    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745746+08:00", comments="Source field: rfc_llm_unit.clause_id")
    public String getClauseId() {
        return clauseId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745752+08:00", comments="Source field: rfc_llm_unit.clause_id")
    public void setClauseId(String clauseId) {
        this.clauseId = clauseId == null ? null : clauseId.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745759+08:00", comments="Source field: rfc_llm_unit.content_type")
    public String getContentType() {
        return contentType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745766+08:00", comments="Source field: rfc_llm_unit.content_type")
    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745775+08:00", comments="Source field: rfc_llm_unit.source_start_line")
    public Integer getSourceStartLine() {
        return sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745783+08:00", comments="Source field: rfc_llm_unit.source_start_line")
    public void setSourceStartLine(Integer sourceStartLine) {
        this.sourceStartLine = sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745794+08:00", comments="Source field: rfc_llm_unit.source_end_line")
    public Integer getSourceEndLine() {
        return sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745798+08:00", comments="Source field: rfc_llm_unit.source_end_line")
    public void setSourceEndLine(Integer sourceEndLine) {
        this.sourceEndLine = sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745811+08:00", comments="Source field: rfc_llm_unit.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745817+08:00", comments="Source field: rfc_llm_unit.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745827+08:00", comments="Source field: rfc_llm_unit.heading_path_json")
    public String getHeadingPathJson() {
        return headingPathJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745846+08:00", comments="Source field: rfc_llm_unit.heading_path_json")
    public void setHeadingPathJson(String headingPathJson) {
        this.headingPathJson = headingPathJson == null ? null : headingPathJson.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745865+08:00", comments="Source field: rfc_llm_unit.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745872+08:00", comments="Source field: rfc_llm_unit.content")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745882+08:00", comments="Source field: rfc_llm_unit.extensions_json")
    public String getExtensionsJson() {
        return extensionsJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745887+08:00", comments="Source field: rfc_llm_unit.extensions_json")
    public void setExtensionsJson(String extensionsJson) {
        this.extensionsJson = extensionsJson == null ? null : extensionsJson.trim();
    }
}