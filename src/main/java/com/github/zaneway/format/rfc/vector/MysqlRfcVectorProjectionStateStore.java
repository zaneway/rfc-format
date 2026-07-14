package com.github.zaneway.format.rfc.vector;

import com.github.zaneway.format.rfc.persistence.mapper.RfcVectorProjectionRecordMapper;
import com.github.zaneway.format.rfc.persistence.model.RfcVectorProjectionRecord;
import com.github.zaneway.format.rfc.storage.StoredRfcDocument;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.github.zaneway.format.rfc.persistence.mapper.RfcVectorProjectionRecordDynamicSqlSupport.documentId;
import static com.github.zaneway.format.rfc.persistence.mapper.RfcVectorProjectionRecordDynamicSqlSupport.projectionName;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * `rfc_vector_projection` 表的 MyBatis 实现，跟踪 Qdrant 投影生命周期。
 */
@Repository
public class MysqlRfcVectorProjectionStateStore implements RfcVectorProjectionStateStore {

  private static final String PROJECTION_NAME = "qdrant";
  private static final String RUNNING_STATUS = "RUNNING";
  private static final String SUCCEEDED_STATUS = "SUCCEEDED";
  private static final String FAILED_STATUS = "FAILED";

  private final RfcVectorProjectionRecordMapper projectionMapper;

  /**
   * @param projectionMapper 向量投影状态表映射器
   */
  public MysqlRfcVectorProjectionStateStore(RfcVectorProjectionRecordMapper projectionMapper) {
    this.projectionMapper = projectionMapper;
  }

  /**
   * {@inheritDoc}
   */
  @Override
    public void markStarted(StoredRfcDocument document) {
        // 同一 documentId + projection_name 仅一行：重导入走重启，首次导入走创建
        projectionMapper.selectOne(c -> c.where(documentId, isEqualTo(document.documentId()))
                        .and(projectionName, isEqualTo(PROJECTION_NAME)))
                .ifPresentOrElse(existing -> restartProjection(existing, document),
                        () -> createProjection(document));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markSucceeded(StoredRfcDocument document) {
        findProjection(document).ifPresent(record -> {
            record.setStatus(SUCCEEDED_STATUS);
            // 成功必须清掉上次失败消息，避免运维误读陈旧 error
            record.setErrorMessage(null);
            record.setCompletedAt(LocalDateTime.now(ZoneOffset.UTC));
            projectionMapper.updateByPrimaryKeySelective(record);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markFailed(StoredRfcDocument document, String errorMessage) {
        findProjection(document).ifPresent(record -> {
            record.setStatus(FAILED_STATUS);
            record.setErrorMessage(errorMessage);
            // 失败不写 completedAt：表示尚未成功收尾，便于与 SUCCEEDED 区分
            record.setCompletedAt(null);
            projectionMapper.updateByPrimaryKeySelective(record);
        });
    }

    /**
     * 重导入同一文档时复用已有状态行，递增 attempt_count 并刷新源摘要，支持失败重试观测。
     */
    private void restartProjection(RfcVectorProjectionRecord record, StoredRfcDocument document) {
        // 源变更后新投影必须以新 sha256 关联，运维才能对比「旧成功快照 vs 新失败尝试」
        record.setSourceSha256(document.sourceSha256());
        record.setStatus(RUNNING_STATUS);
        // attempt_count 单调递增，是重试次数的唯一可观测指标
        record.setAttemptCount(
                record.getAttemptCount() == null ? 1 : Math.incrementExact(record.getAttemptCount()));
        record.setErrorMessage(null);
        record.setCompletedAt(null);
        projectionMapper.updateByPrimaryKeySelective(record);
    }

    /** 首次投影：插入 RUNNING 状态行，attempt_count 从 1 起计。 */
    private void createProjection(StoredRfcDocument document) {
        RfcVectorProjectionRecord record = new RfcVectorProjectionRecord();
        record.setDocumentId(document.documentId());
        record.setProjectionName(PROJECTION_NAME);
        record.setSourceSha256(document.sourceSha256());
        record.setStatus(RUNNING_STATUS);
        record.setAttemptCount(1);
        projectionMapper.insertSelective(record);
    }

    /** 按 documentId + 固定投影名定位唯一状态行（当前仅支持 qdrant）。 */
    private java.util.Optional<RfcVectorProjectionRecord> findProjection(StoredRfcDocument document) {
        return projectionMapper.selectOne(c -> c.where(documentId, isEqualTo(document.documentId()))
                .and(projectionName, isEqualTo(PROJECTION_NAME)));
    }
}
