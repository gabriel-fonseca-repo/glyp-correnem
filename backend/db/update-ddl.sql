
    create schema controle_acesso;

    create table controle_acesso.tb_usuario (
        id_usuario bigserial not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        cpf varchar(14) not null,
        data_nascimento timestamp(6) with time zone,
        ip varchar(255),
        nome varchar(255) not null,
        perfil varchar(20) default USUARIO not null check (perfil in ('ADMIN','USUARIO')),
        qtd_tentativas integer,
        senha varchar(255) not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        primary key (id_usuario)
    );

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists unqc_cpf;

    alter table if exists controle_acesso.tb_usuario 
       add constraint unqc_cpf unique (cpf);

    alter table if exists controle_acesso.tb_usuario 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;
