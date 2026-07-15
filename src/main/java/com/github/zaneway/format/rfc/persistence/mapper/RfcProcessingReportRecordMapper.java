package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcProcessingReportRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcProcessingReportRecord;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.dsl.CountDSLCompleter;
import org.mybatis.dynamic.sql.dsl.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.dsl.SelectDSLCompleter;
import org.mybatis.dynamic.sql.dsl.UpdateDSL;
import org.mybatis.dynamic.sql.dsl.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface RfcProcessingReportRecordMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RfcProcessingReportRecord>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744603+08:00", comments="Source Table: rfc_processing_report")
    BasicColumn[] selectList = BasicColumn.columnList(id, documentId, sourceSha256, schemaVersion, parserVersion, reportStatus, createdAt, updatedAt, countsJson, qualityChecksJson, warningsJson);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744327+08:00", comments="Source Table: rfc_processing_report")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RfcProcessingReportRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.BIGINT),
        @Result(column="source_sha256", property="sourceSha256", jdbcType=JdbcType.CHAR),
        @Result(column="schema_version", property="schemaVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="parser_version", property="parserVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="report_status", property="reportStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="counts_json", property="countsJson", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="quality_checks_json", property="qualityChecksJson", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="warnings_json", property="warningsJson", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<RfcProcessingReportRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744378+08:00", comments="Source Table: rfc_processing_report")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RfcProcessingReportRecordResult")
    Optional<RfcProcessingReportRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744401+08:00", comments="Source Table: rfc_processing_report")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rfcProcessingReportRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744414+08:00", comments="Source Table: rfc_processing_report")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rfcProcessingReportRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744426+08:00", comments="Source Table: rfc_processing_report")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744438+08:00", comments="Source Table: rfc_processing_report")
    default int insert(RfcProcessingReportRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcProcessingReportRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(sourceSha256)
            .withMappedColumn(schemaVersion)
            .withMappedColumn(parserVersion)
            .withMappedColumn(reportStatus)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(countsJson)
            .withMappedColumn(qualityChecksJson)
            .withMappedColumn(warningsJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744479+08:00", comments="Source Table: rfc_processing_report")
    default int insertMultiple(Collection<RfcProcessingReportRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcProcessingReportRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(sourceSha256)
            .withMappedColumn(schemaVersion)
            .withMappedColumn(parserVersion)
            .withMappedColumn(reportStatus)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(countsJson)
            .withMappedColumn(qualityChecksJson)
            .withMappedColumn(warningsJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74454+08:00", comments="Source Table: rfc_processing_report")
    default int insertSelective(RfcProcessingReportRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcProcessingReportRecord, c ->
            c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(sourceSha256, row::getSourceSha256)
            .withMappedColumnWhenPresent(schemaVersion, row::getSchemaVersion)
            .withMappedColumnWhenPresent(parserVersion, row::getParserVersion)
            .withMappedColumnWhenPresent(reportStatus, row::getReportStatus)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(countsJson, row::getCountsJson)
            .withMappedColumnWhenPresent(qualityChecksJson, row::getQualityChecksJson)
            .withMappedColumnWhenPresent(warningsJson, row::getWarningsJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744617+08:00", comments="Source Table: rfc_processing_report")
    default Optional<RfcProcessingReportRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcProcessingReportRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744639+08:00", comments="Source Table: rfc_processing_report")
    default List<RfcProcessingReportRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rfcProcessingReportRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74466+08:00", comments="Source Table: rfc_processing_report")
    default List<RfcProcessingReportRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcProcessingReportRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744684+08:00", comments="Source Table: rfc_processing_report")
    default Optional<RfcProcessingReportRecord> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74471+08:00", comments="Source Table: rfc_processing_report")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rfcProcessingReportRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744724+08:00", comments="Source Table: rfc_processing_report")
    static UpdateDSL updateAllColumns(RfcProcessingReportRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(documentId).equalTo(row::getDocumentId)
                .set(sourceSha256).equalTo(row::getSourceSha256)
                .set(schemaVersion).equalTo(row::getSchemaVersion)
                .set(parserVersion).equalTo(row::getParserVersion)
                .set(reportStatus).equalTo(row::getReportStatus)
                .set(createdAt).equalTo(row::getCreatedAt)
                .set(updatedAt).equalTo(row::getUpdatedAt)
                .set(countsJson).equalTo(row::getCountsJson)
                .set(qualityChecksJson).equalTo(row::getQualityChecksJson)
                .set(warningsJson).equalTo(row::getWarningsJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744758+08:00", comments="Source Table: rfc_processing_report")
    static UpdateDSL updateSelectiveColumns(RfcProcessingReportRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(documentId).equalToWhenPresent(row::getDocumentId)
                .set(sourceSha256).equalToWhenPresent(row::getSourceSha256)
                .set(schemaVersion).equalToWhenPresent(row::getSchemaVersion)
                .set(parserVersion).equalToWhenPresent(row::getParserVersion)
                .set(reportStatus).equalToWhenPresent(row::getReportStatus)
                .set(createdAt).equalToWhenPresent(row::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
                .set(countsJson).equalToWhenPresent(row::getCountsJson)
                .set(qualityChecksJson).equalToWhenPresent(row::getQualityChecksJson)
                .set(warningsJson).equalToWhenPresent(row::getWarningsJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744798+08:00", comments="Source Table: rfc_processing_report")
    default int updateByPrimaryKey(RfcProcessingReportRecord row) {
        return update(c ->
            c.set(documentId).equalTo(row::getDocumentId)
            .set(sourceSha256).equalTo(row::getSourceSha256)
            .set(schemaVersion).equalTo(row::getSchemaVersion)
            .set(parserVersion).equalTo(row::getParserVersion)
            .set(reportStatus).equalTo(row::getReportStatus)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(countsJson).equalTo(row::getCountsJson)
            .set(qualityChecksJson).equalTo(row::getQualityChecksJson)
            .set(warningsJson).equalTo(row::getWarningsJson)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.744842+08:00", comments="Source Table: rfc_processing_report")
    default int updateByPrimaryKeySelective(RfcProcessingReportRecord row) {
        return update(c ->
            c.set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(sourceSha256).equalToWhenPresent(row::getSourceSha256)
            .set(schemaVersion).equalToWhenPresent(row::getSchemaVersion)
            .set(parserVersion).equalToWhenPresent(row::getParserVersion)
            .set(reportStatus).equalToWhenPresent(row::getReportStatus)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(countsJson).equalToWhenPresent(row::getCountsJson)
            .set(qualityChecksJson).equalToWhenPresent(row::getQualityChecksJson)
            .set(warningsJson).equalToWhenPresent(row::getWarningsJson)
            .where(id, isEqualTo(row::getId))
        );
    }
}