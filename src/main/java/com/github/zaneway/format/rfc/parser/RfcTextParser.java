package com.github.zaneway.format.rfc.parser;

import com.github.zaneway.format.rfc.parser.model.*;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import java.util.regex.*;

/**
 * 将 IETF RFC TXT 确定性转换为章节、知识单元、引用关系与规则对象，不依赖 LLM 或外部服务。
 *
 * <p>设计要点:</p>
 * <ul>
 *   <li>纯本地解析，抽取规则经正则固化，相同输入永远得到相同输出 (deterministic)。</li>
 *   <li>对外暴露的行号一律为原 RFC TXT 物理行；清洗只影响解析时的内部索引。</li>
 *   <li>输出 {@link RfcDocument} 不可变，便于写入向量库 / 关系库时安全共享。</li>
 * </ul>
 *
 * <p>源码阅读分区（自上而下）:</p>
 * <ol>
 *   <li>正则常量 — 头部 / 章节 / 单元分类 / 引用 / 规则对象</li>
 *   <li>入口与清洗 — {@link #parse}、目录与分页去除</li>
 *   <li>头部元数据 — 编号、类别、日期、标题</li>
 *   <li>章节切分 — 区间识别、章节树、路径</li>
 *   <li>单元构建 — 段落分块、类型分类、ID 碰撞消解</li>
 *   <li>引用关系 — cites / cites_section / section_ref</li>
 *   <li>规则对象 — OID / 字段 / ABNF / 状态码与 defines 边</li>
 *   <li>行号工具与内部 record</li>
 * </ol>
 */
@Component
public final class RfcTextParser implements RfcDocumentParser {

  // ========================= 正则：头部元数据 =========================

  // 匹配 RFC 编号行,例如 "Request for Comments: 9999"
  private static final Pattern RFC_NUMBER = Pattern.compile("^Request for Comments:\\s*(\\d+)",
      Pattern.CASE_INSENSITIVE);
  // 匹配 RFC 类别行,例如 "Category: Standards Track"
  private static final Pattern CATEGORY = Pattern.compile("^Category:\\s*(.+)$",
      Pattern.CASE_INSENSITIVE);
  // 匹配形如 "Month YYYY" 的发布日期片段 (January ~ December + 4 位年份)
  private static final Pattern MONTH_YEAR = Pattern.compile(
      "\\b(?:January|February|March|April|May|June|July|August|September|October|November|December)\\s+\\d{4}\\b");

  // ========================= 正则：章节边界 =========================

  // 匹配正文编号章节,例如 "1.", "2.3", "10.4.5" 等
  private static final Pattern NUMBERED_SECTION = Pattern.compile(
      "^(\\d+(?:\\.\\d+)*)(?:\\.)?\\s+(.+)$");
  // 匹配附录章节,例如 "Appendix A.", "Appendix B.2"
  private static final Pattern APPENDIX_SECTION = Pattern.compile(
      "^(Appendix\\s+[A-Z](?:\\.\\d+)?)(?:\\.)?\\s+(.+)$", Pattern.CASE_INSENSITIVE);

  // ========================= 正则：单元类型识别 =========================

  // 匹配 ASN.1 定义行,以 "::=" 为特征
  private static final Pattern ASN1_DEFINITION = Pattern.compile("^([A-Za-z][A-Za-z0-9-]*)\\s*::=");
  // 匹配 ABNF 定义行 (排除 MUST 等关键字,避免与正文冲突)
  private static final Pattern ABNF_DEFINITION = Pattern.compile("^([A-Za-z][A-Za-z0-9-]*)\\s*=");
  // RFC 2119 规范性关键字,优先级按出现顺序匹配 (MUST NOT 在 MUST 之前)
  private static final Pattern NORMATIVE_KEYWORD = Pattern.compile(
      "\\b(MUST NOT|SHOULD NOT|MUST|SHOULD|MAY)\\b");

  // ========================= 正则：引用抽取（优先级高→低） =========================

  // 匹配 RFC 引用标记,例如 "[RFC2119]"；参考文献目录与正文引用抽取共用。
  private static final Pattern RFC_REFERENCE = Pattern.compile("\\[RFC\\s*(\\d+)]",
      Pattern.CASE_INSENSITIVE);
  // 外部章节引文形式一:"Section 2.1 of RFC 5280"；必须先于普通 RFC 形式处理，防止重叠重复关系。
  private static final Pattern EXTERNAL_SECTION_OF_RFC = Pattern.compile(
      "\\bSection\\s+(\\d+(?:\\.\\d+)*)\\s+of\\s+RFC\\s*(\\d+)\\b", Pattern.CASE_INSENSITIVE);
  // 外部章节引文形式二:"RFC 5280, Section 2.1"
  private static final Pattern EXTERNAL_RFC_SECTION = Pattern.compile(
      "\\bRFC\\s*(\\d+)\\s*,\\s*Section\\s+(\\d+(?:\\.\\d+)*)\\b", Pattern.CASE_INSENSITIVE);
  // 匹配无方括号的直接 RFC 编号引用,例如 "RFC 2119"
  private static final Pattern DIRECT_RFC_REFERENCE = Pattern.compile("\\bRFC\\s*(\\d+)\\b",
      Pattern.CASE_INSENSITIVE);
  // 匹配本 RFC 内部编号章节引用,例如 "Section 2.1"
  private static final Pattern INTERNAL_NUMBERED_SECTION = Pattern.compile(
      "\\bSection\\s+(\\d+(?:\\.\\d+)*)\\b", Pattern.CASE_INSENSITIVE);
  // 匹配本 RFC 内部附录引用,例如 "Appendix A"、"Appendix B.1"
  private static final Pattern INTERNAL_APPENDIX_SECTION = Pattern.compile(
      "\\bAppendix\\s+([A-Z](?:\\.\\d+)?)\\b", Pattern.CASE_INSENSITIVE);

  // ========================= 正则：规则对象（仅明确语法） =========================

  // 高精度规则对象：只识别明确 ASN.1/ABNF/代码定义语法，绝不从自由正文推断。
  // 匹配 ASN.1 容器定义行,例如 "Extension ::= SEQUENCE {"
  private static final Pattern ASN1_CONTAINER = Pattern.compile(
      "(?m)^([A-Za-z][A-Za-z0-9-]*)\\s*::=\\s*(SEQUENCE|SET|CHOICE)\\s*\\{");
  // 匹配容器大括号内的顶层组件声明行,例如 "extnID OBJECT IDENTIFIER,"
  private static final Pattern ASN1_COMPONENT = Pattern.compile(
      "(?m)^[\\t ]*([A-Za-z][A-Za-z0-9-]*)\\s+([^,\\n]+?)(?:,)?[\\t ]*$");
  // 匹配 ASN.1 OBJECT IDENTIFIER 赋值,例如 "id-ce OBJECT IDENTIFIER ::= { 2 5 29 }"
  private static final Pattern ASN1_OID = Pattern.compile(
      "(?m)^([A-Za-z][A-Za-z0-9-]*)\\s+OBJECT\\s+IDENTIFIER\\s*::=\\s*\\{\\s*([^}]+?)\\s*}");
  // 匹配 ABNF 规则定义行 (与 ABNF_DEFINITION 同形,用于从已分类单元中抽取规则对象)
  private static final Pattern ABNF_RULE = Pattern.compile(
      "(?m)^([A-Za-z][A-Za-z0-9-]*)\\s*=");
  // 匹配命名代码赋值,例如 "ok = 200"（仅在 Status/Error 章节启用）
  private static final Pattern NAMED_CODE = Pattern.compile(
      "(?m)^[\\t ]*([A-Za-z][A-Za-z0-9_-]*)\\s*=\\s*(\\d+)[\\t ]*$");
  // 匹配编号代码行,例如 "200 - OK"（仅在 Status/Error 章节启用）
  private static final Pattern NUMBERED_CODE = Pattern.compile(
      "(?m)^[\\t ]*(\\d+)\\s*-\\s*([A-Za-z][A-Za-z0-9_-]*)[\\t ]*$");

  // ========================= 1. 入口与输入清洗 =========================

