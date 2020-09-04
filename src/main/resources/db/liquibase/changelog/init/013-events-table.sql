-- liquibase formatted sql
-- changeset Sharipov:0.1.3 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'events' and TABLE_SCHEMA = 'distance_study_platform'


create table events
(
    event_id    bigint auto_increment
        primary key,
    teacher_id  bigint       not null,
    subject_id  bigint       not null,
    description varchar(500) null,
    start_date  datetime     null,
    end_date    datetime     null,
    group_id    bigint       not null,
    file_id     varchar(45)  not null,

    constraint events_files_id_fk
        foreign key (file_id) references files (id),
    constraint events_student_groups_group_id_fk
        foreign key (group_id) references student_groups (group_id),
    constraint events_subjects_subject_id_fk
        foreign key (subject_id) references subjects (subject_id),
    constraint events_teachers_user_id_fk
        foreign key (teacher_id) references teachers (user_id)
);

-- rollback drop table distance_study_platform.events;