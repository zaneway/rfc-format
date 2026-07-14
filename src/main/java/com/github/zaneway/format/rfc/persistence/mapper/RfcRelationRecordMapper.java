package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcRelationRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcRelationRecord;
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
 * rfc_relation 表的 MyBatis Dynamic SQL Mapper。
 *
 * <p>提供引用与定义关系的批量写入与按文档查询，支撑跨 RFC 引用图构建。</p>
 */
@Mapper
public interface RfcRelationRecordMapper extends CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<RfcRelationRecord>, CommonUpdateMapper {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214132+08:00", comments = "Source Table: rfc_relation")
  BasicColumn[] selectList = BasicColumn.columnList(id, documentId, relationKey, relationType,
      fromKind, fromIdentifier, toKind, toIdentifier, sourceSectionKey, sourceStartLine,
      sourceEndLine, createdAt, updatedAt, attributesJson);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213712+08:00", comments = "Source Table: rfc_relation")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "RfcRelationRecordResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "document_id", property = "documentId", jdbcType = JdbcType.BIGINT),
      @Result(column = "relation_key", property = "relationKey", jdbcType = JdbcType.VARCHAR),
      @Result(column = "relation_type", property = "relationType", jdbcType = JdbcType.VARCHAR),
      @Result(column = "from_kind", property = "fromKind", jdbcType = JdbcType.VARCHAR),
      @Result(column = "from_identifier", property = "fromIdentifier", jdbcType = JdbcType.VARCHAR),
      @Result(column = "to_kind", property = "toKind", jdbcType = JdbcType.VARCHAR),
      @Result(column = "to_identifier", property = "toIdentifier", jdbcType = JdbcType.VARCHAR),
      @Result(column = "source_section_key", property = "sourceSectionKey", jdbcType = JdbcType.VARCHAR),
      @Result(column = "source_start_line", property = "sourceStartLine", jdbcType = JdbcType.INTEGER),
      @Result(column = "source_end_line", property = "sourceEndLine", jdbcType = JdbcType.INTEGER),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "attributes_json", property = "attributesJson", jdbcType = JdbcType.LONGVARCHAR)
  })
  List<RfcRelationRecord> selectMany(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213784+08:00", comments = "Source Table: rfc_relation")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("RfcRelationRecordResult")
  Optional<RfcRelationRecord> selectOne(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21382+08:00", comments = "Source Table: rfc_relation")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, rfcRelationRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21385+08:00", comments = "Source Table: rfc_relation")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, rfcRelationRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21387+08:00", comments = "Source Table: rfc_relation")
  default int deleteByPrimaryKey(Long id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213886+08:00", comments = "Source Table: rfc_relation")
  default int insert(RfcRelationRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcRelationRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(relationKey)
            .withMappedColumn(relationType)
            .withMappedColumn(fromKind)
            .withMappedColumn(fromIdentifier)
            .withMappedColumn(toKind)
            .withMappedColumn(toIdentifier)
            .withMappedColumn(sourceSectionKey)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(attributesJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.213942+08:00", comments = "Source Table: rfc_relation")
  default int insertMultiple(Collection<RfcRelationRecord> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcRelationRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(relationKey)
            .withMappedColumn(relationType)
            .withMappedColumn(fromKind)
            .withMappedColumn(fromIdentifier)
            .withMappedColumn(toKind)
            .withMappedColumn(toIdentifier)
            .withMappedColumn(sourceSectionKey)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(attributesJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214025+08:00", comments = "Source Table: rfc_relation")
  default int insertSelective(RfcRelationRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcRelationRecord, c ->
        c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(relationKey, row::getRelationKey)
            .withMappedColumnWhenPresent(relationType, row::getRelationType)
            .withMappedColumnWhenPresent(fromKind, row::getFromKind)
            .withMappedColumnWhenPresent(fromIdentifier, row::getFromIdentifier)
            .withMappedColumnWhenPresent(toKind, row::getToKind)
            .withMappedColumnWhenPresent(toIdentifier, row::getToIdentifier)
            .withMappedColumnWhenPresent(sourceSectionKey, row::getSourceSectionKey)
            .withMappedColumnWhenPresent(sourceStartLine, row::getSourceStartLine)
            .withMappedColumnWhenPresent(sourceEndLine, row::getSourceEndLine)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(attributesJson, row::getAttributesJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214154+08:00", comments = "Source Table: rfc_relation")
  default Optional<RfcRelationRecord> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcRelationRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214193+08:00", comments = "Source Table: rfc_relation")
  default List<RfcRelationRecord> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, rfcRelationRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21423+08:00", comments = "Source Table: rfc_relation")
  default List<RfcRelationRecord> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcRelationRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214275+08:00", comments = "Source Table: rfc_relation")
  default Optional<RfcRelationRecord> selectByPrimaryKey(Long id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214311+08:00", comments = "Source Table: rfc_relation")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, rfcRelationRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214328+08:00", comments = "Source Table: rfc_relation")
  static UpdateDSL updateAllColumns(RfcRelationRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(documentId).equalTo(row::getDocumentId)
        .set(relationKey).equalTo(row::getRelationKey)
        .set(relationType).equalTo(row::getRelationType)
        .set(fromKind).equalTo(row::getFromKind)
        .set(fromIdentifier).equalTo(row::getFromIdentifier)
        .set(toKind).equalTo(row::getToKind)
        .set(toIdentifier).equalTo(row::getToIdentifier)
        .set(sourceSectionKey).equalTo(row::getSourceSectionKey)
        .set(sourceStartLine).equalTo(row::getSourceStartLine)
        .set(sourceEndLine).equalTo(row::getSourceEndLine)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedAt).equalTo(row::getUpdatedAt)
        .set(attributesJson).equalTo(row::getAttributesJson);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214395+08:00", comments = "Source Table: rfc_relation")
  static UpdateDSL updateSelectiveColumns(RfcRelationRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(documentId).equalToWhenPresent(row::getDocumentId)
        .set(relationKey).equalToWhenPresent(row::getRelationKey)
        .set(relationType).equalToWhenPresent(row::getRelationType)
        .set(fromKind).equalToWhenPresent(row::getFromKind)
        .set(fromIdentifier).equalToWhenPresent(row::getFromIdentifier)
        .set(toKind).equalToWhenPresent(row::getToKind)
        .set(toIdentifier).equalToWhenPresent(row::getToIdentifier)
        .set(sourceSectionKey).equalToWhenPresent(row::getSourceSectionKey)
        .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
        .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
        .set(attributesJson).equalToWhenPresent(row::getAttributesJson);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21447+08:00", comments = "Source Table: rfc_relation")
  default int updateByPrimaryKey(RfcRelationRecord row) {
    return update(c ->
        c.set(documentId).equalTo(row::getDocumentId)
            .set(relationKey).equalTo(row::getRelationKey)
            .set(relationType).equalTo(row::getRelationType)
            .set(fromKind).equalTo(row::getFromKind)
            .set(fromIdentifier).equalTo(row::getFromIdentifier)
            .set(toKind).equalTo(row::getToKind)
            .set(toIdentifier).equalTo(row::getToIdentifier)
            .set(sourceSectionKey).equalTo(row::getSourceSectionKey)
            .set(sourceStartLine).equalTo(row::getSourceStartLine)
            .set(sourceEndLine).equalTo(row::getSourceEndLine)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(attributesJson).equalTo(row::getAttributesJson)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.214554+08:00", comments = "Source Table: rfc_relation")
  default int updateByPrimaryKeySelective(RfcRelationRecord row) {
    return update(c ->
        c.set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(relationKey).equalToWhenPresent(row::getRelationKey)
            .set(relationType).equalToWhenPresent(row::getRelationType)
            .set(fromKind).equalToWhenPresent(row::getFromKind)
            .set(fromIdentifier).equalToWhenPresent(row::getFromIdentifier)
            .set(toKind).equalToWhenPresent(row::getToKind)
            .set(toIdentifier).equalToWhenPresent(row::getToIdentifier)
            .set(sourceSectionKey).equalToWhenPresent(row::getSourceSectionKey)
            .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
            .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(attributesJson).equalToWhenPresent(row::getAttributesJson)
            .where(id, isEqualTo(row::getId))
    );
  }
}