  /**
   * 解析一个 UTF-8 RFC TXT 文件，执行完整确定性流水线并返回不可变文档聚合根。
   *
   * <p>处理顺序：读盘 → 清洗目录/分页 → 头部元数据 → 章节切分 → 单元构建
   * → 引用关系 → 规则对象（含 defines 边）→ 组装 {@link RfcDocument}。 对外暴露的行号均为原 TXT 物理行，清洗仅影响内部索引。</p>
   *
   * @param sourceFile RFC TXT 文件路径（必须是可读普通文件）
   * @return 不可变的 RFC 文档结构
   * @throws IOException 非普通文件、读盘失败，或头部缺少 RFC 编号/Category/标题时
   */
  public RfcDocument parse(Path sourceFile) throws IOException {
    // 前置校验:只接受普通文件,避免目录 / 软链等带来的歧义
    if (!Files.isRegularFile(sourceFile)) {
      throw new IOException("RFC source must be a regular file: " + sourceFile);
    }
    // 以 UTF-8 读取整文件,后续所有行号都基于这一份原始 lines
    List<String> originalLines = Files.readAllLines(sourceFile, StandardCharsets.UTF_8);
    // 去除目录与分页装饰,得到"真实正文行"
    CleanedRfcText cleaned = removeTableOfContentsAndPageDecorations(originalLines);
    List<String> lines = cleaned.lines();
    // 抽取头部元数据 (RFC 编号 / 类别 / 发布日期 / 标题)
    String rfcNumber = extractHeader(lines, RFC_NUMBER, "RFC number");
    String category = extractHeader(lines, CATEGORY, "Category");
    String publicationDate = extractPublicationDate(lines);
    String title = extractTitle(lines);
    // 章节边界 → 章节树 → 知识单元 → 引用关系 → 规则对象
    List<SectionRange> ranges = findSectionRanges(lines);
    List<RfcSection> sections = toSections(ranges, cleaned.originalLineNumbers());
    ParsedUnits parsedUnits = toUnits(rfcNumber, lines, cleaned.originalLineNumbers(), ranges,
        sections);
    List<RfcRelation> relations = new ArrayList<>(
        extractRelations(rfcNumber, lines, ranges, parsedUnits.units(),
            parsedUnits.originalLineNumbersByIndex()));
    // 从明确语法抽取规则对象,并将其 defines 关系并入总关系列表
    ObjectExtraction objects = extractRuleObjects(parsedUnits.units(),
        parsedUnits.originalLineNumbersByIndex(), sections);
    relations.addAll(objects.definesRelations());
    return new RfcDocument("rfc-" + rfcNumber, rfcNumber, title, category, publicationDate,
        new RfcSource(sourceFile.getFileName().toString(), sha256(sourceFile)), sections,
        parsedUnits.units(),
        List.copyOf(relations), objects.objects());
  }

  /**
   * 去掉 Table of Contents 与分页装饰（换页符 / {@code [Page N]} 行）， 确保章节识别与行号回溯都基于真正的正文。
   *
   * <p>为什么目录必须整段丢弃：RFC 目录行常 mimic 编号章节标题（如 {@code 1. Introduction}），
   * 若保留会导致伪章节切分；目录结束以换页符 {@code \f} 为界，与 IETF TXT 排版惯例一致。</p>
   *
   * @param source 原始 RFC TXT 行列表（1-based 物理行与列表下标一一对应）
   * @return 清洗后行及其到原物理行号的映射；映射与 {@code result} 等长
   */
  private static CleanedRfcText removeTableOfContentsAndPageDecorations(List<String> source) {
    List<String> result = new ArrayList<>(source.size());
    // originalLineNumbers[i] = 清洗后第 i 行对应的原 TXT 1-based 物理行号
    List<Integer> originalLineNumbers = new ArrayList<>(source.size());
    boolean inToc = false;
    for (int index = 0; index < source.size(); index++) {
      String line = source.get(index);
      String trimmed = line.trim();
      // 进入目录区间:丢弃直至遇到换页符
      if (trimmed.equalsIgnoreCase("Table of Contents")) {
        inToc = true;
        continue;
      }
      if (inToc && line.indexOf('\f') >= 0) {
        inToc = false;
        continue;
      }
      // 目录内行、换页符行、"[Page N]" 尾行一律丢弃
      if (inToc || line.indexOf('\f') >= 0 || trimmed.matches(".*\\[Page\\s+\\d+]$")) {
        continue;
      }
      result.add(line);
      originalLineNumbers.add(index + 1);
    }
    return new CleanedRfcText(result, originalLineNumbers);
  }

  // ========================= 2. 头部元数据 =========================

  /**
   * 在头部若干行中按正则抓取第一个匹配项。
   *
   * @param lines   清洗后的行集合
   * @param pattern 用于匹配的正则
   * @param name    字段名称,异常信息使用
   * @return 第一个匹配的 group(1)
   * @throws IOException 头部缺失该字段时抛出
   */
  private static String extractHeader(List<String> lines, Pattern pattern, String name)
      throws IOException {
    for (String line : lines) {
      Matcher matcher = pattern.matcher(line.trim());
      if (matcher.find()) {
        return matcher.group(1).trim();
      }
    }
    throw new IOException("Missing " + name + " in RFC header");
  }

  /**
   * 抽取 RFC 头部中的发布日期（例如 {@code July 2024}）。
   *
   * <p>发布日期在 RFC TXT 中位置不固定，且部分草案可能缺失；未命中时返回空串而非抛错，
   * 避免阻断整份文档解析。</p>
   *
   * @param lines 清洗后的行列表
   * @return 首个匹配的 Month YYYY 字符串，或空串
   */
  private static String extractPublicationDate(List<String> lines) {
    for (String line : lines) {
      Matcher matcher = MONTH_YEAR.matcher(line);
      if (matcher.find()) {
        return matcher.group();
      }
    }
    return "";
  }

  /**
   * 抽取 RFC 标题:RFC 通常在 "Abstract" 段落之前紧贴一行作为标题, 该方法从 Abstract 处向前回溯第一个非空、非 "ISSN:" 开头的行。
   *
   * @throws IOException 未找到 Abstract 或标题时抛出
   */
  private static String extractTitle(List<String> lines) throws IOException {
    for (int index = 0; index < lines.size(); index++) {
      // IETF TXT 惯例：标题紧挨在 "Abstract" 段之前，而非页眉
      if (!lines.get(index).trim().equalsIgnoreCase("Abstract")) {
        continue;
      }
      // 从 Abstract 向上回溯：跳过空行与 ISSN，取第一个实质行作为标题
      for (int candidate = index - 1; candidate >= 0; candidate--) {
        String value = lines.get(candidate).trim();
        if (!value.isEmpty() && !value.startsWith("ISSN:")) {
          return value;
        }
      }
    }
    throw new IOException("Missing RFC title before Abstract");
  }

  // ========================= 3. 章节切分 =========================

  /**
   * 扫描全文，识别三种章节类型（正文编号、Appendix、参考文献分组）， 并在二次遍历中补齐每个区间的 {@code endLine}（下一章节起始行 − 1）。
   *
   * <p>为什么分两遍：第一遍只记录起点，避免在扫描过程中反复修正前一节边界；
   * 第二遍用「下一节 startLine − 1」确定闭区间，最后一节延伸至文件末尾。</p>
   *
   * @param lines 清洗后的 RFC 行列表
   * @return 按文档顺序排列的章节区间；{@code endLine} 为清洗后 1-based 行号
   */
  private static List<SectionRange> findSectionRanges(List<String> lines) {
    List<SectionRange> ranges = new ArrayList<>();
    // 第一遍:识别章节起点 (编号 / Appendix / 参考文献标题)
    for (int index = 0; index < lines.size(); index++) {
      Matcher numbered = NUMBERED_SECTION.matcher(lines.get(index).trim());
      Matcher appendix = APPENDIX_SECTION.matcher(lines.get(index).trim());
      // "200 - OK" 等状态码行满足宽松的数字标题正则，但其连字符前缀明确表示代码定义，
      // 不能切断所属 Status/Error 章节。
      if (numbered.matches() && !numbered.group(2).trim().startsWith("-")) {
        String title = numbered.group(2).trim();
        ranges.add(new SectionRange(numbered.group(1), title, sectionType(title), index + 1, 0));
      } else if (appendix.matches()) {
        ranges.add(
            new SectionRange(appendix.group(1), appendix.group(2).trim(), "appendix", index + 1,
                0));
      } else {
        String title = lines.get(index).trim();
        // 参考文献分组标题本身也可能不成 "N. Title" 形态，须单独登记区间供 citationType 目录扫描
        if (referenceCitationType(title) != null) {
          ranges.add(new SectionRange(title, title, "references", index + 1, 0));
        }
      }
    }
    // 第二遍:用"下一个章节的 startLine - 1"作为当前章节的 endLine;最后一节以文件末尾收尾
    for (int index = 0; index < ranges.size(); index++) {
      int endLine = index + 1 < ranges.size() ? ranges.get(index + 1).startLine - 1 : lines.size();
      ranges.set(index, ranges.get(index).withEndLine(endLine));
    }
    return ranges;
  }

