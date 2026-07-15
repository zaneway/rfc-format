package com.github.zaneway.format.rfc.rag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

/** RFC 问答必须先规划查询，再执行证据补充检索与最终作答。 */
class RfcQuestionAnswerServiceTest {

  @Test
  void plansInitialQueryBeforeCallingQdrant() {
    RecordingVectorStore vectorStore = new RecordingVectorStore(List.of(List.of(document(
        "point-1", "RFC evidence text", "rfc-5280:4.2.1.15:section_content:01"))));
    RecordingAnswerGenerator answerGenerator = new RecordingAnswerGenerator(
        plan("Which RFC defines Freshest CRL?", "Freshest CRL extension RFC 5280", "definition"),
        List.of(), List.of(new RfcEvidenceAssessment(true, "evidence is sufficient")));
    RfcQuestionAnswerService service = new RfcQuestionAnswerService(vectorStore.proxy(), answerGenerator);

    RfcAnswerResponse response = service.answer(new RfcAnswerRequest(
        "What is the Freshest CRL extension?", 3));

    assertThat(answerGenerator.initialPlanQuestions).containsExactly("What is the Freshest CRL extension?");
    assertThat(vectorStore.searchRequests).extracting(SearchRequest::getQuery)
        .containsExactly("Freshest CRL extension RFC 5280");
    assertThat(vectorStore.searchRequests).singleElement().satisfies(request ->
        assertThat(request.getTopK()).isEqualTo(9));
    assertThat(answerGenerator.assessmentQuestions).containsExactly("Which RFC defines Freshest CRL?");
    assertThat(response.answer()).isEqualTo(new RfcAnswer(true, "grounded answer"));
  }

  @Test
  void plansSupplementalQueryFromAccumulatedEvidenceBeforeSecondQdrantQuery() {
    RecordingVectorStore vectorStore = new RecordingVectorStore(List.of(
        List.of(document("point-1", "Initial evidence", "rfc-5280:4.2.1.15:section_content:01")),
        List.of(document("point-2", "Supplemental evidence", "rfc-5280:5:section_content:01"))));
    RecordingAnswerGenerator answerGenerator = new RecordingAnswerGenerator(
        plan("What are Freshest CRL requirements?", "Freshest CRL RFC 5280", "requirements"),
        List.of(plan("Which RFC processing rules govern Freshest CRL?",
            "Freshest CRL processing requirements", "processing requirements")),
        List.of(new RfcEvidenceAssessment(false, "Freshest CRL processing requirements"),
            new RfcEvidenceAssessment(true, "evidence is sufficient")));
    RfcQuestionAnswerService service = new RfcQuestionAnswerService(vectorStore.proxy(), answerGenerator);

    RfcAnswerResponse response = service.answer(new RfcAnswerRequest("What is Freshest CRL?", 2));

    assertThat(vectorStore.searchRequests).extracting(SearchRequest::getQuery)
        .containsExactly("Freshest CRL RFC 5280", "Freshest CRL processing requirements");
    assertThat(answerGenerator.supplementalPlanMissingInformation)
        .containsExactly("Freshest CRL processing requirements");
    assertThat(answerGenerator.assessmentQuestions).containsExactly("What are Freshest CRL requirements?",
        "Which RFC processing rules govern Freshest CRL?");
    assertThat(response.sources()).hasSize(2);
  }

