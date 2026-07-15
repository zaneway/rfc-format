package com.github.zaneway.format.rfc.llm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * 将 LLM 原始响应解析为受严格校验的 RFC 结构化文档。
 *
 * <p>拒绝未知字段而非静默忽略，防止模型响应中混入提示注入内容或未经版本化的契约变更。</p>
 */
@Component
public final class StructuredRfcJsonParser {

  private static final Set<String> CONTENT_TYPES = Set.of("preamble", "text", "term",
      "requirement", "table", "preformatted", "reference", "appendix");

  private final ObjectMapper mapper;

  /**
   * @param objectMapper 项目统一的 Jackson 映射器；内部复制以避免改变其他模块的反序列化策略
   */
  public StructuredRfcJsonParser(ObjectMapper objectMapper) {
    this.mapper = objectMapper.copy().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  /**
   * 解析并校验完整的模型响应。
   *
   * @param response 模型响应；必须是单个 JSON 对象，不能包含 Markdown 或解释文本
   * @return 可安全持久化和投影的结构化文档
   * @throws StructuredRfcValidationException JSON 非法或违反提示词约束时抛出
   */
  public StructuredRfcDocument parse(String response) {
    if (response == null || response.isBlank()) {
      throw new StructuredRfcValidationException("LLM response must contain a JSON object");
    }
    try {
      StructuredRfcDocument document = mapper.readValue(response, StructuredRfcDocument.class);
      validate(document);
      return document;
    } catch (UnrecognizedPropertyException exception) {
      throw new StructuredRfcValidationException(
          "Unknown JSON field in LLM response: " + exception.getPropertyName(), exception);
    } catch (JsonProcessingException exception) {
      throw new StructuredRfcValidationException("LLM response is not valid structured RFC JSON",
          exception);
    }
  }

  private static void validate(StructuredRfcDocument result) {
    require(result != null, "JSON root must not be null");
    StructuredRfcDocument.DocumentMetadata document = result.document();
    require(document != null, "document must not be null");
    require("txt".equals(document.sourceType()), "document.source_type must be txt");
    require("rfc".equals(document.docKind()), "document.doc_kind must be rfc");
    require(nonBlank(document.sourceFile()), "document.source_file must not be blank");
    require(document.relations() != null, "document.relations must not be null");
    require(document.extensions() != null, "document.extensions must not be null");

    List<StructuredRfcDocument.Unit> units = result.units();
    require(units != null && !units.isEmpty(), "units must not be empty");
    Set<String> ids = new HashSet<>();
    for (StructuredRfcDocument.Unit unit : units) {
      require(unit != null, "unit must not be null");
      require(nonBlank(unit.unitId()), "unit_id must not be blank");
      require(ids.add(unit.unitId()), "unit_id must be unique: " + unit.unitId());
      require(CONTENT_TYPES.contains(unit.contentType()),
          "unsupported content_type: " + unit.contentType());
      require(nonBlank(unit.content()), "unit.content must not be blank: " + unit.unitId());
      validateHeadingPath(unit.headingPath(), unit.unitId());
      validateSourceLocation(unit.sourceLocation(), unit.unitId());
    }
  }

  private static void validateHeadingPath(List<StructuredRfcDocument.Heading> headings,
      String unitId) {
    require(headings != null && headings.size() <= 3,
        "heading_path must contain at most three levels: " + unitId);
    int previousLevel = 0;
    for (StructuredRfcDocument.Heading heading : headings) {
      require(heading != null && nonBlank(heading.title()),
          "heading_path title must not be blank: " + unitId);
      require(heading.level() > previousLevel,
          "heading_path levels must be strictly increasing: " + unitId);
      previousLevel = heading.level();
    }
  }

  private static void validateSourceLocation(StructuredRfcDocument.SourceLocation location,
      String unitId) {
    require(location != null, "source_location must not be null: " + unitId);
    boolean hasStart = location.start() != null;
    boolean hasEnd = location.end() != null;
    require(hasStart == hasEnd, "source_location start and end must both be null or set: " + unitId);
    if (hasStart) {
      require(location.start() > 0 && location.end() >= location.start(),
          "source_location range is invalid: " + unitId);
    }
  }

  private static boolean nonBlank(String value) {
    return value != null && !value.isBlank();
  }

  private static void require(boolean condition, String message) {
    if (!condition) {
      throw new StructuredRfcValidationException(message);
    }
  }
}
