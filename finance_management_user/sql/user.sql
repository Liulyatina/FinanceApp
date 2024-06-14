CREATE ROLE user_service_app WITH
    LOGIN
    NOSUPERUSER
    CREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD '12345';

CREATE DATABASE user_service
	WITH
	OWNER=user_service_app
	ENCODING='UTF8'
	CONNECTION LIMIT=-1
	IS_TEMPLATE=False;

CREATE SCHEMA IF NOT EXISTS  app AUTHORIZATION user_service_app;

CREATE TABLE app.users
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

CREATE TABLE app.email_status
(
    uuid   UUID PRIMARY KEY,
    mail   UUID REFERENCES app.users (uuid) ON DELETE CASCADE,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE app.verification
(
    uuid              UUID PRIMARY KEY,
    mail              UUID REFERENCES app.users (uuid) ON DELETE CASCADE,
    verification_code VARCHAR(255)
);