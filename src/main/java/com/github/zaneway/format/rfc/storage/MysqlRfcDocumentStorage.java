package com.github.zaneway.format.rfc.storage;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcDocumentRecordDynamicSqlSupport.documentKey;
import static com.github.zaneway.format.rfc.persistence.mapper.RfcObjectRecordDynamicSqlSupport.documentId;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcExtractedObject;
import com.github.zaneway.format.rfc.parser.model.RfcRelation;
import com.github.zaneway.format.rfc.parser.model.RfcSection;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import com.github.zaneway.format.rfc.persistence.mapper.RfcDocumentRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcIngestionRunRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcObjectRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcProcessingReportRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcRelationRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcSectionRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcUnitRecordMapper;
import com.github.zaneway.format.rfc.persistence.model.RfcDocumentRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcIngestionRunRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcObjectRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcProcessingReportRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcRelationRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcSectionRecord;
import com.github.zaneway.format.rfc.persistence.model.RfcUnitRecord;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基于 MyBatis Generator Dynamic SQL 的 RFC 整图替换存储实现。
 *
 * <p>同一文档的主记录和全部派生结构在一个事务内提交；任一批次失败将整体回滚，
 * 使后续 Qdrant 投影永远只读取已完整持久化的文档。</p>
 */
@Repository
public class MysqlRfcDocumentStorage implements RfcDocumentStorage {

  private static final String PARSER_VERSION = "rfc-text-parser-v1";
  private static final String CLEANING_POLICY_VERSION = "cleaned-rfc-text-v1";
  private static final String CHUNKING_POLICY_VERSION = "section-unit-v1";
  private static final String STORED_STATUS = "STORED";
  private static final String REPORT_SCHEMA_VERSION = "rfc-bundle-v1";
  private static final String REPORT_SUCCESS_STATUS = "SUCCESS";

  private final RfcDocumentRecordMapper documentMapper;
  private final RfcSectionRecordMapper sectionMapper;
  private final RfcUnitRecordMapper unitMapper;
  private final RfcRelationRecordMapper relationMapper;
  private final RfcObjectRecordMapper objectMapper;
  private final RfcIngestionRunRecordMapper ingestionRunMapper;
  private final RfcProcessingReportRecordMapper processingReportMapper;
  private final ObjectMapper jsonMapper;

  /**
   * @param documentMapper         文档主表映射器
   * @param sectionMapper          章节表映射器
   * @param unitMapper             语义单元表映射器
   * @param relationMapper         交叉引用关系表映射器
   * @param objectMapper           提取对象表映射器
   * @param ingestionRunMapper     导入运行审计表映射器
   * @param processingReportMapper 处理报告表映射器
   * @param jsonMapper             RFC 结构化 JSON 字段序列化器（Jackson 2）
   */
  public MysqlRfcDocumentStorage(RfcDocumentRecordMapper documentMapper,
      RfcSectionRecordMapper sectionMapper,
      RfcUnitRecordMapper unitMapper, RfcRelationRecordMapper relationMapper,
      RfcObjectRecordMapper objectMapper, RfcIngestionRunRecordMapper ingestionRunMapper,
      RfcProcessingReportRecordMapper processingReportMapper, ObjectMapper jsonMapper) {
    this.documentMapper = documentMapper;
    this.sectionMapper = sectionMapper;
    this.unitMapper = unitMapper;
    this.relationMapper = relationMapper;
    this.objectMapper = objectMapper;
    this.ingestionRunMapper = ingestionRunMapper;
    this.processingReportMapper = processingReportMapper;
    this.jsonMapper = jsonMapper;
  }

