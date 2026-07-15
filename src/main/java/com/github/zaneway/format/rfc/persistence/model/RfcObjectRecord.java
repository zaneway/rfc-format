package com.github.zaneway.format.rfc.persistence.model;

import jakarta.annotation.Generated;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RfcObjectRecord implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740461+08:00", comments="Source field: rfc_object.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740503+08:00", comments="Source field: rfc_object.document_id")
    private Long documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740526+08:00", comments="Source field: rfc_object.object_key")
    private String objectKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740549+08:00", comments="Source field: rfc_object.object_type")
    private String objectType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740569+08:00", comments="Source field: rfc_object.object_name")
    private String objectName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740597+08:00", comments="Source field: rfc_object.normalized_value")
    private String normalizedValue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74062+08:00", comments="Source field: rfc_object.source_unit_key")
    private String sourceUnitKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740641+08:00", comments="Source field: rfc_object.source_start_line")
    private Integer sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74066+08:00", comments="Source field: rfc_object.source_end_line")
    private Integer sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740688+08:00", comments="Source field: rfc_object.source_start_offset")
    private Integer sourceStartOffset;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740742+08:00", comments="Source field: rfc_object.source_end_offset")
    private Integer sourceEndOffset;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740761+08:00", comments="Source field: rfc_object.created_at")
    private LocalDateTime createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740781+08:00", comments="Source field: rfc_object.updated_at")
    private LocalDateTime updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.7408+08:00", comments="Source field: rfc_object.attributes_json")
    private String attributesJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740824+08:00", comments="Source Table: rfc_object")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740478+08:00", comments="Source field: rfc_object.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740495+08:00", comments="Source field: rfc_object.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740512+08:00", comments="Source field: rfc_object.document_id")
    public Long getDocumentId() {
        return documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74052+08:00", comments="Source field: rfc_object.document_id")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740533+08:00", comments="Source field: rfc_object.object_key")
    public String getObjectKey() {
        return objectKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740543+08:00", comments="Source field: rfc_object.object_key")
    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey == null ? null : objectKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740555+08:00", comments="Source field: rfc_object.object_type")
    public String getObjectType() {
        return objectType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740563+08:00", comments="Source field: rfc_object.object_type")
    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740576+08:00", comments="Source field: rfc_object.object_name")
    public String getObjectName() {
        return objectName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740586+08:00", comments="Source field: rfc_object.object_name")
    public void setObjectName(String objectName) {
        this.objectName = objectName == null ? null : objectName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740605+08:00", comments="Source field: rfc_object.normalized_value")
    public String getNormalizedValue() {
        return normalizedValue;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740614+08:00", comments="Source field: rfc_object.normalized_value")
    public void setNormalizedValue(String normalizedValue) {
        this.normalizedValue = normalizedValue == null ? null : normalizedValue.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740627+08:00", comments="Source field: rfc_object.source_unit_key")
    public String getSourceUnitKey() {
        return sourceUnitKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740635+08:00", comments="Source field: rfc_object.source_unit_key")
    public void setSourceUnitKey(String sourceUnitKey) {
        this.sourceUnitKey = sourceUnitKey == null ? null : sourceUnitKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740648+08:00", comments="Source field: rfc_object.source_start_line")
    public Integer getSourceStartLine() {
        return sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740654+08:00", comments="Source field: rfc_object.source_start_line")
    public void setSourceStartLine(Integer sourceStartLine) {
        this.sourceStartLine = sourceStartLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740666+08:00", comments="Source field: rfc_object.source_end_line")
    public Integer getSourceEndLine() {
        return sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740682+08:00", comments="Source field: rfc_object.source_end_line")
    public void setSourceEndLine(Integer sourceEndLine) {
        this.sourceEndLine = sourceEndLine;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740694+08:00", comments="Source field: rfc_object.source_start_offset")
    public Integer getSourceStartOffset() {
        return sourceStartOffset;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740734+08:00", comments="Source field: rfc_object.source_start_offset")
    public void setSourceStartOffset(Integer sourceStartOffset) {
        this.sourceStartOffset = sourceStartOffset;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740748+08:00", comments="Source field: rfc_object.source_end_offset")
    public Integer getSourceEndOffset() {
        return sourceEndOffset;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740755+08:00", comments="Source field: rfc_object.source_end_offset")
    public void setSourceEndOffset(Integer sourceEndOffset) {
        this.sourceEndOffset = sourceEndOffset;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740769+08:00", comments="Source field: rfc_object.created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740776+08:00", comments="Source field: rfc_object.created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740788+08:00", comments="Source field: rfc_object.updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740795+08:00", comments="Source field: rfc_object.updated_at")
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740807+08:00", comments="Source field: rfc_object.attributes_json")
    public String getAttributesJson() {
        return attributesJson;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740815+08:00", comments="Source field: rfc_object.attributes_json")
    public void setAttributesJson(String attributesJson) {
        this.attributesJson = attributesJson == null ? null : attributesJson.trim();
    }
}