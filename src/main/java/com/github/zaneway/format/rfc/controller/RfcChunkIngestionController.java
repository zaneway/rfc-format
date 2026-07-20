package com.github.zaneway.format.rfc.controller;

import com.github.zaneway.format.rfc.chunk.RfcChunkIngestionResult;
import com.github.zaneway.format.rfc.chunk.RfcChunkIngestionService;
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
 * RFC chunk 聚合文件到 Qdrant 的管理接口。
 */
@RestController
public class RfcChunkIngestionController {

  private static final Logger log = LoggerFactory.getLogger(RfcChunkIngestionController.class);

  private final RfcChunkIngestionService ingestionService;

  public RfcChunkIngestionController(RfcChunkIngestionService ingestionService) {
    this.ingestionService = ingestionService;
  }

  /**
   * 同步导入本机 JSONL 文件。该操作耗时较长，应由受保护的运维入口调用。
   *
   * @param path 聚合文件的绝对或工作目录相对路径
   * @return 全部 RFC 成功写入后的统计
   */
  @PostMapping("/rfc/chunks/ingestions")
  public RfcChunkIngestionResult ingest(
      @RequestParam(value = "path", defaultValue = "docs/rfc/all_chunks.jsonl") String path) {
    if (path == null || path.isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "path is required");
    }
    Path jsonlPath = Path.of(path).toAbsolutePath().normalize();
    if (!Files.isRegularFile(jsonlPath) || !Files.isReadable(jsonlPath)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Readable JSONL file not found: " + jsonlPath);
    }
    log.info("收到 RFC chunk 导入请求，路径={}", jsonlPath);
    try {
      return ingestionService.ingest(jsonlPath);
    } catch (IOException exception) {
      log.error("RFC chunk 文件读取或校验失败，路径={}", jsonlPath, exception);
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT,
          "Unable to parse RFC chunk JSONL", exception);
    } catch (RuntimeException exception) {
      log.error("RFC chunk Qdrant 导入失败，路径={}", jsonlPath, exception);
      throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
          "Unable to write RFC chunks to Qdrant", exception);
    }
  }
}
