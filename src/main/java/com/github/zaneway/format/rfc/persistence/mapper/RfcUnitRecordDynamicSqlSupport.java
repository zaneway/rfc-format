package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcUnitRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73735+08:00", comments="Source Table: rfc_unit")
    public static final RfcUnitRecord rfcUnitRecord = new RfcUnitRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737388+08:00", comments="Source field: rfc_unit.id")
    public static final SqlColumn<Long> id = rfcUnitRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737417+08:00", comments="Source field: rfc_unit.document_id")
    public static final SqlColumn<Long> documentId = rfcUnitRecord.documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737429+08:00", comments="Source field: rfc_unit.unit_key")
    public static final SqlColumn<String> unitKey = rfcUnitRecord.unitKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737441+08:00", comments="Source field: rfc_unit.parent_section_key")
    public static final SqlColumn<String> parentSectionKey = rfcUnitRecord.parentSectionKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73746+08:00", comments="Source field: rfc_unit.unit_type")
    public static final SqlColumn<String> unitType = rfcUnitRecord.unitType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737469+08:00", comments="Source field: rfc_unit.source_start_line")
    public static final SqlColumn<Integer> sourceStartLine = rfcUnitRecord.sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73748+08:00", comments="Source field: rfc_unit.source_end_line")
    public static final SqlColumn<Integer> sourceEndLine = rfcUnitRecord.sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73749+08:00", comments="Source field: rfc_unit.language")
    public static final SqlColumn<String> language = rfcUnitRecord.language;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737499+08:00", comments="Source field: rfc_unit.entity_type")
    public static final SqlColumn<String> entityType = rfcUnitRecord.entityType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737509+08:00", comments="Source field: rfc_unit.entity_name")
    public static final SqlColumn<String> entityName = rfcUnitRecord.entityName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73752+08:00", comments="Source field: rfc_unit.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcUnitRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737532+08:00", comments="Source field: rfc_unit.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcUnitRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737541+08:00", comments="Source field: rfc_unit.content")
    public static final SqlColumn<String> content = rfcUnitRecord.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737551+08:00", comments="Source field: rfc_unit.embedding_text")
    public static final SqlColumn<String> embeddingText = rfcUnitRecord.embeddingText;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73756+08:00", comments="Source field: rfc_unit.semantic_json")
    public static final SqlColumn<String> semanticJson = rfcUnitRecord.semanticJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737568+08:00", comments="Source field: rfc_unit.metadata_json")
    public static final SqlColumn<String> metadataJson = rfcUnitRecord.metadataJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737368+08:00", comments="Source Table: rfc_unit")
    public static final class RfcUnitRecord extends AliasableSqlTable<RfcUnitRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<Long> documentId = column("document_id", JDBCType.BIGINT).withJavaProperty("documentId");

        public final SqlColumn<String> unitKey = column("unit_key", JDBCType.VARCHAR).withJavaProperty("unitKey");

        public final SqlColumn<String> parentSectionKey = column("parent_section_key", JDBCType.VARCHAR).withJavaProperty("parentSectionKey");

        public final SqlColumn<String> unitType = column("unit_type", JDBCType.VARCHAR).withJavaProperty("unitType");

        public final SqlColumn<Integer> sourceStartLine = column("source_start_line", JDBCType.INTEGER).withJavaProperty("sourceStartLine");

        public final SqlColumn<Integer> sourceEndLine = column("source_end_line", JDBCType.INTEGER).withJavaProperty("sourceEndLine");

        public final SqlColumn<String> language = column("language", JDBCType.VARCHAR).withJavaProperty("language");

        public final SqlColumn<String> entityType = column("entity_type", JDBCType.VARCHAR).withJavaProperty("entityType");

        public final SqlColumn<String> entityName = column("entity_name", JDBCType.VARCHAR).withJavaProperty("entityName");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR).withJavaProperty("content");

        public final SqlColumn<String> embeddingText = column("embedding_text", JDBCType.LONGVARCHAR).withJavaProperty("embeddingText");

        public final SqlColumn<String> semanticJson = column("semantic_json", JDBCType.LONGVARCHAR).withJavaProperty("semanticJson");

        public final SqlColumn<String> metadataJson = column("metadata_json", JDBCType.LONGVARCHAR).withJavaProperty("metadataJson");

        public RfcUnitRecord() {
            super("rfc_unit", RfcUnitRecord::new);
        }
    }
}