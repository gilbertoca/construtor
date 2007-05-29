--create schema
CREATE SCHEMA financeiro
AUTHORIZATION gilberto;
GRANT ALL ON SCHEMA financeiro TO gilberto WITH GRANT OPTION;
COMMENT ON SCHEMA financeiro IS 'Schema utilizado pelo m�dulo de finan�as';

--create users and roles
--cria o usuario do modulo financeiro.
CREATE USER financeiro_user WITH PASSWORD 'financeiro_user' NOCREATEDB NOCREATEUSER;

--cria o grupo do modulo financeiro.
CREATE GROUP financeiro WITH USER financeiro_user;
