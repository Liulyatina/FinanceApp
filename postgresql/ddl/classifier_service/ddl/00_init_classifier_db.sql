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

GRANT ALL PRIVILEGES ON DATABASE classifier_service TO classifier_service_app;