  /**
   * 将区间对象升级为 {@link RfcSection}，填充 parent、完整 path，并映射为原 TXT 物理行号。
   *
   * @param ranges              {@link #findSectionRanges} 产出的章节区间
   * @param originalLineNumbers 清洗索引 → 原 RFC TXT 物理行号的映射
   * @return 与 {@code ranges} 等长的章节列表，可直接写入 {@link RfcDocument#sections()}
   */
  private static List<RfcSection> toSections(List<SectionRange> ranges,
      List<Integer> originalLineNumbers) {
    List<RfcSection> sections = new ArrayList<>();
    for (SectionRange range : ranges) {
      // path 由 id 点分展开；Appendix/References 等非数字 id 的 path 仅自身，parent 为 null
      List<String> path = sectionPath(range.id);
      String parent = path.size() > 1 ? path.get(path.size() - 2) : null;
      // 对外暴露物理行号：清洗导致目录/换页被删后，仍能定位到原 TXT
      sections.add(new RfcSection(range.id, range.title, range.type, parent, path,
          physicalLineNumber(originalLineNumbers, range.startLine),
          physicalLineNumber(originalLineNumbers, range.endLine)));
    }
    return sections;
  }

  // ========================= 4. 单元构建 =========================

  /**
   * 把每个章节切成段落块，再按内容特征分类为知识单元并生成 embedding 文本。
   *
   * <p>分类优先级：ASN.1（含 OID 赋值）→ ABNF → 普通正文；含 RFC 2119 关键字的段落
   * 在 canonical 单元之外额外产出 {@code normative_rule} 覆盖层，供规范性检索， 但不参与引用 / 规则对象抽取（见
   * {@link #isCanonicalCitationSource}）。</p>
   *
   * @param rfcNumber           RFC 编号（纯数字）
   * @param lines               清洗后的行列表
   * @param originalLineNumbers 清洗索引到物理行号的映射
   * @param ranges              章节区间
   * @param sections            已构造的章节节点（用于单元 ID 与 embedding 标题）
   * @return 单元列表及与之等长的逐行物理行号映射
   */
  private static ParsedUnits toUnits(String rfcNumber, List<String> lines,
      List<Integer> originalLineNumbers,
      List<SectionRange> ranges, List<RfcSection> sections) {
    Map<String, RfcSection> sectionsById = new LinkedHashMap<>();
    sections.forEach(section -> sectionsById.put(section.id(), section));
    List<RfcUnit> units = new ArrayList<>();
    List<List<Integer>> originalLineNumbersByIndex = new ArrayList<>();
    Set<String> emittedUnitIds = new HashSet<>();
    Map<String, Integer> nextCollisionSuffixByBaseId = new HashMap<>();
    for (SectionRange range : ranges) {
      RfcSection section = sectionsById.get(range.id);
      List<LineBlock> blocks = paragraphBlocks(lines, originalLineNumbers, range.startLine + 1,
          range.endLine);
      int contentIndex = 1;
      for (LineBlock block : blocks) {
        String content = normalizeBlock(block.lines);
        if (content.isBlank()) {
          continue;
        }
        // —— 分类：ASN.1/OID → ABNF → section_content；再视情况叠加 normative_rule ——
        Matcher asn1 = ASN1_DEFINITION.matcher(content);
        Matcher oid = ASN1_OID.matcher(content);
        Matcher abnf = ABNF_DEFINITION.matcher(content);
        boolean isAsn1 = asn1.find();
        // OID 赋值也归入 asn1_definition,便于后续统一抽取 oid_definition 对象
        boolean isOid = !isAsn1 && oid.find();
        if (isAsn1 || isOid) {
          String entityName = isAsn1 ? asn1.group(1) : oid.group(1);
          addUnit(units, originalLineNumbersByIndex, emittedUnitIds, nextCollisionSuffixByBaseId,
              unit(rfcNumber, section, "asn1_definition", entityName, content,
                  block.originalLineNumbers.getFirst(), block.originalLineNumbers.getLast(),
                  Map.of("language", "asn1", "entityName", entityName)), block.originalLineNumbers);
        } else if (abnf.find() && !content.contains("MUST")) {
          // 段落若含 RFC 2119「MUST」等词，常与 ABNF 形式混排；整段含 MUST 时降级为正文，
          // 避免把规范性叙述误判为语法规则（ABNF 正则仅看行首规则名=，仍可能误触混排块）
          addUnit(units, originalLineNumbersByIndex, emittedUnitIds, nextCollisionSuffixByBaseId,
              unit(rfcNumber, section, "abnf_definition", abnf.group(1), content,
                  block.originalLineNumbers.getFirst(), block.originalLineNumbers.getLast(),
                  Map.of("language", "abnf", "entityName", abnf.group(1))),
              block.originalLineNumbers);
        } else {
          // 普通正文段落;后缀按章节内出现顺序编号,保证确定性
          addUnit(units, originalLineNumbersByIndex, emittedUnitIds, nextCollisionSuffixByBaseId,
              unit(rfcNumber, section, "section_content", String.format("%02d", contentIndex++),
                  content,
                  block.originalLineNumbers.getFirst(), block.originalLineNumbers.getLast(),
                  Map.of("language", "en")),
              block.originalLineNumbers);
        }
        // normative_rule 是覆盖层：与上方 canonical 单元共享 content，但不替代其类型
        List<String> keywords = normativeKeywords(content);
        if (!keywords.isEmpty()) {
          addUnit(units, originalLineNumbersByIndex, emittedUnitIds, nextCollisionSuffixByBaseId,
              unit(rfcNumber, section, "normative_rule", "rule-" + block.startLine, content,
                  block.originalLineNumbers.getFirst(), block.originalLineNumbers.getLast(),
                  Map.of("language", "en", "normativeKeywords", keywords)),
              block.originalLineNumbers);
        }
      }
    }
    return new ParsedUnits(List.copyOf(units), List.copyOf(originalLineNumbersByIndex));
  }

  /**
   * 将单元与逐行物理位置按同一列表下标保留，并为同一 RFC 内的公开 ID 消除碰撞。
   *
   * <p>为什么并行维护两个列表：引用行号映射（{@code citationLine}）依赖
   * 「单元下标 → 该单元 content 各行对应的物理行号」，必须与 {@code units} 严格对齐。</p>
   *
   * @param units                       累积中的单元列表（会被追加）
   * @param originalLineNumbersByIndex  与 units 等长的物理行映射（会被追加）
   * @param emittedUnitIds              已占用的单元 ID 集合
   * @param nextCollisionSuffixByBaseId 碰撞后缀计数器（基础 ID → 下一个 ~N）
   * @param unit                        待写入的单元（可能经 {@link #uniqueUnitId} 改 ID）
   * @param originalLineNumbers         该单元 content 各行对应的物理行号
   */
  private static void addUnit(List<RfcUnit> units, List<List<Integer>> originalLineNumbersByIndex,
      Set<String> emittedUnitIds, Map<String, Integer> nextCollisionSuffixByBaseId,
      RfcUnit unit, List<Integer> originalLineNumbers) {
    units.add(uniqueUnitId(unit, emittedUnitIds, nextCollisionSuffixByBaseId));
    originalLineNumbersByIndex.add(List.copyOf(originalLineNumbers));
  }

  /**
   * 保留基础 ID 的首个出现；同一基础 ID 的后续出现按解析顺序追加 {@code ~2}、{@code ~3} 等后缀。
   *
   * <p>为什么需要碰撞消解：同一章节内可能出现同名 ABNF 规则或重复 ASN.1 实体引用块，
   * 基础 ID 公式 {@code rfc-N:section:type:name} 会冲突；后缀递增保证 ID 唯一且顺序确定。 若未来 ID 生成规则本身使用该后缀形式，仍通过已发射 ID
   * 集合继续递增。</p>
   *
   * @param unit                        待去重的单元
   * @param emittedUnitIds              已占用 ID 集合（会被更新）
   * @param nextCollisionSuffixByBaseId 各基础 ID 的下一个后缀值
   * @return ID 唯一化后的单元（仅 {@code id} 可能变化，行号与内容不变）
   */
  private static RfcUnit uniqueUnitId(RfcUnit unit, Set<String> emittedUnitIds,
      Map<String, Integer> nextCollisionSuffixByBaseId) {
    String baseId = unit.id();
    // 首次出现:保留基础 ID 原样返回
    if (emittedUnitIds.add(baseId)) {
      return unit;
    }
    // 碰撞:从 ~2 起递增,直到找到尚未占用的 ID
    int suffix = nextCollisionSuffixByBaseId.getOrDefault(baseId, 2);
    String uniqueId;
    do {
      uniqueId = baseId + "~" + suffix++;
    } while (!emittedUnitIds.add(uniqueId));
    nextCollisionSuffixByBaseId.put(baseId, suffix);
    // 仅替换 id,其余字段(含物理行号)保持不变,避免引用行号串扰
    return new RfcUnit(uniqueId, unit.unitType(), unit.parentSectionId(), unit.content(),
        unit.embeddingText(),
        unit.startLine(), unit.endLine(), unit.semantic());
  }

