drop table gfi.geral_pessoa;
drop table gfi.geral_nacionalidade;
create table gfi.geral_nacionalidade (
   cdnacionalidade smallint not null,
   dcnacionalidade varchar(40),
   primary key (cdnacionalidade)
);

create table gfi.geral_pessoa (
	cdpessoa integer not null,
	rgnumero varchar(30),
	fotopath varchar(255),
	apelido varchar(50),
	rgorgaoexp varchar(6),
	rgemissao date,
	cpf varchar(11),
	cnnumero varchar(20),
	cnlv varchar(10),
	cnfls varchar(10),
	cncidade varchar(50),
	cnsubdistrito varchar(50),
	cnuf varchar(2),
	cdnacionalidade smallint,
	estadocivil char(1) CHECK(estadocivil IN('S','C','D','V')),--S-olteiro, C-asado, D-ivorsiado ou V-iuvo
	escolaridade varchar(20) CHECK(escolaridade IN ('Ensino Fundamental','Ensino Médio','Graduação','Mestrado','Doutorado','Pós-Doutorado')),
	sexo char(1) CHECK(sexo IN('M','F')),--Masculino ou Feminio
	nomepai varchar(100),
	nomemae varchar(100) not null,
	dtnascimento date not null,
	dtfalecimento date,
	nome varchar(100) not null,
	fldependente bit default 0,--Database domain type
	ufe_sg varchar(2),
	loc_nu_sequencial integer,
	tipologradouro varchar(72),
	log_nome varchar(125),
	log_complemento varchar(100),
	bai_nome varchar(72),
	cep varchar(12),
	email varchar(100),
	dtcadastro timestamp not null,
	version integer not null,
	primary key (cdpessoa)
);
