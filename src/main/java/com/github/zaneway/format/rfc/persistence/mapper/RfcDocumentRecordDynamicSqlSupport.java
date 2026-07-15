package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcDocumentRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.724497+08:00", comments="Source Table: rfc_document")
    public static final RfcDocumentRecord rfcDocumentRecord = new RfcDocumentRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.724757+08:00", comments="Source field: rfc_document.id")
    public static final SqlColumn<Long> id = rfcDocumentRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725092+08:00", comments="Source field: rfc_document.document_key")
    public static final SqlColumn<String> documentKey = rfcDocumentRecord.documentKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725127+08:00", comments="Source field: rfc_document.rfc_number")
    public static final SqlColumn<String> rfcNumber = rfcDocumentRecord.rfcNumber;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725161+08:00", comments="Source field: rfc_document.title")
    public static final SqlColumn<String> title = rfcDocumentRecord.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725187+08:00", comments="Source field: rfc_document.publication_date")
    public static final SqlColumn<String> publicationDate = rfcDocumentRecord.publicationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725223+08:00", comments="Source field: rfc_document.status")
    public static final SqlColumn<String> status = rfcDocumentRecord.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725255+08:00", comments="Source field: rfc_document.category")
    public static final SqlColumn<String> category = rfcDocumentRecord.category;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725358+08:00", comments="Source field: rfc_document.source_file")
    public static final SqlColumn<String> sourceFile = rfcDocumentRecord.sourceFile;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725528+08:00", comments="Source field: rfc_document.source_uri")
    public static final SqlColumn<String> sourceUri = rfcDocumentRecord.sourceUri;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725634+08:00", comments="Source field: rfc_document.source_sha256")
    public static final SqlColumn<String> sourceSha256 = rfcDocumentRecord.sourceSha256;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725741+08:00", comments="Source field: rfc_document.parser_version")
    public static final SqlColumn<String> parserVersion = rfcDocumentRecord.parserVersion;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725798+08:00", comments="Source field: rfc_document.cleaning_policy_version")
    public static final SqlColumn<String> cleaningPolicyVersion = rfcDocumentRecord.cleaningPolicyVersion;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725844+08:00", comments="Source field: rfc_document.chunking_policy_version")
    public static final SqlColumn<String> chunkingPolicyVersion = rfcDocumentRecord.chunkingPolicyVersion;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725875+08:00", comments="Source field: rfc_document.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcDocumentRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.7259+08:00", comments="Source field: rfc_document.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcDocumentRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725928+08:00", comments="Source field: rfc_document.obsoletes_json")
    public static final SqlColumn<String> obsoletesJson = rfcDocumentRecord.obsoletesJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.725955+08:00", comments="Source field: rfc_document.updates_json")
    public static final SqlColumn<String> updatesJson = rfcDocumentRecord.updatesJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.724654+08:00", comments="Source Table: rfc_document")
    public static final class RfcDocumentRecord extends AliasableSqlTable<RfcDocumentRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<String> documentKey = column("document_key", JDBCType.VARCHAR).withJavaProperty("documentKey");

        public final SqlColumn<String> rfcNumber = column("rfc_number", JDBCType.VARCHAR).withJavaProperty("rfcNumber");

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR).withJavaProperty("title");

        public final SqlColumn<String> publicationDate = column("publication_date", JDBCType.VARCHAR).withJavaProperty("publicationDate");

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR).withJavaProperty("status");

        public final SqlColumn<String> category = column("category", JDBCType.VARCHAR).withJavaProperty("category");

        public final SqlColumn<String> sourceFile = column("source_file", JDBCType.VARCHAR).withJavaProperty("sourceFile");

        public final SqlColumn<String> sourceUri = column("source_uri", JDBCType.VARCHAR).withJavaProperty("sourceUri");

        public final SqlColumn<String> sourceSha256 = column("source_sha256", JDBCType.CHAR).withJavaProperty("sourceSha256");

        public final SqlColumn<String> parserVersion = column("parser_version", JDBCType.VARCHAR).withJavaProperty("parserVersion");

        public final SqlColumn<String> cleaningPolicyVersion = column("cleaning_policy_version", JDBCType.VARCHAR).withJavaProperty("cleaningPolicyVersion");

        public final SqlColumn<String> chunkingPolicyVersion = column("chunking_policy_version", JDBCType.VARCHAR).withJavaProperty("chunkingPolicyVersion");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public final SqlColumn<String> obsoletesJson = column("obsoletes_json", JDBCType.LONGVARCHAR).withJavaProperty("obsoletesJson");

        public final SqlColumn<String> updatesJson = column("updates_json", JDBCType.LONGVARCHAR).withJavaProperty("updatesJson");

        public RfcDocumentRecord() {
            super("rfc_document", RfcDocumentRecord::new);
        }
    }
}