  /**
   * 覆盖保存一个 RFC 的解析结果。
   *
   * <p>覆盖范围只限同一 {@code document_key}；先删除关系、对象、单元和章节，
   * 再使用 Generator 生成的 {@code insertMultiple} 写入新快照，避免混入旧版本残留数据。</p>
   *
   * @param document  已完成确定性解析的 RFC 文档
   * @param inputPath 本次解析输入文件的绝对路径
   * @return 数据库主文档标识及投影所需版本信息
   * @throws RuntimeException 事务内任一步骤失败时整体回滚
   */
  @Override
  @Transactional
  public StoredRfcDocument store(RfcDocument document, String inputPath) {
    // 主表 upsert 先拿到自增 documentId，后续派生表全部挂在同一外键下
    long documentId = upsertDocument(document);
    // 同一事务内统一时间戳，保证一次快照的 created_at/updated_at 可对齐比对
    LocalDateTime snapshotTime = LocalDateTime.now(ZoneOffset.UTC);
    // 先删后插：覆盖导入时清掉旧章节树与图边，杜绝新旧版本混挂
    deleteDerivedData(documentId);
    insertSections(documentId, document.sections(), snapshotTime);
    insertUnits(documentId, document.units(), snapshotTime);
    insertRelations(documentId, document.relations(), snapshotTime);
    insertObjects(documentId, document.objects(), snapshotTime);
    // 成功路径写一条导入运行记录（统计 counts）；失败路径由 AuditStore 另事务写入
    insertIngestionRun(documentId, document, inputPath);
    // 按 sourceSha256 幂等写处理报告；质量检查失败不会阻断写入，仅固化观测字段
    storeProcessingReport(documentId, document);
    // 返回投影层所需身份：库内 PK + 业务 documentKey + 源指纹
    return new StoredRfcDocument(documentId, document.documentId(), document.rfcNumber(),
        document.source().sha256());
  }

  /**
   * 按 document_key 查找已有主记录，存在则选择性更新，否则插入后回查主键。
   */
  private long upsertDocument(RfcDocument document) {
    RfcDocumentRecord documentRecord = documentRecord(document);
    return documentMapper.selectOne(c -> c.where(documentKey, isEqualTo(document.documentId())))
        .map(existing -> updateDocument(existing.getId(), documentRecord))
        .orElseGet(() -> insertAndFindDocument(document, documentRecord));
  }

  /**
   * 按主键选择性更新；返回原 documentId 供后续派生表写入使用。
   */
  private long updateDocument(Long documentId, RfcDocumentRecord documentRecord) {
    documentRecord.setId(documentId);
    documentMapper.updateByPrimaryKeySelective(documentRecord);
    return documentId;
  }

  /**
   * 首次入库：插入后按 document_key 回查自增主键。 Generator 的 insertSelective 不一定回填 id，故依赖二次查询保证外键可用。
   */
  private long insertAndFindDocument(RfcDocument document, RfcDocumentRecord documentRecord) {
    documentMapper.insertSelective(documentRecord);
    return documentMapper.selectOne(c -> c.where(documentKey, isEqualTo(document.documentId())))
        .map(RfcDocumentRecord::getId)
        .orElseThrow(() -> new IllegalStateException(
            "RFC document insert completed but document_key was not found: "
                + document.documentId()));
  }

  /**
   * 将解析文档映射为主表记录；策略版本常量固化解析/清洗/切分契约，便于审计对比。
   */
  private RfcDocumentRecord documentRecord(RfcDocument document) {
    RfcDocumentRecord record = new RfcDocumentRecord();
    record.setDocumentKey(document.documentId());
    record.setRfcNumber(document.rfcNumber());
    record.setTitle(document.title());
    record.setPublicationDate(document.publicationDate());
    record.setCategory(document.category());
    record.setSourceFile(document.source().fileName());
    record.setSourceSha256(document.source().sha256());
    record.setParserVersion(PARSER_VERSION);
    record.setCleaningPolicyVersion(CLEANING_POLICY_VERSION);
    record.setChunkingPolicyVersion(CHUNKING_POLICY_VERSION);
    return record;
  }

  /**
   * 先删后插派生数据，保证同一 documentId 下不会残留旧版本章节/单元/关系/对象。 删除顺序从叶子（关系、对象）到结构（单元、章节），避免外键或逻辑依赖冲突。
   */
  private void deleteDerivedData(long documentIdValue) {
    relationMapper.delete(c -> c.where(
        com.github.zaneway.format.rfc.persistence.mapper.RfcRelationRecordDynamicSqlSupport.documentId,
        isEqualTo(documentIdValue)));
    objectMapper.delete(c -> c.where(documentId, isEqualTo(documentIdValue)));
    unitMapper.delete(c -> c.where(
        com.github.zaneway.format.rfc.persistence.mapper.RfcUnitRecordDynamicSqlSupport.documentId,
        isEqualTo(documentIdValue)));
    sectionMapper.delete(c -> c.where(
        com.github.zaneway.format.rfc.persistence.mapper.RfcSectionRecordDynamicSqlSupport.documentId,
        isEqualTo(documentIdValue)));
  }

  /**
   * 批量写入章节树；空列表跳过，避免无意义的 insertMultiple。
   */
  private void insertSections(long documentIdValue, List<RfcSection> sections,
      LocalDateTime snapshotTime) {
    if (!sections.isEmpty()) {
      sectionMapper.insertMultiple(
          sections.stream().map(section -> sectionRecord(documentIdValue, section, snapshotTime))
              .toList());
    }
  }

