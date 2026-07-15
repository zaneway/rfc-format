package com.github.zaneway.format.rfc.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import java.net.URISyntaxException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * 物理行号映射与规范性覆盖层去重的回归测试。
 *
 * <p>夹具 {@code rfc9002-line-mapping.txt} 在清洗掉目录 / 分页后，正文中含引用的段落
 * 同时会产出 {@code section_content}（canonical）与 {@code normative_rule}（覆盖层）。 引用事实只应挂在 canonical
 * 单元上，且行号必须回放到原 RFC TXT 的物理行，而不是清洗后的索引。</p>
 */
class RfcCitationLineMappingTest {

  /**
   * 无状态解析器实例，测试间可安全复用。
   */
  private final RfcTextParser parser = new RfcTextParser();

  /**
   * 验证: 1. canonical 与 normative_rule 共享同一物理行区间 (15-16); 2. 仅产出一条 cites，fromIdentifier 指向
   * section_content 而非 normative_rule; 3. 关系行号落在引用标记所在的物理行 15。
   */
  @Test
  void emitsOneCanonicalCitationAtTheOriginalPhysicalSourceLine() throws Exception {
    RfcDocument document = parser.parse(fixturePath());

    // 同一段正文会同时产出 canonical 内容单元与规范性覆盖层
    RfcUnit canonicalUnit = document.units().stream()
        .filter(unit -> unit.unitType().equals("section_content"))
        .filter(unit -> unit.content().contains("[RFC9999]"))
        .findFirst()
        .orElseThrow();
    RfcUnit normativeOverlay = document.units().stream()
        .filter(unit -> unit.unitType().equals("normative_rule"))
        .filter(unit -> unit.content().contains("[RFC9999]"))
        .findFirst()
        .orElseThrow();

    assertThat(canonicalUnit.startLine()).isEqualTo(15);
    assertThat(canonicalUnit.endLine()).isEqualTo(16);
    assertThat(normativeOverlay.startLine()).isEqualTo(15);
    assertThat(normativeOverlay.endLine()).isEqualTo(16);

    // 引用只从 canonical 单元抽取一次，避免覆盖层造成重复边
    assertThat(document.relations()).singleElement().satisfies(relation -> {
      assertThat(relation.toIdentifier()).isEqualTo("rfc-9999");
      assertThat(relation.fromIdentifier()).isEqualTo(canonicalUnit.id())
          .doesNotContain(":normative_rule:");
      assertThat(relation.startLine()).isEqualTo(15);
      assertThat(relation.endLine()).isEqualTo(15);
    });
  }

  /**
   * 加载物理行号映射夹具。
   */
  private Path fixturePath() throws URISyntaxException {
    return Path.of(getClass().getResource("/rfc/rfc9002-line-mapping.txt").toURI());
  }
}
