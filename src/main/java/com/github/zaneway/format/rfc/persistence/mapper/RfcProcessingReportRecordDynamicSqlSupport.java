package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

/**
 * 表 rfc_processing_report 的 MyBatis Dynamic SQL 列定义，由 Generator 生成。
 */
public final class RfcProcessingReportRecordDynamicSqlSupport {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218839+08:00", comments = "Source Table: rfc_processing_report")
  public static final RfcProcessingReportRecord rfcProcessingReportRecord = new RfcProcessingReportRecord();

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218868+08:00", comments = "Source field: rfc_processing_report.id")
  public static final SqlColumn<Long> id = rfcProcessingReportRecord.id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218879+08:00", comments = "Source field: rfc_processing_report.document_id")
  public static final SqlColumn<Long> documentId = rfcProcessingReportRecord.documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218888+08:00", comments = "Source field: rfc_processing_report.source_sha256")
  public static final SqlColumn<String> sourceSha256 = rfcProcessingReportRecord.sourceSha256;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218896+08:00", comments = "Source field: rfc_processing_report.schema_version")
  public static final SqlColumn<String> schemaVersion = rfcProcessingReportRecord.schemaVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218904+08:00", comments = "Source field: rfc_processing_report.parser_version")
  public static final SqlColumn<String> parserVersion = rfcProcessingReportRecord.parserVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218911+08:00", comments = "Source field: rfc_processing_report.report_status")
  public static final SqlColumn<String> reportStatus = rfcProcessingReportRecord.reportStatus;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21892+08:00", comments = "Source field: rfc_processing_report.created_at")
  public static final SqlColumn<LocalDateTime> createdAt = rfcProcessingReportRecord.createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21893+08:00", comments = "Source field: rfc_processing_report.updated_at")
  public static final SqlColumn<LocalDateTime> updatedAt = rfcProcessingReportRecord.updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218938+08:00", comments = "Source field: rfc_processing_report.counts_json")
  public static final SqlColumn<String> countsJson = rfcProcessingReportRecord.countsJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218946+08:00", comments = "Source field: rfc_processing_report.quality_checks_json")
  public static final SqlColumn<String> qualityChecksJson = rfcProcessingReportRecord.qualityChecksJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218954+08:00", comments = "Source field: rfc_processing_report.warnings_json")
  public static final SqlColumn<String> warningsJson = rfcProcessingReportRecord.warningsJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218852+08:00", comments = "Source Table: rfc_processing_report")
  public static final class RfcProcessingReportRecord extends
      AliasableSqlTable<RfcProcessingReportRecord> {

    public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

    public final SqlColumn<Long> documentId = column("document_id",
        JDBCType.BIGINT).withJavaProperty("documentId");

    public final SqlColumn<String> sourceSha256 = column("source_sha256",
        JDBCType.CHAR).withJavaProperty("sourceSha256");

    public final SqlColumn<String> schemaVersion = column("schema_version",
        JDBCType.VARCHAR).withJavaProperty("schemaVersion");

    public final SqlColumn<String> parserVersion = column("parser_version",
        JDBCType.VARCHAR).withJavaProperty("parserVersion");

    public final SqlColumn<String> reportStatus = column("report_status",
        JDBCType.VARCHAR).withJavaProperty("reportStatus");

    public final SqlColumn<LocalDateTime> createdAt = column("created_at",
        JDBCType.TIMESTAMP).withJavaProperty("createdAt");

    public final SqlColumn<LocalDateTime> updatedAt = column("updated_at",
        JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

    public final SqlColumn<String> countsJson = column("counts_json",
        JDBCType.LONGVARCHAR).withJavaProperty("countsJson");

    public final SqlColumn<String> qualityChecksJson = column("quality_checks_json",
        JDBCType.LONGVARCHAR).withJavaProperty("qualityChecksJson");

    public final SqlColumn<String> warningsJson = column("warnings_json",
        JDBCType.LONGVARCHAR).withJavaProperty("warningsJson");

    public RfcProcessingReportRecord() {
      super("rfc_processing_report", RfcProcessingReportRecord::new);
    }
  }
}