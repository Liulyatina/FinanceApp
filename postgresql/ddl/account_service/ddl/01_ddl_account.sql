\c account_service

CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION account_service_app;

CREATE TABLE IF NOT EXISTS app.operation
(
    uuid        UUID PRIMARY KEY,
    date        TIMESTAMP,
    description VARCHAR(255),
    dt_create   TIMESTAMPTZ NOT NULL,
    dt_update   TIMESTAMPTZ NOT NULL,
    category    UUID,
    value       DOUBLE PRECISION,
    currency    UUID
);

CREATE TABLE IF NOT EXISTS app.account
(
    uuid        UUID PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    balance     DOUBLE PRECISION,
    dt_create   TIMESTAMPTZ NOT NULL,
    dt_update   TIMESTAMPTZ NOT NULL,
    type        VARCHAR(255),
    currency    UUID
);

CREATE TABLE IF NOT EXISTS app.account_operation
(
    account_uuid   UUID NOT NULL,
    operation_uuid UUID NOT NULL,
    PRIMARY KEY (account_uuid, operation_uuid),
    CONSTRAINT fk_account FOREIGN KEY (account_uuid) REFERENCES app.account (uuid),
    CONSTRAINT fk_operation FOREIGN KEY (operation_uuid) REFERENCES app.operation (uuid)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA app TO account_service_app;
