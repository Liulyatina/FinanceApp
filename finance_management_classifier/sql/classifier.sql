CREATE ROLE classifier_service_app WITH
    LOGIN
    NOSUPERUSER
    CREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD '12345';

CREATE DATABASE classifier_service
	WITH
	OWNER=classifier_service_app
	ENCODING='UTF8'
	CONNECTION LIMIT=-1
	IS_TEMPLATE=False;

CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION classifier_service_app;

CREATE TABLE app.currency
(
    uuid        UUID PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    dt_create   TIMESTAMPTZ WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    dt_update   TIMESTAMPTZ WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE app.operation_category
(
    uuid      UUID PRIMARY KEY,
    title     VARCHAR(255),
    dt_create TIMESTAMPTZ WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    dt_update TIMESTAMPTZ WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);