  @Test
  void prioritizesNewEvidenceInContextWhenTheFirstRoundAlreadyFilledTheBudget() {
    RecordingVectorStore vectorStore = new RecordingVectorStore(List.of(
        List.of(document("point-1", "A".repeat(4_000), "rfc-5280:1:section_content:01"),
            document("point-2", "B".repeat(4_000), "rfc-5280:2:section_content:01"),
            document("point-3", "C".repeat(4_000), "rfc-5280:3:section_content:01"),
            document("point-4", "D".repeat(4_000), "rfc-5280:4:section_content:01")),
        List.of(document("point-5", "LATEST SUPPLEMENTAL EVIDENCE",
            "rfc-5280:5:section_content:01"))));
    RecordingAnswerGenerator answerGenerator = new RecordingAnswerGenerator(
        plan("initial", "initial query", "initial"),
        List.of(plan("follow up", "follow up query", "supplemental evidence")),
        List.of(new RfcEvidenceAssessment(false, "supplemental evidence"),
            new RfcEvidenceAssessment(true, "evidence is sufficient")));
    RfcQuestionAnswerService service = new RfcQuestionAnswerService(vectorStore.proxy(), answerGenerator);

    service.answer(new RfcAnswerRequest("What is Freshest CRL?", 4));

    assertThat(answerGenerator.assessmentContexts).hasSize(2);
    assertThat(answerGenerator.assessmentContexts.get(1)).contains("LATEST SUPPLEMENTAL EVIDENCE");
  }

  @Test
  void stopsSupplementalRetrievalWhenTheSecondQueryAddsNoEvidence() {
    RecordingVectorStore vectorStore = new RecordingVectorStore(List.of(List.of(), List.of(), List.of(),
        List.of(), List.of()));
    RecordingAnswerGenerator answerGenerator = new RecordingAnswerGenerator(plan("initial", "initial query", "initial"),
        List.of(plan("q2", "query 2", "missing 2"), plan("q3", "query 3", "missing 3"),
            plan("q4", "query 4", "missing 4"), plan("q5", "query 5", "missing 5")),
        List.of(insufficientAssessment(1), insufficientAssessment(2), insufficientAssessment(3),
            insufficientAssessment(4), insufficientAssessment(5)));
    RfcQuestionAnswerService service = new RfcQuestionAnswerService(vectorStore.proxy(), answerGenerator);

    RfcAnswerResponse response = service.answer(new RfcAnswerRequest("What is Freshest CRL?", 2));

    assertThat(vectorStore.searchRequests).hasSize(2);
    assertThat(answerGenerator.supplementalPlanMissingInformation).hasSize(1);
    assertThat(answerGenerator.finalContexts).hasSize(1);
    assertThat(response.answer().result()).isFalse();
  }

  @Test
  void usesAllFiveRoundsWhenEachSupplementalQueryAddsNewEvidence() {
    RecordingVectorStore vectorStore = new RecordingVectorStore(List.of(
        List.of(document("point-1", "Evidence 1", "rfc-5280:1:section_content:01")),
        List.of(document("point-2", "Evidence 2", "rfc-5280:2:section_content:01")),
        List.of(document("point-3", "Evidence 3", "rfc-5280:3:section_content:01")),
        List.of(document("point-4", "Evidence 4", "rfc-5280:4:section_content:01")),
        List.of(document("point-5", "Evidence 5", "rfc-5280:5:section_content:01"))));
    RecordingAnswerGenerator answerGenerator = new RecordingAnswerGenerator(plan("initial", "initial query", "initial"),
        List.of(plan("q2", "query 2", "missing 2"), plan("q3", "query 3", "missing 3"),
            plan("q4", "query 4", "missing 4"), plan("q5", "query 5", "missing 5")),
        List.of(insufficientAssessment(1), insufficientAssessment(2), insufficientAssessment(3),
            insufficientAssessment(4), insufficientAssessment(5)));
    RfcQuestionAnswerService service = new RfcQuestionAnswerService(vectorStore.proxy(), answerGenerator);

    RfcAnswerResponse response = service.answer(new RfcAnswerRequest("What is Freshest CRL?", 2));

    assertThat(vectorStore.searchRequests).hasSize(5);
    assertThat(answerGenerator.supplementalPlanMissingInformation).hasSize(4);
    assertThat(response.sources()).hasSize(5);
    assertThat(response.answer().result()).isFalse();
  }

