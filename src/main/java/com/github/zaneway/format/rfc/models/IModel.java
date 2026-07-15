package com.github.zaneway.format.rfc.models;

import org.springframework.ai.chat.model.*;

/**
 * AI 模型工厂接口。
 * <p>
 * 设计意图: - 通过该接口屏蔽不同厂商 (MiniMax、DeepSeek、OpenAI 等) 的 ChatModel 构建差异, 上层调用方仅依赖 {@link Models}
 * 枚举即可获得对应的 Spring AI {@link ChatModel}。 - 实现类可结合配置中心动态切换模型,实现多模型路由 / 灰度。
 */
public interface IModel {

  /**
   * 根据模型枚举构建可用的 Spring AI {@link ChatModel} 实例。
   *
   * @param name 模型枚举标识；未注册的枚举值由实现方决定抛错或回退
   * @return 已配置好 baseUrl、apiKey 等参数的 ChatModel
   * @throws RuntimeException 密钥缺失或厂商 API 不可达时由底层客户端抛出
   */
  ChatModel getModel(Models name);

}
