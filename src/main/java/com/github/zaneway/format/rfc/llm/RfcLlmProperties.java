package com.github.zaneway.format.rfc.llm;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** RFC 结构化解析模型的 OpenAI 兼容连接配置。 */
@ConfigurationProperties("rfc.llm")
public record RfcLlmProperties(String baseUrl, String apiKey, String model, String promptFile) {
}
