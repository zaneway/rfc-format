package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcLlmProjectionRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746803+08:00", comments="Source Table: rfc_llm_projection")
    public static final RfcLlmProjectionRecord rfcLlmProjectionRecord = new RfcLlmProjectionRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746828+08:00", comments="Source field: rfc_llm_projection.document_id")
    public static final SqlColumn<Long> documentId = rfcLlmProjectionRecord.documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746837+08:00", comments="Source field: rfc_llm_projection.status")
    public static final SqlColumn<String> status = rfcLlmProjectionRecord.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746846+08:00", comments="Source field: rfc_llm_projection.vector_count")
    public static final SqlColumn<Integer> vectorCount = rfcLlmProjectionRecord.vectorCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746854+08:00", comments="Source field: rfc_llm_projection.error_message")
    public static final SqlColumn<String> errorMessage = rfcLlmProjectionRecord.errorMessage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746862+08:00", comments="Source field: rfc_llm_projection.attempted_at")
    public static final SqlColumn<LocalDateTime> attemptedAt = rfcLlmProjectionRecord.attemptedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746871+08:00", comments="Source field: rfc_llm_projection.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcLlmProjectionRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746813+08:00", comments="Source Table: rfc_llm_projection")
    public static final class RfcLlmProjectionRecord extends AliasableSqlTable<RfcLlmProjectionRecord> {
        public final SqlColumn<Long> documentId = column("document_id", JDBCType.BIGINT).withJavaProperty("documentId");

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR).withJavaProperty("status");

        public final SqlColumn<Integer> vectorCount = column("vector_count", JDBCType.INTEGER).withJavaProperty("vectorCount");

        public final SqlColumn<String> errorMessage = column("error_message", JDBCType.VARCHAR).withJavaProperty("errorMessage");

        public final SqlColumn<LocalDateTime> attemptedAt = column("attempted_at", JDBCType.TIMESTAMP).withJavaProperty("attemptedAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public RfcLlmProjectionRecord() {
            super("rfc_llm_projection", RfcLlmProjectionRecord::new);
        }
    }
}