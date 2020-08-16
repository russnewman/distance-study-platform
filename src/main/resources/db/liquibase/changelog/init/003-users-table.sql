-- liquibase formatted sql
-- changeset Sharipov:0.0.3 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'users' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.users (
    user_id  BIGINT         NOT NULL AUTO_INCREMENT,
    email    VARCHAR(45) NOT NULL unique ,
    name     VARCHAR(45) NOT NULL,
    surname  VARCHAR(45) NULL,
    login    VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    active boolean not null default true,
    role varchar(45) not null,
    INDEX email_idx (email ASC) VISIBLE,
    INDEX login_idx (login ASC) VISIBLE,
    PRIMARY KEY (user_id)
)

-- rollback drop table distance_study_platform.users;