  @Test
  void rejectsBlankQuestionBeforePlanningOrVectorSearch() {
    RecordingVectorStore vectorStore = new RecordingVectorStore(List.of());
    RecordingAnswerGenerator answerGenerator = new RecordingAnswerGenerator(plan("q", "query", "info"),
        List.of(), List.of());
    RfcQuestionAnswerService service = new RfcQuestionAnswerService(vectorStore.proxy(), answerGenerator);

    assertThatThrownBy(() -> service.answer(new RfcAnswerRequest(" ", null)))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("question");
    assertThat(answerGenerator.initialPlanQuestions).isEmpty();
    assertThat(vectorStore.searchRequests).isEmpty();
  }

  private static Document document(String id, String text, String unitId) {
    return new Document(id, text, Map.of(
        "unit_id", unitId, "rfc_number", "5280", "section_id", "4.2.1.15",
        "source_start_line", 2671, "source_end_line", 2690));
  }

  private static RfcQueryPlan plan(String question, String query, String requiredInformation) {
    return new RfcQueryPlan(question, query, requiredInformation);
  }

  private static RfcEvidenceAssessment insufficientAssessment(int round) {
    return new RfcEvidenceAssessment(false, "missing evidence " + round);
  }

  private static final class RecordingAnswerGenerator implements RfcAnswerGenerator {
    private final RfcQueryPlan initialPlan;
    private final List<RfcQueryPlan> supplementalPlans;
    private final List<RfcEvidenceAssessment> assessments;
    private final List<String> initialPlanQuestions = new ArrayList<>();
    private final List<String> supplementalPlanMissingInformation = new ArrayList<>();
    private final List<String> assessmentQuestions = new ArrayList<>();
    private final List<String> assessmentContexts = new ArrayList<>();
    private final List<String> finalContexts = new ArrayList<>();
    private int supplementalPlanIndex;
    private int assessmentIndex;

    private RecordingAnswerGenerator(RfcQueryPlan initialPlan, List<RfcQueryPlan> supplementalPlans,
        List<RfcEvidenceAssessment> assessments) {
      this.initialPlan = initialPlan;
      this.supplementalPlans = supplementalPlans;
      this.assessments = assessments;
    }

    @Override
    public RfcQueryPlan planInitialQuery(String originalQuestion) {
      initialPlanQuestions.add(originalQuestion);
      return initialPlan;
    }

    @Override
    public RfcQueryPlan planSupplementalQuery(String originalQuestion, String currentQuestion,
        String context, String missingInformation) {
      supplementalPlanMissingInformation.add(missingInformation);
      return supplementalPlans.get(supplementalPlanIndex++);
    }

    @Override
    public RfcEvidenceAssessment assessEvidence(String originalQuestion, String currentQuestion,
        String context) {
      assessmentQuestions.add(currentQuestion);
      assessmentContexts.add(context);
      return assessments.get(assessmentIndex++);
    }

    @Override
    public RfcAnswer generateFinalAnswer(String question, String context,
        RfcEvidenceAssessment assessment) {
      finalContexts.add(context);
      return new RfcAnswer(true, "grounded answer");
    }
  }

  private static final class RecordingVectorStore {
    private final List<List<Document>> resultBatches;
    private final List<SearchRequest> searchRequests = new ArrayList<>();
    private int resultIndex;

    private RecordingVectorStore(List<List<Document>> resultBatches) {
      this.resultBatches = resultBatches;
    }

    private VectorStore proxy() {
      return (VectorStore) Proxy.newProxyInstance(VectorStore.class.getClassLoader(),
          new Class[]{VectorStore.class}, (proxy, method, arguments) -> {
            if (method.getName().equals("similaritySearch")) {
              searchRequests.add((SearchRequest) arguments[0]);
              return resultIndex < resultBatches.size() ? resultBatches.get(resultIndex++) : List.of();
            }
            throw new UnsupportedOperationException(method.getName());
          });
    }
  }
}
