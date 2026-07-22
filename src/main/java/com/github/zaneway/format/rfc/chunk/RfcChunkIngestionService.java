package com.github.zaneway.format.rfc.chunk;

import com.github.zaneway.format.rfc.vector.RfcVectorClient;
import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import com.github.zaneway.format.rfc.vector.RfcVectorWriteResult;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 将 {@code all_chunks.jsonl} 以 RFC 为一致性边界流式投影到 Qdrant。
 */
@Service
public class RfcChunkIngestionService {

  private static final Logger log = LoggerFactory.getLogger(RfcChunkIngestionService.class);

  private final RfcChunkJsonlReader reader;
  private final RfcChunkVectorMapper mapper;
  private final RfcVectorClient vectorClient;

  public RfcChunkIngestionService(RfcChunkJsonlReader reader, RfcChunkVectorMapper mapper,
      RfcVectorClient vectorClient) {
    this.reader = reader;
    this.mapper = mapper;
    this.vectorClient = vectorClient;
  }

  /**
   * 流式导入聚合文件。同 RFC 采用先删除旧点、再分批写入新点的幂等替换语义。
   *
   * <p>Qdrant 不支持跨 RFC 事务；中途失败时，已完成 RFC 保持新版本，当前 RFC
   * 可能为空或部分写入。使用同一路径重试即可从文件开头安全恢复。</p>
   *
   * @param jsonlPath {@code all_chunks.jsonl} 文件路径
   * @return 完整成功后的读取与写入统计
   * @throws IOException 文件读取、JSON 解析或数据契约校验失败
   */
  public RfcChunkIngestionResult ingest(Path jsonlPath) throws IOException {
    long startedAt = System.nanoTime();
    AtomicInteger writtenPoints = new AtomicInteger();
    AtomicInteger skippedChunks = new AtomicInteger();
    log.info("开始导入 RFC chunk 聚合文件，路径={}", jsonlPath.toAbsolutePath());

    RfcChunkReadResult readResult = reader.readGrouped(jsonlPath, (rfcNumber, chunks) -> {
      List<RfcVectorDocument> documents = new ArrayList<>();
      for (RfcChunk chunk : chunks) {
        documents.add(mapper.toVectorDocument(chunk));
      }
      long rfcStartedAt = System.nanoTime();
      log.info("开始替换 RFC 向量，rfc={}，逻辑块={}，向量点={}",
          rfcNumber, chunks.size(), documents.size());
      try {
        // 以 RFC 为最小一致性边界做 replace：先删旧点，再批量写入新点。
        RfcVectorWriteResult writeResult = vectorClient.replace(
            Integer.toString(rfcNumber), List.copyOf(documents));
        writtenPoints.addAndGet(writeResult.writtenPointCount());
        skippedChunks.addAndGet(writeResult.skippedDocumentCount());
      } catch (RuntimeException exception) {
        log.error("RFC 向量替换失败，rfc={}，逻辑块={}，向量点={}，耗时毫秒={}",
            rfcNumber, chunks.size(), documents.size(), elapsedMillis(rfcStartedAt), exception);
        // 追加 RFC 上下文，便于运维快速定位失败边界并重试。
        throw new IllegalStateException("Failed to replace Qdrant points for RFC " + rfcNumber,
            exception);
      }
      log.info("RFC 向量替换完成，rfc={}，候选={}，累计写入={}，累计跳过={}，耗时毫秒={}",
          rfcNumber, documents.size(), writtenPoints.get(), skippedChunks.get(),
          elapsedMillis(rfcStartedAt));
    });

    long durationMillis = elapsedMillis(startedAt);
    log.info("RFC chunk 聚合文件导入完成，逻辑块={}，向量点={}，跳过={}，RFC 数={}，耗时毫秒={}",
        readResult.chunkCount(), writtenPoints.get(), skippedChunks.get(), readResult.rfcCount(),
        durationMillis);
    return new RfcChunkIngestionResult(readResult.chunkCount(), writtenPoints.get(),
        skippedChunks.get(), readResult.rfcCount(), durationMillis);
  }

  private static long elapsedMillis(long startedAt) {
    return (System.nanoTime() - startedAt) / 1_000_000;
  }
}
