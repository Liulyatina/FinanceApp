\c user_service

CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION user_service_app;

CREATE TABLE IF NOT EXISTS app.users
(
    uuid      UUID PRIMARY KEY,
    mail      VARCHAR(255) UNIQUE,
    fio       VARCHAR(255),
    role      VARCHAR(50),
    status    VARCHAR(50),
    password  VARCHAR(255),
    dt_create TIMESTAMPTZ,
    dt_update TIMESTAMPTZ NOT NULL
);

CREATE TABLE IF NOT EXISTS app.email_status
(
    uuid   UUID PRIMARY KEY,
    mail   UUID REFERENCES app.users (uuid) ON DELETE CASCADE,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS app.verification
(
    uuid              UUID PRIMARY KEY,
    mail              UUID REFERENCES app.users (uuid) ON DELETE CASCADE,
    verification_code VARCHAR(255)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA app TO user_service_app;
