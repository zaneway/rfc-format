package com.github.zaneway.format.rfc.vector;

import java.util.List;

/**
 * 向量数据库操作边界。
 *
 * <p>替换语义：先按 rfc_number 删除旧向量再批量写入，保证同一 RFC 重导入后
 * 集合内不会残留已删除单元的陈旧向量。删除与写入不是事务操作，失败后需幂等重试。</p>
 */
public interface RfcVectorClient {

  /**
   * 以整 RFC 为一致性粒度替换向量集合。
   *
   * @param rfcNumber RFC 编号，作为删除过滤条件与元数据锚点
   * @param documents 待生成 embedding 并写入的向量文档列表
   * @return 实际写入与因 embedding 失败跳过的文档数
   * @throws RuntimeException 向量库删除或写入失败时抛出
   */
  RfcVectorWriteResult replace(String rfcNumber, List<RfcVectorDocument> documents);
}
