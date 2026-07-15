package com.github.zaneway.format.rfc.persistence.mapper;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcDocumentAuthorRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.github.zaneway.format.rfc.persistence.model.RfcDocumentAuthorRecord;
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
public interface RfcDocumentAuthorRecordMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RfcDocumentAuthorRecord>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734912+08:00", comments="Source Table: rfc_document_author")
    BasicColumn[] selectList = BasicColumn.columnList(id, documentId, authorOrder, authorName, organization, email, createdAt, updatedAt);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734409+08:00", comments="Source Table: rfc_document_author")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RfcDocumentAuthorRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.BIGINT),
        @Result(column="author_order", property="authorOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="author_name", property="authorName", jdbcType=JdbcType.VARCHAR),
        @Result(column="organization", property="organization", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<RfcDocumentAuthorRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734481+08:00", comments="Source Table: rfc_document_author")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RfcDocumentAuthorRecordResult")
    Optional<RfcDocumentAuthorRecord> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734528+08:00", comments="Source Table: rfc_document_author")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rfcDocumentAuthorRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734565+08:00", comments="Source Table: rfc_document_author")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rfcDocumentAuthorRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734615+08:00", comments="Source Table: rfc_document_author")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734641+08:00", comments="Source Table: rfc_document_author")
    default int insert(RfcDocumentAuthorRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcDocumentAuthorRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(authorOrder)
            .withMappedColumn(authorName)
            .withMappedColumn(organization)
            .withMappedColumn(email)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734708+08:00", comments="Source Table: rfc_document_author")
    default int insertMultiple(Collection<RfcDocumentAuthorRecord> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rfcDocumentAuthorRecord, c ->
            c.withMappedColumn(id)
            .withMappedColumn(documentId)
            .withMappedColumn(authorOrder)
            .withMappedColumn(authorName)
            .withMappedColumn(organization)
            .withMappedColumn(email)
            .withMappedColumn(createdAt)
            .withMappedColumn(updatedAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734804+08:00", comments="Source Table: rfc_document_author")
    default int insertSelective(RfcDocumentAuthorRecord row) {
        return MyBatis3Utils.insert(this::insert, row, rfcDocumentAuthorRecord, c ->
            c.withMappedColumnWhenPresent(id, row::getId)
            .withMappedColumnWhenPresent(documentId, row::getDocumentId)
            .withMappedColumnWhenPresent(authorOrder, row::getAuthorOrder)
            .withMappedColumnWhenPresent(authorName, row::getAuthorName)
            .withMappedColumnWhenPresent(organization, row::getOrganization)
            .withMappedColumnWhenPresent(email, row::getEmail)
            .withMappedColumnWhenPresent(createdAt, row::getCreatedAt)
            .withMappedColumnWhenPresent(updatedAt, row::getUpdatedAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.734941+08:00", comments="Source Table: rfc_document_author")
    default Optional<RfcDocumentAuthorRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rfcDocumentAuthorRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735001+08:00", comments="Source Table: rfc_document_author")
    default List<RfcDocumentAuthorRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rfcDocumentAuthorRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735046+08:00", comments="Source Table: rfc_document_author")
    default List<RfcDocumentAuthorRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rfcDocumentAuthorRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735096+08:00", comments="Source Table: rfc_document_author")
    default Optional<RfcDocumentAuthorRecord> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735146+08:00", comments="Source Table: rfc_document_author")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rfcDocumentAuthorRecord, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735168+08:00", comments="Source Table: rfc_document_author")
    static UpdateDSL updateAllColumns(RfcDocumentAuthorRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(documentId).equalTo(row::getDocumentId)
                .set(authorOrder).equalTo(row::getAuthorOrder)
                .set(authorName).equalTo(row::getAuthorName)
                .set(organization).equalTo(row::getOrganization)
                .set(email).equalTo(row::getEmail)
                .set(createdAt).equalTo(row::getCreatedAt)
                .set(updatedAt).equalTo(row::getUpdatedAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735221+08:00", comments="Source Table: rfc_document_author")
    static UpdateDSL updateSelectiveColumns(RfcDocumentAuthorRecord row, UpdateDSL dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(documentId).equalToWhenPresent(row::getDocumentId)
                .set(authorOrder).equalToWhenPresent(row::getAuthorOrder)
                .set(authorName).equalToWhenPresent(row::getAuthorName)
                .set(organization).equalToWhenPresent(row::getOrganization)
                .set(email).equalToWhenPresent(row::getEmail)
                .set(createdAt).equalToWhenPresent(row::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(row::getUpdatedAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735279+08:00", comments="Source Table: rfc_document_author")
    default int updateByPrimaryKey(RfcDocumentAuthorRecord row) {
        return update(c ->
            c.set(documentId).equalTo(row::getDocumentId)
            .set(authorOrder).equalTo(row::getAuthorOrder)
            .set(authorName).equalTo(row::getAuthorName)
            .set(organization).equalTo(row::getOrganization)
            .set(email).equalTo(row::getEmail)
            .set(createdAt).equalTo(row::getCreatedAt)
            .set(updatedAt).equalTo(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-07-15T18:05:13.735339+08:00", comments="Source Table: rfc_document_author")
    default int updateByPrimaryKeySelective(RfcDocumentAuthorRecord row) {
        return update(c ->
            c.set(documentId).equalToWhenPresent(row::getDocumentId)
            .set(authorOrder).equalToWhenPresent(row::getAuthorOrder)
            .set(authorName).equalToWhenPresent(row::getAuthorName)
            .set(organization).equalToWhenPresent(row::getOrganization)
            .set(email).equalToWhenPresent(row::getEmail)
            .set(createdAt).equalToWhenPresent(row::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(row::getUpdatedAt)
            .where(id, isEqualTo(row::getId))
        );
    }
}