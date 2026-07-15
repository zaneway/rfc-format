package com.github.zaneway.format.rfc.vector;

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

class SpringAiRfcVectorClientTest {

    @Test
    void convertsStableRfcUnitIdToDeterministicQdrantUuidAndKeepsBusinessIdInMetadata() {
        RecordingVectorStore vectorStore = new RecordingVectorStore();
        SpringAiRfcVectorClient client = new SpringAiRfcVectorClient(vectorStore.proxy());

        client.replace("9999", List.of(new RfcVectorDocument("rfc-9999:1:section_content:1", "text", Map.of())));

        Document stored = vectorStore.addedDocuments.getFirst();
        assertThat(stored.getId()).isEqualTo(UUID.nameUUIDFromBytes(
                "9999\u0000rfc-9999:1:section_content:1".getBytes(StandardCharsets.UTF_8)).toString());
        assertThat(stored.getMetadata()).containsEntry("unit_id", "rfc-9999:1:section_content:1");
    }

    private static final class RecordingVectorStore {
        private final List<Document> addedDocuments = new ArrayList<>();

        @SuppressWarnings("unchecked")
        private VectorStore proxy() {
            return (VectorStore) Proxy.newProxyInstance(VectorStore.class.getClassLoader(), new Class[]{VectorStore.class},
                    (proxy, method, arguments) -> {
                        if (method.getName().equals("add")) {
                            addedDocuments.addAll((List<Document>) arguments[0]);
                        }
                        if (method.getName().equals("getName")) {
                            return "recording";
                        }
                        return null;
                    });
        }
    }
}
