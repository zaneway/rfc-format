package com.github.zaneway.format.rfc.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import com.github.zaneway.format.rfc.parser.model.RfcDocument;
import com.github.zaneway.format.rfc.parser.model.RfcRelation;
import com.github.zaneway.format.rfc.parser.model.RfcUnit;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * 重复 ABNF 单元 ID 碰撞时的引用行号回归测试。
 *
 * <p>夹具 {@code rfc9003-duplicate-abnf-units.txt} 在同一章节内出现两个同名 ABNF 规则
 * {@code value}。解析器必须为第二个单元追加 {@code ~2} 后缀，并保证每条 cites 关系的 物理行号仍绑定到各自单元实例，而不是被碰撞后的共享 ID 串扰。</p>
 */
class RfcDuplicateAbnfCitationTest {

  /**
   * 无状态解析器实例，测试间可安全复用。
   */
  private final RfcTextParser parser = new RfcTextParser();

  /**
   * 验证: 1. 两个 abnf_definition 单元均被产出，且公开 ID 互不重复; 2. 首个保留基础 ID，行号分别为 (14-16) 与 (18-18); 3. 两条 cites
   * 指向同一目标 RFC，但行号与 fromIdentifier 各自独立、互不重复。
   */
  @Test
  void preservesCitationLocationsForDuplicateAbnfUnitIds() throws Exception {
    RfcDocument document = parser.parse(fixturePath());
    List<RfcUnit> abnfUnits = document.units().stream()
        .filter(unit -> unit.unitType().equals("abnf_definition"))
        .toList();

    // 同名规则必须生成两个单元，且 ID 经碰撞消解后唯一
    assertThat(abnfUnits).hasSize(2);
    assertThat(abnfUnits).extracting(RfcUnit::id).contains("rfc-9003:1:abnf_definition:value")
        .doesNotHaveDuplicates();
    assertThat(abnfUnits).extracting(RfcUnit::startLine, RfcUnit::endLine)
        .containsExactly(tuple(14, 16), tuple(18, 18));

    // 引用行号必须跟随各自单元的物理位置，不能都落到同一行
    List<RfcRelation> citations = document.relations().stream()
        .filter(relation -> relation.relationType().equals("cites"))
        .toList();
    assertThat(citations).hasSize(2)
        .extracting(RfcRelation::toIdentifier, RfcRelation::startLine, RfcRelation::endLine)
        .containsExactly(tuple("rfc-7000", 16, 16), tuple("rfc-7000", 18, 18));
    assertThat(citations).extracting(RfcRelation::id).doesNotHaveDuplicates();
    assertThat(citations).extracting(RfcRelation::fromIdentifier).doesNotHaveDuplicates();
  }

  /**
   * 加载重复 ABNF 单元夹具。
   */
  private Path fixturePath() throws URISyntaxException {
    return Path.of(getClass().getResource("/rfc/rfc9003-duplicate-abnf-units.txt").toURI());
  }
}
