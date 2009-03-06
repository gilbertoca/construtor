
drop table gfi.inventario_unidade_medida;
drop table gfi.inventario_categoria;
create table gfi.inventario_unidade_medida (
   cd_unidade_medida varchar(4) not null,
   version integer not null,
   descricao_unidade varchar(30),
   fl_novo bit,
   primary key (cd_unidade_medida)
);
create table gfi.inventario_categoria (
   cd_categoria integer not null,
   nome_categoria varchar(80),
   descricao_categoria varchar(255),
   dt_cadastro timestamp not null,
   version integer not null,
   primary key (cd_categoria)
);
