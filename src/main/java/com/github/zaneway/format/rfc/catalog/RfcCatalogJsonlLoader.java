package com.github.zaneway.format.rfc.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 读取 {@code rfc-summaries.jsonl}，按 {@code rfc_number} 去重（保留最后一次出现）。
 */
@Component
public class RfcCatalogJsonlLoader {

  private final ObjectMapper objectMapper;

  public RfcCatalogJsonlLoader(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * @param jsonlPath 目录摘要 JSONL 文件
   * @return 去重后的摘要列表，顺序为首次出现顺序，内容以后出现的记录为准
   */
  public List<RfcCatalogSummary> load(Path jsonlPath) throws IOException {
    Map<String, RfcCatalogSummary> byNumber = new LinkedHashMap<>();
    try (BufferedReader reader = Files.newBufferedReader(jsonlPath, StandardCharsets.UTF_8)) {
      String line;
      int lineNumber = 0;
      while ((line = reader.readLine()) != null) {
        lineNumber++;
        if (line.isBlank()) {
          continue;
        }
        RfcCatalogSummary summary;
        try {
          summary = objectMapper.readValue(line, RfcCatalogSummary.class);
        } catch (IOException exception) {
          throw new IOException("Invalid JSON at line " + lineNumber + " of " + jsonlPath, exception);
        }
        String rfcNumber = summary.rfcNumber() == null ? "" : summary.rfcNumber().trim();
        if (rfcNumber.isEmpty()) {
          throw new IOException("Missing rfc_number at line " + lineNumber + " of " + jsonlPath);
        }
        byNumber.put(rfcNumber, summary);
      }
    }
    return new ArrayList<>(byNumber.values());
  }
}
