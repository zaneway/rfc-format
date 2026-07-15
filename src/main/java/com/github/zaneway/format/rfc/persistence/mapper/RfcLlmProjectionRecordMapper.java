package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcLlmProjectionRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcLlmProjectionRecord;
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
public interface RfcLlmProjectionRecordMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RfcLlmProjectionRecord>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747105+08:00", comments="Source Table: rfc_llm_projection")
    BasicColumn[] selectList = BasicColumn.columnList(documentId, status, vectorCount, errorMessage, attemptedAt, updatedAt);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746881+08:00", comments="Source Table: rfc_llm_projection")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RfcLlmProjectionRecordResult", value = {
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="vector_count", property="vectorCount", jdbcType=JdbcType.INTEGER),
        @Result(column="error_message", property="errorMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="attempted_at", property="attemptedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<RfcLlmProjectionRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746921+08:00", comments="Source Table: rfc_llm_projection")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RfcLlmProjectionRecordResult")
    Optional<RfcLlmProjectionRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746941+08:00", comments="Source Table: rfc_llm_projection")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rfcLlmProjectionRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746952+08:00", comments="Source Table: rfc_llm_projection")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rfcLlmProjectionRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746968+08:00", comments="Source Table: rfc_llm_projection")
    default int deleteByPrimaryKey(Long documentId_) {
        return delete(c -> 
            c.where(documentId, isEqualTo(documentId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746979+08:00", comments="Source Table: rfc_llm_projection")
    default int insert(RfcLlmProjectionRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcLlmProjectionRecord, c ->
            c.withMappedColumn(documentId)
            .withMappedColumn(status)
            .withMappedColumn(vectorCount)
            .withMappedColumn(errorMessage)
            .withMappedColumn(attemptedAt)
            .withMappedColumn(updatedAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747013+08:00", comments="Source Table: rfc_llm_projection")
    default int insertMultiple(Collection<RfcLlmProjectionRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcLlmProjectionRecord, c ->
            c.withMappedColumn(documentId)
            .withMappedColumn(status)
            .withMappedColumn(vectorCount)
            .withMappedColumn(errorMessage)
            .withMappedColumn(attemptedAt)
            .withMappedColumn(updatedAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747054+08:00", comments="Source Table: rfc_llm_projection")
    default int insertSelective(RfcLlmProjectionRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcLlmProjectionRecord, c ->
            c.withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(status, row::getStatus)
            .withMappedColumnWhenPresent(vectorCount, row::getVectorCount)
            .withMappedColumnWhenPresent(errorMessage, row::getErrorMessage)
            .withMappedColumnWhenPresent(attemptedAt, row::getAttemptedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747117+08:00", comments="Source Table: rfc_llm_projection")
    default Optional<RfcLlmProjectionRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcLlmProjectionRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747135+08:00", comments="Source Table: rfc_llm_projection")
    default List<RfcLlmProjectionRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rfcLlmProjectionRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747157+08:00", comments="Source Table: rfc_llm_projection")
    default List<RfcLlmProjectionRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcLlmProjectionRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747183+08:00", comments="Source Table: rfc_llm_projection")
    default Optional<RfcLlmProjectionRecord> selectByPrimaryKey(Long documentId_) {
        return selectOne(c ->
            c.where(documentId, isEqualTo(documentId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747204+08:00", comments="Source Table: rfc_llm_projection")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rfcLlmProjectionRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747217+08:00", comments="Source Table: rfc_llm_projection")
    static UpdateDSL updateAllColumns(RfcLlmProjectionRecord row, UpdateDSL dsl) {
        return dsl.set(documentId).equalTo(row::getDocumentId)
                .set(status).equalTo(row::getStatus)
                .set(vectorCount).equalTo(row::getVectorCount)
                .set(errorMessage).equalTo(row::getErrorMessage)
                .set(attemptedAt).equalTo(row::getAttemptedAt)
                .set(updatedAt).equalTo(row::getUpdatedAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747244+08:00", comments="Source Table: rfc_llm_projection")
    static UpdateDSL updateSelectiveColumns(RfcLlmProjectionRecord row, UpdateDSL dsl) {
        return dsl.set(documentId).equalToWhenPresent(row::getDocumentId)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(vectorCount).equalToWhenPresent(row::getVectorCount)
                .set(errorMessage).equalToWhenPresent(row::getErrorMessage)
                .set(attemptedAt).equalToWhenPresent(row::getAttemptedAt)
                .set(updatedAt).equalToWhenPresent(row::getUpdatedAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747294+08:00", comments="Source Table: rfc_llm_projection")
    default int updateByPrimaryKey(RfcLlmProjectionRecord row) {
        return update(c ->
            c.set(status).equalTo(row::getStatus)
            .set(vectorCount).equalTo(row::getVectorCount)
            .set(errorMessage).equalTo(row::getErrorMessage)
            .set(attemptedAt).equalTo(row::getAttemptedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .where(documentId, isEqualTo(row::getDocumentId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.747322+08:00", comments="Source Table: rfc_llm_projection")
    default int updateByPrimaryKeySelective(RfcLlmProjectionRecord row) {
        return update(c ->
            c.set(status).equalToWhenPresent(row::getStatus)
            .set(vectorCount).equalToWhenPresent(row::getVectorCount)
            .set(errorMessage).equalToWhenPresent(row::getErrorMessage)
            .set(attemptedAt).equalToWhenPresent(row::getAttemptedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .where(documentId, isEqualTo(row::getDocumentId))
        );
    }
}