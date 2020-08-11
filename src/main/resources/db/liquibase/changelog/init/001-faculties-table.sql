-- liquibase formatted sql
-- changeset Sharipov:0.0.1 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'faculties' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.faculties (
    faculty_id   BIGINT         NOT NULL AUTO_INCREMENT,
    faculty_name VARCHAR(45) NOT NULL,
    PRIMARY KEY (faculty_id)
)

-- rollback drop table distance_study_platform.faculties;
