alter table construtor.venda_pedido drop constraint FK9FF4C8A522FD481C;
alter table construtor.venda_pedido drop constraint FK9FF4C8A51A1419F0;
alter table construtor.venda_pedido drop constraint FK9FF4C8A5AF1FFEFD;
alter table construtor.financeiro_conta_pagar drop constraint FK6E3F84E1B7C37C86;
alter table construtor.financeiro_conta_pagar drop constraint FK6E3F84E1E87520AF;
alter table construtor.financeiro_conta_pagar drop constraint FK6E3F84E1AF1FFEFD;
alter table construtor.financeiro_conta_pagar drop constraint FK6E3F84E1357AB8C0;
alter table construtor.financeiro_conta_pagar drop constraint FK6E3F84E1CD0D1403;
alter table construtor.user_role drop constraint FK984AB56DF02988D6;
alter table construtor.user_role drop constraint FK984AB56D14048CB4;
alter table construtor.financeiro_conta_receber_parcela drop constraint FKCB00A1ED7DC71A11;
alter table construtor.estoque_item_fornecedor drop constraint FKEE320A0DCD0D1403;
alter table construtor.estoque_item_fornecedor drop constraint FKEE320A0D2519BC31;
alter table construtor.venda_venda_item drop constraint FK4B9CA6762519BC31;
alter table construtor.venda_venda_item drop constraint FK4B9CA6767ECE4AFE;
alter table construtor.venda_linhaitem drop constraint FKA45267FD50B988E3;
alter table construtor.venda_linhaitem drop constraint FKA45267FD2519BC31;
alter table construtor.estoque_movimento_estoque_item drop constraint FKA9C8A041D445C4F7;
alter table construtor.estoque_movimento_estoque_item drop constraint FKA9C8A0412519BC31;
alter table construtor.financeiro_movimento_caixa_lancamento drop constraint FKA8ED2CBE357AB8C0;
alter table construtor.financeiro_movimento_caixa_lancamento drop constraint FKA8ED2CBEB7C37C86;
alter table construtor.financeiro_movimento_caixa_lancamento drop constraint FKA8ED2CBEAB199E33;
alter table construtor.financeiro_conta_receber drop constraint FK4CDAEDB422FD481C;
alter table construtor.financeiro_conta_receber drop constraint FK4CDAEDB4E87520AF;
alter table construtor.financeiro_conta_receber drop constraint FK4CDAEDB4AF1FFEFD;
alter table construtor.financeiro_conta_receber drop constraint FK4CDAEDB4357AB8C0;
alter table construtor.financeiro_conta_receber drop constraint FK4CDAEDB4B7C37C86;
alter table construtor.estoque_movimento_entrega drop constraint FK6AEFD1917ECE4AFE;
alter table construtor.estoque_movimento_entrega drop constraint FK6AEFD191AF1FFEFD;
alter table construtor.venda_venda drop constraint FKBB2B0AFC186FDDD8;
alter table construtor.venda_venda drop constraint FKBB2B0AFC8EE702AB;
alter table construtor.venda_venda drop constraint FKBB2B0AFC22FD481C;
alter table construtor.venda_venda drop constraint FKBB2B0AFCAF1FFEFD;
alter table construtor.venda_venda drop constraint FKBB2B0AFCE87520AF;
alter table construtor.venda_venda drop constraint FKBB2B0AFC9E8682A2;
alter table construtor.venda_venda drop constraint FKBB2B0AFC9E9E2169;
alter table construtor.venda_venda drop constraint FKBB2B0AFC1A1419F0;
alter table construtor.venda_venda drop constraint FKBB2B0AFCA99E825F;
alter table construtor.empresa_transportadora drop constraint FK47674CA5A99E825F;
alter table construtor.empresa_transportadora drop constraint FK47674CA59E9E2169;
alter table construtor.estoque_item drop constraint FK511878978EE702AB;
alter table construtor.estoque_item drop constraint FK51187897DD3F911B;
alter table log.log_bairro drop constraint FK66B82A9E9E2169;
alter table construtor.estoque_fornecedor drop constraint FKA9CA6BE99E9E2169;
alter table construtor.estoque_fornecedor drop constraint FKA9CA6BE9A99E825F;
alter table log.log_logradouro drop constraint FK3A0B8C398AF47BDD;
alter table log.log_logradouro drop constraint FK3A0B8C399E9E2169;
alter table construtor.venda_orcamento_item drop constraint FK288D896276FA7392;
alter table construtor.venda_orcamento_item drop constraint FK288D89622519BC31;
alter table construtor.estoque_produto drop constraint FK9E25B2F5279EA2D5;
alter table construtor.venda_orcamento drop constraint FKB6DB049022FD481C;
alter table construtor.financeiro_conta_pagar_parcela drop constraint FKB5E5BC1A7DC71A11;
alter table construtor.estoque_contato drop constraint FKE94F0A26CD0D1403;
alter table construtor.venda_cliente drop constraint FKBB2A009AA99E825F;
alter table construtor.venda_cliente drop constraint FKBB2A009A9E9E2169;
alter table construtor.venda_cliente drop constraint FKBB2A009AAF1FFEFD;
alter table construtor.estoque_movimento_estoque drop constraint FK7376DE519B2209D1;
alter table construtor.estoque_movimento_estoque drop constraint FK7376DE51E87520AF;
alter table construtor.estoque_movimento_estoque drop constraint FK7376DE51A99E825F;
alter table construtor.estoque_movimento_estoque drop constraint FK7376DE519E9E2169;
alter table construtor.financeiro_conta_bancaria drop constraint FKF7FE963AF1FFEFD;
alter table construtor.financeiro_conta_bancaria drop constraint FKF7FE96321134043;
alter table construtor.estoque_movimento_entrega_item drop constraint FKB425B501CBBEB837;
alter table construtor.estoque_movimento_entrega_item drop constraint FKB425B5018D3096FC;
alter table log.log_localidade drop constraint FK2A4360E1CD73BA7F;
alter table construtor.empresa_funcionario drop constraint FK7C9FD068A99E825F;
alter table construtor.empresa_funcionario drop constraint FK7C9FD0689E9E2169;
drop table construtor.venda_pedido;
drop table construtor.venda_plano_pagamento;
drop table construtor.financeiro_conta_pagar;
drop table construtor.user_cookie;
drop table construtor.financeiro_conta_bancaria_lancamento;
drop table construtor.empresa_funcionario_vale;
drop table construtor.user_role;
drop table construtor.financeiro_cheque_recebido;
drop table construtor.financeiro_conta_receber_parcela;
drop table construtor.estoque_item_fornecedor;
drop table construtor.venda_venda_item;
drop table construtor.estoque_natureza_operacao;
drop table construtor.venda_linhaitem;
drop table log.log_faixa_uf;
drop table construtor.estoque_movimento_estoque_item;
drop table construtor.financeiro_movimento_caixa_lancamento;
drop table construtor.financeiro_conta_receber;
drop table construtor.empresa_empresa;
drop table construtor.estoque_movimento_entrega;
drop table construtor.role;
drop table construtor.financeiro_centro_custo;
drop table construtor.venda_venda;
drop table construtor.empresa_transportadora;
drop table construtor.estoque_item;
drop table construtor.estoque_unidade_medida;
drop table construtor.financeiro_cheque_roubado;
drop table log.log_bairro;
drop table construtor.app_user;
drop table construtor.estoque_fornecedor;
drop table log.log_logradouro;
drop table construtor.venda_orcamento_item;
drop table construtor.estoque_categoria;
drop table construtor.estoque_produto;
drop table construtor.venda_orcamento;
drop table construtor.financeiro_conta_pagar_parcela;
drop table construtor.estoque_contato;
drop table construtor.venda_cliente;
drop table construtor.estoque_movimento_estoque;
drop table construtor.estoque_localizacao_secao;
drop table construtor.financeiro_conta_bancaria;
drop table construtor.financeiro_caixa_conta;
drop table log.log_tipo_logr;
drop table construtor.financeiro_movimento_caixa;
drop table construtor.estoque_movimento_entrega_item;
drop table construtor.financeiro_bancario_padrao;
drop table log.log_localidade;
drop table construtor.empresa_funcionario;
drop sequence construtor.empresa_empresa_sequence;
drop sequence construtor.financeiro_cheque_roubado_sequence;
drop sequence construtor.empresa_funcionario_sequence;
drop sequence construtor.estoque_categoria_sequence;
drop sequence construtor.empresa_transportadora_sequence;
drop sequence construtor.estoque_movimento_entrega_sequence;
drop sequence construtor.financeiro_bancario_padrao_sequence;
drop sequence construtor.estoque_movimento_estoque_sequence;
drop sequence construtor.financeiro_conta_receber_sequence;
drop sequence construtor.venda_plano_pagamento_sequence;
drop sequence construtor.venda_pedido_sequence;
drop sequence construtor.financeiro_centro_custo_sequence;
drop sequence construtor.financeiro_caixa_conta_sequence;
drop sequence construtor.venda_venda_sequence;
drop sequence construtor.estoque_fornecedor_sequence;
drop sequence construtor.venda_orcamento_sequence;
drop sequence construtor.financeiro_conta_pagar_sequence;
drop sequence construtor.estoque_item_sequence;
drop sequence construtor.financeiro_conta_bancaria_sequence;
drop sequence construtor.financeiro_cheque_recebido_sequence;
drop sequence construtor.venda_cliente_sequence;
drop sequence construtor.user_cookie_sequence;
drop sequence construtor.financeiro_movimento_caixa_sequence;
drop sequence construtor.estoque_produto_sequence;
create table construtor.venda_pedido (
   cd_pedido int4 not null,
   dt_pedido timestamp not null,
   dt_entrega timestamp,
   dt_envio timestamp,
   frete numeric,
   desconto_total numeric,
   preco_total numeric not null,
   nome_cliente_fatura varchar(100),
   nome_cliente_entrega varchar(100),
   fatura_numero varchar(10),
   fatura_log_nome varchar(125),
   fatura_log_complemento varchar(100),
   fatura_bai_nome varchar(72),
   fatura_cep varchar(8),
   fatura_ufe_sg varchar(2),
   fatura_loc_nu_sequencial int8,
   fatura_tipo_logradouro varchar(72),
   cd_cliente int4,
   cd_funcionario int4,
   cd_transportadora int4,
   entrega_numero varchar(10),
   entrega_log_nome varchar(125),
   entrega_log_complemento varchar(100),
   entrega_bai_nome varchar(72),
   entrega_cep varchar(8),
   entrega_ufe_sg varchar(2),
   entrega_loc_nu_sequencial int8,
   entrega_tipo_logradouro varchar(72),
   primary key (cd_pedido)
);
create table construtor.venda_plano_pagamento (
   cd_plano_pagamento int4 not null,
   descricaoPlano varchar(100) not null,
   fl_habilitado bool,
   juros_por_atraso float8,
   multa_por_atraso float8,
   numero_parcelas int4,
   taxa_juros_ano float8,
   taxa_juros_mes float8,
   tipo_entrada varchar(1),
   tipo_juros varchar(1),
   tipo_juros_por_atraso varchar(2),
   tipo_multa_por_atraso varchar(2),
   valor_entrada_fixo float8,
   valor_entrada_parcela float8,
   valor_minimo_plano float8,
   numero_dias_apos_vencimento int4,
   primary key (cd_plano_pagamento)
);
create table construtor.financeiro_conta_pagar (
   cd_conta_pagar int4 not null,
   cd_fornecedor int4,
   fl_quitada bool,
   cd_centro_custo int4,
   cd_funcionario int4,
   cd_caixa_conta int4,
   valor_entrada float8,
   total_conta float8,
   cd_plano_pagamento int4,
   documento varchar(25),
   descricao_conta_pagar varchar(50),
   dt_conta timestamp,
   portador varchar(30),
   observacoes varchar(255),
   cd_conta_bancaria int4,
   referencia int4,
   fl_conta_fixa bool,
   nro_duplicacoes int4,
   fl_previsao bool,
   fl_cancelado bool,
   cd_movimento_estoque int4,
   fl_entrada bool,
   dt_cancelamento timestamp,
   primary key (cd_conta_pagar)
);
create table construtor.user_cookie (
   id int8 not null,
   username varchar(30) not null,
   cookie_id varchar(100) not null,
   date_created timestamp not null,
   primary key (id)
);
create table construtor.financeiro_conta_bancaria_lancamento (
   cd_conta_bancaria_lancamento int4 not null,
   cd_conta_bancaria int4 not null,
   version int4 not null,
   dt_lancamento timestamp,
   tipo int2,
   fl_pre_datado bool,
   dt_bom_para timestamp,
   cedente varchar(50),
   historico varchar(50),
   numero varchar(20),
   valor float8,
   dt_compensacao timestamp,
   conta_caixa varchar(40),
   hora timestamp,
   referencia int4,
   observacoes varchar(200),
   fl_baixado bool,
   fl_cancelado bool,
   fl_auxiliarcancelamento bool,
   cd_centro_custo int4,
   primary key (cd_conta_bancaria_lancamento, cd_conta_bancaria)
);
create table construtor.empresa_funcionario_vale (
   cd_funcionario_vale int4 not null,
   cd_funcionario int4 not null,
   dt_vale timestamp,
   descricao varchar(254),
   valor float8,
   cd_conta_caixa varchar(15),
   cd_centro_custo int4,
   primary key (cd_funcionario_vale, cd_funcionario)
);
create table construtor.user_role (
   username varchar(20) not null,
   role_name varchar(20) not null,
   primary key (username, role_name)
);
create table construtor.financeiro_cheque_recebido (
   cd_cheque_recebido int4 not null,
   nome_banco varchar(30),
   agencia varchar(20),
   conta varchar(20),
   dt_entrada timestamp,
   fl_pre_datado bool,
   dt_bom_para timestamp,
   valor float8,
   cd_bancario_padrao int4,
   fl_cruzado bool,
   observacoes varchar(250),
   cd_conta_pagar int4,
   fl_compensado bool,
   fl_cancelado bool,
   cd_caixa int4,
   cd_venda int4,
   numero varchar(25),
   cd_centro_custo int4,
   fl_devolvido bool,
   dt_compensado_em timestamp,
   dt_cancelado_em timestamp,
   dt_devolvido_em timestamp,
   fl_repassado bool,
   primary key (cd_cheque_recebido)
);
create table construtor.financeiro_conta_receber_parcela (
   cd_parcela int4 not null,
   cd_conta int4 not null,
   version int4 not null,
   valor_documento float8,
   dt_vencimento timestamp,
   dt_pagamento timestamp,
   desconto float8,
   deducoes float8,
   juros float8,
   acrescimos float8,
   valor_cobrado float8,
   descricao_parcela varchar(100),
   fl_quitada bool,
   dt_hr_parcela timestamp,
   fl_cancelado bool,
   primary key (cd_parcela, cd_conta)
);
create table construtor.estoque_item_fornecedor (
   cd_item int4 not null,
   cd_fornecedor int4 not null,
   primary key (cd_item, cd_fornecedor)
);
create table construtor.venda_venda_item (
   cd_venda int4 not null,
   linha_numero int4 not null,
   version int4 not null,
   quantidade float4 not null,
   desconto float8,
   preco_unitario float8 not null,
   cd_item int4,
   primary key (cd_venda, linha_numero)
);
create table construtor.estoque_natureza_operacao (
   cd_natureza_operacao varchar(15) not null,
   version int4 not null,
   nome_natureza_operacao varchar(100),
   descricao_natureza_operacao varchar(100),
   fl_baixa_estoque bool,
   dt_cadastro date not null,
   fl_efetua_lancamentos bool,
   primary key (cd_natureza_operacao)
);
create table construtor.venda_linhaitem (
   cd_pedido int4 not null,
   linha_numero int4 not null,
   version int4 not null,
   quantidade float8 not null,
   desconto float8,
   preco_unitario numeric not null,
   cd_item int4,
   primary key (cd_pedido, linha_numero)
);
create table log.log_faixa_uf (
   ufe_sg varchar(255) not null,
   ufe_no varchar(72) not null,
   ufe_rad1_ini varchar(5),
   ufe_suf1_ini varchar(3),
   ufe_rad1_fim varchar(5),
   ufe_suf1_fim varchar(3),
   ufe_rad2_ini varchar(5),
   ufe_suf2_ini varchar(3),
   ufe_rad2_fim varchar(5),
   ufe_suf2_fim varchar(3),
   primary key (ufe_sg)
);
create table construtor.estoque_movimento_estoque_item (
   cd_movimento_estoque int4 not null,
   linha_numero int4 not null,
   version int4 not null,
   fl_ajustar_preco_custo bool,
   fl_entrega_realizada bool,
   quantidade float4 not null,
   desconto float8,
   preco_unitario float8 not null,
   cd_item int4,
   primary key (cd_movimento_estoque, linha_numero)
);
create table construtor.financeiro_movimento_caixa_lancamento (
   cd_movimento_caixa_lancamento int4 not null,
   cd_movimento_caixa int4 not null,
   version int4 not null,
   cd_caixa_conta int4 not null,
   dt_hr_lancamento timestamp not null,
   documento varchar(25),
   valor float8,
   historico varchar(255),
   fl_cancelado bool,
   cd_centro_custo int4,
   operacao varchar(1),
   primary key (cd_movimento_caixa_lancamento, cd_movimento_caixa)
);
create table construtor.financeiro_conta_receber (
   cd_conta_receber int4 not null,
   cd_cliente int4,
   fl_quitada bool,
   cd_centro_custo int4,
   cd_funcionario int4,
   cd_caixa_conta int4,
   valor_entrada float8,
   total_conta float8,
   cd_plano_pagamento int4,
   documento varchar(25),
   descricao_conta_receber varchar(50),
   dt_conta timestamp,
   portador varchar(30),
   observacoes varchar(255),
   cd_conta_bancaria int4,
   referencia int4,
   fl_conta_fixa bool,
   nro_duplicacoes int4,
   fl_previsao bool,
   fl_cancelado bool,
   cd_venda int4,
   fl_entrada bool,
   dt_cancelamento timestamp,
   primary key (cd_conta_receber)
);
create table construtor.empresa_empresa (
   cd_empresa int4 not null,
   celular varchar(16),
   email varchar(80),
   responsavel varchar(100),
   cnpj varchar(14),
   dt_cadastro date not null,
   dt_fundacao date,
   Nome_Fantasia varchar(60),
   Inscricao_Estadual varchar(20),
   Razao_Social varchar(60),
   telefone varchar(30),
   fax varchar(30),
   log_numero varchar(10),
   log_nome varchar(125),
   log_complemento varchar(100),
   bai_nome varchar(72),
   cep varchar(8),
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   ufe_sg varchar(2),
   fl_optante_simples bool,
   primary key (cd_empresa)
);
create table construtor.estoque_movimento_entrega (
   cd_movimento_entrega int4 not null,
   cd_venda int4,
   cd_funcionario int4,
   fl_entrega_realizada bool,
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
   valor_frete float8,
   primary key (cd_movimento_entrega)
);
create table construtor.role (
   name varchar(20) not null,
   version int4 not null,
   description varchar(255),
   primary key (name)
);
create table construtor.financeiro_centro_custo (
   cd_centro_custo int4 not null,
   descricao_centro_custo varchar(100),
   codigo_texto varchar(7),
   primary key (cd_centro_custo)
);
create table construtor.venda_venda (
   cd_venda int4 not null,
   cd_cliente int4,
   telefone varchar(30),
   cd_empresa int4,
   total_produtos float8,
   cd_funcionario int4,
   loc_nu_sequencial int8,
   entrega_loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   entrega_tipo_logradouro varchar(72),
   cd_plano_pagamento int4,
   cd_transportadora int4,
   log_numero varchar(10),
   log_nome varchar(125),
   log_complemento varchar(100),
   bai_nome varchar(72),
   cep varchar(8),
   ufe_sg varchar(2),
   fl_gerar_registro_conta_receber bool,
   fl_endereco_entrega bool,
   fl_gerar_recibo_entrega bool,
   entrega_log_numero varchar(10),
   entrega_log_nome varchar(125),
   entrega_log_complemento varchar(100),
   entrega_bai_nome varchar(72),
   entrega_cep varchar(8),
   entrega_ufe_sg varchar(2),
   tipo_endereco int4,
   valor_frete float8,
   rg_inscricao_estadual varchar(14),
   fl_processado bool,
   cd_caixa int4,
   cpf_cliente varchar(14),
   cupom_fiscal varchar(10),
   dt_entrega timestamp,
   dt_hr_venda timestamp,
   forma_pagamento varchar(1),
   nome_cliente varchar(60),
   tipo_pagamento varchar(10),
   total_desconto float8,
   valor_entrada float8,
   valor_interestadual_AICMS float8,
   valor_parcela float8,
   valor_recebido float8,
   valor_total float8,
   valor_total_prazo float8,
   valor_troco float8,
   fl_venda_interestadual bool,
   primary key (cd_venda)
);
create table construtor.empresa_transportadora (
   cd_transportadora int4 not null,
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   nome_contato varchar(60),
   contato_cargo varchar(40),
   dt_cadastro date not null,
   agencia varchar(10),
   banco varchar(20),
   numero_conta varchar(30),
   cpf_cnpj varchar(14),
   celular varchar(16),
   email varchar(80),
   fax varchar(30),
   nome_Razao_Social varchar(100) not null,
   telefone varchar(30) not null,
   log_numero varchar(10),
   log_nome varchar(125),
   log_complemento varchar(100),
   bai_nome varchar(72),
   cep varchar(8),
   ufe_sg varchar(2),
   fl_pessoa_fisica bool,
   primary key (cd_transportadora)
);
create table construtor.estoque_item (
   cd_item int4 not null,
   nome_item varchar(80) not null,
   dt_cadastro date not null,
   dt_descontinuado timestamp,
   preco_venda float8,
   adicional_preco_fixo float8,
   aliquota_icm float4,
   aliquota_ipi float4,
   fl_base_tributaria_ipi bool,
   cd_localizacao_secao varchar(255),
   cd_unidade_medida_compra varchar(4),
   cd_unidade_medida_venda varchar(4),
   observacao varchar(255),
   origem_mercadoria_nota_fiscal int4,
   percentual_desconto_maximo float4,
   percentual_margem_lucro float4,
   tipo_icms varchar(1),
   tipo_nota_fiscal int4,
   preco_custo float8,
   qtd_por_unidade float4,
   estoque_atual float4,
   estoque_minimo float4,
   nivel_reposicao float4,
   fl_descontinuado bool,
   cd_empresa int4,
   cd_produto int4,
   primary key (cd_item)
);
create table construtor.estoque_unidade_medida (
   cd_unidade_medida varchar(4) not null,
   version int4 not null,
   descricao_unidade varchar(30),
   primary key (cd_unidade_medida)
);
create table construtor.financeiro_cheque_roubado (
   cd_cheque_roubado int4 not null,
   cd_bancario_padrao int4,
   banco varchar(100),
   agencia varchar(8),
   conta varchar(7),
   nome varchar(50),
   cheque_inicial varchar(8),
   cheque_final varchar(8),
   dt_cheque_roubado timestamp,
   observacao varchar(254),
   primary key (cd_cheque_roubado)
);
create table log.log_bairro (
   bai_nu_sequencial int8 not null,
   loc_nu_sequencial int8,
   ufe_sg varchar(2) not null,
   bai_no varchar(72) not null,
   bai_no_abrev varchar(36),
   primary key (bai_nu_sequencial)
);
create table construtor.app_user (
   username varchar(20) not null,
   version int4 not null,
   password varchar(255) not null,
   first_name varchar(50) not null,
   last_name varchar(50) not null,
   address varchar(150),
   city varchar(50) not null,
   province varchar(100),
   country varchar(100),
   postal_code varchar(15) not null,
   email varchar(255) not null unique,
   phone_number varchar(255),
   website varchar(255),
   password_hint varchar(255),
   cd_funcionario int4,
   primary key (username)
);
create table construtor.estoque_fornecedor (
   cd_fornecedor int4 not null,
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   rg_inscricao_estadual varchar(14),
   cpf_cnpj varchar(14),
   nome_Razao_Social varchar(100) not null,
   celular varchar(16),
   email varchar(80),
   dt_cadastro date not null,
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
   flPessoaFisica bool,
   primary key (cd_fornecedor)
);
create table log.log_logradouro (
   log_nu_sequencial int8 not null,
   ufe_sg varchar(2) not null,
   bai_nu_sequencial_i int8,
   loc_nu_sequencial int8,
   log_no varchar(70) not null,
   log_nome varchar(125),
   bai_nu_sequencial_f int8,
   cep varchar(8) not null,
   log_complemento varchar(100),
   log_tipo_logradouro varchar(72) not null,
   log_status_tipo_log varchar(1) not null,
   log_no_sem_acento varchar(70) not null,
   primary key (log_nu_sequencial)
);
create table construtor.venda_orcamento_item (
   cd_orcamento int4 not null,
   linha_numero int4 not null,
   version int4 not null,
   quantidade float4 not null,
   desconto float8,
   preco_unitario float8 not null,
   cd_item int4,
   primary key (cd_orcamento, linha_numero)
);
create table construtor.estoque_categoria (
   cd_categoria int4 not null,
   nome_categoria varchar(80),
   descricao_categoria varchar(255),
   dt_cadastro date not null,
   primary key (cd_categoria)
);
create table construtor.estoque_produto (
   cd_produto int4 not null,
   nome_produto varchar(80) not null,
   dt_cadastro date not null,
   descricao_produto varchar(255),
   cd_categoria int4,
   primary key (cd_produto)
);
create table construtor.venda_orcamento (
   cd_orcamento int4 not null,
   cd_cliente int4,
   fl_processado bool,
   cd_funcionario int4,
   forma_pagamento varchar(1),
   cd_plano_pagamento int4,
   dt_orcamento timestamp,
   dt_validade_orcamento timestamp,
   tipo_pagamento varchar(10),
   status varchar(1),
   nome_cliente varchar(60),
   observacao varchar(255),
   tipo_cliente varchar(1),
   valor_desconto float8,
   valor_entrada float8,
   valor_parcela float8,
   valor_total float8,
   valor_total_prazo float8,
   valor_total_produto float8,
   primary key (cd_orcamento)
);
create table construtor.financeiro_conta_pagar_parcela (
   cd_parcela int4 not null,
   cd_conta int4 not null,
   version int4 not null,
   valor_documento float8,
   dt_vencimento timestamp,
   dt_pagamento timestamp,
   desconto float8,
   deducoes float8,
   juros float8,
   acrescimos float8,
   valor_cobrado float8,
   descricao_parcela varchar(100),
   fl_quitada bool,
   dt_hr_parcela timestamp,
   fl_cancelado bool,
   primary key (cd_parcela, cd_conta)
);
create table construtor.estoque_contato (
   cd_fornecedor int4 not null,
   cd_contato int4 not null,
   version int4 not null,
   celular varchar(16),
   email varchar(80),
   nome varchar(100),
   fax varchar(30),
   representante varchar(100),
   telefone varchar(30),
   primary key (cd_fornecedor, cd_contato)
);
create table construtor.venda_cliente (
   cd_cliente int4 not null,
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   cd_funcionario int4,
   nome_referencia_pessoal varchar(60),
   telefone_referencia_pessoal varchar(30),
   celular varchar(16),
   cep_filiacao varchar(8),
   contato_cargo varchar(40),
   dt_admissao_emprego_atual timestamp,
   dt_nascimento timestamp,
   emprego_telefone varchar(30),
   estado_civil varchar(20),
   fl_credito_bloqueado bool,
   limite_credito float8,
   logradouro_filiacao varchar(100),
   naturalidade varchar(100),
   nome_contato varchar(60),
   nome_emprego varchar(100),
   nome_mae varchar(60),
   nome_pai varchar(60),
   profissao varchar(40),
   renda_mensal float8,
   rg_inscricao_estadual varchar(14),
   cpf_cnpj varchar(14),
   dt_cadastro date not null,
   sexo varchar(1),
   email varchar(80) not null,
   nome_Razao_Social varchar(100) not null,
   telefone varchar(30),
   fax varchar(30),
   fl_pessoa_fisica bool,
   log_numero varchar(10),
   log_nome varchar(125),
   log_complemento varchar(100),
   bai_nome varchar(72),
   cep varchar(8),
   ufe_sg varchar(2),
   primary key (cd_cliente)
);
create table construtor.estoque_movimento_estoque (
   cd_movimento_estoque int4 not null,
   frete_por_conta int4,
   cd_natureza_operacao varchar(15),
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   base_icms float8,
   base_icms_substituicao float8,
   fl_gerar_registro_conta_pagar bool,
   cd_fornecedor int4,
   cd_funcionario int4,
   cd_plano_pagamento int4,
   cd_transportadora int4,
   cd_venda int4,
   cpf_cnpj varchar(14),
   desconto float8,
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
   total_ipi float8,
   total_nota float8,
   total_produtos float8,
   valor_frete float8,
   valor_icms float8,
   valor_icms_substituicao float8,
   valor_iss float8,
   despesas_acessorias float8,
   valor_seguro float8,
   fl_processado bool,
   primary key (cd_movimento_estoque)
);
create table construtor.estoque_localizacao_secao (
   cd_localizacao_secao varchar(10) not null,
   version int4 not null,
   descricao_localizacao_secao varchar(50),
   primary key (cd_localizacao_secao)
);
create table construtor.financeiro_conta_bancaria (
   cd_conta_bancaria int4 not null,
   cd_funcionario int4,
   cd_bancario_padrao int4,
   descricao_conta_bancaria varchar(50),
   dt_abertura timestamp,
   agencia varchar(20),
   conta varchar(20),
   fone varchar(50),
   fax varchar(50),
   gerente varchar(50),
   limite float8,
   dt_vencimento timestamp,
   proximo_numero int4,
   saldo_atual float8,
   e_mail varchar(100),
   home_page varchar(100),
   boleto_numero_atual int4,
   boleto_usar_numero_documento bool,
   boleto_identificacao varchar(50),
   boleto_instrucoes varchar(1000),
   boleto_convenio varchar(6),
   boleto_especie varchar(10),
   boleto_aceite varchar(5),
   boleto_carteira varchar(15),
   boleto_operacao_codigo_cedente varchar(10),
   boleto_codigo_cedente varchar(15),
   boleto_bb_digitos int4,
   cd_empresa int4,
   primary key (cd_conta_bancaria)
);
create table construtor.financeiro_caixa_conta (
   cd_caixa_conta int4 not null,
   nome_caixa_conta varchar(80),
   marcador varchar(30),
   tipo_conta varchar(2),
   fl_permite_lancamentos bool,
   primary key (cd_caixa_conta)
);
create table log.log_tipo_logr (
   tipologradouro varchar(72) not null,
   primary key (tipologradouro)
);
create table construtor.financeiro_movimento_caixa (
   cd_movimento_caixa int4 not null,
   fl_processado bool,
   dt_hr_movimento timestamp,
   dt_hr_abertura timestamp,
   fl_fechado bool,
   entradas float8,
   saidas float8,
   saldo_anterior float8,
   saldo float8,
   cd_funcionario int4,
   cd_caixa int4,
   primary key (cd_movimento_caixa)
);
create table construtor.estoque_movimento_entrega_item (
   cd_movimento_entrega int4 not null,
   linha_numero int4 not null,
   version int4 not null,
   dt_hr_saida timestamp,
   dt_hr_chegada timestamp,
   movimento_estoque_cd_movimento_estoque int4,
   movimento_estoque_linha_numero int4,
   quantidade float4,
   fl_entrega_realizada bool,
   primary key (cd_movimento_entrega, linha_numero)
);
create table construtor.financeiro_bancario_padrao (
   cd_bancario_padrao int4 not null,
   descricao_bancario_padrao varchar(40),
   digito int4,
   primary key (cd_bancario_padrao)
);
create table log.log_localidade (
   loc_nu_sequencial int8 not null,
   ufe_sg varchar(255),
   loc_nosub varchar(50) not null,
   loc_no varchar(60),
   cep varchar(8),
   loc_in_situacao int8 not null,
   loc_in_tipo_localid varchar(1),
   loc_nu_sequencial_s int8,
   primary key (loc_nu_sequencial)
);
create table construtor.empresa_funcionario (
   cd_funcionario int4 not null,
   loc_nu_sequencial int8,
   tipo_logradouro varchar(72),
   nome_mae varchar(60),
   nome_pai varchar(60),
   salario float8,
   estado_civil varchar(20),
   naturalidade varchar(100),
   sexo varchar(1),
   rg varchar(14),
   email varchar(80) not null,
   celular varchar(16),
   comissao_sob_venda float8,
   nome varchar(100) not null,
   apelido varchar(10) not null,
   fl_gerar_conta_acesso bool,
   cargo varchar(30),
   cpf varchar(14),
   dt_nascimento timestamp,
   dt_contratacao timestamp,
   telefone varchar(30),
   observacoes varchar(255),
   log_numero varchar(10),
   log_nome varchar(125),
   log_complemento varchar(100),
   bai_nome varchar(72),
   cep varchar(8),
   ufe_sg varchar(2),
   supervisor int4,
   primary key (cd_funcionario)
);
alter table construtor.venda_pedido add constraint FK9FF4C8A522FD481C foreign key (cd_cliente) references construtor.venda_cliente;
alter table construtor.venda_pedido add constraint FK9FF4C8A51A1419F0 foreign key (cd_transportadora) references construtor.empresa_transportadora;
alter table construtor.venda_pedido add constraint FK9FF4C8A5AF1FFEFD foreign key (cd_funcionario) references construtor.empresa_funcionario;
alter table construtor.financeiro_conta_pagar add constraint FK6E3F84E1B7C37C86 foreign key (cd_caixa_conta) references construtor.financeiro_caixa_conta;
alter table construtor.financeiro_conta_pagar add constraint FK6E3F84E1E87520AF foreign key (cd_plano_pagamento) references construtor.venda_plano_pagamento;
alter table construtor.financeiro_conta_pagar add constraint FK6E3F84E1AF1FFEFD foreign key (cd_funcionario) references construtor.empresa_funcionario;
alter table construtor.financeiro_conta_pagar add constraint FK6E3F84E1357AB8C0 foreign key (cd_centro_custo) references construtor.financeiro_centro_custo;
alter table construtor.financeiro_conta_pagar add constraint FK6E3F84E1CD0D1403 foreign key (cd_fornecedor) references construtor.estoque_fornecedor;
create index user_cookie_username_cookie_id on construtor.user_cookie (username, cookie_id);
alter table construtor.user_role add constraint FK984AB56DF02988D6 foreign key (username) references construtor.app_user;
alter table construtor.user_role add constraint FK984AB56D14048CB4 foreign key (role_name) references construtor.role;
alter table construtor.financeiro_conta_receber_parcela add constraint FKCB00A1ED7DC71A11 foreign key (cd_conta) references construtor.financeiro_conta_receber;
alter table construtor.estoque_item_fornecedor add constraint FKEE320A0DCD0D1403 foreign key (cd_fornecedor) references construtor.estoque_fornecedor;
alter table construtor.estoque_item_fornecedor add constraint FKEE320A0D2519BC31 foreign key (cd_item) references construtor.estoque_item;
alter table construtor.venda_venda_item add constraint FK4B9CA6762519BC31 foreign key (cd_item) references construtor.estoque_item;
alter table construtor.venda_venda_item add constraint FK4B9CA6767ECE4AFE foreign key (cd_venda) references construtor.venda_venda;
alter table construtor.venda_linhaitem add constraint FKA45267FD50B988E3 foreign key (cd_pedido) references construtor.venda_pedido;
alter table construtor.venda_linhaitem add constraint FKA45267FD2519BC31 foreign key (cd_item) references construtor.estoque_item;
alter table construtor.estoque_movimento_estoque_item add constraint FKA9C8A041D445C4F7 foreign key (cd_movimento_estoque) references construtor.estoque_movimento_estoque;
alter table construtor.estoque_movimento_estoque_item add constraint FKA9C8A0412519BC31 foreign key (cd_item) references construtor.estoque_item;
alter table construtor.financeiro_movimento_caixa_lancamento add constraint FKA8ED2CBE357AB8C0 foreign key (cd_centro_custo) references construtor.financeiro_centro_custo;
alter table construtor.financeiro_movimento_caixa_lancamento add constraint FKA8ED2CBEB7C37C86 foreign key (cd_caixa_conta) references construtor.financeiro_caixa_conta;
alter table construtor.financeiro_movimento_caixa_lancamento add constraint FKA8ED2CBEAB199E33 foreign key (cd_movimento_caixa) references construtor.financeiro_movimento_caixa;
alter table construtor.financeiro_conta_receber add constraint FK4CDAEDB422FD481C foreign key (cd_cliente) references construtor.venda_cliente;
alter table construtor.financeiro_conta_receber add constraint FK4CDAEDB4E87520AF foreign key (cd_plano_pagamento) references construtor.venda_plano_pagamento;
alter table construtor.financeiro_conta_receber add constraint FK4CDAEDB4AF1FFEFD foreign key (cd_funcionario) references construtor.empresa_funcionario;
alter table construtor.financeiro_conta_receber add constraint FK4CDAEDB4357AB8C0 foreign key (cd_centro_custo) references construtor.financeiro_centro_custo;
alter table construtor.financeiro_conta_receber add constraint FK4CDAEDB4B7C37C86 foreign key (cd_caixa_conta) references construtor.financeiro_caixa_conta;
alter table construtor.estoque_movimento_entrega add constraint FK6AEFD1917ECE4AFE foreign key (cd_venda) references construtor.venda_venda;
alter table construtor.estoque_movimento_entrega add constraint FK6AEFD191AF1FFEFD foreign key (cd_funcionario) references construtor.empresa_funcionario;
alter table construtor.venda_venda add constraint FKBB2B0AFC186FDDD8 foreign key (entrega_tipo_logradouro) references log.log_tipo_logr;
alter table construtor.venda_venda add constraint FKBB2B0AFC8EE702AB foreign key (cd_empresa) references construtor.empresa_empresa;
alter table construtor.venda_venda add constraint FKBB2B0AFC22FD481C foreign key (cd_cliente) references construtor.venda_cliente;
alter table construtor.venda_venda add constraint FKBB2B0AFCAF1FFEFD foreign key (cd_funcionario) references construtor.empresa_funcionario;
alter table construtor.venda_venda add constraint FKBB2B0AFCE87520AF foreign key (cd_plano_pagamento) references construtor.venda_plano_pagamento;
alter table construtor.venda_venda add constraint FKBB2B0AFC9E8682A2 foreign key (entrega_loc_nu_sequencial) references log.log_localidade;
alter table construtor.venda_venda add constraint FKBB2B0AFC9E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table construtor.venda_venda add constraint FKBB2B0AFC1A1419F0 foreign key (cd_transportadora) references construtor.empresa_transportadora;
alter table construtor.venda_venda add constraint FKBB2B0AFCA99E825F foreign key (tipo_logradouro) references log.log_tipo_logr;
alter table construtor.empresa_transportadora add constraint FK47674CA5A99E825F foreign key (tipo_logradouro) references log.log_tipo_logr;
alter table construtor.empresa_transportadora add constraint FK47674CA59E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table construtor.estoque_item add constraint FK511878978EE702AB foreign key (cd_empresa) references construtor.empresa_empresa;
alter table construtor.estoque_item add constraint FK51187897DD3F911B foreign key (cd_produto) references construtor.estoque_produto;
alter table log.log_bairro add constraint FK66B82A9E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table construtor.estoque_fornecedor add constraint FKA9CA6BE99E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table construtor.estoque_fornecedor add constraint FKA9CA6BE9A99E825F foreign key (tipo_logradouro) references log.log_tipo_logr;
alter table log.log_logradouro add constraint FK3A0B8C398AF47BDD foreign key (bai_nu_sequencial_i) references log.log_bairro;
alter table log.log_logradouro add constraint FK3A0B8C399E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table construtor.venda_orcamento_item add constraint FK288D896276FA7392 foreign key (cd_orcamento) references construtor.venda_orcamento;
alter table construtor.venda_orcamento_item add constraint FK288D89622519BC31 foreign key (cd_item) references construtor.estoque_item;
alter table construtor.estoque_produto add constraint FK9E25B2F5279EA2D5 foreign key (cd_categoria) references construtor.estoque_categoria;
alter table construtor.venda_orcamento add constraint FKB6DB049022FD481C foreign key (cd_cliente) references construtor.venda_cliente;
alter table construtor.financeiro_conta_pagar_parcela add constraint FKB5E5BC1A7DC71A11 foreign key (cd_conta) references construtor.financeiro_conta_pagar;
alter table construtor.estoque_contato add constraint FKE94F0A26CD0D1403 foreign key (cd_fornecedor) references construtor.estoque_fornecedor;
alter table construtor.venda_cliente add constraint FKBB2A009AA99E825F foreign key (tipo_logradouro) references log.log_tipo_logr;
alter table construtor.venda_cliente add constraint FKBB2A009A9E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table construtor.venda_cliente add constraint FKBB2A009AAF1FFEFD foreign key (cd_funcionario) references construtor.empresa_funcionario;
alter table construtor.estoque_movimento_estoque add constraint FK7376DE519B2209D1 foreign key (cd_natureza_operacao) references construtor.estoque_natureza_operacao;
alter table construtor.estoque_movimento_estoque add constraint FK7376DE51E87520AF foreign key (cd_plano_pagamento) references construtor.venda_plano_pagamento;
alter table construtor.estoque_movimento_estoque add constraint FK7376DE51A99E825F foreign key (tipo_logradouro) references log.log_tipo_logr;
alter table construtor.estoque_movimento_estoque add constraint FK7376DE519E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
alter table construtor.financeiro_conta_bancaria add constraint FKF7FE963AF1FFEFD foreign key (cd_funcionario) references construtor.empresa_funcionario;
alter table construtor.financeiro_conta_bancaria add constraint FKF7FE96321134043 foreign key (cd_bancario_padrao) references construtor.financeiro_bancario_padrao;
alter table construtor.estoque_movimento_entrega_item add constraint FKB425B501CBBEB837 foreign key (cd_movimento_entrega) references construtor.estoque_movimento_entrega;
alter table construtor.estoque_movimento_entrega_item add constraint FKB425B5018D3096FC foreign key (movimento_estoque_cd_movimento_estoque, movimento_estoque_linha_numero) references construtor.estoque_movimento_estoque_item;
alter table log.log_localidade add constraint FK2A4360E1CD73BA7F foreign key (ufe_sg) references log.log_faixa_uf;
alter table construtor.empresa_funcionario add constraint FK7C9FD068A99E825F foreign key (tipo_logradouro) references log.log_tipo_logr;
alter table construtor.empresa_funcionario add constraint FK7C9FD0689E9E2169 foreign key (loc_nu_sequencial) references log.log_localidade;
create sequence construtor.empresa_empresa_sequence;
create sequence construtor.financeiro_cheque_roubado_sequence;
create sequence construtor.empresa_funcionario_sequence;
create sequence construtor.estoque_categoria_sequence;
create sequence construtor.empresa_transportadora_sequence;
create sequence construtor.estoque_movimento_entrega_sequence;
create sequence construtor.financeiro_bancario_padrao_sequence;
create sequence construtor.estoque_movimento_estoque_sequence;
create sequence construtor.financeiro_conta_receber_sequence;
create sequence construtor.venda_plano_pagamento_sequence;
create sequence construtor.venda_pedido_sequence;
create sequence construtor.financeiro_centro_custo_sequence;
create sequence construtor.financeiro_caixa_conta_sequence;
create sequence construtor.venda_venda_sequence;
create sequence construtor.estoque_fornecedor_sequence;
create sequence construtor.venda_orcamento_sequence;
create sequence construtor.financeiro_conta_pagar_sequence;
create sequence construtor.estoque_item_sequence;
create sequence construtor.financeiro_conta_bancaria_sequence;
create sequence construtor.financeiro_cheque_recebido_sequence;
create sequence construtor.venda_cliente_sequence;
create sequence construtor.user_cookie_sequence;
create sequence construtor.financeiro_movimento_caixa_sequence;
create sequence construtor.estoque_produto_sequence;