  /**
   * 组装单个 {@link RfcUnit}，同时计算供向量库使用的 {@code embeddingText}。
   *
   * <p>ID 命名规则：{@code rfc-{number}:{sectionId}:{type}:{suffix}}；
   * embedding 前缀 {@code [RFC N §section title]} 使检索命中时可回溯章节上下文， 而不必再查 sections 表。</p>
   *
   * @param rfcNumber RFC 编号
   * @param section   所属章节
   * @param type      单元类型
   * @param suffix    类型内区分符（实体名、序号或 rule-行号）
   * @param content   段落正文
   * @param startLine 起始物理行号（1-based）
   * @param endLine   结束物理行号（1-based，含）
   * @param semantic  弱结构化元数据
   * @return 完整单元 record
   */
  private static RfcUnit unit(String rfcNumber, RfcSection section, String type, String suffix,
      String content,
      int startLine, int endLine, Map<String, Object> semantic) {
    String id = "rfc-" + rfcNumber + ":" + section.id() + ":" + type + ":" + suffix;
    String embedding =
        "[RFC " + rfcNumber + " §" + section.id() + " " + section.title() + "]\n" + content;
    return new RfcUnit(id, type, section.id(), content, embedding, startLine, endLine, semantic);
  }

  // ========================= 5. 引用关系 =========================

  /**
   * 从 canonical 单元中抽取引用关系，并合并 Normative/Informative 分类目录。
   *
   * <p>流水线步骤：① 扫 References 分组建立 {@code target → citationType} 目录；
   * ② 收集本 RFC 已知章节 ID 供 {@code section_ref} 判定 resolved/unresolved； ③ 逐单元调用
   * {@link #extractUnitCitations}。外部章节形式必须先匹配， 避免同一段引文同时退化为普通 RFC 或内部章节引用。</p>
   *
   * @param rfcNumber                  当前 RFC 编号
   * @param lines                      清洗后的行（供 References 目录扫描）
   * @param ranges                     章节区间
   * @param units                      已构造的知识单元
   * @param originalLineNumbersByIndex 与 units 等长的物理行映射
   * @return 引用关系列表（不含 defines 边）
   * @throws IllegalStateException units 与物理行映射数量不一致时
   */
  private static List<RfcRelation> extractRelations(String rfcNumber, List<String> lines,
      List<SectionRange> ranges, List<RfcUnit> units,
      List<List<Integer>> originalLineNumbersByIndex) {
    // units 与物理行映射必须按下标一一对应,否则引用行号会串扰
    if (units.size() != originalLineNumbersByIndex.size()) {
      throw new IllegalStateException("Unit and physical line mapping counts must match");
    }
    List<RfcRelation> relations = new ArrayList<>();
    // 步骤 1:从 Normative/Informative/References 分组建立目标 RFC → citationType 目录
    Map<String, String> citationTypes = referenceCitationTypes(lines, ranges);
    // 步骤 2:收集本 RFC 已识别章节 ID,供内部 section_ref 判定 resolved / unresolved
    Set<String> knownSectionIds = new HashSet<>();
    for (SectionRange range : ranges) {
      knownSectionIds.add(range.id());
    }
    // 步骤 3:仅扫描 canonical 单元(跳过 normative_rule 覆盖层,避免重复边)
    for (int unitIndex = 0; unitIndex < units.size(); unitIndex++) {
      RfcUnit unit = units.get(unitIndex);
      if (!isCanonicalCitationSource(unit)) {
        continue;
      }
      relations.addAll(
          extractUnitCitations(rfcNumber, unit, originalLineNumbersByIndex.get(unitIndex),
              citationTypes, knownSectionIds));
    }
    return relations;
  }

  /**
   * 依次提取一个物理内容单元中的外部章节、普通 RFC 与内部章节引用，并保留 UTF-16 证据偏移。
   *
   * <p>优先级与 {@code consumed} 区间互斥：高优先级规则占用的文本区间会阻止低优先级
   * 重复匹配——例如 {@code Section 2 of RFC 5280} 不应再拆出 {@code RFC 5280} 与 {@code Section 2}。
   * {@code occurrence} 在同一单元内对相同语义目标递增，便于回放第 N 次出现。</p>
   *
   * @param rfcNumber           当前 RFC 编号
   * @param unit                待扫描的 canonical 单元
   * @param originalLineNumbers 该单元 content 各行对应的物理行号
   * @param citationTypes       References 目录（目标 RFC → normative/informative/unspecified）
   * @param knownSectionIds     本 RFC 已识别章节 ID，供内部引用解析
   * @return 该单元产出的全部引用关系
   */
  private static List<RfcRelation> extractUnitCitations(String rfcNumber, RfcUnit unit,
      List<Integer> originalLineNumbers,
      Map<String, String> citationTypes,
      Set<String> knownSectionIds) {
    List<RfcRelation> relations = new ArrayList<>();
    // consumed:已被更高优先级规则占用的文本区间,用于抑制重叠重复关系
    List<Span> consumed = new ArrayList<>();
    // occurrences:同一来源内相同语义目标的出现次序计数器
    Map<String, Integer> occurrences = new HashMap<>();

    // 优先级 1: "Section X of RFC N"
    Matcher sectionOfRfc = EXTERNAL_SECTION_OF_RFC.matcher(unit.content());
    while (sectionOfRfc.find()) {
      String targetRfc = sectionOfRfc.group(2);
      String targetSection = sectionOfRfc.group(1);
      relations.add(externalSectionRelation(unit, originalLineNumbers, citationTypes, occurrences,
          sectionOfRfc.start(), sectionOfRfc.end(), targetRfc, targetSection));
      consumed.add(new Span(sectionOfRfc.start(), sectionOfRfc.end()));
    }

    // 优先级 2: "RFC N, Section X"
    Matcher rfcSection = EXTERNAL_RFC_SECTION.matcher(unit.content());
    while (rfcSection.find()) {
      if (overlaps(consumed, rfcSection.start(), rfcSection.end())) {
        continue;
      }
      String targetRfc = rfcSection.group(1);
      String targetSection = rfcSection.group(2);
      relations.add(externalSectionRelation(unit, originalLineNumbers, citationTypes, occurrences,
          rfcSection.start(), rfcSection.end(), targetRfc, targetSection));
      consumed.add(new Span(rfcSection.start(), rfcSection.end()));
    }

    // 优先级 3: "[RFC N]" 方括号引用
    Matcher bracketedRfc = RFC_REFERENCE.matcher(unit.content());
    while (bracketedRfc.find()) {
      if (overlaps(consumed, bracketedRfc.start(), bracketedRfc.end())) {
        continue;
      }
      relations.add(directRfcRelation(unit, originalLineNumbers, citationTypes, occurrences,
          bracketedRfc.start(), bracketedRfc.end(), bracketedRfc.group(1)));
      consumed.add(new Span(bracketedRfc.start(), bracketedRfc.end()));
    }

    // 优先级 4: 无方括号的 "RFC N"
    Matcher directRfc = DIRECT_RFC_REFERENCE.matcher(unit.content());
    while (directRfc.find()) {
      if (overlaps(consumed, directRfc.start(), directRfc.end())) {
        continue;
      }
      relations.add(directRfcRelation(unit, originalLineNumbers, citationTypes, occurrences,
          directRfc.start(), directRfc.end(), directRfc.group(1)));
      consumed.add(new Span(directRfc.start(), directRfc.end()));
    }

    // 优先级 5: 本 RFC 内部 "Section X"（须避开已被外部章节消费的区间）
    Matcher numberedSection = INTERNAL_NUMBERED_SECTION.matcher(unit.content());
    while (numberedSection.find()) {
      if (overlaps(consumed, numberedSection.start(), numberedSection.end())) {
        continue;
      }
      String targetSection = numberedSection.group(1);
      relations.add(internalSectionRelation(rfcNumber, unit, originalLineNumbers, occurrences,
          numberedSection.start(), numberedSection.end(), targetSection, knownSectionIds));
      consumed.add(new Span(numberedSection.start(), numberedSection.end()));
    }

    // 优先级 6: 本 RFC 内部 "Appendix X"
    Matcher appendixSection = INTERNAL_APPENDIX_SECTION.matcher(unit.content());
    while (appendixSection.find()) {
      if (overlaps(consumed, appendixSection.start(), appendixSection.end())) {
        continue;
      }
      String targetSection = "Appendix " + appendixSection.group(1);
      relations.add(internalSectionRelation(rfcNumber, unit, originalLineNumbers, occurrences,
          appendixSection.start(), appendixSection.end(), targetSection, knownSectionIds));
      consumed.add(new Span(appendixSection.start(), appendixSection.end()));
    }
    return relations;
  }

