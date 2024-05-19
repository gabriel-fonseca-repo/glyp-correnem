
	DROP SCHEMA IF EXISTS controle_acesso CASCADE;
	DROP SCHEMA IF EXISTS manager CASCADE;
	
	CREATE SCHEMA IF NOT EXISTS controle_acesso;
	CREATE SCHEMA IF NOT EXISTS manager;

    create table controle_acesso.tb_instancia (
        situacao varchar(2) default '1',
        data_alteracao timestamp(6),
        data_alteracao_situacao timestamp(6),
        data_inclusao timestamp default current_timestamp,
        id_instancia bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        id_usuario_responsavel bigint not null unique,
        cpf varchar(14) not null,
        primary key (id_instancia)
    );

    create table controle_acesso.tb_usuario (
        situacao varchar(2) default '1',
        data_alteracao timestamp(6),
        data_alteracao_situacao timestamp(6),
        data_inclusao timestamp default current_timestamp,
        id_usuario bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        primary key (id_usuario)
    );

    create table controle_acesso.tb_usuario_instancia (
        situacao varchar(2) default '1',
        data_alteracao timestamp(6),
        data_alteracao_situacao timestamp(6),
        data_inclusao timestamp default current_timestamp,
        id_instancia bigint not null,
        id_usuario bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        idusuarioinstancia bigint not null,
        email_empresa varchar(255),
        primary key (id_instancia, id_usuario, idusuarioinstancia)
    );

    alter table if exists controle_acesso.tb_instancia 
       add constraint FK7ojygrvrv8tronx4lc6utkw8q 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint FKirvxhagbdg2jqsdwvvi6n4vhh 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint FKhdeci4j3obarfio47wxmlgp8h 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint FKiu2cjes8oswv6qsgli91ogryp 
       foreign key (id_usuario_responsavel) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario 
       add constraint FKf9pgmhj51d5junj8lwk4bxnvc 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario 
       add constraint FK9fbo59b9ywu59tkmjyo8up4i7 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario 
       add constraint FKqp7pm6h3vnwftd09h26d7k3sf 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario_instancia 
       add constraint FKprkpemrwa7dh3swd1hon9dus3 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario_instancia 
       add constraint FKjwgnc1fymtsuyeotras1wf4c7 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario_instancia 
       add constraint FK9nps2i044s7qrg6x864lfoymt 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_usuario_instancia 
       add constraint FKrw38etswpgg022i30gkif3280 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists controle_acesso.tb_usuario_instancia 
       add constraint FKpgwv3fach78wfkokcvxetswb2 
       foreign key (id_usuario) 
       references controle_acesso.tb_usuario;
