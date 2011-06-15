--We assume the Database Instance existence: "parkDB"

DROP USER park CASCADE;
--Oracle assumes schema and user as the same thing
CREATE USER park IDENTIFIED BY park
--we use the default installation settings
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE TEMP
QUOTA UNLIMITED ON users
--PROFILE park
--PASSWORD EXPIRE
ACCOUNT UNLOCK;

--Grant some SYSTEM privileges, so the developer can build and test
GRANT CREATE SESSION,
CREATE TABLE, --Enables user to create tables in his schema.
CREATE VIEW, --Enables user to create views in his schema.
CREATE PROCEDURE, --Enables user to create procedures in his schema.
DEBUG CONNECT SESSION, --Enables user to create debug session in his schema.
DEBUG ANY PROCEDURE, -- Enables user to debug procedures/functions in his schema.
CREATE TRIGGER, --Enables user to create triggers in his schema.
CREATE SEQUENCE, --Enables user to create sequences in his schema.
CREATE TYPE,
CREATE SYNONYM --Enables user to create synonyms in his schema.
TO park;