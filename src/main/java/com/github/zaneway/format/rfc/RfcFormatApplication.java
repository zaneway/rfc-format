package com.github.zaneway.format.rfc;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

/**
 * RFC 格式化与解析服务的 Spring Boot 启动入口。
 *
 * <p>引导 ApplicationContext 初始化后，自动装配解析器、MySQL 存储、
 * Qdrant 投影及 HTTP 控制器等流水线组件。</p>
 */
@SpringBootApplication
public class RfcFormatApplication {


  /**
   * JVM 入口方法:委托给 Spring Boot 启动嵌入式 Servlet 容器并加载所有 Bean。
   *
   * @param args 命令行参数,可用于覆盖 application.yaml 中的配置
   */
  public static void main(String[] args) {
    SpringApplication.run(RfcFormatApplication.class, args);

  }

}
