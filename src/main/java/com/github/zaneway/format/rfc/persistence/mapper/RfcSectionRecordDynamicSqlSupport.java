package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

/**
 * 表 rfc_section 的 MyBatis Dynamic SQL 列定义，由 Generator 生成。
 */
public final class RfcSectionRecordDynamicSqlSupport {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210153+08:00", comments = "Source Table: rfc_section")
  public static final RfcSectionRecord rfcSectionRecord = new RfcSectionRecord();

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210199+08:00", comments = "Source field: rfc_section.id")
  public static final SqlColumn<Long> id = rfcSectionRecord.id;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210215+08:00", comments = "Source field: rfc_section.document_id")
  public static final SqlColumn<Long> documentId = rfcSectionRecord.documentId;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210231+08:00", comments = "Source field: rfc_section.section_key")
  public static final SqlColumn<String> sectionKey = rfcSectionRecord.sectionKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210246+08:00", comments = "Source field: rfc_section.parent_section_key")
  public static final SqlColumn<String> parentSectionKey = rfcSectionRecord.parentSectionKey;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210258+08:00", comments = "Source field: rfc_section.title")
  public static final SqlColumn<String> title = rfcSectionRecord.title;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21027+08:00", comments = "Source field: rfc_section.section_type")
  public static final SqlColumn<String> sectionType = rfcSectionRecord.sectionType;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210283+08:00", comments = "Source field: rfc_section.source_start_line")
  public static final SqlColumn<Integer> sourceStartLine = rfcSectionRecord.sourceStartLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210295+08:00", comments = "Source field: rfc_section.source_end_line")
  public static final SqlColumn<Integer> sourceEndLine = rfcSectionRecord.sourceEndLine;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210308+08:00", comments = "Source field: rfc_section.created_at")
  public static final SqlColumn<LocalDateTime> createdAt = rfcSectionRecord.createdAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210321+08:00", comments = "Source field: rfc_section.updated_at")
  public static final SqlColumn<LocalDateTime> updatedAt = rfcSectionRecord.updatedAt;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210335+08:00", comments = "Source field: rfc_section.section_path_json")
  public static final SqlColumn<String> sectionPathJson = rfcSectionRecord.sectionPathJson;

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210173+08:00", comments = "Source Table: rfc_section")
  public static final class RfcSectionRecord extends AliasableSqlTable<RfcSectionRecord> {

    public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

    public final SqlColumn<Long> documentId = column("document_id",
        JDBCType.BIGINT).withJavaProperty("documentId");

    public final SqlColumn<String> sectionKey = column("section_key",
        JDBCType.VARCHAR).withJavaProperty("sectionKey");

    public final SqlColumn<String> parentSectionKey = column("parent_section_key",
        JDBCType.VARCHAR).withJavaProperty("parentSectionKey");

    public final SqlColumn<String> title = column("title", JDBCType.VARCHAR).withJavaProperty(
        "title");

    public final SqlColumn<String> sectionType = column("section_type",
        JDBCType.VARCHAR).withJavaProperty("sectionType");

    public final SqlColumn<Integer> sourceStartLine = column("source_start_line",
        JDBCType.INTEGER).withJavaProperty("sourceStartLine");

    public final SqlColumn<Integer> sourceEndLine = column("source_end_line",
        JDBCType.INTEGER).withJavaProperty("sourceEndLine");

    public final SqlColumn<LocalDateTime> createdAt = column("created_at",
        JDBCType.TIMESTAMP).withJavaProperty("createdAt");

    public final SqlColumn<LocalDateTime> updatedAt = column("updated_at",
        JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

    public final SqlColumn<String> sectionPathJson = column("section_path_json",
        JDBCType.LONGVARCHAR).withJavaProperty("sectionPathJson");

    public RfcSectionRecord() {
      super("rfc_section", RfcSectionRecord::new);
    }
  }
}