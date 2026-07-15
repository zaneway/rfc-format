package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcLlmDocumentRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74505+08:00", comments="Source Table: rfc_llm_document")
    public static final RfcLlmDocumentRecord rfcLlmDocumentRecord = new RfcLlmDocumentRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745079+08:00", comments="Source field: rfc_llm_document.id")
    public static final SqlColumn<Long> id = rfcLlmDocumentRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74509+08:00", comments="Source field: rfc_llm_document.source_file")
    public static final SqlColumn<String> sourceFile = rfcLlmDocumentRecord.sourceFile;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745098+08:00", comments="Source field: rfc_llm_document.source_sha256")
    public static final SqlColumn<String> sourceSha256 = rfcLlmDocumentRecord.sourceSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745106+08:00", comments="Source field: rfc_llm_document.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcLlmDocumentRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745116+08:00", comments="Source field: rfc_llm_document.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcLlmDocumentRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745124+08:00", comments="Source field: rfc_llm_document.document_json")
    public static final SqlColumn<String> documentJson = rfcLlmDocumentRecord.documentJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745133+08:00", comments="Source field: rfc_llm_document.structured_json")
    public static final SqlColumn<String> structuredJson = rfcLlmDocumentRecord.structuredJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745063+08:00", comments="Source Table: rfc_llm_document")
    public static final class RfcLlmDocumentRecord extends AliasableSqlTable<RfcLlmDocumentRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<String> sourceFile = column("source_file", JDBCType.VARCHAR).withJavaProperty("sourceFile");

        public final SqlColumn<String> sourceSha256 = column("source_sha256", JDBCType.CHAR).withJavaProperty("sourceSha256");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public final SqlColumn<String> documentJson = column("document_json", JDBCType.LONGVARCHAR).withJavaProperty("documentJson");

        public final SqlColumn<String> structuredJson = column("structured_json", JDBCType.LONGVARCHAR).withJavaProperty("structuredJson");

        public RfcLlmDocumentRecord() {
            super("rfc_llm_document", RfcLlmDocumentRecord::new);
        }
    }
}