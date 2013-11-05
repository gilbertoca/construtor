--Assumimos a existência do banco: "bcoproducao"

--Não podemos REMOVER um ROLE antes da REMOÇÃO do banco de dados, pois o banco de dados pertence ao ROLE(nos casos de crição de banco dados)
--Peçamos então ao administrador do banco para fazê-lo
DROP ROLE modulo;
--Desde da versão 8.1 o postresql assume USERS e GRUPS como ROLE. Todos pertecem ao cluster!
--cria o grupo do modulo modulo.
CREATE ROLE modulo
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;
--cria o usuario do modulo modulo
CREATE ROLE reca_user LOGIN
  ENCRYPTED PASSWORD 'reca_user'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;
GRANT ger TO reca_user;
GRANT "log" TO reca_user;
GRANT sgp TO reca_user;
GRANT und TO reca_user;
GRANT modulo TO reca_user;

-- DROP SCHEMA modulo;
CREATE SCHEMA modulo
  AUTHORIZATION modulo;

GRANT ALL ON SCHEMA modulo TO modulo;
GRANT ALL ON SCHEMA modulo TO "Administrador" WITH GRANT OPTION;
COMMENT ON SCHEMA modulo IS 'Schema utilizado pelo módulo Recadastramento de Servidores Civis e Militares';