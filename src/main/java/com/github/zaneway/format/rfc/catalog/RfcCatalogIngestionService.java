package com.github.zaneway.format.rfc.catalog;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 将 {@code rfc-summaries.jsonl} 投影到独立的 Qdrant catalog collection。
 */
@Service
public class RfcCatalogIngestionService {

  private static final Logger log = LoggerFactory.getLogger(RfcCatalogIngestionService.class);

  private final RfcCatalogJsonlLoader loader;
  private final RfcCatalogEmbeddingMapper mapper;
  private final RfcCatalogVectorClient vectorClient;

  public RfcCatalogIngestionService(RfcCatalogJsonlLoader loader,
      RfcCatalogEmbeddingMapper mapper,
      RfcCatalogVectorClient vectorClient) {
    this.loader = loader;
    this.mapper = mapper;
    this.vectorClient = vectorClient;
  }

  /**
   * 读取 JSONL、去重、映射并全量替换 catalog 向量集合。
   *
   * @param jsonlPath 摘要目录文件路径
   * @return 读入、写入与重复跳过统计
   */
  public RfcCatalogIngestionResult ingest(Path jsonlPath) throws IOException {
    log.info("开始导入 RFC 摘要目录，路径={}", jsonlPath.toAbsolutePath());
    int rawCount = countNonBlankLines(jsonlPath);
    List<RfcCatalogSummary> summaries = loader.load(jsonlPath);
    List<RfcVectorDocument> documents = summaries.stream()
        .map(mapper::toVectorDocument)
        .toList();
    vectorClient.replaceAll(documents);
    int duplicates = Math.max(0, rawCount - documents.size());
    log.info("RFC 摘要目录导入完成，读取={}，写入={}，重复覆盖={}",
        rawCount, documents.size(), duplicates);
    return new RfcCatalogIngestionResult(rawCount, documents.size(), duplicates);
  }

  private static int countNonBlankLines(Path jsonlPath) throws IOException {
    int count = 0;
    try (var reader = java.nio.file.Files.newBufferedReader(jsonlPath)) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.isBlank()) {
          count++;
        }
      }
    }
    return count;
  }
}
