package com.github.zaneway.format.rfc.config;

import io.qdrant.client.QdrantClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.qdrant.QdrantVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 为 RFC 摘要目录提供独立的 Qdrant collection（默认 {@code rfc-catalog}），
 * 与章节向量 collection {@code rfc-data} 隔离。
 */
@Configuration
public class RfcCatalogVectorStoreConfiguration {

  /**
   * @param qdrantClient     与主 VectorStore 共用的 Qdrant gRPC 客户端
   * @param embeddingModel   与查询侧一致的 embedding 模型
   * @param collectionName   catalog collection 名称
   * @param initializeSchema 是否在缺失时自动创建 collection
   */
  @Bean(name = "rfcCatalogVectorStore")
  public VectorStore rfcCatalogVectorStore(QdrantClient qdrantClient,
      EmbeddingModel embeddingModel,
      @Value("${rfc.catalog.collection-name:rfc-catalog}") String collectionName,
      @Value("${rfc.catalog.initialize-schema:true}") boolean initializeSchema) {
    return QdrantVectorStore.builder(qdrantClient, embeddingModel)
        .collectionName(collectionName)
        .initializeSchema(initializeSchema)
        .build();
  }
}
