package com.github.zaneway.format.rfc.llm;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/** RFC LLM 客户端装配；密钥只能来自运行时配置，禁止硬编码。 */
@Configuration
@EnableConfigurationProperties(RfcLlmProperties.class)
public class RfcLlmConfiguration {

  private final ResourceLoader resourceLoader;

  public RfcLlmConfiguration(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  /** @return 可调用的 LLM 端口；缺少配置时在实际导入时失败而不是启动时泄露密钥 */
  @Bean
  public RfcStructuredLlmClient rfcStructuredLlmClient(RfcLlmProperties properties) {
    if (blank(properties.baseUrl()) || blank(properties.apiKey()) || blank(properties.model()) || blank(properties.promptFile())) {
      return request -> { throw new IllegalStateException("rfc.llm.base-url, rfc.llm.api-key, rfc.llm.model and rfc.llm.prompt-file are required"); };
    }
    OpenAiChatModel model = OpenAiChatModel.builder().options(OpenAiChatOptions.builder()
        .baseUrl(properties.baseUrl()).apiKey(properties.apiKey()).model(properties.model())
        .temperature(0.0).build()).build();
    ChatClient client = ChatClient.create(model);
    String systemPrompt = readPrompt(properties.promptFile());
    return request -> client.prompt().system(systemPrompt).user(renderRequest(request)).call().content();
  }

  private static String renderRequest(RfcStructuredLlmRequest request) {
    return """
        处理模式：full_document
        文档来源类型：txt
        文件名：%s
        RFC 编号（可选）：
        输入是否带原始行号：false

        已知文档元数据：
        当前标题路径：
        前文衔接上下文：
        ─── RFC 文本开始 ───
        %s
        ─── RFC 文本结束 ───
        """.formatted(request.sourceFile(), request.documentText());
  }

  private String readPrompt(String promptFile) {
    Resource resource = resourceLoader.getResource(promptFile);
    try (InputStream input = resource.getInputStream()) {
      return new String(input.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException exception) {
      throw new IllegalStateException("Cannot read RFC LLM prompt file: " + promptFile, exception);
    }
  }

  private static boolean blank(String value) {
    return value == null || value.isBlank();
  }
}
