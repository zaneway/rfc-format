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
 * 测试夹具:src/test/resources/rfc/rfc9999.txt (一份人工构造的 RFC 样例),
 * 覆盖 RFC 编号、标题、章节层级、ASN.1 模块与规范性段落等关键解析路径。
 */
class RfcTextParserTest {

    // 测试共用解析器实例;内部无状态,可安全并发
    private final RfcTextParser parser = new RfcTextParser();

    /**
     * 验证:
     * 1. 头部 RFC 编号 / 标题 / 类别解析正确;
     * 2. 文件 SHA-256 长度符合 64 字符规范;
     * 3. 目录、点引导表项、"[Page N]" 分页装饰都不会泄漏到 Unit 中。
     */
    @Test
    void parsesHeadersAndRemovesTableOfContentsAndPageDecorations() throws Exception {
        RfcDocument document = parser.parse(fixturePath());

        assertThat(document.rfcNumber()).isEqualTo("9999");
        assertThat(document.title()).isEqualTo("Example Transport Profile");
        assertThat(document.category()).isEqualTo("Standards Track");
        assertThat(document.source().sha256()).hasSize(64);
        assertThat(document.units())
                .extracting(RfcUnit::content)
                .noneMatch(content -> content.contains("Table of Contents")
                        || content.contains("Introduction ................................")
                        || content.contains("[Page 2]"));
    }

    /**
     * 验证章节层级:
     * - 顶级章节 (如 "2") 的 parentId 为 null,path 只有自身;
     * - 子章节 (如 "2.1") 的 parentId 指向 "2",path 完整记录 "2" → "2.1"。
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
     * 验证 ASN.1 模块解析:
     * - 整段定义被保留为单个 asn1_definition Unit (含开始与闭合大括号);
     * - semantic 元数据正确记录 entityName 与 language;
     * - 行号区间合法 (start <= end)。
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
     * 验证 RFC 2119 规范性段落:
     * - content 完整保留原始换行与关键词 "MUST";
     * - semantic.normativeKeywords 包含去重后的关键字列表;
     * - 行号区间合法。
     */
    @Test
    void emitsWholeNormativeParagraphWithLineRange() throws Exception {
        RfcDocument document = parser.parse(fixturePath());

        RfcUnit rule = document.units().stream()
                .filter(unit -> unit.unitType().equals("normative_rule"))
                .findFirst()
                .orElseThrow();

        assertThat(rule.content()).isEqualTo("An implementation MUST preserve the message identifier while a\n"
                + "message is retransmitted.");
        assertThat(rule.semantic()).containsEntry("normativeKeywords", java.util.List.of("MUST"));
        assertThat(rule.startLine()).isLessThanOrEqualTo(rule.endLine());
    }

    /**
     * 解析测试夹具 RFC9999 的绝对路径,所有用例共用。
     */
    private Path fixturePath() throws URISyntaxException {
        return Path.of(getClass().getResource("/Users/zaneway/Downloads/rfc-archive/rfc5280.txt").toURI());
    }
}
