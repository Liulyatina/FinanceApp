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

GRANT ALL PRIVILEGES ON DATABASE user_service TO user_service_app;

