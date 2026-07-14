package com.github.zaneway.format.rfc.vector;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import com.github.zaneway.format.rfc.storage.StoredRfcDocument;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * MySQL 提交后的 RFC canonical unit Qdrant 投影器。
 *
 * <p>仅投影 {@code section_content}、{@code asn1_definition}、{@code abnf_definition}
 * 三类单元；投影失败不回滚 MySQL，通过 {@link ProjectionResult} 与状态表供后续重试。</p>
 */
@Service
public class QdrantRfcVectorProjector implements RfcVectorProjector {

  private static final Set<String> CANONICAL_UNIT_TYPES = Set.of("section_content",
      "asn1_definition", "abnf_definition");

  private final RfcVectorClient vectorClient;
  private final RfcVectorProjectionStateStore projectionStateStore;

  /**
   * @param vectorClient         Qdrant 向量库操作适配器
   * @param projectionStateStore 投影状态持久化，用于运维观测与失败恢复
   */
  public QdrantRfcVectorProjector(RfcVectorClient vectorClient,
      RfcVectorProjectionStateStore projectionStateStore) {
    this.vectorClient = vectorClient;
    this.projectionStateStore = projectionStateStore;
  }

  /**
   * 将已提交的 RFC 文档投影到 Qdrant。
   *
   * <p>流程：标记 RUNNING → 筛选 canonical 单元 → 按 RFC 范围整批替换向量 → 标记 SUCCEEDED/FAILED。
   * 状态写入失败时仍返回 FAILED 结果，不向上抛异常，以免掩盖 Qdrant 侧的真实错误。</p>
   *
   * @param storedDocument MySQL 中已持久化的文档身份
   * @param document       内存中的完整解析结果，提供待投影的单元与元数据
   * @return 投影成功时携带写入条数；失败时携带截断后的错误消息，不抛出异常
   */
  @Override
  public ProjectionResult project(StoredRfcDocument storedDocument, RfcDocument document) {
    // 先占坑 RUNNING：运维可观测「已开始投影」；状态库故障本身也视为投影失败
    try {
      projectionStateStore.markStarted(storedDocument);
    } catch (RuntimeException exception) {
      return ProjectionResult.failed(errorMessage(exception));
    }
    // 仅投影物理内容单元；normative_rule 是检索覆盖层，embedding 内容与 section_content 重复
    List<RfcVectorDocument> vectors = document.units().stream()
        .filter(unit -> CANONICAL_UNIT_TYPES.contains(unit.unitType()))
        .map(unit -> toVectorDocument(document, unit))
        .toList();
    try {
      // 按 rfc_number 整批替换：同文档重导入时旧向量不会残留
      vectorClient.replace(document.rfcNumber(), vectors);
      try {
        projectionStateStore.markSucceeded(storedDocument);
      } catch (RuntimeException exception) {
        // Qdrant 已写入成功，但状态表失败 → 标记 FAILED 供人工核对，不抛异常给调用方
        markFailedQuietly(storedDocument, errorMessage(exception));
        return ProjectionResult.failed(errorMessage(exception));
      }
      return ProjectionResult.succeeded(vectors.size());
    } catch (RuntimeException exception) {
      // Qdrant 删除/写入失败：MySQL 事实层已提交，只回写状态供重试，绝不反向回滚
      String message = errorMessage(exception);
      markFailedQuietly(storedDocument, message);
      return ProjectionResult.failed(message);
    }
  }

  /**
   * 状态表写入失败时静默处理，保持调用方已获得的 Qdrant 失败结果不被二次异常覆盖。
   */
  private void markFailedQuietly(StoredRfcDocument storedDocument, String message) {
    try {
      projectionStateStore.markFailed(storedDocument, message);
    } catch (RuntimeException ignored) {
      // Qdrant 或状态库已出错；保持调用方可见的失败结果，不用二次异常掩盖它。
    }
  }

  /**
   * 将解析单元映射为向量文档，元数据合并 RFC 级定位信息与单元语义字段， 供 Qdrant 过滤检索时按 rfc_number、section_id、unit_type 等维度查询。
   */
  private static RfcVectorDocument toVectorDocument(RfcDocument document, RfcUnit unit) {
    LinkedHashMap<String, Object> metadata = new LinkedHashMap<>();
    // rfc_number 是 SpringAiRfcVectorClient.replace 删除过滤键，必须稳定存在
    metadata.put("rfc_number", document.rfcNumber());
    metadata.put("document_id", document.documentId());
    metadata.put("section_id", unit.parentSectionId());
    metadata.put("unit_type", unit.unitType());
    // 物理行号写入元数据，检索命中后可直接回溯原文位置
    metadata.put("source_start_line", unit.startLine());
    metadata.put("source_end_line", unit.endLine());
    // semantic 后写：允许 language/entityName 等扩展字段进入过滤维度
    metadata.putAll(unit.semantic());
    // 向量正文用 embeddingText（含 [RFC N §…] 前缀），检索上下文更完整
    return new RfcVectorDocument(unit.id(), unit.embeddingText(), Map.copyOf(metadata));
  }

  /**
   * 提取可读错误消息并截断至 4000 字符，防止状态表 error_message 列溢出。
   */
  private static String errorMessage(RuntimeException exception) {
    String message = exception.getMessage();
    if (message == null || message.isBlank()) {
      return exception.getClass().getSimpleName();
    }
    return message.length() <= 4_000 ? message : message.substring(0, 4_000);
  }
}
