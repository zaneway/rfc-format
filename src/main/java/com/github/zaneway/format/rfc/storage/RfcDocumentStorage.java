package com.github.zaneway.format.rfc.storage;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;

/**
 * RFC 结构化解析结果的关系型事实存储边界。
 *
 * <p>实现方须保证同一 {@code document_key} 的派生数据在同一事务内原子替换，
 * 使下游向量投影只读取完整一致的快照。</p>
 */
public interface RfcDocumentStorage {

  /**
   * 原子替换同一 document key 对应的全部派生结构数据。
   *
   * @param document  已完成确定性解析的 RFC 文档
   * @param inputPath 本次解析输入文件的绝对路径，用于导入审计溯源
   * @return 已持久化文档的数据库标识及投影所需的版本摘要
   * @throws RuntimeException 事务内任一批次写入失败时整体回滚并抛出
   */
  StoredRfcDocument store(RfcDocument document, String inputPath);
}
