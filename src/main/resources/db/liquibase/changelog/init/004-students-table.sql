-- liquibase formatted sql
-- changeset Sharipov:0.0.4 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'students' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.students (
    group_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL,
    PRIMARY KEY (user_id),
    INDEX group_id_idx (group_id ASC) VISIBLE,
    INDEX user_id_students_idx (user_id ASC) VISIBLE,
    UNIQUE INDEX user_id_UNIQUE (user_id ASC) VISIBLE,
    CONSTRAINT group_id_students
        FOREIGN KEY (group_id)
            REFERENCES distance_study_platform.student_groups (group_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT user_id_students
        FOREIGN KEY (user_id)
            REFERENCES distance_study_platform.users (user_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)

-- rollback drop table distance_study_platform.students;
