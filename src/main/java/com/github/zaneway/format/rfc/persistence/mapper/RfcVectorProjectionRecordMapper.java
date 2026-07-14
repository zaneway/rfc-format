package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcVectorProjectionRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcVectorProjectionRecord;
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

/**
 * rfc_vector_projection 表的 MyBatis Dynamic SQL Mapper。
 *
 * <p>提供向量投影状态的写入与查询，供 {@code MysqlRfcVectorProjectionStateStore} 判定投影进度与幂等重试。</p>
 */
@Mapper
public interface RfcVectorProjectionRecordMapper extends CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<RfcVectorProjectionRecord>, CommonUpdateMapper {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216864+08:00", comments = "Source Table: rfc_vector_projection")
  BasicColumn[] selectList = BasicColumn.columnList(id, documentId, projectionName, sourceSha256,
      status, attemptCount, completedAt, createdAt, updatedAt, errorMessage);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216473+08:00", comments = "Source Table: rfc_vector_projection")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "RfcVectorProjectionRecordResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "document_id", property = "documentId", jdbcType = JdbcType.BIGINT),
      @Result(column = "projection_name", property = "projectionName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "source_sha256", property = "sourceSha256", jdbcType = JdbcType.CHAR),
      @Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
      @Result(column = "attempt_count", property = "attemptCount", jdbcType = JdbcType.INTEGER),
      @Result(column = "completed_at", property = "completedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "error_message", property = "errorMessage", jdbcType = JdbcType.LONGVARCHAR)
  })
  List<RfcVectorProjectionRecord> selectMany(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216528+08:00", comments = "Source Table: rfc_vector_projection")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("RfcVectorProjectionRecordResult")
  Optional<RfcVectorProjectionRecord> selectOne(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216563+08:00", comments = "Source Table: rfc_vector_projection")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, rfcVectorProjectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21658+08:00", comments = "Source Table: rfc_vector_projection")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, rfcVectorProjectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216597+08:00", comments = "Source Table: rfc_vector_projection")
  default int deleteByPrimaryKey(Long id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216612+08:00", comments = "Source Table: rfc_vector_projection")
  default int insert(RfcVectorProjectionRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcVectorProjectionRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(projectionName)
            .withMappedColumn(sourceSha256)
            .withMappedColumn(status)
            .withMappedColumn(attemptCount)
            .withMappedColumn(completedAt)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(errorMessage)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216695+08:00", comments = "Source Table: rfc_vector_projection")
  default int insertMultiple(Collection<RfcVectorProjectionRecord> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcVectorProjectionRecord,
        c ->
            c.withMappedColumn(id)
                .withMappedColumn(documentId)
                .withMappedColumn(projectionName)
                .withMappedColumn(sourceSha256)
                .withMappedColumn(status)
                .withMappedColumn(attemptCount)
                .withMappedColumn(completedAt)
                .withMappedColumn(createdAt)
                .withMappedColumn(updatedAt)
                .withMappedColumn(errorMessage)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216757+08:00", comments = "Source Table: rfc_vector_projection")
  default int insertSelective(RfcVectorProjectionRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcVectorProjectionRecord, c ->
        c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(projectionName, row::getProjectionName)
            .withMappedColumnWhenPresent(sourceSha256, row::getSourceSha256)
            .withMappedColumnWhenPresent(status, row::getStatus)
            .withMappedColumnWhenPresent(attemptCount, row::getAttemptCount)
            .withMappedColumnWhenPresent(completedAt, row::getCompletedAt)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(errorMessage, row::getErrorMessage)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216894+08:00", comments = "Source Table: rfc_vector_projection")
  default Optional<RfcVectorProjectionRecord> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcVectorProjectionRecord,
        completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216933+08:00", comments = "Source Table: rfc_vector_projection")
  default List<RfcVectorProjectionRecord> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, rfcVectorProjectionRecord,
        completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216964+08:00", comments = "Source Table: rfc_vector_projection")
  default List<RfcVectorProjectionRecord> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcVectorProjectionRecord,
        completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.216992+08:00", comments = "Source Table: rfc_vector_projection")
  default Optional<RfcVectorProjectionRecord> selectByPrimaryKey(Long id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217035+08:00", comments = "Source Table: rfc_vector_projection")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, rfcVectorProjectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217053+08:00", comments = "Source Table: rfc_vector_projection")
  static UpdateDSL updateAllColumns(RfcVectorProjectionRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(documentId).equalTo(row::getDocumentId)
        .set(projectionName).equalTo(row::getProjectionName)
        .set(sourceSha256).equalTo(row::getSourceSha256)
        .set(status).equalTo(row::getStatus)
        .set(attemptCount).equalTo(row::getAttemptCount)
        .set(completedAt).equalTo(row::getCompletedAt)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedAt).equalTo(row::getUpdatedAt)
        .set(errorMessage).equalTo(row::getErrorMessage);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217109+08:00", comments = "Source Table: rfc_vector_projection")
  static UpdateDSL updateSelectiveColumns(RfcVectorProjectionRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(documentId).equalToWhenPresent(row::getDocumentId)
        .set(projectionName).equalToWhenPresent(row::getProjectionName)
        .set(sourceSha256).equalToWhenPresent(row::getSourceSha256)
        .set(status).equalToWhenPresent(row::getStatus)
        .set(attemptCount).equalToWhenPresent(row::getAttemptCount)
        .set(completedAt).equalToWhenPresent(row::getCompletedAt)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
        .set(errorMessage).equalToWhenPresent(row::getErrorMessage);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217159+08:00", comments = "Source Table: rfc_vector_projection")
  default int updateByPrimaryKey(RfcVectorProjectionRecord row) {
    return update(c ->
        c.set(documentId).equalTo(row::getDocumentId)
            .set(projectionName).equalTo(row::getProjectionName)
            .set(sourceSha256).equalTo(row::getSourceSha256)
            .set(status).equalTo(row::getStatus)
            .set(attemptCount).equalTo(row::getAttemptCount)
            .set(completedAt).equalTo(row::getCompletedAt)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(errorMessage).equalTo(row::getErrorMessage)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217201+08:00", comments = "Source Table: rfc_vector_projection")
  default int updateByPrimaryKeySelective(RfcVectorProjectionRecord row) {
    return update(c ->
        c.set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(projectionName).equalToWhenPresent(row::getProjectionName)
            .set(sourceSha256).equalToWhenPresent(row::getSourceSha256)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(attemptCount).equalToWhenPresent(row::getAttemptCount)
            .set(completedAt).equalToWhenPresent(row::getCompletedAt)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(errorMessage).equalToWhenPresent(row::getErrorMessage)
            .where(id, isEqualTo(row::getId))
    );
  }
}