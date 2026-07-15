package com.github.zaneway.format.rfc.catalog;

import com.github.zaneway.format.rfc.vector.RfcVectorDocument;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;

import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class SpringAiRfcCatalogVectorClientTest {

  @Test
  void deletesCatalogUnitTypeThenAddsDeterministicUuidPoints() {
    RecordingVectorStore store = new RecordingVectorStore();
    SpringAiRfcCatalogVectorClient client = new SpringAiRfcCatalogVectorClient(store.proxy());

    client.replaceAll(List.of(new RfcVectorDocument(
        "rfc-9000:catalog:summary",
        "RFC 9000: QUIC",
        Map.of("rfc_number", "9000", "unit_type", "catalog_summary"))));

    assertThat(store.deletedFilters).hasSize(1);
    Document stored = store.addedDocuments.getFirst();
    assertThat(stored.getId()).isEqualTo(UUID.nameUUIDFromBytes(
        "catalog\u00009000".getBytes(StandardCharsets.UTF_8)).toString());
    assertThat(stored.getText()).isEqualTo("RFC 9000: QUIC");
    assertThat(stored.getMetadata()).containsEntry("unit_id", "rfc-9000:catalog:summary");
  }

  private static final class RecordingVectorStore {
    private final List<Document> addedDocuments = new ArrayList<>();
    private final List<Object> deletedFilters = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private VectorStore proxy() {
      return (VectorStore) Proxy.newProxyInstance(VectorStore.class.getClassLoader(),
          new Class[]{VectorStore.class}, (proxy, method, arguments) -> {
            if (method.getName().equals("add")) {
              addedDocuments.addAll((List<Document>) arguments[0]);
            }
            if (method.getName().equals("delete") && arguments != null && arguments.length == 1) {
              deletedFilters.add(arguments[0]);
            }
            if (method.getName().equals("getName")) {
              return "recording-catalog";
            }
            return null;
          });
    }
  }
}
