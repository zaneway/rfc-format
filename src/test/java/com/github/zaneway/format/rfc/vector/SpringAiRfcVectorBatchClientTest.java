package com.github.zaneway.format.rfc.vector;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;

class SpringAiRfcVectorBatchClientTest {

  @Test
  void writesBoundedBatchesAndUsesDeterministicQdrantIds() {
    RecordingVectorStore store = new RecordingVectorStore();
    SpringAiRfcVectorClient client = new SpringAiRfcVectorClient(store.proxy(), 2);
    List<RfcVectorDocument> documents = List.of(
        vector("unit-1"), vector("unit-2"), vector("unit-3"));

    client.replace("9999", documents);

    assertThat(store.addBatchSizes).containsExactly(2, 1);
    assertThat(store.deleteCalls).isEqualTo(1);
    Document first = store.addedDocuments.getFirst();
    assertThat(first.getId()).isEqualTo(UUID.nameUUIDFromBytes(
        "9999\0unit-1".getBytes(StandardCharsets.UTF_8)).toString());
    assertThat(first.getMetadata()).containsEntry("unit_id", "unit-1");
  }

  private static RfcVectorDocument vector(String id) {
    return new RfcVectorDocument(id, "text", Map.of("rfc_number", "9999"));
  }

  private static final class RecordingVectorStore {
    private final List<Document> addedDocuments = new ArrayList<>();
    private final List<Integer> addBatchSizes = new ArrayList<>();
    private int deleteCalls;

    @SuppressWarnings("unchecked")
    private VectorStore proxy() {
      return (VectorStore) Proxy.newProxyInstance(VectorStore.class.getClassLoader(),
          new Class[]{VectorStore.class}, (proxy, method, arguments) -> {
            if (method.getName().equals("add")) {
              List<Document> batch = (List<Document>) arguments[0];
              addBatchSizes.add(batch.size());
              addedDocuments.addAll(batch);
            } else if (method.getName().equals("delete")) {
              deleteCalls++;
            } else if (method.getName().equals("getName")) {
              return "recording";
            }
            return null;
          });
    }
  }
}
