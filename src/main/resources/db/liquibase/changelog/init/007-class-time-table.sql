-- liquibase formatted sql
-- changeset Sharipov:0.0.7 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'class_time' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.class_time (
    class_time_id BIGINT  NOT NULL AUTO_INCREMENT,
    start_time    TIME NOT NULL,
    end_time    TIME NOT NULL,
    PRIMARY KEY (class_time_id)
)

-- rollback drop table distance_study_platform.class_time;
