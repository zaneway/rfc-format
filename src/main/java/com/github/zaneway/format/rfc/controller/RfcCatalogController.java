package com.github.zaneway.format.rfc.controller;

import com.github.zaneway.format.rfc.catalog.RfcCatalogIngestionResult;
import com.github.zaneway.format.rfc.catalog.RfcCatalogIngestionService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * RFC 摘要目录导入接口：将 {@code rfc-summaries.jsonl} 写入独立 Qdrant collection。
 */
@RestController
public class RfcCatalogController {

  private static final Logger log = LoggerFactory.getLogger(RfcCatalogController.class);

  private final RfcCatalogIngestionService ingestionService;

  public RfcCatalogController(RfcCatalogIngestionService ingestionService) {
    this.ingestionService = ingestionService;
  }

  /**
   * 从本机路径读取 JSONL 并全量替换 catalog collection。
   *
   * @param path {@code rfc-summaries.jsonl} 的绝对或相对路径
   */
  @PostMapping("/rfc/catalog/ingestions")
  public RfcCatalogIngestionResult ingest(@RequestParam("path") String path) {
    if (path == null || path.isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "path is required");
    }
    Path jsonl = Path.of(path).toAbsolutePath().normalize();
    if (!Files.isRegularFile(jsonl)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JSONL file not found: " + jsonl);
    }
    log.info("收到 RFC catalog 导入请求，路径={}", jsonl);
    try {
      RfcCatalogIngestionResult result = ingestionService.ingest(jsonl);
      log.info("RFC catalog 导入完成，读取={}，写入={}，重复覆盖={}",
          result.readCount(), result.writtenCount(), result.duplicateSkippedCount());
      return result;
    } catch (IOException exception) {
      log.error("RFC catalog 导入失败，路径={}", jsonl, exception);
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT,
          "Unable to ingest catalog JSONL", exception);
    }
  }
}
