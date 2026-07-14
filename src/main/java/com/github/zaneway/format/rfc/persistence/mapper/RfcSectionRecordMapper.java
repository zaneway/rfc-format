package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcSectionRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcSectionRecord;
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
 * rfc_section 表的 MyBatis Dynamic SQL Mapper。
 *
 * <p>提供章节节点的批量写入与按文档查询，支撑章节树重建与单元归属解析。</p>
 */
@Mapper
public interface RfcSectionRecordMapper extends CommonCountMapper, CommonDeleteMapper,
    CommonInsertMapper<RfcSectionRecord>, CommonUpdateMapper {

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210803+08:00", comments = "Source Table: rfc_section")
  BasicColumn[] selectList = BasicColumn.columnList(id, documentId, sectionKey, parentSectionKey,
      title, sectionType, sourceStartLine, sourceEndLine, createdAt, updatedAt, sectionPathJson);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210356+08:00", comments = "Source Table: rfc_section")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "RfcSectionRecordResult", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "document_id", property = "documentId", jdbcType = JdbcType.BIGINT),
      @Result(column = "section_key", property = "sectionKey", jdbcType = JdbcType.VARCHAR),
      @Result(column = "parent_section_key", property = "parentSectionKey", jdbcType = JdbcType.VARCHAR),
      @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
      @Result(column = "section_type", property = "sectionType", jdbcType = JdbcType.VARCHAR),
      @Result(column = "source_start_line", property = "sourceStartLine", jdbcType = JdbcType.INTEGER),
      @Result(column = "source_end_line", property = "sourceEndLine", jdbcType = JdbcType.INTEGER),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "section_path_json", property = "sectionPathJson", jdbcType = JdbcType.LONGVARCHAR)
  })
  List<RfcSectionRecord> selectMany(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210431+08:00", comments = "Source Table: rfc_section")
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @ResultMap("RfcSectionRecordResult")
  Optional<RfcSectionRecord> selectOne(SelectStatementProvider selectStatement);

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210474+08:00", comments = "Source Table: rfc_section")
  default long count(CountDSLCompleter completer) {
    return MyBatis3Utils.countFrom(this::count, rfcSectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210499+08:00", comments = "Source Table: rfc_section")
  default int delete(DeleteDSLCompleter completer) {
    return MyBatis3Utils.deleteFrom(this::delete, rfcSectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210519+08:00", comments = "Source Table: rfc_section")
  default int deleteByPrimaryKey(Long id_) {
    return delete(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210537+08:00", comments = "Source Table: rfc_section")
  default int insert(RfcSectionRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcSectionRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(sectionKey)
            .withMappedColumn(parentSectionKey)
            .withMappedColumn(title)
            .withMappedColumn(sectionType)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(sectionPathJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.2106+08:00", comments = "Source Table: rfc_section")
  default int insertMultiple(Collection<RfcSectionRecord> records) {
    return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcSectionRecord, c ->
        c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(sectionKey)
            .withMappedColumn(parentSectionKey)
            .withMappedColumn(title)
            .withMappedColumn(sectionType)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(sectionPathJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210692+08:00", comments = "Source Table: rfc_section")
  default int insertSelective(RfcSectionRecord row) {
    return MyBatis3Utils.insert(this::insert, row, rfcSectionRecord, c ->
        c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(sectionKey, row::getSectionKey)
            .withMappedColumnWhenPresent(parentSectionKey, row::getParentSectionKey)
            .withMappedColumnWhenPresent(title, row::getTitle)
            .withMappedColumnWhenPresent(sectionType, row::getSectionType)
            .withMappedColumnWhenPresent(sourceStartLine, row::getSourceStartLine)
            .withMappedColumnWhenPresent(sourceEndLine, row::getSourceEndLine)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(sectionPathJson, row::getSectionPathJson)
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21083+08:00", comments = "Source Table: rfc_section")
  default Optional<RfcSectionRecord> selectOne(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcSectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.21089+08:00", comments = "Source Table: rfc_section")
  default List<RfcSectionRecord> select(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectList(this::selectMany, selectList, rfcSectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210937+08:00", comments = "Source Table: rfc_section")
  default List<RfcSectionRecord> selectDistinct(SelectDSLCompleter completer) {
    return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcSectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.210988+08:00", comments = "Source Table: rfc_section")
  default Optional<RfcSectionRecord> selectByPrimaryKey(Long id_) {
    return selectOne(c ->
        c.where(id, isEqualTo(id_))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211033+08:00", comments = "Source Table: rfc_section")
  default int update(UpdateDSLCompleter completer) {
    return MyBatis3Utils.update(this::update, rfcSectionRecord, completer);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211058+08:00", comments = "Source Table: rfc_section")
  static UpdateDSL updateAllColumns(RfcSectionRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalTo(row::getId)
        .set(documentId).equalTo(row::getDocumentId)
        .set(sectionKey).equalTo(row::getSectionKey)
        .set(parentSectionKey).equalTo(row::getParentSectionKey)
        .set(title).equalTo(row::getTitle)
        .set(sectionType).equalTo(row::getSectionType)
        .set(sourceStartLine).equalTo(row::getSourceStartLine)
        .set(sourceEndLine).equalTo(row::getSourceEndLine)
        .set(createdAt).equalTo(row::getCreatedAt)
        .set(updatedAt).equalTo(row::getUpdatedAt)
        .set(sectionPathJson).equalTo(row::getSectionPathJson);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211121+08:00", comments = "Source Table: rfc_section")
  static UpdateDSL updateSelectiveColumns(RfcSectionRecord row, UpdateDSL dsl) {
    return dsl.set(id).equalToWhenPresent(row::getId)
        .set(documentId).equalToWhenPresent(row::getDocumentId)
        .set(sectionKey).equalToWhenPresent(row::getSectionKey)
        .set(parentSectionKey).equalToWhenPresent(row::getParentSectionKey)
        .set(title).equalToWhenPresent(row::getTitle)
        .set(sectionType).equalToWhenPresent(row::getSectionType)
        .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
        .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
        .set(createdAt).equalToWhenPresent(row::getCreatedAt)
        .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
        .set(sectionPathJson).equalToWhenPresent(row::getSectionPathJson);
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211184+08:00", comments = "Source Table: rfc_section")
  default int updateByPrimaryKey(RfcSectionRecord row) {
    return update(c ->
        c.set(documentId).equalTo(row::getDocumentId)
            .set(sectionKey).equalTo(row::getSectionKey)
            .set(parentSectionKey).equalTo(row::getParentSectionKey)
            .set(title).equalTo(row::getTitle)
            .set(sectionType).equalTo(row::getSectionType)
            .set(sourceStartLine).equalTo(row::getSourceStartLine)
            .set(sourceEndLine).equalTo(row::getSourceEndLine)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(sectionPathJson).equalTo(row::getSectionPathJson)
            .where(id, isEqualTo(row::getId))
    );
  }

  @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2026-07-14T08:59:55.211256+08:00", comments = "Source Table: rfc_section")
  default int updateByPrimaryKeySelective(RfcSectionRecord row) {
    return update(c ->
        c.set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(sectionKey).equalToWhenPresent(row::getSectionKey)
            .set(parentSectionKey).equalToWhenPresent(row::getParentSectionKey)
            .set(title).equalToWhenPresent(row::getTitle)
            .set(sectionType).equalToWhenPresent(row::getSectionType)
            .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
            .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(sectionPathJson).equalToWhenPresent(row::getSectionPathJson)
            .where(id, isEqualTo(row::getId))
    );
  }
}