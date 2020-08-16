-- liquibase formatted sql
-- changeset Sharipov:0.1.1 endDelimiter:\n/
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.tables where TABLE_NAME = 'users_2_authorities' and TABLE_SCHEMA = 'distance_study_platform'

CREATE TABLE distance_study_platform.users_2_authorities (
    user_id BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    CONSTRAINT fk_users_2_authorities__users
        FOREIGN KEY (user_id)
            REFERENCES distance_study_platform.users (user_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_users_2_authorities__authorities
        FOREIGN KEY (authority_id)
            REFERENCES distance_study_platform.authorities (authority_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)

-- rollback drop table distance_study_platform.users_2_authorities;
