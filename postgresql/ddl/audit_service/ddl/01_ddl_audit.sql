\c audit_service

CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION audit_service_app;

CREATE TABLE IF NOT EXISTS app.users
(
    uuid      UUID PRIMARY KEY,
    mail      VARCHAR(255) NOT NULL,
    fio       VARCHAR(255) NOT NULL,
    role      VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS app.audit
(
    uuid      UUID PRIMARY KEY,
    user_id   UUID        NOT NULL,
    text      TEXT        NOT NULL,
    type      VARCHAR(50) NOT NULL,
    entity_id UUID,
    dt_create TIMESTAMPTZ   NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES app.users (uuid)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA app TO audit_service_app;
