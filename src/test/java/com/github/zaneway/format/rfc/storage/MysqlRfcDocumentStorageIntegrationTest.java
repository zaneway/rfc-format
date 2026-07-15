package com.github.zaneway.format.rfc.storage;

import com.github.zaneway.format.rfc.RfcFormatApplication;
import com.github.zaneway.format.rfc.parser.RfcDocumentParser;
import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.persistence.mapper.RfcObjectRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcProcessingReportRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcRelationRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcSectionRecordMapper;
import com.github.zaneway.format.rfc.persistence.mapper.RfcUnitRecordMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * 需显式开启的 MySQL 真库写入验证，避免默认单元测试污染开发数据库。
 */
@SpringBootTest(classes = RfcFormatApplication.class)
@EnabledIfSystemProperty(named = "rfc.mysql.integration.enabled", matches = "true")
class MysqlRfcDocumentStorageIntegrationTest {

  @Autowired
  private RfcDocumentParser parser;

  @Autowired
  private RfcDocumentStorage storage;

  @Autowired
  private RfcSectionRecordMapper sectionMapper;

  @Autowired
  private RfcUnitRecordMapper unitMapper;

  @Autowired
  private RfcRelationRecordMapper relationMapper;

  @Autowired
  private RfcObjectRecordMapper objectMapper;

  @Autowired
  private RfcProcessingReportRecordMapper processingReportMapper;

  @Test
  void storesParsedDocumentAndAllDerivedLayers() throws Exception {
    Path fixture = fixturePath();
    RfcDocument document = parser.parse(fixture);

    StoredRfcDocument stored = storage.store(document, fixture.toString());

    assertThat(sectionMapper.count(c -> c.where(
        com.github.zaneway.format.rfc.persistence.mapper.RfcSectionRecordDynamicSqlSupport.documentId,
        isEqualTo(stored.documentId())))).isEqualTo(document.sections().size());
    assertThat(unitMapper.count(c -> c.where(
        com.github.zaneway.format.rfc.persistence.mapper.RfcUnitRecordDynamicSqlSupport.documentId,
        isEqualTo(stored.documentId())))).isEqualTo(document.units().size());
    assertThat(relationMapper.count(c -> c.where(
        com.github.zaneway.format.rfc.persistence.mapper.RfcRelationRecordDynamicSqlSupport.documentId,
        isEqualTo(stored.documentId())))).isEqualTo(document.relations().size());
    assertThat(objectMapper.count(c -> c.where(
        com.github.zaneway.format.rfc.persistence.mapper.RfcObjectRecordDynamicSqlSupport.documentId,
        isEqualTo(stored.documentId())))).isEqualTo(document.objects().size());
    assertThat(processingReportMapper.count(c -> c.where(
        com.github.zaneway.format.rfc.persistence.mapper.RfcProcessingReportRecordDynamicSqlSupport.documentId,
        isEqualTo(stored.documentId())))).isEqualTo(1L);
  }

  private Path fixturePath() throws URISyntaxException {
    return Path.of(getClass().getResource("/rfc/rfc9999.txt").toURI());
  }
}
