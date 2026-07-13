package com.github.zaneway.format.rfc.models.chat;

import java.util.*;
import org.springframework.ai.chat.client.*;
import org.springframework.ai.chat.client.advisor.*;
import org.springframework.ai.chat.memory.*;
import org.springframework.ai.openai.*;

/**
 * MiniMax 系列模型的 Spring AI ChatClient 装配与样例调用。
 *
 * 用途:
 * - 通过 OpenAI 兼容协议访问 MiniMax-M3 模型;
 * - 提供构建 ChatClient、单轮 / 多轮会话样例,以及模型输出清洗能力。
 *
 * 安全提示:
 * - 当前 KEY 为硬编码字符串,严禁提交到生产仓库;
 *   上线前应迁移到配置中心 / 环境变量 / Vault 等密钥管理设施。
 */
public class MinimaxChat {
  // MiniMax 提供的、与 OpenAI 兼容的 Chat Completions 接入点
  static final String OPENAI_URL = "https://api.minimaxi.com/v1";
  // 调用 MiniMax-M3 所需的 API Key (硬编码仅用于本地调试,生产环境必须外置)
  static final String KEY = "sk-cp-Lvtc6XhNZe8Dki5_kNrqgmBeNTyxdr_B0zyYrphpCSdvRi3Wd92NnNd3h4peSEKvFOpOxJbSF0MRomH5mOTZMrAC32fI5aABrqFzVhgoFABQKQmEIf662SI";
  // 模型标识,需与厂商侧注册名称保持一致
  static final String  MODEL_NAME = "MiniMax-M3";

//  @Resource
//  private ChatModel chatModel;


  /**
   * 构建一个开启了短期记忆的 ChatClient 并发起单轮调用,用于本地冒烟测试。
   *
   * 注意:此方法仅打印响应内容,不返回结果,适合作为脚本式验证入口。
   */
  public void buildClient(){

    // 1. 通过 OpenAI 兼容协议构造底层 ChatModel,并通过 extraBody 关闭思考模式
    OpenAiChatModel model = OpenAiChatModel.builder()
        .options(OpenAiChatOptions.builder().baseUrl(OPENAI_URL).apiKey(KEY).model(MODEL_NAME)
            //关闭思考模式
            .extraBody(Map.of("enable_thinking",false))
            .build())
        .build();

    ChatClient.Builder builder = ChatClient.builder(model);

    // 2. 设置默认 System Prompt + 基于滑动窗口的会话记忆 Advisor
    ChatClient client = builder.defaultSystem("测试一下默认提示词")
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(
            MessageWindowChatMemory.builder().build()).build()).build();

    // 3. 触发一次同步调用,直接输出响应内容
    String content = client.prompt("我问个什么好？ 我是用户输入").user("").call().content();
    System.out.println(content);


  }


  /**
   * 本地调试入口:演示多轮会话记忆与会话 ID 注入。
   *
   * @param args 命令行参数 (未使用)
   */
  public static void main(String[] args) {

    // 构造底层 ChatModel;关闭思考模式,注意思考模型本身无法真正关闭
    OpenAiChatModel model = OpenAiChatModel.builder()
        .options(OpenAiChatOptions.builder().baseUrl(OPENAI_URL).apiKey(KEY).model(MODEL_NAME)
            //关闭思考模式，思考模型无法关闭
            .extraBody(Map.of("enable_thinking",false))
            .build())
        .build();

    ChatClient.Builder builder = ChatClient.builder(model);

//    ChatClient client = builder.defaultSystem("你是zaneway的个人AI助理，精通PKI/CA体系知识")
//        .build();

      // 指定身份 (System Prompt) 并装配会话记忆 Advisor
      ChatClient client = builder.defaultSystem("你是zaneway的个人AI助理，精通PKI/CA体系知识,如果用户问你是谁，你就回答：我是zaneway")
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(
            MessageWindowChatMemory.builder().build()).build()).build();

    // 第一轮:自我介绍类问题;通过 CONVERSATION_ID 关联会话上下文
    String content = client.prompt().user("你是谁").advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "zaneway-demo-session")).call().content();
    System.out.println(content);

    // 第二轮:验证多轮记忆是否生效 (模型应能回答"刚才问了什么")
    content = client.prompt().user("刚才我问了什么？").advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "zaneway-demo-session")).call().content();
    System.out.println(content);


  }



  /**
   * 清洗模型原始输出:截取 {@code </think>} 标签之后的内容,
   * 用于去掉思考模型在最终答案前的内部推理片段。
   *
   * @param message 模型返回的原始文本
   * @return 截取后的最终答复片段
   */
  public String cleanModelOutput(String message){
    String end = "</think>";
    int index = message.indexOf(end);
    return message.substring(index, message.length());
  }


}
