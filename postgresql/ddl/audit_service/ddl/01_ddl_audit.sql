\c audit_service

CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION audit_service_app;

CREATE TABLE IF NOT EXISTS app.audit
(
    uuid UUID PRIMARY KEY,
    user_id UUID,
    text TEXT NOT NULL,
    type VARCHAR(50) NOT NULL,
    entity_id UUID,
    dt_create TIMESTAMPTZ NOT NULL DEFAULT now()
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA app TO audit_service_app;