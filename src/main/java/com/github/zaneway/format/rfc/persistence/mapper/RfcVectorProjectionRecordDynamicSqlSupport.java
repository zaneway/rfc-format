package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcVectorProjectionRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74202+08:00", comments="Source Table: rfc_vector_projection")
    public static final RfcVectorProjectionRecord rfcVectorProjectionRecord = new RfcVectorProjectionRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742055+08:00", comments="Source field: rfc_vector_projection.id")
    public static final SqlColumn<Long> id = rfcVectorProjectionRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742067+08:00", comments="Source field: rfc_vector_projection.document_id")
    public static final SqlColumn<Long> documentId = rfcVectorProjectionRecord.documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742077+08:00", comments="Source field: rfc_vector_projection.projection_name")
    public static final SqlColumn<String> projectionName = rfcVectorProjectionRecord.projectionName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742088+08:00", comments="Source field: rfc_vector_projection.source_sha256")
    public static final SqlColumn<String> sourceSha256 = rfcVectorProjectionRecord.sourceSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742096+08:00", comments="Source field: rfc_vector_projection.status")
    public static final SqlColumn<String> status = rfcVectorProjectionRecord.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742106+08:00", comments="Source field: rfc_vector_projection.attempt_count")
    public static final SqlColumn<Integer> attemptCount = rfcVectorProjectionRecord.attemptCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742116+08:00", comments="Source field: rfc_vector_projection.completed_at")
    public static final SqlColumn<LocalDateTime> completedAt = rfcVectorProjectionRecord.completedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742132+08:00", comments="Source field: rfc_vector_projection.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcVectorProjectionRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742143+08:00", comments="Source field: rfc_vector_projection.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcVectorProjectionRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742151+08:00", comments="Source field: rfc_vector_projection.error_message")
    public static final SqlColumn<String> errorMessage = rfcVectorProjectionRecord.errorMessage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.742037+08:00", comments="Source Table: rfc_vector_projection")
    public static final class RfcVectorProjectionRecord extends AliasableSqlTable<RfcVectorProjectionRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<Long> documentId = column("document_id", JDBCType.BIGINT).withJavaProperty("documentId");

        public final SqlColumn<String> projectionName = column("projection_name", JDBCType.VARCHAR).withJavaProperty("projectionName");

        public final SqlColumn<String> sourceSha256 = column("source_sha256", JDBCType.CHAR).withJavaProperty("sourceSha256");

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR).withJavaProperty("status");

        public final SqlColumn<Integer> attemptCount = column("attempt_count", JDBCType.INTEGER).withJavaProperty("attemptCount");

        public final SqlColumn<LocalDateTime> completedAt = column("completed_at", JDBCType.TIMESTAMP).withJavaProperty("completedAt");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public final SqlColumn<String> errorMessage = column("error_message", JDBCType.LONGVARCHAR).withJavaProperty("errorMessage");

        public RfcVectorProjectionRecord() {
            super("rfc_vector_projection", RfcVectorProjectionRecord::new);
        }
    }
}