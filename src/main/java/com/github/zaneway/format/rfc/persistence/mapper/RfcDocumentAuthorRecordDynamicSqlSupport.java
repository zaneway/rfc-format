package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcDocumentAuthorRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734218+08:00", comments="Source Table: rfc_document_author")
    public static final RfcDocumentAuthorRecord rfcDocumentAuthorRecord = new RfcDocumentAuthorRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734267+08:00", comments="Source field: rfc_document_author.id")
    public static final SqlColumn<Long> id = rfcDocumentAuthorRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734292+08:00", comments="Source field: rfc_document_author.document_id")
    public static final SqlColumn<Long> documentId = rfcDocumentAuthorRecord.documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734309+08:00", comments="Source field: rfc_document_author.author_order")
    public static final SqlColumn<Integer> authorOrder = rfcDocumentAuthorRecord.authorOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734327+08:00", comments="Source field: rfc_document_author.author_name")
    public static final SqlColumn<String> authorName = rfcDocumentAuthorRecord.authorName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734341+08:00", comments="Source field: rfc_document_author.organization")
    public static final SqlColumn<String> organization = rfcDocumentAuthorRecord.organization;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734355+08:00", comments="Source field: rfc_document_author.email")
    public static final SqlColumn<String> email = rfcDocumentAuthorRecord.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734372+08:00", comments="Source field: rfc_document_author.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcDocumentAuthorRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734387+08:00", comments="Source field: rfc_document_author.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcDocumentAuthorRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73424+08:00", comments="Source Table: rfc_document_author")
    public static final class RfcDocumentAuthorRecord extends AliasableSqlTable<RfcDocumentAuthorRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<Long> documentId = column("document_id", JDBCType.BIGINT).withJavaProperty("documentId");

        public final SqlColumn<Integer> authorOrder = column("author_order", JDBCType.INTEGER).withJavaProperty("authorOrder");

        public final SqlColumn<String> authorName = column("author_name", JDBCType.VARCHAR).withJavaProperty("authorName");

        public final SqlColumn<String> organization = column("organization", JDBCType.VARCHAR).withJavaProperty("organization");

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR).withJavaProperty("email");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public RfcDocumentAuthorRecord() {
            super("rfc_document_author", RfcDocumentAuthorRecord::new);
        }
    }
}