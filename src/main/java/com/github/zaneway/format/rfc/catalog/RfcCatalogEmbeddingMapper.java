package com.github.zaneway.format.rfc.catalog;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * 将 RFC 摘要目录记录映射为 Qdrant catalog collection 的向量文档。
 *
 * <p>嵌入文本使用规范化组装串，而非原始 {@code summary} 字段，避免与 abstract 重复截断。</p>
 */
@Component
public class RfcCatalogEmbeddingMapper {

  static final String UNIT_TYPE = "catalog_summary";
  static final String SCHEMA_VERSION = "rfc-catalog-v1";

  private static final Set<String> KNOWN_CATEGORIES = Set.of(
      "Standards Track",
      "Informational",
      "Experimental",
      "Best Current Practice",
      "Historic",
      "Proposed Standard");

  private static final Map<String, String> CATEGORY_ALIASES = Map.of(
      "Standard Track", "Standards Track",
      "Standards", "Standards Track");

  /**
   * @param summary 已去重的目录摘要
   * @return 可写入 catalog collection 的向量文档
   */
  public RfcVectorDocument toVectorDocument(RfcCatalogSummary summary) {
    String rfcNumber = nullToEmpty(summary.rfcNumber()).trim();
    String title = nullToEmpty(summary.title()).trim();
    String categoryRaw = nullToEmpty(summary.category()).trim();
    String category = normalizeCategory(categoryRaw);
    String published = nullToEmpty(summary.publicationDate()).trim();
    String obsoletesRaw = nullToEmpty(summary.obsoletes()).trim();
    String updatesRaw = nullToEmpty(summary.updates()).trim();
    List<String> obsoletes = splitRfcList(obsoletesRaw);
    List<String> updates = splitRfcList(updatesRaw);
    String abstractText = nullToEmpty(summary.abstractText()).trim();

    String text = """
        RFC %s: %s
        Category: %s
        Published: %s
        Obsoletes: %s
        Updates: %s

        %s""".formatted(
        rfcNumber,
        title,
        category,
        published,
        obsoletesRaw,
        updatesRaw,
        abstractText);

    LinkedHashMap<String, Object> metadata = new LinkedHashMap<>();
    metadata.put("rfc_number", rfcNumber);
    metadata.put("document_id", "rfc-" + rfcNumber);
    metadata.put("unit_type", UNIT_TYPE);
    metadata.put("schema_version", SCHEMA_VERSION);
    metadata.put("title", title);
    metadata.put("category", category);
    if (!categoryRaw.isEmpty() && !categoryRaw.equals(category)) {
      metadata.put("category_raw", categoryRaw);
    } else if (!categoryRaw.isEmpty() && category.isEmpty()) {
      metadata.put("category_raw", categoryRaw);
    }
    metadata.put("publication_date", published);
    metadata.put("obsoletes", obsoletes);
    metadata.put("updates", updates);
    metadata.put("source_file", nullToEmpty(summary.sourceFile()).trim());
    metadata.put("extract_method", nullToEmpty(summary.extractMethod()).trim());
    String warning = nullToEmpty(summary.warning()).trim();
    if (!warning.isEmpty()) {
      metadata.put("warning", warning);
    }

    return new RfcVectorDocument("rfc-" + rfcNumber + ":catalog:summary", text, Map.copyOf(metadata));
  }

  static String normalizeCategory(String raw) {
    if (raw.isEmpty()) {
      return "";
    }
    String alias = CATEGORY_ALIASES.get(raw);
    if (alias != null) {
      return alias;
    }
    if (KNOWN_CATEGORIES.contains(raw)) {
      return raw;
    }
    return "";
  }

  static List<String> splitRfcList(String raw) {
    if (raw == null || raw.isBlank()) {
      return List.of();
    }
    String[] parts = raw.split(",");
    List<String> values = new ArrayList<>(parts.length);
    for (String part : parts) {
      String token = part.trim();
      if (token.toLowerCase(Locale.ROOT).startsWith("rfc")) {
        token = token.substring(3).trim();
      }
      if (!token.isEmpty()) {
        values.add(token);
      }
    }
    return List.copyOf(values);
  }

  private static String nullToEmpty(String value) {
    return value == null ? "" : value;
  }
}
