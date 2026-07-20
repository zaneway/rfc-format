package com.github.zaneway.format.rfc.chunk;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

/**
 * 流式读取 RFC chunk JSONL，并按连续的 RFC 编号分组输出。
 *
 * <p>聚合文件超过 500 MiB，不能使用 {@code readAllLines} 或先反序列化为完整列表。
 * 只保留当前 RFC 的块，内存上界由单个 RFC 的块数决定。</p>
 */
@Component
public class RfcChunkJsonlReader {

  private final ObjectMapper objectMapper;

  public RfcChunkJsonlReader(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * 逐行解析文件，并按 RFC 将不可变块列表交给消费方。
   *
   * @param jsonlPath 聚合 JSONL 路径
   * @param consumer  每个 RFC 完整读取后调用一次；运行时异常会停止导入
   * @return 原始 chunk 数与 RFC 分组数
   * @throws IOException JSON、字段约束或 RFC 分组顺序不合法时抛出，消息包含行号
   */
  public RfcChunkReadResult readGrouped(Path jsonlPath,
      BiConsumer<Integer, List<RfcChunk>> consumer) throws IOException {
    int lineNumber = 0;
    int chunkCount = 0;
    int rfcCount = 0;
    Integer currentRfc = null;
    List<RfcChunk> currentChunks = new ArrayList<>();
    Set<Integer> completedRfcs = new HashSet<>();

    try (BufferedReader reader = Files.newBufferedReader(jsonlPath, StandardCharsets.UTF_8)) {
      String line;
      while ((line = reader.readLine()) != null) {
        lineNumber++;
        if (line.isBlank()) {
          continue;
        }
        RfcChunk chunk = parse(line, lineNumber, jsonlPath);
        validate(chunk, lineNumber, jsonlPath);
        if (currentRfc != null && chunk.rfcNumber() != currentRfc) {
          consumer.accept(currentRfc, List.copyOf(currentChunks));
          completedRfcs.add(currentRfc);
          rfcCount++;
          currentChunks.clear();
          if (completedRfcs.contains(chunk.rfcNumber())) {
            throw invalid(jsonlPath, lineNumber,
                "RFC " + chunk.rfcNumber() + " is not stored in one contiguous group");
          }
        }
        currentRfc = chunk.rfcNumber();
        currentChunks.add(chunk);
        chunkCount++;
      }
    }

    if (currentRfc == null) {
      throw new IOException("RFC chunk JSONL is empty: " + jsonlPath);
    }
    consumer.accept(currentRfc, List.copyOf(currentChunks));
    return new RfcChunkReadResult(chunkCount, rfcCount + 1);
  }

  private RfcChunk parse(String line, int lineNumber, Path path) throws IOException {
    try {
      return objectMapper.readValue(line, RfcChunk.class);
    } catch (RuntimeException exception) {
      throw new IOException("Invalid JSON at line " + lineNumber + " of " + path, exception);
    }
  }

  /**
   * 入口即校验事实字段，避免先删除 Qdrant 中的旧 RFC 后才发现当前分组存在坏数据。
   */
  private static void validate(RfcChunk chunk, int lineNumber, Path path) throws IOException {
    if (chunk.rfcNumber() <= 0) {
      throw invalid(path, lineNumber, "rfc_number must be positive");
    }
    if (chunk.depth() < 0) {
      throw invalid(path, lineNumber, "depth must not be negative");
    }
    if (chunk.text() == null || chunk.text().isBlank()) {
      throw invalid(path, lineNumber, "text is required");
    }
    if (chunk.references() == null) {
      throw invalid(path, lineNumber, "references is required");
    }
    if (chunk.sourceFile() == null || chunk.sourceFile().isBlank()) {
      throw invalid(path, lineNumber, "source_file is required");
    }
    if (chunk.startLine() < 1 || chunk.endLine() < chunk.startLine()) {
      throw invalid(path, lineNumber, "source line range is invalid");
    }
  }

  private static IOException invalid(Path path, int lineNumber, String message) {
    return new IOException("Invalid RFC chunk at line " + lineNumber + " of " + path + ": " + message);
  }
}
