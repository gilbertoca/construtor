alter table gfi.inventario_item_fornecedor drop constraint FKEE320A0DCD0D1403;
alter table gfi.inventario_item_fornecedor drop constraint FKEE320A0D2519BC31;
alter table gfi.inventario_controle_item drop constraint FKA9C8A041D445C4F7;
alter table gfi.inventario_controle_item drop constraint FKA9C8A0412519BC31;
alter table gfi.inventario_entrega drop constraint FK6AEFD1917ECE4AFE;
alter table gfi.inventario_entrega drop constraint FK6AEFD191AF1FFEFD;
alter table gfi.inventario_item drop constraint FK511878978EE702AB;
alter table gfi.inventario_item drop constraint FK51187897DD3F911B;
alter table gfi.inventario_fornecedor drop constraint FKA9CA6BE99E9E2169;
alter table gfi.inventario_fornecedor drop constraint FKA9CA6BE9A99E825F;
alter table gfi.inventario_contato drop constraint FKE94F0A26CD0D1403;
alter table gfi.inventario_controle drop constraint FK7376DE519B2209D1;
alter table gfi.inventario_controle drop constraint FK7376DE51E87520AF;
alter table gfi.inventario_controle drop constraint FK7376DE51A99E825F;
alter table gfi.inventario_controle drop constraint FK7376DE519E9E2169;
alter table gfi.inventario_entrega_item drop constraint FKB425B501CBBEB837;
alter table gfi.inventario_entrega_item drop constraint FKB425B5018D3096FC;
drop table gfi.inventario_item_fornecedor;
drop table gfi.inventario_natureza_operacao;
drop table gfi.inventario_controle_item;
drop table gfi.inventario_entrega;
drop table gfi.inventario_item;
drop table gfi.inventario_unidade_medida;
drop table gfi.inventario_fornecedor;
drop table gfi.inventario_categoria;
drop table gfi.inventario_produto;
drop table gfi.inventario_contato;
drop table gfi.inventario_controle;
drop table gfi.inventario_localizacao_secao;
drop table gfi.inventario_entrega_item;
drop sequence gfi.inventario_categoria_sequence;
drop sequence gfi.inventario_entrega_sequence;
drop sequence gfi.inventario_controle_sequence;
drop sequence gfi.inventario_fornecedor_sequence;
drop sequence gfi.inventario_item_sequence;
drop sequence gfi.inventario_produto_sequence;
create table gfi.inventario_item_fornecedor (
   cd_item integer not null,
   cd_fornecedor integer not null,
   primary key (cd_item, cd_fornecedor)
);
create table gfi.inventario_natureza_operacao (
   cd_natureza_operacao varchar(15) not null,
   version integer not null,
   nome_natureza_operacao varchar(100),
   descricao_natureza_operacao varchar(100),
   fl_baixa_estoque smallint,
   dt_cadastro timestamp not null,
   fl_efetua_lancamentos smallint,
   primary key (cd_natureza_operacao)
);
create table gfi.inventario_controle_item (
   cd_controle integer not null,
   linha_numero integer not null,
   version integer not null,
   fl_ajustar_preco_custo smallint,
   fl_entrega_realizada smallint,
   quantidade float, not null,
   desconto numeric(10,2),
   preco_unitario numeric(10,2) not null,
   cd_item integer,
   primary key (cd_controle, linha_numero)
);
create table gfi.inventario_entrega (
   cd_entrega integer not null,
   cd_venda integer,
   cd_funcionario integer,
   fl_entrega_realizada smallint,
   dt_hr_saida timestamp,
   dt_hr_chegada timestamp,
   log_numero varchar(10),
   log_nome varchar(125),
   log_complemento varchar(100),
   bai_nome varchar(72),
   cep varchar(8),
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   ufe_sg varchar(2),
   valor_frete numeric(10,2),
   primary key (cd_entrega)
);
create table gfi.inventario_item (
   cd_item integer not null,
   nome_item varchar(80) not null,
   dt_cadastro timestamp not null,
   dt_descontinuado timestamp,
   preco_venda numeric(10,2),
   adicional_preco_fixo numeric(10,2),
   aliquota_icm float,
   aliquota_ipi float,
   fl_base_tributaria_ipi smallint,
   cd_localizacao_secao varchar(255),
   cd_unidade_medida_compra varchar(4),
   cd_unidade_medida_venda varchar(4),
   observacao varchar(255),
   origem_mercadoria_nota_fiscal integer,
   percentual_desconto_maximo float,
   percentual_margem_lucro float,
   tipo_icms varchar(1),
   tipo_nota_fiscal integer,
   preco_custo numeric(10,2),
   qtd_por_unidade float,
   estoque_atual float,
   estoque_minimo float,
   nivel_reposicao float,
   fl_descontinuado smallint,
   cd_empresa integer,
   cd_produto integer,
   version integer not null,
   primary key (cd_item)
);
create table gfi.inventario_unidade_medida (
   cd_unidade_medida varchar(4) not null,
   version integer not null,
   descricao_unidade varchar(30),
   primary key (cd_unidade_medida)
);
create table gfi.inventario_fornecedor (
   cd_fornecedor integer not null,
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   rg_inscricao_estadual varchar(14),
   cpf_cnpj varchar(14),
   nome_Razao_Social varchar(100) not null,
   celular varchar(16),
   email varchar(80),
   dt_cadastro timestamp not null,
   dt_fundacao timestamp,
   observacoes varchar(255),
   telefone varchar(30),
   fax varchar(30),
   home_Page varchar(100),
   agencia varchar(10),
   banco varchar(20),
   numero_conta varchar(30),
   log_numero varchar(10),
   log_nome varchar(125),
   log_complemento varchar(100),
   bai_nome varchar(72),
   cep varchar(8),
   ufe_sg varchar(2),
   flPessoaFisica smallint,
   primary key (cd_fornecedor)
);
create table gfi.inventario_categoria (
   cd_categoria integer not null,
   nome_categoria varchar(80),
   descricao_categoria varchar(255),
   dt_cadastro timestamp not null,
   version integer not null,
   primary key (cd_categoria)
);
create table gfi.inventario_produto (
   cd_produto integer not null,
   nome_produto varchar(80) not null,
   dt_cadastro timestamp not null,
   descricao_produto varchar(255),
   cd_categoria integer,
   version integer not null,
   primary key (cd_produto)
);
create table gfi.inventario_contato (
   cd_fornecedor integer not null,
   cd_contato integer not null,
   version integer not null,
   celular varchar(16),
   email varchar(80),
   nome varchar(100),
   fax varchar(30),
   representante varchar(100),
   telefone varchar(30),
   primary key (cd_fornecedor, cd_contato)
);
create table gfi.inventario_controle (
   cd_controle integer not null,
   frete_por_conta integer,
   cd_natureza_operacao varchar(15),
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   base_icms numeric(10,2),
   base_icms_substituicao numeric(10,2),
   fl_gerar_registro_conta_pagar smallint,
   cd_fornecedor integer,
   cd_funcionario integer,
   cd_plano_pagamento integer,
   cd_transportadora integer,
   cd_venda integer,
   cpf_cnpj varchar(14),
   desconto numeric(10,2),
   dt_emissao_nota timestamp,
   dt_hr_movimento timestamp,
   log_numero varchar(10),
   log_nome varchar(125),
   log_complemento varchar(100),
   bai_nome varchar(72),
   cep varchar(8),
   ufe_sg varchar(2),
   forma_pagamento varchar(1),
   nome_razao_social varchar(100),
   nome_transporadora varchar(100),
   numero_nota varchar(20),
   rg_inscricao_estadual varchar(14),
   serie_nota varchar(10),
   tipo_movimento varchar(10),
   tipo_pagamento varchar(10),
   total_ipi numeric(10,2),
   total_nota numeric(10,2),
   total_produtos numeric(10,2),
   valor_frete numeric(10,2),
   valor_icms numeric(10,2),
   valor_icms_substituicao numeric(10,2),
   valor_iss numeric(10,2),
   despesas_acessorias numeric(10,2),
   valor_seguro numeric(10,2),
   fl_processado smallint,
   primary key (cd_controle)
);
create table gfi.inventario_localizacao_secao (
   cd_localizacao_secao varchar(10) not null,
   version integer not null,
   descricao_localizacao_secao varchar(50),
   primary key (cd_localizacao_secao)
);
create table gfi.inventario_entrega_item (
   cd_entrega integer not null,
   linha_numero integer not null,
   version integer not null,
   dt_hr_saida timestamp,
   dt_hr_chegada timestamp,
   controle_cd_controle integer,
   controle_linha_numero integer,
   quantidade float,
   fl_entrega_realizada smallint,
   primary key (cd_entrega, linha_numero)
);
alter table gfi.inventario_item_fornecedor add constraint FKEE320A0DCD0D1403 foreign key (cd_fornecedor) references gfi.inventario_fornecedor;
alter table gfi.inventario_item_fornecedor add constraint FKEE320A0D2519BC31 foreign key (cd_item) references gfi.inventario_item;
alter table gfi.inventario_controle_item add constraint FKA9C8A041D445C4F7 foreign key (cd_controle) references gfi.inventario_controle;
alter table gfi.inventario_controle_item add constraint FKA9C8A0412519BC31 foreign key (cd_item) references gfi.inventario_item;
alter table gfi.inventario_entrega add constraint FK6AEFD1917ECE4AFE foreign key (cd_venda) references construtor.venda_venda;
alter table gfi.inventario_entrega add constraint FK6AEFD191AF1FFEFD foreign key (cd_funcionario) references construtor.empresa_funcionario;
alter table gfi.inventario_item add constraint FK511878978EE702AB foreign key (cd_empresa) references construtor.empresa_empresa;
alter table gfi.inventario_item add constraint FK51187897DD3F911B foreign key (cd_produto) references gfi.inventario_produto;
alter table gfi.inventario_fornecedor add constraint FKA9CA6BE99E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table gfi.inventario_fornecedor add constraint FKA9CA6BE9A99E825F foreign key (tipo_logradouro) references log.log_tipo_logr;
alter table gfi.inventario_produto add constraint FK9E25B2F5279EA2D5 foreign key (cd_categoria) references gfi.inventario_categoria;
alter table gfi.inventario_contato add constraint FKE94F0A26CD0D1403 foreign key (cd_fornecedor) references gfi.inventario_fornecedor;
alter table gfi.inventario_controle add constraint FK7376DE519B2209D1 foreign key (cd_natureza_operacao) references gfi.inventario_natureza_operacao;
alter table gfi.inventario_controle add constraint FK7376DE51E87520AF foreign key (cd_plano_pagamento) references construtor.venda_plano_pagamento;
alter table gfi.inventario_controle add constraint FK7376DE51A99E825F foreign key (tipo_logradouro) references log.log_tipo_logr;
alter table gfi.inventario_controle add constraint FK7376DE519E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table gfi.inventario_entrega_item add constraint FKB425B501CBBEB837 foreign key (cd_entrega) references gfi.inventario_entrega;
alter table gfi.inventario_entrega_item add constraint FKB425B5018D3096FC foreign key (controle_cd_controle, controle_linha_numero) references gfi.inventario_controle_item;
create sequence gfi.inventario_categoria_sequence;
create sequence gfi.inventario_entrega_sequence;
create sequence gfi.inventario_controle_sequence;
create sequence gfi.inventario_fornecedor_sequence;
create sequence gfi.inventario_item_sequence;
create sequence gfi.inventario_produto_sequence;
