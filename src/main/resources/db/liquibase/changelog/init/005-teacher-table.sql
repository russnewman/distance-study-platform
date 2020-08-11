-- liquibase formatted sql
-- changeset Sharipov:0.0.5 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'teachers' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.teachers (
    user_id BIGINT NOT NULL,
    INDEX user_id_teachers_idx (user_id ASC) VISIBLE,
    UNIQUE INDEX user_id_UNIQUE (user_id ASC) VISIBLE,
    PRIMARY KEY (user_id),
    CONSTRAINT user_id_teachers
        FOREIGN KEY (user_id)
            REFERENCES distance_study_platform.users (user_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)

-- rollback drop table distance_study_platform.teachers;
