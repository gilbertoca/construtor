--ATENÇÃO: este script deve ser executado após a excução do script usr.sql e com a sessão aberta como usuário seg_user
--SEQUENCES

CREATE SEQUENCE seg.audit_id_seq;
CREATE SEQUENCE seg.permissoes_id_seq;
CREATE SEQUENCE seg.grupo_id_seq;
CREATE SEQUENCE seg.usuario_id_seq;

--VIEWS

-- View: seg.vw_cargo
-- DROP VIEW seg.vw_cargo;
CREATE OR REPLACE VIEW seg.vw_cargo AS
 SELECT sgp_cargo.cdcargo AS id, sgp_cargo.cdquadroprofissional AS quadro_id, sgp_cargo.dccargo AS descricao, sgp_cargo.flativo, sgp_cargo.escolaridade, sgp_cargo.remuneracao, sgp_cargo.requisito
   FROM sgp.sgp_cargo
  ORDER BY sgp_cargo.dccargo;

-- View: seg.vw_municipio
-- DROP VIEW seg.vw_municipio;
CREATE OR REPLACE VIEW seg.vw_municipio AS
 SELECT log_localidade.loc_nu_sequencial AS id, log_localidade.loc_no AS descricao, log_localidade.cep, log_localidade.ufe_sg AS uf
   FROM log.log_localidade
  WHERE log_localidade.ufe_sg::text = 'TO'::text AND log_localidade.loc_in_tipo_localid::text = 'M'::text OR log_localidade.loc_no::text = 'Brasília'::text
  ORDER BY log_localidade.loc_nu_sequencial;

-- View: seg.vw_orgao
-- DROP VIEW seg.vw_orgao;
CREATE OR REPLACE VIEW seg.vw_orgao AS
 SELECT und_entidade.cdentidade AS id, und_entidade.cnpj, und_entidade.dcentidade AS descricao, und_entidade.loc_nu_sequencial AS municipio_id
   FROM und.und_entidade
  WHERE und_entidade.cdentidademembrode = 0 AND und_entidade.flativo = true
  ORDER BY und_entidade.dcentidade;

-- View: seg.vw_quadro
-- DROP VIEW seg.vw_quadro;
CREATE OR REPLACE VIEW seg.vw_quadro AS
 SELECT sgp_quadroprofissional.cdquadroprofissional AS id, sgp_quadroprofissional.dcquadroprofissional AS descricao
   FROM sgp.sgp_quadroprofissional
  ORDER BY sgp_quadroprofissional.dcquadroprofissional;

-- View: seg.vw_servidor
-- DROP VIEW seg.vw_servidor;
CREATE OR REPLACE VIEW seg.vw_servidor AS
 SELECT s.matricula, s.cdentidade AS lotacao, s.cdentidadeorgao AS orgao_id, g.cdpessoa AS pessoa_id, g.nome, g.cpf, s.cdcargo AS cargo_id, s.cdcargocomissao AS cargocomissao_id
   FROM sgp.sgp_servidor s
   JOIN ger.ger_pessoa g ON s.cdpessoa = g.cdpessoa
   WHERE s.dtexclusao is null
  ORDER BY g.nome;

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

