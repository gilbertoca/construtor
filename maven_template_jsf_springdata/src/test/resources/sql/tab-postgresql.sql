--ATENÇÃO: este script deve ser executado após a excução do script usr.sql e com a sessão aberta como usuário seg_user
--SEQUENCES

CREATE SEQUENCE seg.audit_id_seq;
CREATE SEQUENCE seg.permissoes_id_seq;
CREATE SEQUENCE seg.grupo_id_seq;
CREATE SEQUENCE seg.usuario_id_seq;

--VIEWS


--TABLES

-- Table: seg.estado_civil
CREATE TABLE seg.estado_civil
(
  id INTEGER NOT NULL,
  descricao character varying(60) NOT NULL,
  CONSTRAINT estado_civil_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE seg.estado_civil IS 'Estado civil do servidor';

-- Table: seg.audit
CREATE TABLE seg.audit  (
  ID  INTEGER NOT NULL DEFAULT nextval('seg.audit_id_seq'),
  User_ID varchar(14) NOT NULL,
  Event_Type varchar(10) NOT NULL,
  Table_Name varchar(50) NOT NULL,
  Record_ID varchar(50) NOT NULL,
  Event_Date timestamp with time zone NOT NULL,
  Description varchar(4000) NOT NULL,
  CONSTRAINT audit_pk PRIMARY KEY (id)
);

-- Table: seg.permissoes
CREATE TABLE seg.permissoes (
                id INTEGER NOT NULL DEFAULT nextval('seg.permissoes_id_seq'),
                token VARCHAR(100),
                url VARCHAR(255),
                CONSTRAINT permissoes_id PRIMARY KEY (id)
);
COMMENT ON COLUMN seg.permissoes.token IS 'Identificador para o objeto (página, componente...)';

-- Table: seg.grupo
CREATE TABLE seg.grupo (
                id INTEGER NOT NULL DEFAULT nextval('seg.grupo_id_seq'),
                descricao VARCHAR(100),
                CONSTRAINT grupo_id PRIMARY KEY (id)
);
COMMENT ON TABLE seg.grupo IS 'Grupo para os usuários';

-- Table: seg.usuario
CREATE TABLE seg.usuario (
                id INTEGER NOT NULL DEFAULT nextval('seg.usuario_id_seq'),
                login VARCHAR(100) NOT NULL,
                nome VARCHAR(100) NOT NULL,
                email VARCHAR(255) NOT NULL,
                senha VARCHAR(50) NOT NULL,
                dt_criacao TIMESTAMP DEFAULT now() NOT NULL,
                dt_ultimo_acesso TIMESTAMP,
                cpf VARCHAR(11) NOT NULL,
                CONSTRAINT usuario_id PRIMARY KEY (id)
);

-- Table: seg.usuario_grupo
CREATE TABLE seg.usuario_grupo
(
  usuario_id integer NOT NULL,
  grupo_id integer NOT NULL,
  CONSTRAINT usuario_grupo_pk PRIMARY KEY (usuario_id, grupo_id)
);

-- Table: seg.grupo_permissoes
CREATE TABLE seg.grupo_permissoes (
                grupo_id INTEGER NOT NULL,
                permissoes_id INTEGER NOT NULL,
                CONSTRAINT grupo_permissoes_pk PRIMARY KEY (grupo_id, permissoes_id)
);


--CONSTRAINTS
-- Foreign Key: seg.usuario_grupo_grupo_fk
-- ALTER TABLE seg.usuario_grupo DROP CONSTRAINT usuario_grupo_grupo_fk;
ALTER TABLE seg.usuario_grupo
  ADD CONSTRAINT usuario_grupo_grupo_fk FOREIGN KEY (grupo_id)
      REFERENCES seg.grupo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Foreign Key: seg.usuario_grupo_usuario_fk
-- ALTER TABLE seg.usuario_grupo DROP CONSTRAINT usuario_grupo_usuario_fk;
ALTER TABLE seg.usuario_grupo
  ADD CONSTRAINT usuario_grupo_usuario_fk FOREIGN KEY (usuario_id)
      REFERENCES seg.usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Constraint: seg.usuario_cpf_uk
-- ALTER TABLE seg.usuario DROP CONSTRAINT usuario_cpf_uk;
ALTER TABLE seg.usuario
  ADD CONSTRAINT usuario_cpf_uk UNIQUE(cpf);

-- Foreign Key: seg.grupo_permissao_grupo
-- ALTER TABLE seg.grupo_permissoes DROP CONSTRAINT grupo_permissao_grupo;
ALTER TABLE seg.grupo_permissoes
  ADD CONSTRAINT grupo_permissao_grupo_fk FOREIGN KEY (grupo_id)
      REFERENCES seg.grupo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Foreign Key: seg.grupo_permissoes_permissoes_fk
-- ALTER TABLE seg.grupo_permissoes DROP CONSTRAINT grupo_permissoes_permissoes_fk;
ALTER TABLE seg.grupo_permissoes
  ADD CONSTRAINT grupo_permissoes_permissoes_fk FOREIGN KEY (permissoes_id)
      REFERENCES seg.permissoes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

