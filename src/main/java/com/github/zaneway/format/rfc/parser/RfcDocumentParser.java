package com.github.zaneway.format.rfc.parser;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;

import java.io.IOException;
import java.nio.file.Path;

/**
 * RFC 文本到结构化文档的解析边界（流水线入口契约）。
 *
 * <p>实现必须保持确定性：相同字节输入 → 相同 {@link RfcDocument}（含章节、单元、
 * 引用关系与规则对象的 ID 与行号）。本接口不负责写文件、落库或向量化， 只做无副作用的领域模型转换；Spring 默认实现为 {@link RfcTextParser}。</p>
 *
 * <p>调用方应把 {@link IOException} 视为输入不可解析（非普通文件、头部缺失等），
 * 而非可重试的瞬时故障。</p>
 */
@FunctionalInterface
public interface RfcDocumentParser {

  /**
   * 解析单个 UTF-8 RFC TXT 文件。
   *
   * @param sourceFile 普通文件路径（非目录）；实现通常要求可读的 .txt
   * @return 不可变的文档聚合根，含 sections / units / relations / objects
   * @throws IOException 文件不可读、不是普通文件，或头部关键字段缺失时抛出
   */
  RfcDocument parse(Path sourceFile) throws IOException;
}