  /**
   * 创建未校验的外部 RFC 章节引用；目标 RFC/章节不存在于当前导入集也仍是有效解析事实。
   *
   * <p>{@code resolutionStatus} 固定为 {@code unresolved}：跨文档章节解析需等目标 RFC 入库后
   * 由下游链接器完成，解析器本身不假装已解析。</p>
   *
   * @return {@code cites_section} 关系，{@code toKind=document}，{@code toIdentifier=rfc-N}
   */
  private static RfcRelation externalSectionRelation(RfcUnit unit,
      List<Integer> originalLineNumbers,
      Map<String, String> citationTypes,
      Map<String, Integer> occurrences, int startOffset, int endOffset,
      String targetRfc, String targetSection) {
    String target = "rfc-" + targetRfc;
    int occurrence = nextOccurrence(occurrences, "cites_section:" + target + ":" + targetSection);
    return citationRelation(unit, originalLineNumbers, "cites_section", "document", target,
        startOffset, endOffset,
        occurrence, citationTypes.getOrDefault(target, "unspecified"), "external_section",
        targetRfc,
        targetSection, "unresolved");
  }

  /**
   * 创建普通 RFC 引用；方括号、紧凑写法和空格写法的 evidence 均保持原样。
   *
   * <p>{@code citationType} 来自 References 目录，未出现在任何分组时为 {@code unspecified}，
   * 表示 RFC 正文未通过正式编排声明规范性，而非「非规范引用」的语义推断。</p>
   *
   * @return {@code cites} 关系，{@code toKind=document}
   */
  private static RfcRelation directRfcRelation(RfcUnit unit, List<Integer> originalLineNumbers,
      Map<String, String> citationTypes, Map<String, Integer> occurrences,
      int startOffset, int endOffset, String targetRfc) {
    String target = "rfc-" + targetRfc;
    int occurrence = nextOccurrence(occurrences, "cites:" + target);
    return citationRelation(unit, originalLineNumbers, "cites", "document", target, startOffset,
        endOffset,
        occurrence, citationTypes.getOrDefault(target, "unspecified"), "rfc", targetRfc,
        null, "unresolved");
  }

  /**
   * 创建当前 RFC 的章节引用；是否能解析只由当前已识别的章节 ID 决定。
   *
   * <p>与外部引用不同：{@code section_ref} 的 {@code toIdentifier} 形如
   * {@code rfc-N:2.1}，{@code resolutionStatus} 取决于 {@code knownSectionIds} 是否含目标章节——解析器不跨文件验证，只反映本
   * TXT 内的结构事实。</p>
   *
   * @return {@code section_ref} 关系，{@code toKind=section}
   */
  private static RfcRelation internalSectionRelation(String rfcNumber, RfcUnit unit,
      List<Integer> originalLineNumbers,
      Map<String, Integer> occurrences, int startOffset, int endOffset,
      String targetSection, Set<String> knownSectionIds) {
    String target = "rfc-" + rfcNumber + ":" + targetSection;
    int occurrence = nextOccurrence(occurrences, "section_ref:" + target);
    String resolutionStatus = knownSectionIds.contains(targetSection) ? "resolved" : "unresolved";
    return citationRelation(unit, originalLineNumbers, "section_ref", "section", target,
        startOffset, endOffset,
        occurrence, "unspecified", "internal_section", rfcNumber, targetSection, resolutionStatus);
  }

  /**
   * 统一构造含可回放位置的引用关系，避免不同 RFC 写法遗失审计字段。
   *
   * <p>行号由 UTF-16 偏移映射到单元保存的物理行列表；{@code endLine} 取匹配最后一个字符
   * 所在行（{@code endOffset - 1}），使多行引文覆盖完整行区间。 {@code attributes} 同时保留偏移与原文切片，使下游无需再次扫描正文即可审计。</p>
   *
   * @param unit                来源单元
   * @param originalLineNumbers 该单元 content 的逐行物理行号
   * @param relationType        cites / cites_section / section_ref
   * @param toKind              目标实体种类
   * @param target              目标标识符
   * @param startOffset         在 unit.content 中的 UTF-16 起始偏移（含）
   * @param endOffset           在 unit.content 中的 UTF-16 结束偏移（不含）
   * @param occurrence          同一单元内相同语义目标的 1-based 出现次序
   * @param citationType        normative / informative / unspecified
   * @param citationScope       external_section / rfc / internal_section
   * @param targetRfcNumber     被引 RFC 编号
   * @param targetSectionId     被引章节 ID，普通 RFC 引用时为 null
   * @param resolutionStatus    resolved / unresolved
   * @return 完整 {@link RfcRelation} record
   */
  private static RfcRelation citationRelation(RfcUnit unit, List<Integer> originalLineNumbers,
      String relationType,
      String toKind, String target, int startOffset, int endOffset,
      int occurrence, String citationType, String citationScope,
      String targetRfcNumber, String targetSectionId,
      String resolutionStatus) {
    // 起止偏移分别映射到原 RFC TXT 物理行;end 取最后一个字符所在行
    int startLine = citationLine(originalLineNumbers, unit.content(), startOffset);
    int endLine = citationLine(originalLineNumbers, unit.content(),
        Math.max(startOffset, endOffset - 1));
    Map<String, Object> attributes = new LinkedHashMap<>();
    attributes.put("citationType", citationType);
    attributes.put("citationText", unit.content().substring(startOffset, endOffset));
    attributes.put("occurrence", occurrence);
    attributes.put("startOffset", startOffset);
    attributes.put("endOffset", endOffset);
    attributes.put("targetRfcNumber", targetRfcNumber);
    if (targetSectionId != null) {
      attributes.put("targetSectionId", targetSectionId);
    }
    attributes.put("citationScope", citationScope);
    attributes.put("resolutionStatus", resolutionStatus);
    return new RfcRelation(relationId(unit.id(), relationType, target, occurrence), relationType,
        "unit", unit.id(), toKind, target, unit.parentSectionId(), startLine, endLine, attributes);
  }

  /**
   * 同一来源、关系类型与目标的重复标记保留独立 occurrence，便于精确回放第 N 次出现。
   *
   * @param occurrences 可变的出现次序计数器
   * @param key         语义键（含关系类型与目标）
   * @return 本次匹配的 1-based occurrence
   */
  private static int nextOccurrence(Map<String, Integer> occurrences, String key) {
    return occurrences.merge(key, 1, Integer::sum);
  }

  /**
   * 判断 {@code [start, end)} 是否与已消费的半开区间列表存在重叠。
   *
   * <p>用于引用优先级链：已被高优先级规则占用的字符区间不再产出低优先级关系。</p>
   *
   * @param spans 已消费区间列表
   * @param start 候选匹配起始偏移（含）
   * @param end   候选匹配结束偏移（不含）
   * @return 存在任意重叠时为 true
   */
  private static boolean overlaps(List<Span> spans, int start, int end) {
    return spans.stream().anyMatch(span -> start < span.end() && end > span.start());
  }

  // ========================= 6. 规则对象 =========================

