package com.github.zaneway.format.rfc.catalog;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import java.util.List;

/**
 * RFC 摘要目录向量库操作边界；写入目标为独立的 catalog collection。
 */
public interface RfcCatalogVectorClient {

  /**
   * 全量替换 catalog collection 中的摘要点。
   *
   * <p>先按 {@code unit_type=catalog_summary} 删除旧点，再批量写入新快照。
   * 两步非事务：删除成功但写入失败时集合暂时为空，需重试恢复。</p>
   *
   * @param documents 待写入文档；空列表等效于清空目录投影
   */
  void replaceAll(List<RfcVectorDocument> documents);
}
