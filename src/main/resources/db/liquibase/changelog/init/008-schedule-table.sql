-- liquibase formatted sql
-- changeset Sharipov:0.0.8 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'schedule' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.schedule (
                                                  schedule_id   BIGINT         NOT NULL AUTO_INCREMENT,
                                                  subject_id    BIGINT         NOT NULL,
                                                  odd_week      boolean     NOT NULL,
                                                  group_id      BIGINT         NOT NULL,
                                                  class_time_id BIGINT         NOT NULL,
                                                  class_type    VARCHAR(45) NOT NULL,
                                                  day_name      VARCHAR(45) NOT NULL,
                                                  teacher_id       BIGINT         NOT NULL,
                                                  lesson_link              VARCHAR(200) NULL,
                                                  PRIMARY KEY (schedule_id),
                                                  INDEX group_id_idx (group_id ASC) VISIBLE,
                                                  INDEX class_time_id_idx (class_time_id ASC) VISIBLE,
                                                  INDEX subject_id_idx (subject_id ASC) VISIBLE,
                                                  INDEX fk_SCHEDULE_TEACHERS1_idx (teacher_id ASC) VISIBLE,
                                                  CONSTRAINT group_id_schedule
                                                      FOREIGN KEY (group_id)
                                                          REFERENCES distance_study_platform.student_groups (group_id)
                                                          ON DELETE NO ACTION
                                                          ON UPDATE NO ACTION,
                                                  CONSTRAINT class_time_id_schedule
                                                      FOREIGN KEY (class_time_id)
                                                          REFERENCES distance_study_platform.class_time (class_time_id)
                                                          ON DELETE NO ACTION
                                                          ON UPDATE NO ACTION,
                                                  CONSTRAINT subject_id_schedule
                                                      FOREIGN KEY (subject_id)
                                                          REFERENCES distance_study_platform.subjects (subject_id)
                                                          ON DELETE NO ACTION
                                                          ON UPDATE NO ACTION,
                                                  CONSTRAINT fk_SCHEDULE_TEACHERS1
                                                      FOREIGN KEY (teacher_id)
                                                          REFERENCES distance_study_platform.teachers (user_id)
                                                          ON DELETE NO ACTION
                                                          ON UPDATE NO ACTION
)



-- rollback drop table distance_study_platform.schedule;