  /**
   * 从 canonical 单元中抽取明确规则对象及对应的 {@code defines} 边。
   *
   * <p>边界刻意保守：ASN.1 字段只接受容器大括号内的顶层组件；代码只接受
   * Error/Status 等明确标题下的赋值或编号形式，避免把自然语言误当定义。 与引用抽取一致，跳过 {@code normative_rule} 覆盖层。</p>
   *
   * @param units                      全部知识单元
   * @param originalLineNumbersByIndex 与 units 等长的物理行映射
   * @param sections                   章节列表（用于标题门控 Status/Error 与 ABNF/代码区分）
   * @return 对象列表及其 defines 关系（二者必须同批返回，见 {@link ObjectExtraction}）
   * @throws IllegalStateException units 与物理行映射数量不一致时
   */
  private static ObjectExtraction extractRuleObjects(List<RfcUnit> units,
      List<List<Integer>> originalLineNumbersByIndex,
      List<RfcSection> sections) {
    if (units.size() != originalLineNumbersByIndex.size()) {
      throw new IllegalStateException("Unit and physical line mapping counts must match");
    }
    // 章节标题用于判定 Status/Error 代码区,以及避免把代码章节内的 "=" 当成 ABNF
    Map<String, String> sectionTitles = new HashMap<>();
    for (RfcSection section : sections) {
      sectionTitles.put(section.id(), section.title());
    }
    List<RfcExtractedObject> objects = new ArrayList<>();
    List<RfcRelation> definesRelations = new ArrayList<>();
    Map<String, Integer> objectOccurrences = new HashMap<>();
    for (int index = 0; index < units.size(); index++) {
      RfcUnit unit = units.get(index);
      // 与引用抽取一致:跳过 normative_rule 覆盖层
      if (!isCanonicalCitationSource(unit)) {
        continue;
      }
      List<Integer> physicalLines = originalLineNumbersByIndex.get(index);
      // ASN.1 定义单元 → OID / 字段对象
      if (unit.unitType().equals("asn1_definition")) {
        extractAsn1Objects(unit, physicalLines, objectOccurrences, objects, definesRelations);
      }
      String sectionTitle = sectionTitles.getOrDefault(unit.parentSectionId(), "");
      // ABNF 定义单元 → 规则对象;代码章节内的等号行留给 extractCodeObjects
      if (unit.unitType().equals("abnf_definition") && !isCodeSectionTitle(sectionTitle)) {
        extractAbnfObjects(unit, physicalLines, objectOccurrences, objects, definesRelations);
      }
      // 任意 canonical 单元都可尝试抽取 Status/Error 代码(内部按标题门控)
      extractCodeObjects(unit, physicalLines, sectionTitle,
          objectOccurrences, objects, definesRelations);
    }
    return new ObjectExtraction(List.copyOf(objects), List.copyOf(definesRelations));
  }

  /**
   * 识别明确的 OBJECT IDENTIFIER 赋值，以及 SEQUENCE/SET/CHOICE 大括号内的顶层字段。
   *
   * <p>OID 只接受纯数字弧，无法规范化时跳过对象产出（原始 evidence 仍留在 unit.content）。
   * 容器字段扫描限定在 {@code { ... }} 体内，避免跨容器或嵌套体内的误匹配。</p>
   *
   * @param unit             ASN.1 定义单元
   * @param physicalLines    该单元 content 的逐行物理行号
   * @param occurrences      对象出现次序计数器（会被更新）
   * @param objects          累积对象列表（会被追加）
   * @param definesRelations 累积 defines 边（会被追加）
   */
  private static void extractAsn1Objects(RfcUnit unit, List<Integer> physicalLines,
      Map<String, Integer> occurrences, List<RfcExtractedObject> objects,
      List<RfcRelation> definesRelations) {
    // —— OID 赋值:只接受纯数字弧,规范化为点分形式 ——
    Matcher oid = ASN1_OID.matcher(unit.content());
    while (oid.find()) {
      String normalizedValue = normalizeOidValue(oid.group(2));
      if (normalizedValue.isEmpty()) {
        continue;
      }
      addObject(unit, physicalLines, occurrences, objects, definesRelations, "oid_definition",
          oid.group(1),
          normalizedValue, oid.start(), oid.end(),
          Map.of("notation", "OBJECT IDENTIFIER", "assignedValue", oid.group(2).trim()));
    }

    // —— 容器字段:定位 "{ ... }" 体,仅抽取体内部的顶层组件行 ——
    Matcher container = ASN1_CONTAINER.matcher(unit.content());
    while (container.find()) {
      int bodyStart = container.end();
      int bodyEnd = unit.content().indexOf('}', bodyStart);
      if (bodyEnd < 0) {
        continue;
      }
      String containerType = container.group(2);
      Matcher component = ASN1_COMPONENT.matcher(unit.content());
      while (component.find()) {
        // 组件必须完全落在当前容器体内,避免跨容器误匹配
        if (component.start() < bodyStart || component.end() > bodyEnd) {
          continue;
        }
        String declaration = component.group(2).trim();
        String declaredType = asn1DeclaredType(declaration);
        if (declaredType.isEmpty()) {
          continue;
        }
        Map<String, Object> attributes = new LinkedHashMap<>();
        attributes.put("containerType", containerType);
        attributes.put("declaredType", declaredType);
        if (containsAsn1Keyword(declaration, "OPTIONAL")) {
          attributes.put("optional", true);
        }
        String defaultValue = asn1DefaultValue(declaration);
        if (defaultValue != null) {
          attributes.put("defaultValue", defaultValue);
        }
        addObject(unit, physicalLines, occurrences, objects, definesRelations, "field_definition",
            component.group(1), declaredType, component.start(), component.end(), attributes);
      }
    }
  }

  /**
   * 从已被 unit 分类器确认的 ABNF 定义单元抽取首个规则名。
   *
   * <p>只取第一个 {@code name =} 匹配：一个单元通常对应一条 ABNF 规则块；
   * 多规则块应已被段落切分或碰撞 ID 机制区分。</p>
   */
  private static void extractAbnfObjects(RfcUnit unit, List<Integer> physicalLines,
      Map<String, Integer> occurrences, List<RfcExtractedObject> objects,
      List<RfcRelation> definesRelations) {
    Matcher rule = ABNF_RULE.matcher(unit.content());
    if (rule.find()) {
      addObject(unit, physicalLines, occurrences, objects, definesRelations, "abnf_rule",
          rule.group(1),
          rule.group(1), rule.start(), rule.end(), Map.of("language", "abnf"));
    }
  }

  /**
   * 仅在标题明确指向错误/状态码的章节抽取代码，避免把普通等号表达式误归类为协议代码。
   *
   * <p>标题门控是最后一道防线：即使单元被误标为 {@code abnf_definition}，
   * {@link #isCodeSectionTitle} 也会阻止 ABNF 抽取；反之代码抽取只在 title 含 status/error/result code
   * 时启用，两种形式（{@code NAME = N} 与 {@code N - NAME}）均支持。</p>
   */
  private static void extractCodeObjects(RfcUnit unit, List<Integer> physicalLines,
      String sectionTitle,
      Map<String, Integer> occurrences, List<RfcExtractedObject> objects,
      List<RfcRelation> definesRelations) {
    String normalizedTitle = sectionTitle.toLowerCase(Locale.ROOT);
    // 标题门控:非 Status/Error/Result Code 章节直接跳过
    String objectType;
    if (normalizedTitle.contains("status")) {
      objectType = "status_code";
    } else if (normalizedTitle.contains("error") || normalizedTitle.contains("result code")) {
      objectType = "error_code";
    } else {
      return;
    }
    // 形式一: NAME = 数字
    Matcher namedCode = NAMED_CODE.matcher(unit.content());
    while (namedCode.find()) {
      addObject(unit, physicalLines, occurrences, objects, definesRelations, objectType,
          namedCode.group(1),
          namedCode.group(2), namedCode.start(), namedCode.end(),
          Map.of("code", namedCode.group(2)));
    }
    // 形式二: 数字 - NAME
    Matcher numberedCode = NUMBERED_CODE.matcher(unit.content());
    while (numberedCode.find()) {
      addObject(unit, physicalLines, occurrences, objects, definesRelations, objectType,
          numberedCode.group(2),
          numberedCode.group(1), numberedCode.start(), numberedCode.end(),
          Map.of("code", numberedCode.group(1)));
    }
  }

  /**
   * 判断章节标题是否明确指向状态码 / 错误码 / 结果码定义区域。
   *
   * <p>用于 ABNF 与代码抽取的互斥门控：Status/Error 章节内的 {@code =} 行
   * 优先解释为代码定义而非语法规则。</p>
   */
  private static boolean isCodeSectionTitle(String sectionTitle) {
    String normalizedTitle = sectionTitle.toLowerCase(Locale.ROOT);
    return normalizedTitle.contains("status") || normalizedTitle.contains("error")
        || normalizedTitle.contains("result code");
  }

