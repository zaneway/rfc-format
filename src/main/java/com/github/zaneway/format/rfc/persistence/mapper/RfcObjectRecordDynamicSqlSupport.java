package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcObjectRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74085+08:00", comments="Source Table: rfc_object")
    public static final RfcObjectRecord rfcObjectRecord = new RfcObjectRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740881+08:00", comments="Source field: rfc_object.id")
    public static final SqlColumn<Long> id = rfcObjectRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740896+08:00", comments="Source field: rfc_object.document_id")
    public static final SqlColumn<Long> documentId = rfcObjectRecord.documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740908+08:00", comments="Source field: rfc_object.object_key")
    public static final SqlColumn<String> objectKey = rfcObjectRecord.objectKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740917+08:00", comments="Source field: rfc_object.object_type")
    public static final SqlColumn<String> objectType = rfcObjectRecord.objectType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740925+08:00", comments="Source field: rfc_object.object_name")
    public static final SqlColumn<String> objectName = rfcObjectRecord.objectName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740934+08:00", comments="Source field: rfc_object.normalized_value")
    public static final SqlColumn<String> normalizedValue = rfcObjectRecord.normalizedValue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740943+08:00", comments="Source field: rfc_object.source_unit_key")
    public static final SqlColumn<String> sourceUnitKey = rfcObjectRecord.sourceUnitKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740952+08:00", comments="Source field: rfc_object.source_start_line")
    public static final SqlColumn<Integer> sourceStartLine = rfcObjectRecord.sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740961+08:00", comments="Source field: rfc_object.source_end_line")
    public static final SqlColumn<Integer> sourceEndLine = rfcObjectRecord.sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740969+08:00", comments="Source field: rfc_object.source_start_offset")
    public static final SqlColumn<Integer> sourceStartOffset = rfcObjectRecord.sourceStartOffset;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740979+08:00", comments="Source field: rfc_object.source_end_offset")
    public static final SqlColumn<Integer> sourceEndOffset = rfcObjectRecord.sourceEndOffset;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740989+08:00", comments="Source field: rfc_object.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcObjectRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741+08:00", comments="Source field: rfc_object.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcObjectRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741009+08:00", comments="Source field: rfc_object.attributes_json")
    public static final SqlColumn<String> attributesJson = rfcObjectRecord.attributesJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.740864+08:00", comments="Source Table: rfc_object")
    public static final class RfcObjectRecord extends AliasableSqlTable<RfcObjectRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<Long> documentId = column("document_id", JDBCType.BIGINT).withJavaProperty("documentId");

        public final SqlColumn<String> objectKey = column("object_key", JDBCType.VARCHAR).withJavaProperty("objectKey");

        public final SqlColumn<String> objectType = column("object_type", JDBCType.VARCHAR).withJavaProperty("objectType");

        public final SqlColumn<String> objectName = column("object_name", JDBCType.VARCHAR).withJavaProperty("objectName");

        public final SqlColumn<String> normalizedValue = column("normalized_value", JDBCType.VARCHAR).withJavaProperty("normalizedValue");

        public final SqlColumn<String> sourceUnitKey = column("source_unit_key", JDBCType.VARCHAR).withJavaProperty("sourceUnitKey");

        public final SqlColumn<Integer> sourceStartLine = column("source_start_line", JDBCType.INTEGER).withJavaProperty("sourceStartLine");

        public final SqlColumn<Integer> sourceEndLine = column("source_end_line", JDBCType.INTEGER).withJavaProperty("sourceEndLine");

        public final SqlColumn<Integer> sourceStartOffset = column("source_start_offset", JDBCType.INTEGER).withJavaProperty("sourceStartOffset");

        public final SqlColumn<Integer> sourceEndOffset = column("source_end_offset", JDBCType.INTEGER).withJavaProperty("sourceEndOffset");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public final SqlColumn<String> attributesJson = column("attributes_json", JDBCType.LONGVARCHAR).withJavaProperty("attributesJson");

        public RfcObjectRecord() {
            super("rfc_object", RfcObjectRecord::new);
        }
    }
}