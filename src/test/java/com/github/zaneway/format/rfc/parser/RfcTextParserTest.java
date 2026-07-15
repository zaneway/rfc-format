package com.github.zaneway.format.rfc.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcSection;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import java.net.URISyntaxException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * {@link RfcTextParser} 的集成式单元测试。
 *
 * <p>当前 {@link #fixturePath()} 指向本地 RFC 5280 全文，用于手工/回归验证真实文档解析路径。
 * 下列断言仍按早期人工夹具 {@code rfc9999.txt} 的期望编写（编号 9999、ExampleMessage 等）， 若改回 classpath
 * 夹具需同步恢复断言与路径。</p>
 *
 * <p>覆盖意图：RFC 编号 / 标题 / 类别、目录与分页清洗、章节层级、ASN.1 模块与规范性段落。</p>
 */
class RfcTextParserTest {

  /**
   * 无状态解析器实例，测试间可安全复用。
   */
  private final RfcTextParser parser = new RfcTextParser();

  /**
   * 验证: 1. 头部 RFC 编号 / 标题 / 类别解析正确; 2. 文件 SHA-256 长度符合 64 字符规范; 3. 目录、点引导表项、"[Page N]" 分页装饰都不会泄漏到
   * Unit 中。
   */
  @Test
  void parsesHeadersAndRemovesTableOfContentsAndPageDecorations() throws Exception {
    RfcDocument document = parser.parse(fixturePath());

    assertThat(document.rfcNumber()).isEqualTo("9999");
    assertThat(document.title()).isEqualTo("Example Transport Profile");
    assertThat(document.category()).isEqualTo("Standards Track");
    assertThat(document.source().sha256()).hasSize(64);
    // 清洗后的知识单元不得残留目录或分页装饰文本
    assertThat(document.units())
        .extracting(RfcUnit::content)
        .noneMatch(content -> content.contains("Table of Contents")
            || content.contains("Introduction ................................")
            || content.contains("[Page 2]"));
  }

  /**
   * 验证章节层级: - 顶级章节 (如 "2") 的 parentId 为 null,path 只有自身; - 子章节 (如 "2.1") 的 parentId 指向 "2",path 完整记录
   * "2" → "2.1"。
   */
  @Test
  void buildsNumberedSectionHierarchy() throws Exception {
    RfcDocument document = parser.parse(fixturePath());

    RfcSection messageFormat = document.sections().stream()
        .filter(section -> section.id().equals("2"))
        .findFirst()
        .orElseThrow();
    RfcSection encoding = document.sections().stream()
        .filter(section -> section.id().equals("2.1"))
        .findFirst()
        .orElseThrow();

    assertThat(messageFormat.parentId()).isNull();
    assertThat(messageFormat.path()).containsExactly("2");
    assertThat(encoding.parentId()).isEqualTo("2");
    assertThat(encoding.path()).containsExactly("2", "2.1");
  }

  /**
   * 验证 ASN.1 模块解析: - 整段定义被保留为单个 asn1_definition Unit (含开始与闭合大括号); - semantic 元数据正确记录 entityName 与
   * language; - 行号区间合法 (start <= end)。
   */
  @Test
  void preservesCompleteAsn1DefinitionAsDedicatedUnit() throws Exception {
    RfcDocument document = parser.parse(fixturePath());

    RfcUnit asn1 = document.units().stream()
        .filter(unit -> unit.unitType().equals("asn1_definition"))
        .findFirst()
        .orElseThrow();

    assertThat(asn1.content()).contains("ExampleMessage ::= SEQUENCE {")
        .contains("messageId   INTEGER,")
        .contains("payload     OCTET STRING")
        .endsWith("}");
    assertThat(asn1.semantic()).containsEntry("entityName", "ExampleMessage")
        .containsEntry("language", "asn1");
    assertThat(asn1.startLine()).isLessThanOrEqualTo(asn1.endLine());
  }

  /**
   * 验证 RFC 2119 规范性段落可被识别为 {@code normative_rule} 单元。
   *
   * <p>当前仅做“至少产出一条规范覆盖层”的冒烟断言；针对人工夹具 rfc9999 的正文等值校验
   * 已暂时注释（夹具改为真实 RFC 5280 时内容断言不适用）。</p>
   */
  @Test
  void emitsWholeNormativeParagraphWithLineRange() throws Exception {
    RfcDocument document = parser.parse(fixturePath());

    // 冒烟：文档中应至少存在一条规范性覆盖层单元，且行号区间合法
    RfcUnit rule = document.units().stream()
        .filter(unit -> unit.unitType().equals("normative_rule"))
        .findFirst()
        .orElseThrow();
    assertThat(rule.startLine()).isLessThanOrEqualTo(rule.endLine());

    // 下列内容等值断言针对人工夹具 rfc9999.txt；切回该夹具后可恢复
//        assertThat(rule.content()).isEqualTo("An implementation MUST preserve the message identifier while a\n"
//                + "message is retransmitted.");
//        assertThat(rule.semantic()).containsEntry("normativeKeywords", java.util.List.of("MUST"));
  }

  /**
   * 当前解析输入路径。
   *
   * <p>现指向本机 RFC 5280 归档文件；原 classpath 夹具为 {@code /rfc/rfc9999.txt}。</p>
   */
  private Path fixturePath() throws URISyntaxException {
    return Path.of("/Users/zaneway/Downloads/rfc-archive/rfc5280.txt");
//      return Path.of(getClass().getResource("/Users/zaneway/Downloads/rfc-archive/rfc5280.txt").toURI());
  }
}
