CREATE ROLE audit_service_app WITH
    LOGIN
    NOSUPERUSER
    CREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD '12345';

CREATE DATABASE audit_service
	WITH
	OWNER=audit_service_app
	ENCODING='UTF8'
	CONNECTION LIMIT=-1
	IS_TEMPLATE=False;

CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION audit_service_app;

    CREATE TABLE app.users
    (
        uuid UUID PRIMARY KEY,
        mail VARCHAR(255) NOT NULL,
        fio  VARCHAR(255) NOT NULL,
        role VARCHAR(50)  NOT NULL
    );

CREATE TABLE app.audit
(
    uuid      UUID PRIMARY KEY,
    user_id   UUID        NOT NULL,
    text      TEXT        NOT NULL,
    type      VARCHAR(50) NOT NULL,
    entity_id UUID,
    dt_create TIMESTAMPTZ   NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES app.users (uuid)
);