  /**
   * 解析章节 → 表记录；path 序列化为 JSON，行号保留物理溯源。
   */
  private RfcSectionRecord sectionRecord(long documentIdValue, RfcSection section,
      LocalDateTime snapshotTime) {
    RfcSectionRecord record = new RfcSectionRecord();
    record.setDocumentId(documentIdValue);
    record.setSectionKey(section.id());
    record.setParentSectionKey(section.parentId());
    record.setTitle(section.title());
    record.setSectionType(section.sectionType());
    record.setSectionPathJson(json(section.path()));
    record.setSourceStartLine(section.startLine());
    record.setSourceEndLine(section.endLine());
    record.setCreatedAt(snapshotTime);
    record.setUpdatedAt(snapshotTime);
    return record;
  }

  /**
   * 批量写入语义单元，供后续 canonical 过滤与 Qdrant 投影读取。
   */
  private void insertUnits(long documentIdValue, List<RfcUnit> units, LocalDateTime snapshotTime) {
    if (!units.isEmpty()) {
      unitMapper.insertMultiple(
          units.stream().map(unit -> unitRecord(documentIdValue, unit, snapshotTime)).toList());
    }
  }

  /**
   * 解析单元 → 表记录；semantic 中高频字段抽成列便于 SQL 过滤，完整 map 仍落入 semantic_json。
   */
  private RfcUnitRecord unitRecord(long documentIdValue, RfcUnit unit, LocalDateTime snapshotTime) {
    RfcUnitRecord record = new RfcUnitRecord();
    record.setDocumentId(documentIdValue);
    record.setUnitKey(unit.id());
    record.setParentSectionKey(unit.parentSectionId());
    record.setUnitType(unit.unitType());
    record.setContent(unit.content());
    record.setEmbeddingText(unit.embeddingText());
    record.setSourceStartLine(unit.startLine());
    record.setSourceEndLine(unit.endLine());
    record.setLanguage(stringValue(unit.semantic(), "language"));
    record.setEntityType(stringValue(unit.semantic(), "entityType"));
    record.setEntityName(stringValue(unit.semantic(), "entityName"));
    record.setSemanticJson(json(unit.semantic()));
    record.setCreatedAt(snapshotTime);
    record.setUpdatedAt(snapshotTime);
    return record;
  }

  /**
   * 批量写入引用/defines 边；空列表跳过。
   */
  private void insertRelations(long documentIdValue, List<RfcRelation> relations,
      LocalDateTime snapshotTime) {
    if (!relations.isEmpty()) {
      relationMapper.insertMultiple(relations.stream()
          .map(relation -> relationRecord(documentIdValue, relation, snapshotTime)).toList());
    }
  }

  /**
   * 解析关系 → 表记录；扩展属性（citationType、occurrence 等）落入 attributes_json。
   */
  private RfcRelationRecord relationRecord(long documentIdValue, RfcRelation relation,
      LocalDateTime snapshotTime) {
    RfcRelationRecord record = new RfcRelationRecord();
    record.setDocumentId(documentIdValue);
    record.setRelationKey(relation.id());
    record.setRelationType(relation.relationType());
    record.setFromKind(relation.fromKind());
    record.setFromIdentifier(relation.fromIdentifier());
    record.setToKind(relation.toKind());
    record.setToIdentifier(relation.toIdentifier());
    record.setSourceSectionKey(relation.sourceSectionId());
    record.setSourceStartLine(relation.startLine());
    record.setSourceEndLine(relation.endLine());
    record.setAttributesJson(json(relation.attributes()));
    record.setCreatedAt(snapshotTime);
    record.setUpdatedAt(snapshotTime);
    return record;
  }

  /**
   * 批量写入规则对象（OID/字段/ABNF/状态码等）；空列表跳过。
   */
  private void insertObjects(long documentIdValue, List<RfcExtractedObject> objects,
      LocalDateTime snapshotTime) {
    if (!objects.isEmpty()) {
      objectMapper.insertMultiple(
          objects.stream().map(object -> objectRecord(documentIdValue, object, snapshotTime))
              .toList());
    }
  }

