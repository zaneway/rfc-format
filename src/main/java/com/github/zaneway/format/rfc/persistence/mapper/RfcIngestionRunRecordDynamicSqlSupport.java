package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcIngestionRunRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743163+08:00", comments="Source Table: rfc_ingestion_run")
    public static final RfcIngestionRunRecord rfcIngestionRunRecord = new RfcIngestionRunRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743193+08:00", comments="Source field: rfc_ingestion_run.id")
    public static final SqlColumn<Long> id = rfcIngestionRunRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743206+08:00", comments="Source field: rfc_ingestion_run.run_key")
    public static final SqlColumn<String> runKey = rfcIngestionRunRecord.runKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743216+08:00", comments="Source field: rfc_ingestion_run.document_id")
    public static final SqlColumn<Long> documentId = rfcIngestionRunRecord.documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743227+08:00", comments="Source field: rfc_ingestion_run.input_path")
    public static final SqlColumn<String> inputPath = rfcIngestionRunRecord.inputPath;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743235+08:00", comments="Source field: rfc_ingestion_run.input_sha256")
    public static final SqlColumn<String> inputSha256 = rfcIngestionRunRecord.inputSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743244+08:00", comments="Source field: rfc_ingestion_run.status")
    public static final SqlColumn<String> status = rfcIngestionRunRecord.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743254+08:00", comments="Source field: rfc_ingestion_run.started_at")
    public static final SqlColumn<LocalDateTime> startedAt = rfcIngestionRunRecord.startedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743263+08:00", comments="Source field: rfc_ingestion_run.finished_at")
    public static final SqlColumn<LocalDateTime> finishedAt = rfcIngestionRunRecord.finishedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743274+08:00", comments="Source field: rfc_ingestion_run.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcIngestionRunRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743283+08:00", comments="Source field: rfc_ingestion_run.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcIngestionRunRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743292+08:00", comments="Source field: rfc_ingestion_run.statistics_json")
    public static final SqlColumn<String> statisticsJson = rfcIngestionRunRecord.statisticsJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.7433+08:00", comments="Source field: rfc_ingestion_run.error_message")
    public static final SqlColumn<String> errorMessage = rfcIngestionRunRecord.errorMessage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.743177+08:00", comments="Source Table: rfc_ingestion_run")
    public static final class RfcIngestionRunRecord extends AliasableSqlTable<RfcIngestionRunRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<String> runKey = column("run_key", JDBCType.CHAR).withJavaProperty("runKey");

        public final SqlColumn<Long> documentId = column("document_id", JDBCType.BIGINT).withJavaProperty("documentId");

        public final SqlColumn<String> inputPath = column("input_path", JDBCType.VARCHAR).withJavaProperty("inputPath");

        public final SqlColumn<String> inputSha256 = column("input_sha256", JDBCType.CHAR).withJavaProperty("inputSha256");

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR).withJavaProperty("status");

        public final SqlColumn<LocalDateTime> startedAt = column("started_at", JDBCType.TIMESTAMP).withJavaProperty("startedAt");

        public final SqlColumn<LocalDateTime> finishedAt = column("finished_at", JDBCType.TIMESTAMP).withJavaProperty("finishedAt");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public final SqlColumn<String> statisticsJson = column("statistics_json", JDBCType.LONGVARCHAR).withJavaProperty("statisticsJson");

        public final SqlColumn<String> errorMessage = column("error_message", JDBCType.LONGVARCHAR).withJavaProperty("errorMessage");

        public RfcIngestionRunRecord() {
            super("rfc_ingestion_run", RfcIngestionRunRecord::new);
        }
    }
}