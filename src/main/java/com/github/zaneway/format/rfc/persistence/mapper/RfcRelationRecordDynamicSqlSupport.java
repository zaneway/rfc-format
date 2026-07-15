package com.github.zaneway.format.rfc.persistence.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class RfcRelationRecordDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739303+08:00", comments="Source Table: rfc_relation")
    public static final RfcRelationRecord rfcRelationRecord = new RfcRelationRecord();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73938+08:00", comments="Source field: rfc_relation.id")
    public static final SqlColumn<Long> id = rfcRelationRecord.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739399+08:00", comments="Source field: rfc_relation.document_id")
    public static final SqlColumn<Long> documentId = rfcRelationRecord.documentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739413+08:00", comments="Source field: rfc_relation.relation_key")
    public static final SqlColumn<String> relationKey = rfcRelationRecord.relationKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739424+08:00", comments="Source field: rfc_relation.relation_type")
    public static final SqlColumn<String> relationType = rfcRelationRecord.relationType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739433+08:00", comments="Source field: rfc_relation.from_kind")
    public static final SqlColumn<String> fromKind = rfcRelationRecord.fromKind;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739446+08:00", comments="Source field: rfc_relation.from_identifier")
    public static final SqlColumn<String> fromIdentifier = rfcRelationRecord.fromIdentifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739457+08:00", comments="Source field: rfc_relation.to_kind")
    public static final SqlColumn<String> toKind = rfcRelationRecord.toKind;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73948+08:00", comments="Source field: rfc_relation.to_identifier")
    public static final SqlColumn<String> toIdentifier = rfcRelationRecord.toIdentifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739489+08:00", comments="Source field: rfc_relation.source_section_key")
    public static final SqlColumn<String> sourceSectionKey = rfcRelationRecord.sourceSectionKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739499+08:00", comments="Source field: rfc_relation.source_start_line")
    public static final SqlColumn<Integer> sourceStartLine = rfcRelationRecord.sourceStartLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739521+08:00", comments="Source field: rfc_relation.source_end_line")
    public static final SqlColumn<Integer> sourceEndLine = rfcRelationRecord.sourceEndLine;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739531+08:00", comments="Source field: rfc_relation.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = rfcRelationRecord.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739543+08:00", comments="Source field: rfc_relation.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = rfcRelationRecord.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739552+08:00", comments="Source field: rfc_relation.attributes_json")
    public static final SqlColumn<String> attributesJson = rfcRelationRecord.attributesJson;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.739325+08:00", comments="Source Table: rfc_relation")
    public static final class RfcRelationRecord extends AliasableSqlTable<RfcRelationRecord> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT).withJavaProperty("id");

        public final SqlColumn<Long> documentId = column("document_id", JDBCType.BIGINT).withJavaProperty("documentId");

        public final SqlColumn<String> relationKey = column("relation_key", JDBCType.VARCHAR).withJavaProperty("relationKey");

        public final SqlColumn<String> relationType = column("relation_type", JDBCType.VARCHAR).withJavaProperty("relationType");

        public final SqlColumn<String> fromKind = column("from_kind", JDBCType.VARCHAR).withJavaProperty("fromKind");

        public final SqlColumn<String> fromIdentifier = column("from_identifier", JDBCType.VARCHAR).withJavaProperty("fromIdentifier");

        public final SqlColumn<String> toKind = column("to_kind", JDBCType.VARCHAR).withJavaProperty("toKind");

        public final SqlColumn<String> toIdentifier = column("to_identifier", JDBCType.VARCHAR).withJavaProperty("toIdentifier");

        public final SqlColumn<String> sourceSectionKey = column("source_section_key", JDBCType.VARCHAR).withJavaProperty("sourceSectionKey");

        public final SqlColumn<Integer> sourceStartLine = column("source_start_line", JDBCType.INTEGER).withJavaProperty("sourceStartLine");

        public final SqlColumn<Integer> sourceEndLine = column("source_end_line", JDBCType.INTEGER).withJavaProperty("sourceEndLine");

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP).withJavaProperty("createdAt");

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP).withJavaProperty("updatedAt");

        public final SqlColumn<String> attributesJson = column("attributes_json", JDBCType.LONGVARCHAR).withJavaProperty("attributesJson");

        public RfcRelationRecord() {
            super("rfc_relation", RfcRelationRecord::new);
        }
    }
}