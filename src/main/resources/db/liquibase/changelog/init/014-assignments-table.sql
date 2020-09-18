-- liquibase formatted sql
-- changeset Sharipov:0.1.4 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'assignments' and TABLE_SCHEMA = 'distance_study_platform'


create table assignments
(
    assignment_id bigint auto_increment
        primary key,
    event_id      bigint       not null,
    student_id    bigint       not null,
    grade         int          null,
    commentary    varchar(200) null,
    file_id       varchar(45)  null,
    constraint assignments_events_event_id_fk
        foreign key (event_id) references events (event_id) ON DELETE CASCADE,
    constraint assignments_files_id_fk
        foreign key (file_id) references files (id),
    constraint assignments_students_user_id_fk
        foreign key (student_id) references students (user_id)
);

-- rollback drop table distance_study_platform.assignments;

