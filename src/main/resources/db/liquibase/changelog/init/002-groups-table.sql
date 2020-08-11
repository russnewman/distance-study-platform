-- liquibase formatted sql
-- changeset Sharipov:0.0.2 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'student_group' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.student_group (
    group_id   BIGINT NOT NULL AUTO_INCREMENT,
    faculty_id BIGINT NOT NULL,
    group_name VARCHAR(45) NOT NULL,
    PRIMARY KEY (group_id),
    INDEX faculty_id_idx (faculty_id ASC) VISIBLE,
    CONSTRAINT faculty_id
        FOREIGN KEY (faculty_id)
            REFERENCES distance_study_platform.faculties (faculty_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)

-- rollback drop table distance_study_platform.teams;
