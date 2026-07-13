package com.github.zaneway.format.rfc.parser;

import com.github.zaneway.format.rfc.parser.model.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import java.util.regex.*;

/**
 * 将 IETF RFC TXT 确定性转换为章节和知识单元，不依赖 LLM 或外部服务。
 *
 * 设计要点:
 * - 纯本地解析,所有抽取规则通过正则固化,确保相同输入永远得到相同输出 (deterministic)。
 * - 行号基于原始 RFC TXT (经过目录与分页符清洗后的版本),全部从 1 开始计数。
 * - 输出 {@link RfcDocument} 不可变,便于后续写入向量库 / 关系库时安全共享。
 */
public final class RfcTextParser {

    // 匹配 RFC 编号行,例如 "Request for Comments: 9999"
    private static final Pattern RFC_NUMBER = Pattern.compile("^Request for Comments:\\s*(\\d+)", Pattern.CASE_INSENSITIVE);
    // 匹配 RFC 类别行,例如 "Category: Standards Track"
    private static final Pattern CATEGORY = Pattern.compile("^Category:\\s*(.+)$", Pattern.CASE_INSENSITIVE);
    // 匹配形如 "Month YYYY" 的发布日期片段 (January ~ December + 4 位年份)
    private static final Pattern MONTH_YEAR = Pattern.compile("\\b(?:January|February|March|April|May|June|July|August|September|October|November|December)\\s+\\d{4}\\b");
    // 匹配正文编号章节,例如 "1.", "2.3", "10.4.5" 等
    private static final Pattern NUMBERED_SECTION = Pattern.compile("^(\\d+(?:\\.\\d+)*)(?:\\.)?\\s+(.+)$");
    // 匹配附录章节,例如 "Appendix A.", "Appendix B.2"
    private static final Pattern APPENDIX_SECTION = Pattern.compile("^(Appendix\\s+[A-Z](?:\\.\\d+)?)(?:\\.)?\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    // 匹配 ASN.1 定义行,以 "::=" 为特征
    private static final Pattern ASN1_DEFINITION = Pattern.compile("^([A-Za-z][A-Za-z0-9-]*)\\s*::=");
    // 匹配 ABNF 定义行 (排除 MUST 等关键字,避免与正文冲突)
    private static final Pattern ABNF_DEFINITION = Pattern.compile("^([A-Za-z][A-Za-z0-9-]*)\\s*=");
    // 匹配 References 区中的 RFC 引用,例如 "[RFC2119]"
    private static final Pattern RFC_REFERENCE = Pattern.compile("\\[RFC(\\d+)]", Pattern.CASE_INSENSITIVE);
    // RFC 2119 规范性关键字,优先级按出现顺序匹配 (MUST NOT 在 MUST 之前)
    private static final Pattern NORMATIVE_KEYWORD = Pattern.compile("\\b(MUST NOT|SHOULD NOT|MUST|SHOULD|MAY)\\b");

    /**
     * 解析一个 UTF-8 RFC TXT 文件。
     *
     * 处理流水线:
     *   读文件 → 清洗目录 / 分页符 → 抽取头部字段 → 切分章节区间 → 构造 Section/Unit/Relation。
     *
     * @param sourceFile RFC TXT 文件路径
     * @return 不可变的 RFC 文档结构
     * @throws IOException 文件不可读或不是普通文件时抛出
     */
    public RfcDocument parse(Path sourceFile) throws IOException {
        // 前置校验:只接受普通文件,避免目录 / 软链等带来的歧义
        if (!Files.isRegularFile(sourceFile)) {
            throw new IOException("RFC source must be a regular file: " + sourceFile);
        }
        // 以 UTF-8 读取整文件,后续所有行号都基于这一份原始 lines
        List<String> originalLines = Files.readAllLines(sourceFile, StandardCharsets.UTF_8);
        // 去除目录与分页装饰,得到"真实正文行"
        List<String> lines = removeTableOfContentsAndPageDecorations(originalLines);
        // 抽取头部元数据 (RFC 编号 / 类别 / 发布日期 / 标题)
        String rfcNumber = extractHeader(lines, RFC_NUMBER, "RFC number");
        String category = extractHeader(lines, CATEGORY, "Category");
        String publicationDate = extractPublicationDate(lines);
        String title = extractTitle(lines);
        // 章节边界 → 章节树 → 知识单元 → 关系边
        List<SectionRange> ranges = findSectionRanges(lines);
        List<RfcSection> sections = toSections(ranges);
        List<RfcUnit> units = toUnits(rfcNumber, lines, ranges, sections);
        List<RfcRelation> relations = extractRelations(rfcNumber, lines, ranges);
        return new RfcDocument("rfc-" + rfcNumber, rfcNumber, title, category, publicationDate,
                new RfcSource(sourceFile.getFileName().toString(), sha256(sourceFile)), sections, units, relations);
    }

    /**
     * 去掉 Table of Contents 与分页装饰 (换页符 / "[Page N]" 行),
     * 确保章节识别与行号回溯都基于真正的正文。
     *
     * 状态机说明:
     * - 命中 "Table of Contents" 后进入 inToc 状态,直到出现换页符 (\f) 才退出;
     * - 任何换页符或 "[Page N]" 行一律丢弃。
     */
    private static List<String> removeTableOfContentsAndPageDecorations(List<String> source) {
        List<String> result = new ArrayList<>(source.size());
        boolean inToc = false;
        for (String line : source) {
            String trimmed = line.trim();
            if (trimmed.equalsIgnoreCase("Table of Contents")) {
                inToc = true;
                continue;
            }
            if (inToc && line.indexOf('\f') >= 0) {
                inToc = false;
                continue;
            }
            if (inToc || line.indexOf('\f') >= 0 || trimmed.matches(".*\\[Page\\s+\\d+]$")) {
                continue;
            }
            result.add(line);
        }
        return result;
    }

    /**
     * 在头部若干行中按正则抓取第一个匹配项。
     *
     * @param lines  清洗后的行集合
     * @param pattern 用于匹配的正则
     * @param name   字段名称,异常信息使用
     * @return 第一个匹配的 group(1)
     * @throws IOException 头部缺失该字段时抛出
     */
    private static String extractHeader(List<String> lines, Pattern pattern, String name) throws IOException {
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line.trim());
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
        }
        throw new IOException("Missing " + name + " in RFC header");
    }

    /**
     * 抽取 RFC 头部中的发布日期 (例如 "July 2024"),未命中时返回空串而非抛错。
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
     * 抽取 RFC 标题:RFC 通常在 "Abstract" 段落之前紧贴一行作为标题,
     * 该方法从 Abstract 处向前回溯第一个非空、非 "ISSN:" 开头的行。
     *
     * @throws IOException 未找到 Abstract 或标题时抛出
     */
    private static String extractTitle(List<String> lines) throws IOException {
        for (int index = 0; index < lines.size(); index++) {
            if (!lines.get(index).trim().equalsIgnoreCase("Abstract")) {
                continue;
            }
            for (int candidate = index - 1; candidate >= 0; candidate--) {
                String value = lines.get(candidate).trim();
                if (!value.isEmpty() && !value.startsWith("ISSN:")) {
                    return value;
                }
            }
        }
        throw new IOException("Missing RFC title before Abstract");
    }

    /**
     * 扫描全文,识别三种章节类型 (正文编号、Appendix、References),
     * 并在二次遍历中补齐每个区间的 endLine (下一章节起始行 - 1)。
     */
    private static List<SectionRange> findSectionRanges(List<String> lines) {
        List<SectionRange> ranges = new ArrayList<>();
        for (int index = 0; index < lines.size(); index++) {
            Matcher numbered = NUMBERED_SECTION.matcher(lines.get(index).trim());
            Matcher appendix = APPENDIX_SECTION.matcher(lines.get(index).trim());
            if (numbered.matches()) {
                ranges.add(new SectionRange(numbered.group(1), numbered.group(2).trim(), "body", index + 1, 0));
            } else if (appendix.matches()) {
                ranges.add(new SectionRange(appendix.group(1), appendix.group(2).trim(), "appendix", index + 1, 0));
            } else if (lines.get(index).trim().equalsIgnoreCase("References")) {
                ranges.add(new SectionRange("References", "References", "references", index + 1, 0));
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
     * 将区间对象升级为 {@link RfcSection} (含 parent / 完整路径)。
     */
    private static List<RfcSection> toSections(List<SectionRange> ranges) {
        List<RfcSection> sections = new ArrayList<>();
        for (SectionRange range : ranges) {
            List<String> path = sectionPath(range.id);
            String parent = path.size() > 1 ? path.get(path.size() - 2) : null;
            sections.add(new RfcSection(range.id, range.title, range.type, parent, path, range.startLine, range.endLine));
        }
        return sections;
    }

    /**
     * 把每个章节切成"段落块",再根据内容特征分类为:
     * - asn1_definition : 命中 "::=" 的 ASN.1 模块
     * - abnf_definition : 命中 "=" 但非 MUST 等关键字的 ABNF 规则
     * - section_content : 普通正文段落
     * - normative_rule  : 包含 RFC 2119 关键字的规范性段落 (与上面三类共存)
     *
     * 每个 Unit 都会附带 embeddingText,用于后续写入向量库。
     */
    private static List<RfcUnit> toUnits(String rfcNumber, List<String> lines, List<SectionRange> ranges,
                                         List<RfcSection> sections) {
        Map<String, RfcSection> sectionsById = new LinkedHashMap<>();
        sections.forEach(section -> sectionsById.put(section.id(), section));
        List<RfcUnit> units = new ArrayList<>();
        for (SectionRange range : ranges) {
            RfcSection section = sectionsById.get(range.id);
            List<LineBlock> blocks = paragraphBlocks(lines, range.startLine + 1, range.endLine);
            int contentIndex = 1;
            for (LineBlock block : blocks) {
                String content = normalizeBlock(block.lines);
                if (content.isBlank()) {
                    continue;
                }
                Matcher asn1 = ASN1_DEFINITION.matcher(content);
                Matcher abnf = ABNF_DEFINITION.matcher(content);
                if (asn1.find()) {
                    units.add(unit(rfcNumber, section, "asn1_definition", asn1.group(1), content,
                            block.startLine, block.endLine, Map.of("language", "asn1", "entityName", asn1.group(1))));
                } else if (abnf.find() && !content.contains("MUST")) {
                    units.add(unit(rfcNumber, section, "abnf_definition", abnf.group(1), content,
                            block.startLine, block.endLine, Map.of("language", "abnf", "entityName", abnf.group(1))));
                } else {
                    units.add(unit(rfcNumber, section, "section_content", String.format("%02d", contentIndex++), content,
                            block.startLine, block.endLine, Map.of("language", "en")));
                }
                // 提取 RFC 2119 关键字,如果段落含规范性表述,额外产出 normative_rule 单元
                List<String> keywords = normativeKeywords(content);
                if (!keywords.isEmpty()) {
                    units.add(unit(rfcNumber, section, "normative_rule", "rule-" + block.startLine, content,
                            block.startLine, block.endLine, Map.of("language", "en", "normativeKeywords", keywords)));
                }
            }
        }
        return units;
    }

    /**
     * 组装单个 {@link RfcUnit} 对象,同时计算 embeddingText。
     *
     * 命名规则: rfc-{number}:{sectionId}:{type}:{suffix}
     * embeddingText 形如 "[RFC 9999 §2.1 Message Format]\n...",有助于检索时回溯上下文。
     */
    private static RfcUnit unit(String rfcNumber, RfcSection section, String type, String suffix, String content,
                                int startLine, int endLine, Map<String, Object> semantic) {
        String id = "rfc-" + rfcNumber + ":" + section.id() + ":" + type + ":" + suffix;
        String embedding = "[RFC " + rfcNumber + " §" + section.id() + " " + section.title() + "]\n" + content;
        return new RfcUnit(id, type, section.id(), content, embedding, startLine, endLine, semantic);
    }

    /**
     * 从 References 章节中提取形如 [RFCxxxx] 的引用,产出文档级 references 关系。
     * 若不存在 References 章节则返回空列表 (不抛错)。
     */
    private static List<RfcRelation> extractRelations(String rfcNumber, List<String> lines, List<SectionRange> ranges) {
        List<RfcRelation> relations = new ArrayList<>();
        SectionRange references = ranges.stream().filter(range -> range.id.equals("References")).findFirst().orElse(null);
        if (references == null) {
            return relations;
        }
        for (int index = references.startLine; index <= references.endLine && index <= lines.size(); index++) {
            Matcher matcher = RFC_REFERENCE.matcher(lines.get(index - 1));
            while (matcher.find()) {
                String target = "rfc-" + matcher.group(1);
                relations.add(new RfcRelation("rfc-" + rfcNumber + ":references:" + target, "references",
                        "document", "rfc-" + rfcNumber, "document", target, "References", index, index));
            }
        }
        return relations;
    }

    /**
     * 将一个章节范围内的连续非空行聚合成"段落块",空行作为段落分隔符。
     * 返回的每个 LineBlock 都带有原始行号区间,便于回溯。
     */
    private static List<LineBlock> paragraphBlocks(List<String> lines, int startLine, int endLine) {
        List<LineBlock> blocks = new ArrayList<>();
        List<String> current = new ArrayList<>();
        int blockStart = startLine;
        for (int lineNumber = startLine; lineNumber <= endLine && lineNumber <= lines.size(); lineNumber++) {
            String line = lines.get(lineNumber - 1);
            if (line.trim().isEmpty()) {
                if (!current.isEmpty()) {
                    blocks.add(new LineBlock(blockStart, lineNumber - 1, List.copyOf(current)));
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
        if (!current.isEmpty()) {
            blocks.add(new LineBlock(blockStart, Math.min(endLine, lines.size()), List.copyOf(current)));
        }
        return blocks;
    }

    /**
     * 对段落进行归一化:先求出最小公共缩进,再统一去除,
     * 最后将多行拼接为单个字符串 (用 \n 分隔) 并 trim 收尾。
     */
    private static String normalizeBlock(List<String> lines) {
        int indent = lines.stream().filter(line -> !line.trim().isEmpty())
                .mapToInt(RfcTextParser::leadingWhitespace).min().orElse(0);
        return lines.stream().map(line -> line.length() >= indent ? line.substring(indent) : line)
                .reduce((left, right) -> left + "\n" + right).orElse("").trim();
    }

    /** 计算一行字符串开头的空白字符 (空格 / 制表符) 数量。 */
    private static int leadingWhitespace(String value) {
        int index = 0;
        while (index < value.length() && Character.isWhitespace(value.charAt(index))) {
            index++;
        }
        return index;
    }

    /** 抽取段落中所有 RFC 2119 关键字,返回去重后的列表,保留首次出现顺序。 */
    private static List<String> normativeKeywords(String content) {
        List<String> values = new ArrayList<>();
        Matcher matcher = NORMATIVE_KEYWORD.matcher(content);
        while (matcher.find()) {
            values.add(matcher.group(1));
        }
        return values.stream().distinct().toList();
    }

    /**
     * 由章节 id (如 "2.1.3") 构造完整路径:["2", "2.1", "2.1.3"]。
     * 对 Appendix / References 等非数字 id,直接返回单元素列表。
     */
    private static List<String> sectionPath(String sectionId) {
        if (!sectionId.matches("\\d+(?:\\.\\d+)*")) {
            return List.of(sectionId);
        }
        String[] values = sectionId.split("\\.");
        List<String> path = new ArrayList<>();
        for (int index = 1; index <= values.length; index++) {
            path.add(String.join(".", java.util.Arrays.copyOf(values, index)));
        }
        return path;
    }

    /**
     * 计算文件 SHA-256,用于在 {@link RfcSource} 中建立内容指纹,
     * 便于后续判断文件是否被修改 (相同的 RFC 文本应有相同的 hash)。
     *
     * @throws IOException 读取失败时抛出
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

    /** 内部使用的章节区间记录:起始行已确定,endLine 在二次扫描时回填。 */
    private record SectionRange(String id, String title, String type, int startLine, int endLine) {
        private SectionRange withEndLine(int value) {
            return new SectionRange(id, title, type, startLine, value);
        }
    }

    /** 内部使用的段落块记录:同一段内的全部原始行 + 起止行号。 */
    private record LineBlock(int startLine, int endLine, List<String> lines) {
    }
}
