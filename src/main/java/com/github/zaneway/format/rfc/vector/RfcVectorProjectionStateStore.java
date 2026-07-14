package com.github.zaneway.format.rfc.vector;

import com.github.zaneway.format.rfc.storage.StoredRfcDocument;

/**
 * Qdrant 投影恢复状态的关系型持久化边界。
 *
 * <p>状态与 MySQL 事实数据解耦：投影失败不回滚文档存储，
 * 通过 attempt_count 与 status 支持运维重试与对账。</p>
 */
public interface RfcVectorProjectionStateStore {

  /**
   * 标记投影开始。已存在记录时递增 attempt_count 并刷新 source_sha256。
   *
   * @param document 已持久化的文档身份
   * @throws RuntimeException 状态表写入失败时抛出，由投影器转为 FAILED 结果
   */
  void markStarted(StoredRfcDocument document);

  /**
   * 标记投影成功并记录完成时间。
   *
   * @param document 已持久化的文档身份
   * @throws RuntimeException 状态表更新失败时抛出
   */
  void markSucceeded(StoredRfcDocument document);

  /**
   * 标记投影失败并保留错误消息，completed_at 置空表示未完成。
   *
   * @param document     已持久化的文档身份
   * @param errorMessage 截断后的失败原因
   * @throws RuntimeException 状态表更新失败时抛出
   */
  void markFailed(StoredRfcDocument document, String errorMessage);
}
