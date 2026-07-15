package com.github.zaneway.format.rfc.models;

/**
 * 系统支持的 AI 模型枚举，供 Chat 层路由使用。
 *
 * <p>与 RFC 解析/存储流水线解耦，用于后续基于 LLM 的问答或辅助分析场景。</p>
 * <p>
 * 字段约定: - {@code type}  : 厂商/系列标识 (如 MiniMax、DeepSeek) - {@code name}  : 实际调用时使用的具体模型名
 * <p>
 * 注意:新增模型时务必在此处追加,避免在业务代码中硬编码字符串。
 */
public enum Models {

  // MiniMax 厂商的 MiniMax-M3 模型
  MiniMax_M3("MiniMax", "MiniMax-M3"),
  // DeepSeek 厂商的 DeepSeek_V4 模型
  DeepSeek_V4("DeepSeek", "DeepSeek_V4"),

  ;


  /**
   * 厂商或模型系列标识。
   */
  private String type;

  /**
   * 调用方使用的具体模型名称 (与厂商 API 的 model 字段对齐)。
   */
  private String name;

  /**
   * 枚举构造方法。
   *
   * @param type 模型所属厂商或系列
   * @param name 模型实际调用名称
   */
  Models(String type, String name) {
    this.type = type;
    this.name = name;
  }


  /**
   * @return 模型所属厂商或系列
   */
  public String getType() {
    return type;
  }

  /**
   * @return 实际调用时使用的模型名称
   */
  public String getName() {
    return name;
  }
}
