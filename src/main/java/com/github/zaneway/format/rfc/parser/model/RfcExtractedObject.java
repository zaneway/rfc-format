package com.github.zaneway.format.rfc.parser.model;

import java.util.Map;

/**
 * RFC 原文中以确定性语法抽取的规则对象，由解析器「规则对象」阶段产出。
 *
 * <p>只接受 ASN.1/ABNF/代码章节等明确语法，不从自由正文推断；每个对象通过
 * {@code defines} 关系（{@link RfcRelation}）与其来源 {@link RfcUnit} 关联。 对象位置使用来源单元
 * {@link RfcUnit#content()} 的 UTF-16 偏移，行号则保持 RFC TXT 原始物理行号；二者共同保证后续处理可以回放到原始证据，而不依赖二次解析。</p>
 *
 * <p>对象类型约定（由解析器按明确语法产出，不从自由正文推断）：</p>
 * <ul>
 *   <li>{@code oid_definition} — ASN.1 OBJECT IDENTIFIER 赋值</li>
 *   <li>{@code field_definition} — SEQUENCE/SET/CHOICE 容器内的顶层字段</li>
 *   <li>{@code abnf_rule} — ABNF 规则名</li>
 *   <li>{@code status_code} / {@code error_code} — 状态码或错误码章节中的代码定义</li>
 * </ul>
 */
public record RfcExtractedObject(
    /** 对象唯一 ID，形如 "{sourceUnitId}:{objectType}:{name}:{occurrence}"。 */
    String id,
    /** 对象类型，例如 "oid_definition" / "field_definition" / "abnf_rule"。 */
    String objectType,
    /** 规则对象在原文中的名称（字段名、规则名或代码标识符）。 */
    String name,
    /** 规范化后的取值（例如 OID 数字弧 "1.2.840"、代码数字 "200"）；无法规范化时为空串。 */
    String normalizedValue,
    /** 产出该对象的来源知识单元 ID。 */
    String sourceUnitId,
    /** 起始行号 (1-based,基于原 RFC TXT 物理行)。 */
    int startLine,
    /** 结束行号 (1-based,基于原 RFC TXT 物理行)。 */
    int endLine,
    /** 在来源单元 content 中的 UTF-16 起始偏移 (含)。 */
    int startOffset,
    /** 在来源单元 content 中的 UTF-16 结束偏移 (不含)。 */
    int endOffset,
    /** 与对象类型相关的结构化属性（如 notation、declaredType、optional 等）。 */
    Map<String, Object> attributes) {

  /**
   * 防御性复制可选的结构化属性，避免解析产物在聚合边界被外部修改。
   */
  public RfcExtractedObject {
    attributes = Map.copyOf(attributes);
  }
}
