package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcIngestionRunRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcIngestionRunRecord;
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
 * rfc_ingestion_run 表的 MyBatis Dynamic SQL Mapper。
 *
 * <p>提供导入运行审计记录的写入与状态更新，供 {@code RfcIngestionAuditStore} 追踪每次导入尝试。</p>
 */
@Mapper
public interface RfcIngestionRunRecordMapper extends CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<RfcIngestionRunRecord>, CommonUpdateMapper {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218187+08:00", comments = "Source Table: rfc_ingestion_run")
  BasicColumn[] selectList = BasicColumn.columnList(id, runKey, documentId, inputPath, inputSha256,
      status, startedAt, finishedAt, createdAt, updatedAt, statisticsJson, errorMessage);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217778+08:00", comments = "Source Table: rfc_ingestion_run")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "RfcIngestionRunRecordResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "run_key", property = "runKey", jdbcType = JdbcType.CHAR),
      @Result(column = "document_id", property = "documentId", jdbcType = JdbcType.BIGINT),
      @Result(column = "input_path", property = "inputPath", jdbcType = JdbcType.VARCHAR),
      @Result(column = "input_sha256", property = "inputSha256", jdbcType = JdbcType.CHAR),
      @Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
      @Result(column = "started_at", property = "startedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "finished_at", property = "finishedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "statistics_json", property = "statisticsJson", jdbcType = JdbcType.LONGVARCHAR),
      @Result(column = "error_message", property = "errorMessage", jdbcType = JdbcType.LONGVARCHAR)
  })
  List<RfcIngestionRunRecord> selectMany(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217843+08:00", comments = "Source Table: rfc_ingestion_run")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("RfcIngestionRunRecordResult")
  Optional<RfcIngestionRunRecord> selectOne(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217911+08:00", comments = "Source Table: rfc_ingestion_run")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, rfcIngestionRunRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217942+08:00", comments = "Source Table: rfc_ingestion_run")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, rfcIngestionRunRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21796+08:00", comments = "Source Table: rfc_ingestion_run")
  default int deleteByPrimaryKey(Long id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.217978+08:00", comments = "Source Table: rfc_ingestion_run")
  default int insert(RfcIngestionRunRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcIngestionRunRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(runKey)
            .withMappedColumn(documentId)
            .withMappedColumn(inputPath)
            .withMappedColumn(inputSha256)
            .withMappedColumn(status)
            .withMappedColumn(startedAt)
            .withMappedColumn(finishedAt)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(statisticsJson)
            .withMappedColumn(errorMessage)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218037+08:00", comments = "Source Table: rfc_ingestion_run")
  default int insertMultiple(Collection<RfcIngestionRunRecord> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcIngestionRunRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(runKey)
            .withMappedColumn(documentId)
            .withMappedColumn(inputPath)
            .withMappedColumn(inputSha256)
            .withMappedColumn(status)
            .withMappedColumn(startedAt)
            .withMappedColumn(finishedAt)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(statisticsJson)
            .withMappedColumn(errorMessage)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218101+08:00", comments = "Source Table: rfc_ingestion_run")
  default int insertSelective(RfcIngestionRunRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcIngestionRunRecord, c ->
        c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(runKey, row::getRunKey)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(inputPath, row::getInputPath)
            .withMappedColumnWhenPresent(inputSha256, row::getInputSha256)
            .withMappedColumnWhenPresent(status, row::getStatus)
            .withMappedColumnWhenPresent(startedAt, row::getStartedAt)
            .withMappedColumnWhenPresent(finishedAt, row::getFinishedAt)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(statisticsJson, row::getStatisticsJson)
            .withMappedColumnWhenPresent(errorMessage, row::getErrorMessage)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218206+08:00", comments = "Source Table: rfc_ingestion_run")
  default Optional<RfcIngestionRunRecord> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcIngestionRunRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218235+08:00", comments = "Source Table: rfc_ingestion_run")
  default List<RfcIngestionRunRecord> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, rfcIngestionRunRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218263+08:00", comments = "Source Table: rfc_ingestion_run")
  default List<RfcIngestionRunRecord> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcIngestionRunRecord,
        completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218291+08:00", comments = "Source Table: rfc_ingestion_run")
  default Optional<RfcIngestionRunRecord> selectByPrimaryKey(Long id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218356+08:00", comments = "Source Table: rfc_ingestion_run")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, rfcIngestionRunRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218397+08:00", comments = "Source Table: rfc_ingestion_run")
  static UpdateDSL updateAllColumns(RfcIngestionRunRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(runKey).equalTo(row::getRunKey)
        .set(documentId).equalTo(row::getDocumentId)
        .set(inputPath).equalTo(row::getInputPath)
        .set(inputSha256).equalTo(row::getInputSha256)
        .set(status).equalTo(row::getStatus)
        .set(startedAt).equalTo(row::getStartedAt)
        .set(finishedAt).equalTo(row::getFinishedAt)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedAt).equalTo(row::getUpdatedAt)
        .set(statisticsJson).equalTo(row::getStatisticsJson)
        .set(errorMessage).equalTo(row::getErrorMessage);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218439+08:00", comments = "Source Table: rfc_ingestion_run")
  static UpdateDSL updateSelectiveColumns(RfcIngestionRunRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(runKey).equalToWhenPresent(row::getRunKey)
        .set(documentId).equalToWhenPresent(row::getDocumentId)
        .set(inputPath).equalToWhenPresent(row::getInputPath)
        .set(inputSha256).equalToWhenPresent(row::getInputSha256)
        .set(status).equalToWhenPresent(row::getStatus)
        .set(startedAt).equalToWhenPresent(row::getStartedAt)
        .set(finishedAt).equalToWhenPresent(row::getFinishedAt)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
        .set(statisticsJson).equalToWhenPresent(row::getStatisticsJson)
        .set(errorMessage).equalToWhenPresent(row::getErrorMessage);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218478+08:00", comments = "Source Table: rfc_ingestion_run")
  default int updateByPrimaryKey(RfcIngestionRunRecord row) {
    return update(c ->
        c.set(runKey).equalTo(row::getRunKey)
            .set(documentId).equalTo(row::getDocumentId)
            .set(inputPath).equalTo(row::getInputPath)
            .set(inputSha256).equalTo(row::getInputSha256)
            .set(status).equalTo(row::getStatus)
            .set(startedAt).equalTo(row::getStartedAt)
            .set(finishedAt).equalTo(row::getFinishedAt)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(statisticsJson).equalTo(row::getStatisticsJson)
            .set(errorMessage).equalTo(row::getErrorMessage)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.218517+08:00", comments = "Source Table: rfc_ingestion_run")
  default int updateByPrimaryKeySelective(RfcIngestionRunRecord row) {
    return update(c ->
        c.set(runKey).equalToWhenPresent(row::getRunKey)
            .set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(inputPath).equalToWhenPresent(row::getInputPath)
            .set(inputSha256).equalToWhenPresent(row::getInputSha256)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(startedAt).equalToWhenPresent(row::getStartedAt)
            .set(finishedAt).equalToWhenPresent(row::getFinishedAt)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(statisticsJson).equalToWhenPresent(row::getStatisticsJson)
            .set(errorMessage).equalToWhenPresent(row::getErrorMessage)
            .where(id, isEqualTo(row::getId))
    );
  }
}