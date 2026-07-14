package com.github.zaneway.format.rfc.vector;

/**
 * MySQL 到 Qdrant 向量投影的最终状态，持久化于 {@code rfc_vector_projection} 表。
 */
public enum ProjectionStatus {

  /**
   * 向量已成功写入 Qdrant，状态表记录完成时间。
   */
  SUCCEEDED,

  /**
   * 投影过程中任一步骤失败；MySQL 事实数据不受影响，可重试。
   */
  FAILED
}
