package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcLlmDocumentRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcLlmDocumentRecord;
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
public interface RfcLlmDocumentRecordMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RfcLlmDocumentRecord>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745399+08:00", comments="Source Table: rfc_llm_document")
    BasicColumn[] selectList = BasicColumn.columnList(id, sourceFile, sourceSha256, createdAt, updatedAt, documentJson, structuredJson);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745143+08:00", comments="Source Table: rfc_llm_document")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RfcLlmDocumentRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="source_file", property="sourceFile", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_sha256", property="sourceSha256", jdbcType=JdbcType.CHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="document_json", property="documentJson", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="structured_json", property="structuredJson", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<RfcLlmDocumentRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74519+08:00", comments="Source Table: rfc_llm_document")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RfcLlmDocumentRecordResult")
    Optional<RfcLlmDocumentRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745219+08:00", comments="Source Table: rfc_llm_document")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rfcLlmDocumentRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745232+08:00", comments="Source Table: rfc_llm_document")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rfcLlmDocumentRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745243+08:00", comments="Source Table: rfc_llm_document")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745256+08:00", comments="Source Table: rfc_llm_document")
    default int insert(RfcLlmDocumentRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcLlmDocumentRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(sourceFile)
            .withMappedColumn(sourceSha256)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(documentJson)
            .withMappedColumn(structuredJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745294+08:00", comments="Source Table: rfc_llm_document")
    default int insertMultiple(Collection<RfcLlmDocumentRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcLlmDocumentRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(sourceFile)
            .withMappedColumn(sourceSha256)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(documentJson)
            .withMappedColumn(structuredJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745347+08:00", comments="Source Table: rfc_llm_document")
    default int insertSelective(RfcLlmDocumentRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcLlmDocumentRecord, c ->
            c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(sourceFile, row::getSourceFile)
            .withMappedColumnWhenPresent(sourceSha256, row::getSourceSha256)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(documentJson, row::getDocumentJson)
            .withMappedColumnWhenPresent(structuredJson, row::getStructuredJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745413+08:00", comments="Source Table: rfc_llm_document")
    default Optional<RfcLlmDocumentRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcLlmDocumentRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745434+08:00", comments="Source Table: rfc_llm_document")
    default List<RfcLlmDocumentRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rfcLlmDocumentRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745458+08:00", comments="Source Table: rfc_llm_document")
    default List<RfcLlmDocumentRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcLlmDocumentRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745492+08:00", comments="Source Table: rfc_llm_document")
    default Optional<RfcLlmDocumentRecord> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745514+08:00", comments="Source Table: rfc_llm_document")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rfcLlmDocumentRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745532+08:00", comments="Source Table: rfc_llm_document")
    static UpdateDSL updateAllColumns(RfcLlmDocumentRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(sourceFile).equalTo(row::getSourceFile)
                .set(sourceSha256).equalTo(row::getSourceSha256)
                .set(createdAt).equalTo(row::getCreatedAt)
                .set(updatedAt).equalTo(row::getUpdatedAt)
                .set(documentJson).equalTo(row::getDocumentJson)
                .set(structuredJson).equalTo(row::getStructuredJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745564+08:00", comments="Source Table: rfc_llm_document")
    static UpdateDSL updateSelectiveColumns(RfcLlmDocumentRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(sourceFile).equalToWhenPresent(row::getSourceFile)
                .set(sourceSha256).equalToWhenPresent(row::getSourceSha256)
                .set(createdAt).equalToWhenPresent(row::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
                .set(documentJson).equalToWhenPresent(row::getDocumentJson)
                .set(structuredJson).equalToWhenPresent(row::getStructuredJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745598+08:00", comments="Source Table: rfc_llm_document")
    default int updateByPrimaryKey(RfcLlmDocumentRecord row) {
        return update(c ->
            c.set(sourceFile).equalTo(row::getSourceFile)
            .set(sourceSha256).equalTo(row::getSourceSha256)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(documentJson).equalTo(row::getDocumentJson)
            .set(structuredJson).equalTo(row::getStructuredJson)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.745637+08:00", comments="Source Table: rfc_llm_document")
    default int updateByPrimaryKeySelective(RfcLlmDocumentRecord row) {
        return update(c ->
            c.set(sourceFile).equalToWhenPresent(row::getSourceFile)
            .set(sourceSha256).equalToWhenPresent(row::getSourceSha256)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(documentJson).equalToWhenPresent(row::getDocumentJson)
            .set(structuredJson).equalToWhenPresent(row::getStructuredJson)
            .where(id, isEqualTo(row::getId))
        );
    }
}