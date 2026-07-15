package com.github.zaneway.format.rfc.llm;

import org.springframework.stereotype.Service;

/**
 * LLM RFC 解析、MySQL 事实层写入和 Qdrant 投影的同步编排器。
 *
 * <p>没有跨 MySQL/Qdrant 的分布式事务：MySQL {@code store} 返回意味着事务已提交，
 * 此后 Qdrant 失败只记录为 {@link LlmProjectionStatus#SKIPPED}。</p>
 */
@Service
public final class LlmRfcIngestionService {

  private final RfcStructuredLlmClient llmClient;
  private final StructuredRfcJsonParser jsonParser;
  private final LlmRfcDocumentStorage storage;
  private final RfcStructuredVectorProjector projector;

  /**
   * @param llmClient LLM 调用端口
   * @param jsonParser 严格 JSON 契约校验器
   * @param storage MySQL 事务存储端口
   * @param projector Qdrant 投影端口
   */
  public LlmRfcIngestionService(RfcStructuredLlmClient llmClient,
      StructuredRfcJsonParser jsonParser, LlmRfcDocumentStorage storage,
      RfcStructuredVectorProjector projector) {
    this.llmClient = llmClient;
    this.jsonParser = jsonParser;
    this.storage = storage;
    this.projector = projector;
  }

  /**
   * 导入一份 RFC TXT。
   *
   * @param sourceFile 不可信来源文件名，仅作为提示词输入和审计字段
   * @param sourceText RFC 原文
   * @return 已提交文档 ID 及投影状态
   */
  public LlmRfcIngestionResult ingest(String sourceFile, String sourceText) {
    if (sourceFile == null || sourceFile.isBlank() || sourceText == null || sourceText.isBlank()) {
      throw new IllegalArgumentException("sourceFile and sourceText must not be blank");
    }
    String rawResponse = llmClient.parse(new RfcStructuredLlmRequest(sourceFile, sourceText));
    StructuredRfcDocument document = jsonParser.parse(rawResponse);
    if (!sourceFile.equals(document.document().sourceFile())) {
      throw new StructuredRfcValidationException("LLM document.source_file does not match input");
    }
    StoredLlmRfcDocument stored = storage.store(document, sourceText, rawResponse);
    return new LlmRfcIngestionResult(stored.documentId(), projector.project(stored, document));
  }
}
