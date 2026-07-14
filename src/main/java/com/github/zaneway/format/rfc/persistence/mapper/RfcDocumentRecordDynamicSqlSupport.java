package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

/**
 * 表 rfc_document 的 MyBatis Dynamic SQL 列定义，由 Generator 生成。
 */
public final class RfcDocumentRecordDynamicSqlSupport {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.197751+08:00", comments = "Source Table: rfc_document")
  public static final RfcDocumentRecord rfcDocumentRecord = new RfcDocumentRecord();

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198026+08:00", comments = "Source field: rfc_document.id")
  public static final SqlColumn<Long> id = rfcDocumentRecord.id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198365+08:00", comments = "Source field: rfc_document.document_key")
  public static final SqlColumn<String> documentKey = rfcDocumentRecord.documentKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198399+08:00", comments = "Source field: rfc_document.rfc_number")
  public static final SqlColumn<String> rfcNumber = rfcDocumentRecord.rfcNumber;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198429+08:00", comments = "Source field: rfc_document.title")
  public static final SqlColumn<String> title = rfcDocumentRecord.title;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198454+08:00", comments = "Source field: rfc_document.publication_date")
  public static final SqlColumn<String> publicationDate = rfcDocumentRecord.publicationDate;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198478+08:00", comments = "Source field: rfc_document.status")
  public static final SqlColumn<String> status = rfcDocumentRecord.status;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198501+08:00", comments = "Source field: rfc_document.category")
  public static final SqlColumn<String> category = rfcDocumentRecord.category;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198524+08:00", comments = "Source field: rfc_document.source_file")
  public static final SqlColumn<String> sourceFile = rfcDocumentRecord.sourceFile;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198549+08:00", comments = "Source field: rfc_document.source_uri")
  public static final SqlColumn<String> sourceUri = rfcDocumentRecord.sourceUri;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198575+08:00", comments = "Source field: rfc_document.source_sha256")
  public static final SqlColumn<String> sourceSha256 = rfcDocumentRecord.sourceSha256;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198604+08:00", comments = "Source field: rfc_document.parser_version")
  public static final SqlColumn<String> parserVersion = rfcDocumentRecord.parserVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.19863+08:00", comments = "Source field: rfc_document.cleaning_policy_version")
  public static final SqlColumn<String> cleaningPolicyVersion = rfcDocumentRecord.cleaningPolicyVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198656+08:00", comments = "Source field: rfc_document.chunking_policy_version")
  public static final SqlColumn<String> chunkingPolicyVersion = rfcDocumentRecord.chunkingPolicyVersion;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198684+08:00", comments = "Source field: rfc_document.created_at")
  public static final SqlColumn<LocalDateTime> createdAt = rfcDocumentRecord.createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198711+08:00", comments = "Source field: rfc_document.updated_at")
  public static final SqlColumn<LocalDateTime> updatedAt = rfcDocumentRecord.updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198736+08:00", comments = "Source field: rfc_document.obsoletes_json")
  public static final SqlColumn<String> obsoletesJson = rfcDocumentRecord.obsoletesJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.198761+08:00", comments = "Source field: rfc_document.updates_json")
  public static final SqlColumn<String> updatesJson = rfcDocumentRecord.updatesJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.197928+08:00", comments = "Source Table: rfc_document")
  public static final class RfcDocumentRecord extends AliasableSqlTable<RfcDocumentRecord> {

    public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

    public final SqlColumn<String> documentKey = column("document_key",
        JDBCType.VARCHAR).withJavaProperty("documentKey");

    public final SqlColumn<String> rfcNumber = column("rfc_number",
        JDBCType.VARCHAR).withJavaProperty("rfcNumber");

    public final SqlColumn<String> title = column("title", JDBCType.VARCHAR).withJavaProperty(
        "title");

    public final SqlColumn<String> publicationDate = column("publication_date",
        JDBCType.VARCHAR).withJavaProperty("publicationDate");

    public final SqlColumn<String> status = column("status", JDBCType.VARCHAR).withJavaProperty(
        "status");

    public final SqlColumn<String> category = column("category", JDBCType.VARCHAR).withJavaProperty(
        "category");

    public final SqlColumn<String> sourceFile = column("source_file",
        JDBCType.VARCHAR).withJavaProperty("sourceFile");

    public final SqlColumn<String> sourceUri = column("source_uri",
        JDBCType.VARCHAR).withJavaProperty("sourceUri");

    public final SqlColumn<String> sourceSha256 = column("source_sha256",
        JDBCType.CHAR).withJavaProperty("sourceSha256");

    public final SqlColumn<String> parserVersion = column("parser_version",
        JDBCType.VARCHAR).withJavaProperty("parserVersion");

    public final SqlColumn<String> cleaningPolicyVersion = column("cleaning_policy_version",
        JDBCType.VARCHAR).withJavaProperty("cleaningPolicyVersion");

    public final SqlColumn<String> chunkingPolicyVersion = column("chunking_policy_version",
        JDBCType.VARCHAR).withJavaProperty("chunkingPolicyVersion");

    public final SqlColumn<LocalDateTime> createdAt = column("created_at",
        JDBCType.TIMESTAMP).withJavaProperty("createdAt");

    public final SqlColumn<LocalDateTime> updatedAt = column("updated_at",
        JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

    public final SqlColumn<String> obsoletesJson = column("obsoletes_json",
        JDBCType.LONGVARCHAR).withJavaProperty("obsoletesJson");

    public final SqlColumn<String> updatesJson = column("updates_json",
        JDBCType.LONGVARCHAR).withJavaProperty("updatesJson");

    public RfcDocumentRecord() {
      super("rfc_document", RfcDocumentRecord::new);
    }
  }
}