package com.github.zaneway.format.rfc.controller;

import com.github.zaneway.format.rfc.llm.LlmRfcIngestionResult;
import com.github.zaneway.format.rfc.llm.LlmRfcIngestionService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/** RFC TXT 的 LLM 结构化导入 HTTP 入口。 */
@RestController
@RequestMapping("/api/rfc/llm")
public final class LlmRfcIngestionController {
  private final LlmRfcIngestionService service;
  public LlmRfcIngestionController(LlmRfcIngestionService service) { this.service = service; }

  /** @param file UTF-8 RFC TXT 文件；仅接受 .txt，避免将二进制内容交给模型 */
  @PostMapping(value = "/ingestions", consumes = "multipart/form-data")
  public LlmRfcIngestionResult ingest(@RequestParam("file") MultipartFile file) {
    String fileName = file.getOriginalFilename();
    if (file.isEmpty() || fileName == null || !fileName.toLowerCase().endsWith(".txt")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "file must be a non-empty .txt RFC document");
    }
    try {
      return service.ingest(fileName, new String(file.getBytes(), StandardCharsets.UTF_8));
    } catch (IOException exception) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot read RFC text file", exception);
    }
  }
}
