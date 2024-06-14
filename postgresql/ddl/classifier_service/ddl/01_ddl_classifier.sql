\c classifier_service

CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION classifier_service_app;

CREATE TABLE IF NOT EXISTS app.currency
(
    uuid        UUID PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    dt_create   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    dt_update   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS app.operation_category
(
    uuid      UUID PRIMARY KEY,
    title     VARCHAR(255),
    dt_create TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    dt_update TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA app TO classifier_service_app;
