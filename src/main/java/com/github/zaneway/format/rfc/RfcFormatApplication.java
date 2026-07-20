package com.github.zaneway.format.rfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RFC chunk 向量导入服务的 Spring Boot 启动入口。
 *
 * <p>启动后装配 JSONL 流式读取、超长块切分、Ollama embedding、
 * Qdrant 批量写入和运维 HTTP 接口。</p>
 */
@SpringBootApplication
public class RfcFormatApplication {
  /**
   * JVM 入口方法：委托 Spring Boot 启动服务并加载 RFC chunk 入库组件。
   *
   * @param args 命令行参数，可用于覆盖 application.yaml 中的配置
   */
  public static void main(String[] args) {
    SpringApplication.run(RfcFormatApplication.class, args);
  }
}