  /**
   * 解析对象 → 表记录；保留单元内字符偏移，便于与 defines 边交叉校验。
   */
  private RfcObjectRecord objectRecord(long documentIdValue, RfcExtractedObject object,
      LocalDateTime snapshotTime) {
    RfcObjectRecord record = new RfcObjectRecord();
    record.setDocumentId(documentIdValue);
    record.setObjectKey(object.id());
    record.setObjectType(object.objectType());
    record.setObjectName(object.name());
    record.setNormalizedValue(object.normalizedValue());
    record.setSourceUnitKey(object.sourceUnitId());
    record.setSourceStartLine(object.startLine());
    record.setSourceEndLine(object.endLine());
    record.setSourceStartOffset(object.startOffset());
    record.setSourceEndOffset(object.endOffset());
    record.setAttributesJson(json(object.attributes()));
    record.setCreatedAt(snapshotTime);
    record.setUpdatedAt(snapshotTime);
    return record;
  }

  /**
   * 记录一次成功的导入运行，统计各派生结构数量供运维观测。
   */
  private void insertIngestionRun(long documentIdValue, RfcDocument document, String inputPath) {
    LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
    RfcIngestionRunRecord record = new RfcIngestionRunRecord();
    record.setRunKey(UUID.randomUUID().toString());
    record.setDocumentId(documentIdValue);
    record.setInputPath(inputPath);
    record.setInputSha256(document.source().sha256());
    record.setStatus(STORED_STATUS);
    record.setStatisticsJson(
        json(Map.of("sections", document.sections().size(), "units", document.units().size(),
            "relations", document.relations().size(), "objects", document.objects().size())));
    record.setStartedAt(now);
    record.setFinishedAt(now);
    ingestionRunMapper.insertSelective(record);
  }

  /**
   * 按 documentId + sourceSha256 幂等写入处理报告：同一源摘要存在则更新，否则插入。 质量检查字段用于在入库时即发现解析器输出的结构性异常。
   */
  private void storeProcessingReport(long documentIdValue, RfcDocument document) {
    RfcProcessingReportRecord report = processingReportRecord(documentIdValue, document);
    processingReportMapper.selectOne(c -> c.where(
            com.github.zaneway.format.rfc.persistence.mapper.RfcProcessingReportRecordDynamicSqlSupport.documentId,
            isEqualTo(documentIdValue)).and(
            com.github.zaneway.format.rfc.persistence.mapper.RfcProcessingReportRecordDynamicSqlSupport.sourceSha256,
            isEqualTo(document.source().sha256())))
        // 幂等：同 documentId + sourceSha256 则更新，换源则插入新报告行
        .ifPresentOrElse(existing -> {
          report.setId(existing.getId());
          processingReportMapper.updateByPrimaryKeySelective(report);
        }, () -> processingReportMapper.insertSelective(report));
  }

  /**
   * 构造处理报告：计数与质量检查（单元 ID 唯一、行号区间合法）在入库时固化， 避免事后重算依赖已删除的旧快照。
   */
  private RfcProcessingReportRecord processingReportRecord(long documentIdValue,
      RfcDocument document) {
    RfcProcessingReportRecord record = new RfcProcessingReportRecord();
    record.setDocumentId(documentIdValue);
    record.setSourceSha256(document.source().sha256());
    record.setSchemaVersion(REPORT_SCHEMA_VERSION);
    record.setParserVersion(PARSER_VERSION);
    record.setReportStatus(REPORT_SUCCESS_STATUS);
    record.setCountsJson(
        json(Map.of("sections", document.sections().size(), "units", document.units().size(),
            "relations", document.relations().size(), "objects", document.objects().size())));
    // 质量检查只固化布尔结果供观测；false 时仍允许入库，由运维/下游决定是否重解析
    record.setQualityChecksJson(json(Map.of(
        "uniqueUnitIds",
        document.units().stream().map(RfcUnit::id).distinct().count() == document.units().size(),
        "sourceRangesValid",
        document.sections().stream().allMatch(section -> section.startLine() <= section.endLine()),
        "relationSourceRangesValid", document.relations().stream()
            .allMatch(relation -> relation.startLine() <= relation.endLine()))));
    record.setWarningsJson("[]");
    return record;
  }

  /**
   * 序列化失败视为编程错误，在事务内触发回滚而非静默降级。
   */
  private String json(Object value) {
    try {
      return jsonMapper.writeValueAsString(value);
    } catch (JsonProcessingException exception) {
      throw new IllegalStateException("Cannot serialize RFC structured value as JSON", exception);
    }
  }

  /**
   * 从 semantic map 抽取可选标量列；缺失时写 null，不默认空串以免污染检索过滤。
   */
  private static String stringValue(Map<String, Object> values, String key) {
    Object value = values.get(key);
    return value == null ? null : String.valueOf(value);
  }
}
