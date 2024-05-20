-- @formatter:off
;;alter table if exists controle_acesso.tb_usuario add column ultimo_login timestamp(6) with time zone;
;;create table controle_acesso.tb_menu ( id_menu bigserial not null, data_alteracao timestamp(6) with time zone, data_alteracao_situacao timestamp(6) with time zone, data_inclusao timestamp(6) with time zone default current_timestamp, situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO', 'INATIVO')), codigo varchar(255) not null, name varchar(255) not null, ordenacao integer, perfil varchar(20) default 'USUARIO' check (perfil in ('ADMIN', 'USUARIO')), principal varchar(255) not null, id_usuario_alteracao bigint, id_usuario_alteracao_situacao bigint, id_usuario_inclusao bigint, id_menu_pai bigint, primary key (id_menu) );
;;alter table if exists controle_acesso.tb_menu drop constraint if exists unqc_codigo_menu;
;;alter table if exists controle_acesso.tb_menu add constraint unqc_codigo_menu unique (codigo);
;;alter table if exists controle_acesso.tb_menu add constraint fk_usuario_alteracao foreign key (id_usuario_alteracao) references controle_acesso.tb_usuario;
;;alter table if exists controle_acesso.tb_menu add constraint fk_usuario_alteracao_situacao foreign key (id_usuario_alteracao_situacao) references controle_acesso.tb_usuario;
;;alter table if exists controle_acesso.tb_menu add constraint fk_usuario_inclusao foreign key (id_usuario_inclusao) references controle_acesso.tb_usuario;
;;alter table if exists controle_acesso.tb_menu add constraint fk_menu_menu_pai foreign key (id_menu_pai) references controle_acesso.tb_menu;