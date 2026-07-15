package com.github.zaneway.format.rfc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RFC 解析与持久化共用的 Jackson 2 JSON 配置。
 */
@Configuration
public class RfcJsonConfiguration {

  /**
   * 提供解析模型所使用的 Jackson 2 ObjectMapper。
   *
   * <p>Spring Boot 4 默认配置的是 Jackson 3 类型；RFC 模型与现有依赖仍基于
   * {@code com.fasterxml.jackson}，因此显式声明该 Bean。</p>
   */
  @Bean
  public ObjectMapper rfcObjectMapper() {
    return new ObjectMapper();
  }
}
