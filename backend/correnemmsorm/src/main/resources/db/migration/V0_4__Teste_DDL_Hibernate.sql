
    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao set data type timestamp(6);

    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao_situacao set data type timestamp(6);

    alter table if exists controle_acesso.tb_instancia 
       alter column data_inclusao set data type timestamp(6);

    create table controle_acesso.tb_instancia_teste (
        id_instancia bigserial not null,
        data_alteracao timestamp(6),
        data_alteracao_situacao timestamp(6),
        data_inclusao timestamp(6) default current_timestamp,
        situacao varchar(2) default current_timestamp,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        primary key (id_instancia)
    );

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao_situacao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario 
       alter column data_inclusao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario 
       add column cpf varchar(14) not null;

    alter table if exists controle_acesso.tb_usuario_instancia 
       alter column data_alteracao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario_instancia 
       alter column data_alteracao_situacao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario_instancia 
       alter column data_inclusao set data type timestamp(6);

    alter table if exists controle_acesso.tb_instancia_teste 
       add constraint FK7ql3tdys0mhldn4wg2fdmlsxj 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia_teste 
       add constraint FKfesnrny4qg3ay6yo8eg2awawv 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia_teste 
       add constraint FKpqt03s859cpsnin2fjbu74ooe 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;