  /**
   * 增加独立对象及唯一的 unit → object {@code defines} 边；二者共享来源位置但不共享 ID。
   *
   * <p>对象 ID 含 occurrence 后缀，使同名 OID/字段在同一单元多次出现时仍可区分；
   * defines 关系 ID 以 {@code unitId:defines:objectId} 命名，与引用关系 ID 空间分离。</p>
   */
  private static void addObject(RfcUnit unit, List<Integer> physicalLines,
      Map<String, Integer> occurrences,
      List<RfcExtractedObject> objects, List<RfcRelation> definesRelations,
      String objectType, String name, String normalizedValue, int startOffset, int endOffset,
      Map<String, Object> attributes) {
    // 对象 ID = 来源单元 + 类型 + 名称 + 出现次序,保证同名对象可区分
    String baseKey = unit.id() + ":" + objectType + ":" + name;
    int occurrence = nextOccurrence(occurrences, baseKey);
    String objectId = baseKey + ":" + occurrence;
    int startLine = citationLine(physicalLines, unit.content(), startOffset);
    int endLine = citationLine(physicalLines, unit.content(), Math.max(startOffset, endOffset - 1));
    RfcExtractedObject object = new RfcExtractedObject(objectId, objectType, name, normalizedValue,
        unit.id(),
        startLine, endLine, startOffset, endOffset, attributes);
    objects.add(object);
    // defines 边与对象共享行号/偏移,但使用独立关系 ID
    definesRelations.add(
        new RfcRelation(unit.id() + ":defines:" + objectId, "defines", "unit", unit.id(),
            "object", objectId, unit.parentSectionId(), startLine, endLine,
            Map.of("startOffset", startOffset, "endOffset", endOffset,
                "objectType", objectType, "objectName", name)));
  }

  /**
   * OID 规范化只接受数字弧；含非数字 token 时返回空串，不在 {@code normalizedValue} 中伪造可解析值。
   */
  private static String normalizeOidValue(String value) {
    String[] arcs = value.trim().split("\\s+");
    if (arcs.length == 0 || Arrays.stream(arcs).anyMatch(arc -> !arc.matches("\\d+"))) {
      return "";
    }
    return String.join(".", arcs);
  }

  /**
   * 抽取 ASN.1 组件声明中的显式类型（OPTIONAL/DEFAULT 之前的片段）。
   *
   * <p>返回空串时调用方跳过 field_definition 产出，避免把纯注释行或 malformed 声明
   * 误注册为字段对象。</p>
   */
  private static String asn1DeclaredType(String declaration) {
    String value = declaration.replaceFirst("(?i)\\s+(OPTIONAL|DEFAULT)\\b.*$", "").trim();
    return value;
  }

  /**
   * 判断 ASN.1 组件声明中是否包含指定关键字（如 OPTIONAL）。
   */
  private static boolean containsAsn1Keyword(String declaration, String keyword) {
    return Pattern.compile("(?i)\\b" + Pattern.quote(keyword) + "\\b").matcher(declaration).find();
  }

  /**
   * 抽取 ASN.1 组件声明中 DEFAULT 之后的默认值；未出现则返回 null。
   */
  private static String asn1DefaultValue(String declaration) {
    Matcher defaultMatcher = Pattern.compile("(?i)\\bDEFAULT\\s+(.+)$").matcher(declaration);
    return defaultMatcher.find() ? defaultMatcher.group(1).trim() : null;
  }

  // ========================= 7. 引用分类目录与章节类型辅助 =========================

  /**
   * 只有物理内容单元产出引用与规则对象事实；{@code normative_rule} 只是同一内容的检索覆盖层。
   *
   * <p>若对 normative_rule 也做抽取，同一正文段落会产生双倍 cites/defines 边，
   * 破坏 occurrence 计数与审计一致性。</p>
   */
  private static boolean isCanonicalCitationSource(RfcUnit unit) {
    return unit.unitType().equals("section_content")
        || unit.unitType().equals("asn1_definition")
        || unit.unitType().equals("abnf_definition");
  }

  /**
   * 建立当前 RFC 内的参考文献分类目录。
   *
   * <p>不同分组重复列出同一 RFC 时，以 RFC 编排中更强的规范性分类为准：
   * normative &gt; informative &gt; unspecified（见 {@link #strongerCitationType}）。 目录仅决定
   * {@code citationType} 属性，不要求被引 RFC 已导入。</p>
   *
   * @param lines  清洗后的行列表
   * @param ranges 章节区间（含 References 分组）
   * @return 目标 documentId（{@code rfc-N}）→ citationType 映射
   */
  private static Map<String, String> referenceCitationTypes(List<String> lines,
      List<SectionRange> ranges) {
    Map<String, String> citationTypes = new HashMap<>();
    for (SectionRange range : ranges) {
      String citationType = referenceCitationType(range.title);
      if (citationType == null) {
        continue;
      }
      // 仅扫描该 References 分组行区间内的 [RFC N]，正文 cite 不参与分类目录
      for (int lineNumber = range.startLine;
          lineNumber <= range.endLine && lineNumber <= lines.size(); lineNumber++) {
        Matcher matcher = RFC_REFERENCE.matcher(lines.get(lineNumber - 1));
        while (matcher.find()) {
          String target = "rfc-" + matcher.group(1);
          // 同一 RFC 出现在 Normative + Informative 时取更强分类，避免后写覆盖更强结论
          citationTypes.merge(target, citationType, RfcTextParser::strongerCitationType);
        }
      }
    }
    return citationTypes;
  }

  /**
   * 返回标题对应的章节类型；编号参考文献章节仍保留其数字章节 ID。
   *
   * <p>Normative/Informative/References 标题同时触发 {@code references} 类型，
   * 以便 {@link #referenceCitationTypes} 能在正确区间内扫描 {@code [RFC N]} 条目。</p>
   */
  private static String sectionType(String title) {
    return referenceCitationType(title) == null ? "body" : "references";
  }

  /**
   * 识别独立或编号章节的三种 RFC 参考文献标题。
   *
   * <p>分类仅来自 RFC 的正式编排标题，不能据正文自然语言推断依赖强度；
   * 未识别的 References 子标题返回 null，不参与 citation 目录。</p>
   *
   * @return normative / informative / unspecified，或非 References 标题时 null
   */
  private static String referenceCitationType(String title) {
    if (title.equalsIgnoreCase("Normative References")) {
      return "normative";
    }
    if (title.equalsIgnoreCase("Informative References")) {
      return "informative";
    }
    if (title.equalsIgnoreCase("References")) {
      return "unspecified";
    }
    return null;
  }

  /**
   * 选择同一目标 RFC 在多个 References 分组中出现时的最高优先级分类。
   *
   * @return 优先级较高的一方；相等时保留 {@code left}
   */
  private static String strongerCitationType(String left, String right) {
    return citationTypePriority(left) >= citationTypePriority(right) ? left : right;
  }

  /**
   * 引用分类优先级：normative(2) &gt; informative(1) &gt; unspecified/其它(0)。
   */
  private static int citationTypePriority(String citationType) {
    return switch (citationType) {
      case "normative" -> 2;
      case "informative" -> 1;
      default -> 0;
    };
  }

  // ========================= 8. 行号映射 / 段落与指纹工具 =========================

  /**
   * 根据文本匹配前的换行数，以单元保存的物理行映射定位到原 RFC TXT 物理行号。
   *
   * @param originalLineNumbers 该单元 content 各行对应的物理行号（与 content 行一一对应）
   * @param content             单元正文
   * @param matchStart          UTF-16 匹配起始偏移
   * @return 1-based 原 TXT 物理行号
   * @throws IllegalStateException 缺少物理行映射时
   */
  private static int citationLine(List<Integer> originalLineNumbers, String content,
      int matchStart) {
    if (originalLineNumbers == null || originalLineNumbers.isEmpty()) {
      throw new IllegalStateException("Missing physical line mapping for citation source");
    }
    return originalLineNumbers.get(lineOffset(content, matchStart));
  }

  /**
   * 返回匹配所在的内容行序号（0-based，相对 unit.content 而非原 TXT）。
   *
   * <p>unit.content 由段落归一化拼接而成，行数通常少于原 TXT 区间行数，
   * 故需此映射桥接 UTF-16 偏移 → content 行下标 → 物理行号。</p>
   */
  private static int lineOffset(String content, int matchStart) {
    int offset = 0;
    for (int index = 0; index < matchStart; index++) {
      if (content.charAt(index) == '\n') {
        offset++;
      }
    }
    return offset;
  }

