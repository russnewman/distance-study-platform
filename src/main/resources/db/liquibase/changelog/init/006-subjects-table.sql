-- liquibase formatted sql
-- changeset Sharipov:0.0.6 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'subjects' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.subjects (
    subjects_id BIGINT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(45) NOT NULL,
    description VARCHAR(500) NULL,
    PRIMARY KEY (subjects_id)
)

-- rollback drop table distance_study_platform.subjects;
