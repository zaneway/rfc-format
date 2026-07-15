package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcLlmUnitRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745915+08:00", comments="Source Table: rfc_llm_unit")
    public static final RfcLlmUnitRecord rfcLlmUnitRecord = new RfcLlmUnitRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745941+08:00", comments="Source field: rfc_llm_unit.id")
    public static final SqlColumn<Long> id = rfcLlmUnitRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745949+08:00", comments="Source field: rfc_llm_unit.document_id")
    public static final SqlColumn<Long> documentId = rfcLlmUnitRecord.documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745959+08:00", comments="Source field: rfc_llm_unit.unit_id")
    public static final SqlColumn<String> unitId = rfcLlmUnitRecord.unitId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745973+08:00", comments="Source field: rfc_llm_unit.clause_id")
    public static final SqlColumn<String> clauseId = rfcLlmUnitRecord.clauseId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74598+08:00", comments="Source field: rfc_llm_unit.content_type")
    public static final SqlColumn<String> contentType = rfcLlmUnitRecord.contentType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745989+08:00", comments="Source field: rfc_llm_unit.source_start_line")
    public static final SqlColumn<Integer> sourceStartLine = rfcLlmUnitRecord.sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745996+08:00", comments="Source field: rfc_llm_unit.source_end_line")
    public static final SqlColumn<Integer> sourceEndLine = rfcLlmUnitRecord.sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746014+08:00", comments="Source field: rfc_llm_unit.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcLlmUnitRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746026+08:00", comments="Source field: rfc_llm_unit.heading_path_json")
    public static final SqlColumn<String> headingPathJson = rfcLlmUnitRecord.headingPathJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746034+08:00", comments="Source field: rfc_llm_unit.content")
    public static final SqlColumn<String> content = rfcLlmUnitRecord.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746043+08:00", comments="Source field: rfc_llm_unit.extensions_json")
    public static final SqlColumn<String> extensionsJson = rfcLlmUnitRecord.extensionsJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745925+08:00", comments="Source Table: rfc_llm_unit")
    public static final class RfcLlmUnitRecord extends AliasableSqlTable<RfcLlmUnitRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<Long> documentId = column("document_id", JDBCType.BIGINT).withJavaProperty("documentId");

        public final SqlColumn<String> unitId = column("unit_id", JDBCType.VARCHAR).withJavaProperty("unitId");

        public final SqlColumn<String> clauseId = column("clause_id", JDBCType.VARCHAR).withJavaProperty("clauseId");

        public final SqlColumn<String> contentType = column("content_type", JDBCType.VARCHAR).withJavaProperty("contentType");

        public final SqlColumn<Integer> sourceStartLine = column("source_start_line", JDBCType.INTEGER).withJavaProperty("sourceStartLine");

        public final SqlColumn<Integer> sourceEndLine = column("source_end_line", JDBCType.INTEGER).withJavaProperty("sourceEndLine");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<String> headingPathJson = column("heading_path_json", JDBCType.LONGVARCHAR).withJavaProperty("headingPathJson");

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR).withJavaProperty("content");

        public final SqlColumn<String> extensionsJson = column("extensions_json", JDBCType.LONGVARCHAR).withJavaProperty("extensionsJson");

        public RfcLlmUnitRecord() {
            super("rfc_llm_unit", RfcLlmUnitRecord::new);
        }
    }
}