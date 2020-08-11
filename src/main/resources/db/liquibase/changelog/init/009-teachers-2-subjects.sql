-- liquibase formatted sql
-- changeset Sharipov:0.0.9 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'teachers_2_subjects' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.teachers_2_subjects (
    subject_id BIGINT NOT NULL,
    teacher_id BIGINT NOT NULL,
    CONSTRAINT fk_teachers_2_subjects__subjects
        FOREIGN KEY (subject_id)
            REFERENCES distance_study_platform.subjects (subjects_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_teachers_2_subjects__teachers
        FOREIGN KEY (teacher_id)
            REFERENCES distance_study_platform.teachers (user_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)

-- rollback drop table distance_study_platform.teachers_2_subjects;
