package com.github.zaneway.format.rfc.vector;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.storage.StoredRfcDocument;

/**
 * 将已提交的 RFC 文档投影到向量库的边界。
 *
 * <p>位于 MySQL 存储之后，仅消费已持久化的事实数据；投影失败不反向回滚关系库。</p>
 */
public interface RfcVectorProjector {

  /**
   * 执行一次向量投影。
   *
   * @param storedDocument MySQL 中已持久化的文档身份
   * @param document       内存中的完整解析结果
   * @return 可观测的投影结果，失败时通过 status 表达而非抛异常
   */
  ProjectionResult project(StoredRfcDocument storedDocument, RfcDocument document);
}
