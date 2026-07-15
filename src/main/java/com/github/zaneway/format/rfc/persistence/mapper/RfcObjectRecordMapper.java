package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcObjectRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcObjectRecord;
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
public interface RfcObjectRecordMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RfcObjectRecord>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741395+08:00", comments="Source Table: rfc_object")
    BasicColumn[] selectList = BasicColumn.columnList(id, documentId, objectKey, objectType, objectName, normalizedValue, sourceUnitKey, sourceStartLine, sourceEndLine, sourceStartOffset, sourceEndOffset, createdAt, updatedAt, attributesJson);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741022+08:00", comments="Source Table: rfc_object")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RfcObjectRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.BIGINT),
        @Result(column="object_key", property="objectKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="object_type", property="objectType", jdbcType=JdbcType.VARCHAR),
        @Result(column="object_name", property="objectName", jdbcType=JdbcType.VARCHAR),
        @Result(column="normalized_value", property="normalizedValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_unit_key", property="sourceUnitKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_start_line", property="sourceStartLine", jdbcType=JdbcType.INTEGER),
        @Result(column="source_end_line", property="sourceEndLine", jdbcType=JdbcType.INTEGER),
        @Result(column="source_start_offset", property="sourceStartOffset", jdbcType=JdbcType.INTEGER),
        @Result(column="source_end_offset", property="sourceEndOffset", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="attributes_json", property="attributesJson", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<RfcObjectRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74108+08:00", comments="Source Table: rfc_object")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RfcObjectRecordResult")
    Optional<RfcObjectRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74111+08:00", comments="Source Table: rfc_object")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rfcObjectRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741127+08:00", comments="Source Table: rfc_object")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rfcObjectRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.74114+08:00", comments="Source Table: rfc_object")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741158+08:00", comments="Source Table: rfc_object")
    default int insert(RfcObjectRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcObjectRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(objectKey)
            .withMappedColumn(objectType)
            .withMappedColumn(objectName)
            .withMappedColumn(normalizedValue)
            .withMappedColumn(sourceUnitKey)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(sourceStartOffset)
            .withMappedColumn(sourceEndOffset)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(attributesJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741216+08:00", comments="Source Table: rfc_object")
    default int insertMultiple(Collection<RfcObjectRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcObjectRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(objectKey)
            .withMappedColumn(objectType)
            .withMappedColumn(objectName)
            .withMappedColumn(normalizedValue)
            .withMappedColumn(sourceUnitKey)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(sourceStartOffset)
            .withMappedColumn(sourceEndOffset)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(attributesJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741294+08:00", comments="Source Table: rfc_object")
    default int insertSelective(RfcObjectRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcObjectRecord, c ->
            c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(objectKey, row::getObjectKey)
            .withMappedColumnWhenPresent(objectType, row::getObjectType)
            .withMappedColumnWhenPresent(objectName, row::getObjectName)
            .withMappedColumnWhenPresent(normalizedValue, row::getNormalizedValue)
            .withMappedColumnWhenPresent(sourceUnitKey, row::getSourceUnitKey)
            .withMappedColumnWhenPresent(sourceStartLine, row::getSourceStartLine)
            .withMappedColumnWhenPresent(sourceEndLine, row::getSourceEndLine)
            .withMappedColumnWhenPresent(sourceStartOffset, row::getSourceStartOffset)
            .withMappedColumnWhenPresent(sourceEndOffset, row::getSourceEndOffset)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(attributesJson, row::getAttributesJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741413+08:00", comments="Source Table: rfc_object")
    default Optional<RfcObjectRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcObjectRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741447+08:00", comments="Source Table: rfc_object")
    default List<RfcObjectRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rfcObjectRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741483+08:00", comments="Source Table: rfc_object")
    default List<RfcObjectRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcObjectRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741512+08:00", comments="Source Table: rfc_object")
    default Optional<RfcObjectRecord> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741539+08:00", comments="Source Table: rfc_object")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rfcObjectRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741554+08:00", comments="Source Table: rfc_object")
    static UpdateDSL updateAllColumns(RfcObjectRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(documentId).equalTo(row::getDocumentId)
                .set(objectKey).equalTo(row::getObjectKey)
                .set(objectType).equalTo(row::getObjectType)
                .set(objectName).equalTo(row::getObjectName)
                .set(normalizedValue).equalTo(row::getNormalizedValue)
                .set(sourceUnitKey).equalTo(row::getSourceUnitKey)
                .set(sourceStartLine).equalTo(row::getSourceStartLine)
                .set(sourceEndLine).equalTo(row::getSourceEndLine)
                .set(sourceStartOffset).equalTo(row::getSourceStartOffset)
                .set(sourceEndOffset).equalTo(row::getSourceEndOffset)
                .set(createdAt).equalTo(row::getCreatedAt)
                .set(updatedAt).equalTo(row::getUpdatedAt)
                .set(attributesJson).equalTo(row::getAttributesJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741602+08:00", comments="Source Table: rfc_object")
    static UpdateDSL updateSelectiveColumns(RfcObjectRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(documentId).equalToWhenPresent(row::getDocumentId)
                .set(objectKey).equalToWhenPresent(row::getObjectKey)
                .set(objectType).equalToWhenPresent(row::getObjectType)
                .set(objectName).equalToWhenPresent(row::getObjectName)
                .set(normalizedValue).equalToWhenPresent(row::getNormalizedValue)
                .set(sourceUnitKey).equalToWhenPresent(row::getSourceUnitKey)
                .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
                .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
                .set(sourceStartOffset).equalToWhenPresent(row::getSourceStartOffset)
                .set(sourceEndOffset).equalToWhenPresent(row::getSourceEndOffset)
                .set(createdAt).equalToWhenPresent(row::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
                .set(attributesJson).equalToWhenPresent(row::getAttributesJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741655+08:00", comments="Source Table: rfc_object")
    default int updateByPrimaryKey(RfcObjectRecord row) {
        return update(c ->
            c.set(documentId).equalTo(row::getDocumentId)
            .set(objectKey).equalTo(row::getObjectKey)
            .set(objectType).equalTo(row::getObjectType)
            .set(objectName).equalTo(row::getObjectName)
            .set(normalizedValue).equalTo(row::getNormalizedValue)
            .set(sourceUnitKey).equalTo(row::getSourceUnitKey)
            .set(sourceStartLine).equalTo(row::getSourceStartLine)
            .set(sourceEndLine).equalTo(row::getSourceEndLine)
            .set(sourceStartOffset).equalTo(row::getSourceStartOffset)
            .set(sourceEndOffset).equalTo(row::getSourceEndOffset)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(attributesJson).equalTo(row::getAttributesJson)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.741704+08:00", comments="Source Table: rfc_object")
    default int updateByPrimaryKeySelective(RfcObjectRecord row) {
        return update(c ->
            c.set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(objectKey).equalToWhenPresent(row::getObjectKey)
            .set(objectType).equalToWhenPresent(row::getObjectType)
            .set(objectName).equalToWhenPresent(row::getObjectName)
            .set(normalizedValue).equalToWhenPresent(row::getNormalizedValue)
            .set(sourceUnitKey).equalToWhenPresent(row::getSourceUnitKey)
            .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
            .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
            .set(sourceStartOffset).equalToWhenPresent(row::getSourceStartOffset)
            .set(sourceEndOffset).equalToWhenPresent(row::getSourceEndOffset)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(attributesJson).equalToWhenPresent(row::getAttributesJson)
            .where(id, isEqualTo(row::getId))
        );
    }
}