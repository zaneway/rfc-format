package com.github.zaneway.format.rfc.vector;

/**
 * 单次 Qdrant 投影的可观测结果，供导入流水线返回给调用方。
 *
 * @param status             投影最终状态
 * @param projectedUnitCount 成功写入的向量条数；失败时为 0
 * @param errorMessage       失败原因；成功时为 {@code null}
 */
public record ProjectionResult(ProjectionStatus status, int projectedUnitCount,
                               String errorMessage) {

  /**
   * @param projectedUnitCount 实际写入 Qdrant 的 canonical 单元数量
   * @return 成功结果
   */
  public static ProjectionResult succeeded(int projectedUnitCount) {
    return new ProjectionResult(ProjectionStatus.SUCCEEDED, projectedUnitCount, null);
  }

  /**
   * @param errorMessage 截断后的失败原因
   * @return 失败结果，投影条数为 0
   */
  public static ProjectionResult failed(String errorMessage) {
    return new ProjectionResult(ProjectionStatus.FAILED, 0, errorMessage);
  }
}
