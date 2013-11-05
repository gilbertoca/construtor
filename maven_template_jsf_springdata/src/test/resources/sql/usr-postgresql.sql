--Assumimos a existência do banco: "bcoproducao"

--Não podemos REMOVER um ROLE antes da REMOÇÃO do banco de dados, pois o banco de dados pertence ao ROLE(nos casos de crição de banco dados)
--Peçamos então ao administrador do banco para fazê-lo
DROP ROLE reca;
--Desde da versão 8.1 o postresql assume USERS e GRUPS como ROLE. Todos pertecem ao cluster!
--cria o grupo do modulo reca.
CREATE ROLE reca
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;
--cria o usuario do modulo reca
CREATE ROLE reca_user LOGIN
  ENCRYPTED PASSWORD 'reca_user'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;
GRANT ger TO reca_user;
GRANT "log" TO reca_user;
GRANT sgp TO reca_user;
GRANT und TO reca_user;
GRANT reca TO reca_user;

-- DROP SCHEMA reca;
CREATE SCHEMA reca
  AUTHORIZATION reca;

GRANT ALL ON SCHEMA reca TO reca;
GRANT ALL ON SCHEMA reca TO "Administrador" WITH GRANT OPTION;
COMMENT ON SCHEMA reca IS 'Schema utilizado pelo módulo Recadastramento de Servidores Civis e Militares';