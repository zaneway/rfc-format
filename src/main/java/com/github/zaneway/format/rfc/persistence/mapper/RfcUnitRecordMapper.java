package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcUnitRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcUnitRecord;
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
public interface RfcUnitRecordMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RfcUnitRecord>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737976+08:00", comments="Source Table: rfc_unit")
    BasicColumn[] selectList = BasicColumn.columnList(id, documentId, unitKey, parentSectionKey, unitType, sourceStartLine, sourceEndLine, language, entityType, entityName, createdAt, updatedAt, content, embeddingText, semanticJson, metadataJson);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737582+08:00", comments="Source Table: rfc_unit")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RfcUnitRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.BIGINT),
        @Result(column="unit_key", property="unitKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_section_key", property="parentSectionKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="unit_type", property="unitType", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_start_line", property="sourceStartLine", jdbcType=JdbcType.INTEGER),
        @Result(column="source_end_line", property="sourceEndLine", jdbcType=JdbcType.INTEGER),
        @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR),
        @Result(column="entity_type", property="entityType", jdbcType=JdbcType.VARCHAR),
        @Result(column="entity_name", property="entityName", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="embedding_text", property="embeddingText", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="semantic_json", property="semanticJson", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="metadata_json", property="metadataJson", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<RfcUnitRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737648+08:00", comments="Source Table: rfc_unit")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RfcUnitRecordResult")
    Optional<RfcUnitRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737684+08:00", comments="Source Table: rfc_unit")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rfcUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737703+08:00", comments="Source Table: rfc_unit")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rfcUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737718+08:00", comments="Source Table: rfc_unit")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737735+08:00", comments="Source Table: rfc_unit")
    default int insert(RfcUnitRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcUnitRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(unitKey)
            .withMappedColumn(parentSectionKey)
            .withMappedColumn(unitType)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(language)
            .withMappedColumn(entityType)
            .withMappedColumn(entityName)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(content)
            .withMappedColumn(embeddingText)
            .withMappedColumn(semanticJson)
            .withMappedColumn(metadataJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737789+08:00", comments="Source Table: rfc_unit")
    default int insertMultiple(Collection<RfcUnitRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcUnitRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(unitKey)
            .withMappedColumn(parentSectionKey)
            .withMappedColumn(unitType)
            .withMappedColumn(sourceStartLine)
            .withMappedColumn(sourceEndLine)
            .withMappedColumn(language)
            .withMappedColumn(entityType)
            .withMappedColumn(entityName)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
            .withMappedColumn(content)
            .withMappedColumn(embeddingText)
            .withMappedColumn(semanticJson)
            .withMappedColumn(metadataJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73787+08:00", comments="Source Table: rfc_unit")
    default int insertSelective(RfcUnitRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcUnitRecord, c ->
            c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(unitKey, row::getUnitKey)
            .withMappedColumnWhenPresent(parentSectionKey, row::getParentSectionKey)
            .withMappedColumnWhenPresent(unitType, row::getUnitType)
            .withMappedColumnWhenPresent(sourceStartLine, row::getSourceStartLine)
            .withMappedColumnWhenPresent(sourceEndLine, row::getSourceEndLine)
            .withMappedColumnWhenPresent(language, row::getLanguage)
            .withMappedColumnWhenPresent(entityType, row::getEntityType)
            .withMappedColumnWhenPresent(entityName, row::getEntityName)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
            .withMappedColumnWhenPresent(content, row::getContent)
            .withMappedColumnWhenPresent(embeddingText, row::getEmbeddingText)
            .withMappedColumnWhenPresent(semanticJson, row::getSemanticJson)
            .withMappedColumnWhenPresent(metadataJson, row::getMetadataJson)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.737998+08:00", comments="Source Table: rfc_unit")
    default Optional<RfcUnitRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738035+08:00", comments="Source Table: rfc_unit")
    default List<RfcUnitRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rfcUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738068+08:00", comments="Source Table: rfc_unit")
    default List<RfcUnitRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73811+08:00", comments="Source Table: rfc_unit")
    default Optional<RfcUnitRecord> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738142+08:00", comments="Source Table: rfc_unit")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rfcUnitRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73816+08:00", comments="Source Table: rfc_unit")
    static UpdateDSL updateAllColumns(RfcUnitRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(documentId).equalTo(row::getDocumentId)
                .set(unitKey).equalTo(row::getUnitKey)
                .set(parentSectionKey).equalTo(row::getParentSectionKey)
                .set(unitType).equalTo(row::getUnitType)
                .set(sourceStartLine).equalTo(row::getSourceStartLine)
                .set(sourceEndLine).equalTo(row::getSourceEndLine)
                .set(language).equalTo(row::getLanguage)
                .set(entityType).equalTo(row::getEntityType)
                .set(entityName).equalTo(row::getEntityName)
                .set(createdAt).equalTo(row::getCreatedAt)
                .set(updatedAt).equalTo(row::getUpdatedAt)
                .set(content).equalTo(row::getContent)
                .set(embeddingText).equalTo(row::getEmbeddingText)
                .set(semanticJson).equalTo(row::getSemanticJson)
                .set(metadataJson).equalTo(row::getMetadataJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738228+08:00", comments="Source Table: rfc_unit")
    static UpdateDSL updateSelectiveColumns(RfcUnitRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(documentId).equalToWhenPresent(row::getDocumentId)
                .set(unitKey).equalToWhenPresent(row::getUnitKey)
                .set(parentSectionKey).equalToWhenPresent(row::getParentSectionKey)
                .set(unitType).equalToWhenPresent(row::getUnitType)
                .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
                .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
                .set(language).equalToWhenPresent(row::getLanguage)
                .set(entityType).equalToWhenPresent(row::getEntityType)
                .set(entityName).equalToWhenPresent(row::getEntityName)
                .set(createdAt).equalToWhenPresent(row::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
                .set(content).equalToWhenPresent(row::getContent)
                .set(embeddingText).equalToWhenPresent(row::getEmbeddingText)
                .set(semanticJson).equalToWhenPresent(row::getSemanticJson)
                .set(metadataJson).equalToWhenPresent(row::getMetadataJson);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.738301+08:00", comments="Source Table: rfc_unit")
    default int updateByPrimaryKey(RfcUnitRecord row) {
        return update(c ->
            c.set(documentId).equalTo(row::getDocumentId)
            .set(unitKey).equalTo(row::getUnitKey)
            .set(parentSectionKey).equalTo(row::getParentSectionKey)
            .set(unitType).equalTo(row::getUnitType)
            .set(sourceStartLine).equalTo(row::getSourceStartLine)
            .set(sourceEndLine).equalTo(row::getSourceEndLine)
            .set(language).equalTo(row::getLanguage)
            .set(entityType).equalTo(row::getEntityType)
            .set(entityName).equalTo(row::getEntityName)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .set(content).equalTo(row::getContent)
            .set(embeddingText).equalTo(row::getEmbeddingText)
            .set(semanticJson).equalTo(row::getSemanticJson)
            .set(metadataJson).equalTo(row::getMetadataJson)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.73837+08:00", comments="Source Table: rfc_unit")
    default int updateByPrimaryKeySelective(RfcUnitRecord row) {
        return update(c ->
            c.set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(unitKey).equalToWhenPresent(row::getUnitKey)
            .set(parentSectionKey).equalToWhenPresent(row::getParentSectionKey)
            .set(unitType).equalToWhenPresent(row::getUnitType)
            .set(sourceStartLine).equalToWhenPresent(row::getSourceStartLine)
            .set(sourceEndLine).equalToWhenPresent(row::getSourceEndLine)
            .set(language).equalToWhenPresent(row::getLanguage)
            .set(entityType).equalToWhenPresent(row::getEntityType)
            .set(entityName).equalToWhenPresent(row::getEntityName)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .set(content).equalToWhenPresent(row::getContent)
            .set(embeddingText).equalToWhenPresent(row::getEmbeddingText)
            .set(semanticJson).equalToWhenPresent(row::getSemanticJson)
            .set(metadataJson).equalToWhenPresent(row::getMetadataJson)
            .where(id, isEqualTo(row::getId))
        );
    }
}