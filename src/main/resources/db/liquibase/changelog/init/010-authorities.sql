-- liquibase formatted sql
-- changeset Sharipov:0.1.0 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'authorities' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.authorities
(
    authority_id      BIGINT NOT NULL auto_increment,
    name varchar(100) not null unique ,
    PRIMARY KEY (authority_id),
    INDEX authority_id_idx (authority_id ASC) VISIBLE,
    INDEX name_idx (name ASC) VISIBLE
)

-- rollback drop table distance_study_platform.authorities;
