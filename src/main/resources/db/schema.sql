CREATE TABLE IF NOT EXISTS rfc_document (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    document_key VARCHAR(128) NOT NULL,
    rfc_number VARCHAR(32) NOT NULL,
    title VARCHAR(1024) NOT NULL,
    publication_date VARCHAR(32) NULL,
    status VARCHAR(128) NULL,
    category VARCHAR(128) NULL,
    obsoletes_json JSON NULL,
    updates_json JSON NULL,
    source_file VARCHAR(1024) NOT NULL,
    source_uri VARCHAR(2048) NULL,
    source_sha256 CHAR(64) NOT NULL,
    parser_version VARCHAR(64) NOT NULL,
    cleaning_policy_version VARCHAR(64) NOT NULL,
    chunking_policy_version VARCHAR(64) NOT NULL,
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id),
    UNIQUE KEY uk_rfc_document_document_key (document_key),
    KEY idx_rfc_document_rfc_number (rfc_number),
    KEY idx_rfc_document_source_sha256 (source_sha256)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS rfc_section (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    document_id BIGINT UNSIGNED NOT NULL,
    section_key VARCHAR(255) NOT NULL,
    parent_section_key VARCHAR(255) NULL,
    title VARCHAR(1024) NOT NULL,
    section_type VARCHAR(64) NOT NULL,
    section_path_json JSON NULL,
    source_start_line INT UNSIGNED NOT NULL,
    source_end_line INT UNSIGNED NOT NULL,
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id),
    UNIQUE KEY uk_rfc_section_document_section (document_id, section_key),
    KEY idx_rfc_section_document_id (document_id),
    CONSTRAINT fk_rfc_section_document
        FOREIGN KEY (document_id) REFERENCES rfc_document (id)
        ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS rfc_unit (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    document_id BIGINT UNSIGNED NOT NULL,
    unit_key VARCHAR(512) NOT NULL,
    parent_section_key VARCHAR(255) NULL,
    unit_type VARCHAR(64) NOT NULL,
    content LONGTEXT NOT NULL,
    embedding_text LONGTEXT NOT NULL,
    source_start_line INT UNSIGNED NOT NULL,
    source_end_line INT UNSIGNED NOT NULL,
    language VARCHAR(32) NULL,
    entity_type VARCHAR(64) NULL,
    entity_name VARCHAR(512) NULL,
    semantic_json JSON NULL,
    metadata_json JSON NULL,
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id),
    UNIQUE KEY uk_rfc_unit_unit_key (unit_key),
    KEY idx_rfc_unit_document_id (document_id),
    KEY idx_rfc_unit_document_type (document_id, unit_type),
    KEY idx_rfc_unit_entity_name (entity_name),
    CONSTRAINT fk_rfc_unit_document
        FOREIGN KEY (document_id) REFERENCES rfc_document (id)
        ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS rfc_relation (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    document_id BIGINT UNSIGNED NOT NULL,
    relation_key VARCHAR(512) NOT NULL,
    relation_type VARCHAR(64) NOT NULL,
    from_kind VARCHAR(32) NOT NULL,
    from_identifier VARCHAR(512) NOT NULL,
    to_kind VARCHAR(32) NOT NULL,
    to_identifier VARCHAR(512) NOT NULL,
    source_section_key VARCHAR(255) NULL,
    source_start_line INT UNSIGNED NULL,
    source_end_line INT UNSIGNED NULL,
    attributes_json JSON NULL,
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id),
    UNIQUE KEY uk_rfc_relation_relation_key (relation_key),
    KEY idx_rfc_relation_document_id (document_id),
    KEY idx_rfc_relation_target (to_kind, to_identifier),
    CONSTRAINT fk_rfc_relation_document
        FOREIGN KEY (document_id) REFERENCES rfc_document (id)
        ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS rfc_ingestion_run (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    run_key CHAR(36) NOT NULL,
    document_id BIGINT UNSIGNED NULL,
    input_path VARCHAR(2048) NOT NULL,
    input_sha256 CHAR(64) NULL,
    status VARCHAR(32) NOT NULL,
    statistics_json JSON NULL,
    error_message TEXT NULL,
    started_at DATETIME(3) NOT NULL,
    finished_at DATETIME(3) NULL,
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id),
    UNIQUE KEY uk_rfc_ingestion_run_run_key (run_key),
    KEY idx_rfc_ingestion_run_document_id (document_id),
    KEY idx_rfc_ingestion_run_status_started_at (status, started_at),
    CONSTRAINT fk_rfc_ingestion_run_document
        FOREIGN KEY (document_id) REFERENCES rfc_document (id)
        ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
