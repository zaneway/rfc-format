package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcLlmUnitRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcLlmUnitRecord;
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
public interface RfcLlmUnitRecordMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RfcLlmUnitRecord>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746404+08:00", comments="Source Table: rfc_llm_unit")
    BasicColumn[] selectList = BasicColumn.columnList(id, documentId, unitId, clauseId, contentType, sourceStartLine, sourceEndLine, createdAt, headingPathJson, content, extensionsJson);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746053+08:00", comments="Source Table: rfc_llm_unit")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RfcLlmUnitRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.BIGINT),
        @Result(column="unit_id", property="unitId", jdbcType=JdbcType.VARCHAR),
        @Result(column="clause_id", property="clauseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="content_type", property="contentType", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_start_line", property="sourceStartLine", jdbcType=JdbcType.INTEGER),
        @Result(column="source_end_line", property="sourceEndLine", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="heading_path_json", property="headingPathJson", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="extensions_json", property="extensionsJson", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<RfcLlmUnitRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746195+08:00", comments="Source Table: rfc_llm_unit")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RfcLlmUnitRecordResult")
    Optional<RfcLlmUnitRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746216+08:00", comments="Source Table: rfc_llm_unit")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rfcLlmUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746228+08:00", comments="Source Table: rfc_llm_unit")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rfcLlmUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746239+08:00", comments="Source Table: rfc_llm_unit")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746257+08:00", comments="Source Table: rfc_llm_unit")
    default int insert(RfcLlmUnitRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcLlmUnitRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(unitId)
            .withMappedColumn(clauseId)
            .withMappedColumn(contentType)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(createdAt)
            .withMappedColumn(headingPathJson)
            .withMappedColumn(content)
            .withMappedColumn(extensionsJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746298+08:00", comments="Source Table: rfc_llm_unit")
    default int insertMultiple(Collection<RfcLlmUnitRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcLlmUnitRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(unitId)
            .withMappedColumn(clauseId)
            .withMappedColumn(contentType)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(createdAt)
            .withMappedColumn(headingPathJson)
            .withMappedColumn(content)
            .withMappedColumn(extensionsJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74634+08:00", comments="Source Table: rfc_llm_unit")
    default int insertSelective(RfcLlmUnitRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcLlmUnitRecord, c ->
            c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(unitId, row::getUnitId)
            .withMappedColumnWhenPresent(clauseId, row::getClauseId)
            .withMappedColumnWhenPresent(contentType, row::getContentType)
            .withMappedColumnWhenPresent(sourceStartLine, row::getSourceStartLine)
            .withMappedColumnWhenPresent(sourceEndLine, row::getSourceEndLine)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(headingPathJson, row::getHeadingPathJson)
            .withMappedColumnWhenPresent(content, row::getContent)
            .withMappedColumnWhenPresent(extensionsJson, row::getExtensionsJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746424+08:00", comments="Source Table: rfc_llm_unit")
    default Optional<RfcLlmUnitRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcLlmUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746444+08:00", comments="Source Table: rfc_llm_unit")
    default List<RfcLlmUnitRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rfcLlmUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746465+08:00", comments="Source Table: rfc_llm_unit")
    default List<RfcLlmUnitRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcLlmUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746484+08:00", comments="Source Table: rfc_llm_unit")
    default Optional<RfcLlmUnitRecord> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746503+08:00", comments="Source Table: rfc_llm_unit")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rfcLlmUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746515+08:00", comments="Source Table: rfc_llm_unit")
    static UpdateDSL updateAllColumns(RfcLlmUnitRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(documentId).equalTo(row::getDocumentId)
                .set(unitId).equalTo(row::getUnitId)
                .set(clauseId).equalTo(row::getClauseId)
                .set(contentType).equalTo(row::getContentType)
                .set(sourceStartLine).equalTo(row::getSourceStartLine)
                .set(sourceEndLine).equalTo(row::getSourceEndLine)
                .set(createdAt).equalTo(row::getCreatedAt)
                .set(headingPathJson).equalTo(row::getHeadingPathJson)
                .set(content).equalTo(row::getContent)
                .set(extensionsJson).equalTo(row::getExtensionsJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746547+08:00", comments="Source Table: rfc_llm_unit")
    static UpdateDSL updateSelectiveColumns(RfcLlmUnitRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(documentId).equalToWhenPresent(row::getDocumentId)
                .set(unitId).equalToWhenPresent(row::getUnitId)
                .set(clauseId).equalToWhenPresent(row::getClauseId)
                .set(contentType).equalToWhenPresent(row::getContentType)
                .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
                .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
                .set(createdAt).equalToWhenPresent(row::getCreatedAt)
                .set(headingPathJson).equalToWhenPresent(row::getHeadingPathJson)
                .set(content).equalToWhenPresent(row::getContent)
                .set(extensionsJson).equalToWhenPresent(row::getExtensionsJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746583+08:00", comments="Source Table: rfc_llm_unit")
    default int updateByPrimaryKey(RfcLlmUnitRecord row) {
        return update(c ->
            c.set(documentId).equalTo(row::getDocumentId)
            .set(unitId).equalTo(row::getUnitId)
            .set(clauseId).equalTo(row::getClauseId)
            .set(contentType).equalTo(row::getContentType)
            .set(sourceStartLine).equalTo(row::getSourceStartLine)
            .set(sourceEndLine).equalTo(row::getSourceEndLine)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(headingPathJson).equalTo(row::getHeadingPathJson)
            .set(content).equalTo(row::getContent)
            .set(extensionsJson).equalTo(row::getExtensionsJson)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.746624+08:00", comments="Source Table: rfc_llm_unit")
    default int updateByPrimaryKeySelective(RfcLlmUnitRecord row) {
        return update(c ->
            c.set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(unitId).equalToWhenPresent(row::getUnitId)
            .set(clauseId).equalToWhenPresent(row::getClauseId)
            .set(contentType).equalToWhenPresent(row::getContentType)
            .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
            .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(headingPathJson).equalToWhenPresent(row::getHeadingPathJson)
            .set(content).equalToWhenPresent(row::getContent)
            .set(extensionsJson).equalToWhenPresent(row::getExtensionsJson)
            .where(id, isEqualTo(row::getId))
        );
    }
}