  /**
   * 构造关系 ID：同时包含关系类型、目标和 occurrence，以区分同一单元中的重叠语义目标。
   *
   * @return {@code {unitId}:{relationType}:{target}:{occurrence}}
   */
  private static String relationId(String unitId, String relationType, String target,
      int occurrence) {
    return unitId + ":" + relationType + ":" + target + ":" + occurrence;
  }

  /**
   * 将一个章节范围内的连续非空行聚合成段落块，空行作为段落分隔符。
   *
   * <p>从 {@code startLine + 1} 开始扫描：章节标题行本身已归入 {@link RfcSection}，
   * 不应重复进入单元 content。返回的每个 {@link LineBlock} 携带清洗索引区间 及逐行物理行号，供 {@link #toUnits} 分类与行号回填。</p>
   *
   * @param lines               清洗后的全文行
   * @param originalLineNumbers 清洗索引 → 物理行号映射
   * @param startLine           章节内容起始行（清洗后 1-based，不含标题行）
   * @param endLine             章节结束行（清洗后 1-based，含）
   * @return 按文档顺序的段落块列表
   */
  private static List<LineBlock> paragraphBlocks(List<String> lines,
      List<Integer> originalLineNumbers,
      int startLine, int endLine) {
    List<LineBlock> blocks = new ArrayList<>();
    List<String> current = new ArrayList<>();
    int blockStart = startLine;
    for (int lineNumber = startLine; lineNumber <= endLine && lineNumber <= lines.size();
        lineNumber++) {
      String line = lines.get(lineNumber - 1);
      if (line.trim().isEmpty()) {
        // 空行作为段落分隔:冲刷当前块并重置起点
        if (!current.isEmpty()) {
          blocks.add(lineBlock(blockStart, lineNumber - 1, current, originalLineNumbers));
          current.clear();
        }
        blockStart = lineNumber + 1;
      } else {
        if (current.isEmpty()) {
          blockStart = lineNumber;
        }
        current.add(line);
      }
    }
    // 章节末尾若仍有未冲刷段落,一并收尾
    if (!current.isEmpty()) {
      blocks.add(
          lineBlock(blockStart, Math.min(endLine, lines.size()), current, originalLineNumbers));
    }
    return blocks;
  }

  /**
   * 段落块：保留清洗后行区间及块内各行的原 TXT 物理行号，供单元行号与引用映射共用。
   */
  private static LineBlock lineBlock(int startLine, int endLine, List<String> lines,
      List<Integer> originalLineNumbers) {
    return new LineBlock(startLine, endLine, List.copyOf(lines),
        List.copyOf(originalLineNumbers.subList(startLine - 1, endLine)));
  }

  /**
   * 将 1-based 清洗索引转换为原 RFC TXT 的 1-based 物理行号。
   *
   * @param originalLineNumbers {@link CleanedRfcText} 中的映射表
   * @param cleanedLineNumber   清洗后行号（1-based）
   */
  private static int physicalLineNumber(List<Integer> originalLineNumbers, int cleanedLineNumber) {
    return originalLineNumbers.get(cleanedLineNumber - 1);
  }

  /**
   * 对段落进行归一化：求最小公共缩进后统一去除，再拼接为单个字符串。
   *
   * <p>RFC TXT 常对续行做固定缩进；归一化使 ASN.1/ABNF 行首正则能稳定匹配，
   * 同时 {@code content} 仍保留段落内换行以供审计。</p>
   *
   * @param lines 段落内的原始行（含缩进）
   * @return trim 后的段落文本；空段落返回空串
   */
  private static String normalizeBlock(List<String> lines) {
    // 取非空行最小公共缩进：RFC 续行缩进统一剥离后，行首正则才能匹配 ASN.1/ABNF
    int indent = lines.stream().filter(line -> !line.trim().isEmpty())
        .mapToInt(RfcTextParser::leadingWhitespace).min().orElse(0);
    return lines.stream().map(line -> line.length() >= indent ? line.substring(indent) : line)
        .reduce((left, right) -> left + "\n" + right).orElse("").trim();
  }

  /**
   * 计算一行字符串开头的空白字符 (空格 / 制表符) 数量。
   */
  private static int leadingWhitespace(String value) {
    int index = 0;
    while (index < value.length() && Character.isWhitespace(value.charAt(index))) {
      index++;
    }
    return index;
  }

  /**
   * 抽取段落中所有 RFC 2119 关键字，返回去重列表（保留首次出现顺序）。
   *
   * <p>去重后写入 {@code semantic.normativeKeywords}，避免 MUST/MUST NOT 重复
   * 污染检索元数据；完整原文仍保留在 {@code content}。</p>
   */
  private static List<String> normativeKeywords(String content) {
    List<String> values = new ArrayList<>();
    Matcher matcher = NORMATIVE_KEYWORD.matcher(content);
    while (matcher.find()) {
      values.add(matcher.group(1));
    }
    return values.stream().distinct().toList();
  }

  /**
   * 由章节 id（如 {@code 2.1.3}）构造完整路径：{@code ["2", "2.1", "2.1.3"]}。
   *
   * <p>对 Appendix / References 等非纯数字 id，无法按点分递进，直接返回单元素列表；
   * {@link RfcSection#parentId()} 对这类 id 为 null。</p>
   *
   * @param sectionId 章节标识
   * @return 从根到当前节点的 id 路径
   */
  private static List<String> sectionPath(String sectionId) {
    // Appendix A / Normative References 等无法按点分递进，路径仅含自身
    if (!sectionId.matches("\\d+(?:\\.\\d+)*")) {
      return List.of(sectionId);
    }
    // "2.1.3" → ["2", "2.1", "2.1.3"]，供 parentId 与树形导航使用
    String[] values = sectionId.split("\\.");
    List<String> path = new ArrayList<>();
    for (int index = 1; index <= values.length; index++) {
      path.add(String.join(".", java.util.Arrays.copyOf(values, index)));
    }
    return path;
  }

  /**
   * 计算文件 SHA-256，写入 {@link RfcSource} 作为内容指纹。
   *
   * <p>相同 RFC 文本必须产生相同 hash，供入库幂等与变更检测使用。</p>
   *
   * @param file 源 TXT 路径
   * @return 小写 hex 字符串（64 字符）
   * @throws IOException           读盘失败
   * @throws IllegalStateException JVM 不支持 SHA-256 时（不应发生在现代 JDK）
   */
  private static String sha256(Path file) throws IOException {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(Files.readAllBytes(file));
      StringBuilder builder = new StringBuilder(hash.length * 2);
      for (byte value : hash) {
        builder.append(String.format("%02x", value));
      }
      return builder.toString();
    } catch (NoSuchAlgorithmException exception) {
      throw new IllegalStateException("SHA-256 is required by the Java runtime", exception);
    }
  }

  // ========================= 9. 内部中间结构 =========================

  /**
   * 内部章节区间：{@code startLine} 在首遍扫描确定，{@code endLine} 由 {@link #findSectionRanges} 第二遍回填（均为清洗后
   * 1-based 行号）。
   */
  private record SectionRange(String id, String title, String type, int startLine, int endLine) {

    /**
     * 不可变更新 endLine，供第二遍扫描使用。
     */
    private SectionRange withEndLine(int value) {
      return new SectionRange(id, title, type, startLine, value);
    }
  }

  /**
   * 清洗后的文本及其 1-based 清洗索引到原 RFC TXT 物理行号的映射。
   */
  private record CleanedRfcText(List<String> lines, List<Integer> originalLineNumbers) {

    private CleanedRfcText {
      lines = List.copyOf(lines);
      originalLineNumbers = List.copyOf(originalLineNumbers);
    }
  }

  /**
   * 已构造的知识单元及按相同下标对应的内容行到原 RFC TXT 物理行号映射。
   */
  private record ParsedUnits(List<RfcUnit> units, List<List<Integer>> originalLineNumbersByIndex) {

  }

  /**
   * 已被优先级更高的引文规则占用的 UTF-16 半开区间 {@code [start, end)}。
   */
  private record Span(int start, int end) {

  }

  /**
   * 规则对象与其 defines 关系作为同一不可分割的解析结果一并返回，避免部分写入。
   */
  private record ObjectExtraction(List<RfcExtractedObject> objects,
                                  List<RfcRelation> definesRelations) {

  }

  /**
   * 段落块：章节内连续非空行聚合，含清洗行区间与逐行物理行号。
   */
  private record LineBlock(int startLine, int endLine, List<String> lines,
                           List<Integer> originalLineNumbers) {

  }
}
