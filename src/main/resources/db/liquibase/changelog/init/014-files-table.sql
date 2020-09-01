-- liquibase formatted sql
-- changeset Sharipov:0.0.8 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'files' and TABLE_SCHEMA = 'distance_study_platform'


create table files
(
    id varchar(45) not null,
    filename varchar(45) not null,
    filetype varchar(45) null,
    file longblob not null,
    constraint files_pk
        primary key (id)
);
