package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcDocumentRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcDocumentRecord;
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
 * rfc_document 表的 MyBatis Dynamic SQL Mapper。
 *
 * <p>提供文档主记录的 CRUD 与条件查询，供 {@code MysqlRfcDocumentStorage} 在整图替换入库时使用。</p>
 */
@Mapper
public interface RfcDocumentRecordMapper extends CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<RfcDocumentRecord>, CommonUpdateMapper {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.204318+08:00", comments = "Source Table: rfc_document")
  BasicColumn[] selectList = BasicColumn.columnList(id, documentKey, rfcNumber, title,
      publicationDate, status, category, sourceFile, sourceUri, sourceSha256, parserVersion,
      cleaningPolicyVersion, chunkingPolicyVersion, createdAt, updatedAt, obsoletesJson,
      updatesJson);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.199056+08:00", comments = "Source Table: rfc_document")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "RfcDocumentRecordResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "document_key", property = "documentKey", jdbcType = JdbcType.VARCHAR),
      @Result(column = "rfc_number", property = "rfcNumber", jdbcType = JdbcType.VARCHAR),
      @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
      @Result(column = "publication_date", property = "publicationDate", jdbcType = JdbcType.VARCHAR),
      @Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
      @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
      @Result(column = "source_file", property = "sourceFile", jdbcType = JdbcType.VARCHAR),
      @Result(column = "source_uri", property = "sourceUri", jdbcType = JdbcType.VARCHAR),
      @Result(column = "source_sha256", property = "sourceSha256", jdbcType = JdbcType.CHAR),
      @Result(column = "parser_version", property = "parserVersion", jdbcType = JdbcType.VARCHAR),
      @Result(column = "cleaning_policy_version", property = "cleaningPolicyVersion", jdbcType = JdbcType.VARCHAR),
      @Result(column = "chunking_policy_version", property = "chunkingPolicyVersion", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "obsoletes_json", property = "obsoletesJson", jdbcType = JdbcType.LONGVARCHAR),
      @Result(column = "updates_json", property = "updatesJson", jdbcType = JdbcType.LONGVARCHAR)
  })
  List<RfcDocumentRecord> selectMany(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.201216+08:00", comments = "Source Table: rfc_document")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("RfcDocumentRecordResult")
  Optional<RfcDocumentRecord> selectOne(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.201573+08:00", comments = "Source Table: rfc_document")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, rfcDocumentRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.201756+08:00", comments = "Source Table: rfc_document")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, rfcDocumentRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.202013+08:00", comments = "Source Table: rfc_document")
  default int deleteByPrimaryKey(Long id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.202229+08:00", comments = "Source Table: rfc_document")
  default int insert(RfcDocumentRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcDocumentRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(documentKey)
            .withMappedColumn(rfcNumber)
            .withMappedColumn(title)
            .withMappedColumn(publicationDate)
            .withMappedColumn(status)
            .withMappedColumn(category)
            .withMappedColumn(sourceFile)
            .withMappedColumn(sourceUri)
            .withMappedColumn(sourceSha256)
            .withMappedColumn(parserVersion)
            .withMappedColumn(cleaningPolicyVersion)
            .withMappedColumn(chunkingPolicyVersion)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(obsoletesJson)
            .withMappedColumn(updatesJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.20312+08:00", comments = "Source Table: rfc_document")
  default int insertMultiple(Collection<RfcDocumentRecord> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcDocumentRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(documentKey)
            .withMappedColumn(rfcNumber)
            .withMappedColumn(title)
            .withMappedColumn(publicationDate)
            .withMappedColumn(status)
            .withMappedColumn(category)
            .withMappedColumn(sourceFile)
            .withMappedColumn(sourceUri)
            .withMappedColumn(sourceSha256)
            .withMappedColumn(parserVersion)
            .withMappedColumn(cleaningPolicyVersion)
            .withMappedColumn(chunkingPolicyVersion)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(obsoletesJson)
            .withMappedColumn(updatesJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.203621+08:00", comments = "Source Table: rfc_document")
  default int insertSelective(RfcDocumentRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcDocumentRecord, c ->
        c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentKey, row::getDocumentKey)
            .withMappedColumnWhenPresent(rfcNumber, row::getRfcNumber)
            .withMappedColumnWhenPresent(title, row::getTitle)
            .withMappedColumnWhenPresent(publicationDate, row::getPublicationDate)
            .withMappedColumnWhenPresent(status, row::getStatus)
            .withMappedColumnWhenPresent(category, row::getCategory)
            .withMappedColumnWhenPresent(sourceFile, row::getSourceFile)
            .withMappedColumnWhenPresent(sourceUri, row::getSourceUri)
            .withMappedColumnWhenPresent(sourceSha256, row::getSourceSha256)
            .withMappedColumnWhenPresent(parserVersion, row::getParserVersion)
            .withMappedColumnWhenPresent(cleaningPolicyVersion, row::getCleaningPolicyVersion)
            .withMappedColumnWhenPresent(chunkingPolicyVersion, row::getChunkingPolicyVersion)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(obsoletesJson, row::getObsoletesJson)
            .withMappedColumnWhenPresent(updatesJson, row::getUpdatesJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.204637+08:00", comments = "Source Table: rfc_document")
  default Optional<RfcDocumentRecord> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcDocumentRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.204853+08:00", comments = "Source Table: rfc_document")
  default List<RfcDocumentRecord> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, rfcDocumentRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.204999+08:00", comments = "Source Table: rfc_document")
  default List<RfcDocumentRecord> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcDocumentRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.205175+08:00", comments = "Source Table: rfc_document")
  default Optional<RfcDocumentRecord> selectByPrimaryKey(Long id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.205343+08:00", comments = "Source Table: rfc_document")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, rfcDocumentRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.205518+08:00", comments = "Source Table: rfc_document")
  static UpdateDSL updateAllColumns(RfcDocumentRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(documentKey).equalTo(row::getDocumentKey)
        .set(rfcNumber).equalTo(row::getRfcNumber)
        .set(title).equalTo(row::getTitle)
        .set(publicationDate).equalTo(row::getPublicationDate)
        .set(status).equalTo(row::getStatus)
        .set(category).equalTo(row::getCategory)
        .set(sourceFile).equalTo(row::getSourceFile)
        .set(sourceUri).equalTo(row::getSourceUri)
        .set(sourceSha256).equalTo(row::getSourceSha256)
        .set(parserVersion).equalTo(row::getParserVersion)
        .set(cleaningPolicyVersion).equalTo(row::getCleaningPolicyVersion)
        .set(chunkingPolicyVersion).equalTo(row::getChunkingPolicyVersion)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedAt).equalTo(row::getUpdatedAt)
        .set(obsoletesJson).equalTo(row::getObsoletesJson)
        .set(updatesJson).equalTo(row::getUpdatesJson);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.206562+08:00", comments = "Source Table: rfc_document")
  static UpdateDSL updateSelectiveColumns(RfcDocumentRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(documentKey).equalToWhenPresent(row::getDocumentKey)
        .set(rfcNumber).equalToWhenPresent(row::getRfcNumber)
        .set(title).equalToWhenPresent(row::getTitle)
        .set(publicationDate).equalToWhenPresent(row::getPublicationDate)
        .set(status).equalToWhenPresent(row::getStatus)
        .set(category).equalToWhenPresent(row::getCategory)
        .set(sourceFile).equalToWhenPresent(row::getSourceFile)
        .set(sourceUri).equalToWhenPresent(row::getSourceUri)
        .set(sourceSha256).equalToWhenPresent(row::getSourceSha256)
        .set(parserVersion).equalToWhenPresent(row::getParserVersion)
        .set(cleaningPolicyVersion).equalToWhenPresent(row::getCleaningPolicyVersion)
        .set(chunkingPolicyVersion).equalToWhenPresent(row::getChunkingPolicyVersion)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
        .set(obsoletesJson).equalToWhenPresent(row::getObsoletesJson)
        .set(updatesJson).equalToWhenPresent(row::getUpdatesJson);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.206853+08:00", comments = "Source Table: rfc_document")
  default int updateByPrimaryKey(RfcDocumentRecord row) {
    return update(c ->
        c.set(documentKey).equalTo(row::getDocumentKey)
            .set(rfcNumber).equalTo(row::getRfcNumber)
            .set(title).equalTo(row::getTitle)
            .set(publicationDate).equalTo(row::getPublicationDate)
            .set(status).equalTo(row::getStatus)
            .set(category).equalTo(row::getCategory)
            .set(sourceFile).equalTo(row::getSourceFile)
            .set(sourceUri).equalTo(row::getSourceUri)
            .set(sourceSha256).equalTo(row::getSourceSha256)
            .set(parserVersion).equalTo(row::getParserVersion)
            .set(cleaningPolicyVersion).equalTo(row::getCleaningPolicyVersion)
            .set(chunkingPolicyVersion).equalTo(row::getChunkingPolicyVersion)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(obsoletesJson).equalTo(row::getObsoletesJson)
            .set(updatesJson).equalTo(row::getUpdatesJson)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.207256+08:00", comments = "Source Table: rfc_document")
  default int updateByPrimaryKeySelective(RfcDocumentRecord row) {
    return update(c ->
        c.set(documentKey).equalToWhenPresent(row::getDocumentKey)
            .set(rfcNumber).equalToWhenPresent(row::getRfcNumber)
            .set(title).equalToWhenPresent(row::getTitle)
            .set(publicationDate).equalToWhenPresent(row::getPublicationDate)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(category).equalToWhenPresent(row::getCategory)
            .set(sourceFile).equalToWhenPresent(row::getSourceFile)
            .set(sourceUri).equalToWhenPresent(row::getSourceUri)
            .set(sourceSha256).equalToWhenPresent(row::getSourceSha256)
            .set(parserVersion).equalToWhenPresent(row::getParserVersion)
            .set(cleaningPolicyVersion).equalToWhenPresent(row::getCleaningPolicyVersion)
            .set(chunkingPolicyVersion).equalToWhenPresent(row::getChunkingPolicyVersion)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(obsoletesJson).equalToWhenPresent(row::getObsoletesJson)
            .set(updatesJson).equalToWhenPresent(row::getUpdatesJson)
            .where(id, isEqualTo(row::getId))
    );
  }
}