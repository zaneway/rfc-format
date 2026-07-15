package com.github.zaneway.format.rfc.controller;

import com.github.zaneway.format.rfc.ingestion.RfcIngestionResult;
import com.github.zaneway.format.rfc.ingestion.RfcIngestionService;
import com.github.zaneway.format.rfc.parser.RfcDocumentParser;
import com.github.zaneway.format.rfc.storage.RfcDocumentStorage;
import com.github.zaneway.format.rfc.storage.StoredRfcDocument;
import com.github.zaneway.format.rfc.vector.ProjectionResult;
import com.github.zaneway.format.rfc.vector.RfcVectorProjector;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FileControllerTest {

    @Test
    void uploadsTxtToIngestionServiceUsingOriginalFileNameAsSource() {
        RecordingIngestionService ingestionService = new RecordingIngestionService();
        FileController controller = new FileController(ingestionService);

        RfcIngestionResult result = controller.ingest(new MockMultipartFile("file", "rfc9999.txt", "text/plain",
                "Request for Comments: 9999".getBytes(StandardCharsets.UTF_8)));

        assertThat(ingestionService.content).isEqualTo("Request for Comments: 9999");
        assertThat(ingestionService.inputPath).isEqualTo("upload:rfc9999.txt");
        assertThat(result.storedDocument().documentId()).isEqualTo(42L);
    }

    @Test
    void rejectsNonTxtUpload() {
        FileController controller = new FileController(new RecordingIngestionService());

        assertThatThrownBy(() -> controller.ingest(new MockMultipartFile("file", "rfc9999.pdf", "application/pdf", new byte[]{1})))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining(".txt");
    }

    @Test
    void exposesCanonicalAndLegacyUploadPaths() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new FileController(new RecordingIngestionService())).build();

        mockMvc.perform(multipart("/rfc/ingestions")
                        .file(new MockMultipartFile("file", "rfc9999.txt", "text/plain",
                                "Request for Comments: 9999".getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk());

        mockMvc.perform(multipart("/upload")
                        .file(new MockMultipartFile("file", "rfc9999.txt", "text/plain",
                                "Request for Comments: 9999".getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk());
    }

    private static final class RecordingIngestionService extends RfcIngestionService {
        private String content;
        private String inputPath;

        private RecordingIngestionService() {
            super(parser(), storage(), projector(), (path, sha256, failure) -> { });
        }

        @Override
        public RfcIngestionResult ingest(Path sourceFile, String originalInputPath) throws IOException {
            content = Files.readString(sourceFile);
            inputPath = originalInputPath;
            return new RfcIngestionResult(new StoredRfcDocument(42L, "rfc-9999", "9999", "a".repeat(64)),
                    ProjectionResult.succeeded(1));
        }

        private static RfcDocumentParser parser() {
            return path -> null;
        }

        private static RfcDocumentStorage storage() {
            return (document, inputPath) -> null;
        }

        private static RfcVectorProjector projector() {
            return (document, parsed) -> null;
        }
    }
}
