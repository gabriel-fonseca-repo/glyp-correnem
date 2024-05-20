
    create schema configuracao;

    create schema controle_acesso;

    create schema nfe;

    create table configuracao.tb_certificado (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_certificado bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        vencimento timestamp(6) with time zone not null,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        senha varchar(255) not null,
        arquivo bytea not null,
        primary key (id_certificado)
    );

    create table configuracao.tb_certificado_entidade (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_certificado bigint not null,
        id_certificado_entidade bigserial not null,
        id_entidade bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        primary key (id_certificado_entidade)
    );

    create table configuracao.tb_email_smtp (
        porta_smtp integer not null,
        starttls_smtp varchar(1) not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_email_smtp bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        tls_version varchar(20),
        host_smtp varchar(50) not null,
        senha_email varchar(50) not null,
        usuario_email varchar(100) not null,
        primary key (id_email_smtp)
    );

    create table configuracao.tb_entidade (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_entidade bigserial not null,
        id_instancia bigint not null,
        id_uf bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        ultimo_nsu_nfe bigint not null,
        cep varchar(9),
        nr_endereco varchar(10),
        cpf varchar(11),
        cnpj varchar(14),
        flag_cpf_cnpj varchar(14),
        nr_inscr_municipal varchar(15),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        telefone varchar(20),
        email varchar(100),
        endereco varchar(125),
        bairro varchar(200),
        complemento varchar(200),
        nome varchar(400) not null,
        razao_social varchar(400) not null,
        cidade varchar(255),
        primary key (id_entidade)
    );

    create table configuracao.tb_fornecedor (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_fornecedor bigserial not null,
        id_instancia bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        cpf varchar(11),
        cnpj varchar(14),
        flag_cpf_cnpj varchar(14),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        email varchar(100),
        razao_social varchar(400) not null,
        primary key (id_fornecedor)
    );

    create table configuracao.tb_parametro_sistema (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_parametro_sistema bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        descricao varchar(200) not null,
        valor varchar(200) not null,
        funcionalidade varchar(500),
        primary key (id_parametro_sistema)
    );

    create table configuracao.tb_uf (
        codigo_ibge varchar(5) not null,
        sigla varchar(5) not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_uf bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        primary key (id_uf)
    );

    create table controle_acesso.tb_instancia (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_instancia bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        id_usuario_responsavel bigint unique,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        primary key (id_instancia)
    );

    create table controle_acesso.tb_menu (
        ordenacao integer,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_menu bigserial not null,
        id_menu_pai bigint,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        perfil varchar(20) default 'USUARIO' check (perfil in ('ADMIN','USUARIO')),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        codigo varchar(255) not null,
        name varchar(255) not null,
        principal varchar(255) not null,
        primary key (id_menu),
        constraint unqc_codigo_menu unique (codigo)
    );

    create table controle_acesso.tb_usuario (
        qtd_tentativas integer,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        data_nascimento timestamp(6) with time zone,
        id_instancia bigint,
        id_usuario bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        cpf varchar(14) not null,
        perfil varchar(20) default 2 check (perfil in ('ADMIN','USUARIO')),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        email varchar(255) not null,
        ip varchar(255),
        login varchar(255) not null unique,
        nome varchar(255) not null,
        senha varchar(255) not null,
        primary key (id_usuario),
        constraint unqc_cpf_instancia unique (id_instancia, cpf),
        constraint unqc_email_instancia unique (id_instancia, email)
    );

    create table nfe.tb_evento_nfe (
        obtido_do_site boolean,
        codigo_evento varchar(6) not null,
        base bigint,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_evento timestamp(6) with time zone not null,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_evento_nfe bigserial not null,
        id_instancia bigint not null,
        id_nfe bigint not null,
        id_uf bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        numero_protocolo bigint,
        origem bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        chave_acesso varchar(44) not null,
        data_hora_an varchar(100),
        data_hora_local varchar(100),
        descricao_evento varchar(100) not null,
        justificativa_evento varchar(1500),
        xml bytea,
        primary key (id_evento_nfe)
    );

    create table nfe.tb_historico_manifestacao_nfe (
        status_manifestacao varchar(1),
        codigo_evento varchar(6) not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_historico_manifestacao_nfe bigserial not null,
        id_instancia bigint not null,
        id_nfe bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        descricao_evento varchar(100) not null,
        primary key (id_historico_manifestacao_nfe)
    );

    create table nfe.tb_log_nfe (
        level integer not null,
        tipo_problema_comunicacao varchar(1),
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_log_nfe bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        nfe_chave_acesso varchar(44),
        mensagem varchar(500) not null,
        xml bytea,
        primary key (id_log_nfe)
    );

    create table nfe.tb_nfe (
        cfop varchar(4),
        ind_pag_condicao_pagamento varchar(2),
        modelo_documento varchar(2) not null,
        nota_propria boolean,
        nota_propria_de_terceiros boolean,
        operacao_com_exterior boolean,
        origem char(1) not null check (origem in ('SEFAZ','EMAIL','OCR_EMAIL','OCR_UPLOAD','UPLOAD','UPLOAD_EXCEL','PREFEITURA','DIGITADA','SISTEMA_PREFEITURA','XML_NFE','SEFAZ_MANUAL','WEBSERVICE_NFEMASTER','UPLOAD_PORTAL_FORNECEDOR')),
        tipo_frete varchar(2),
        uf_destinatario varchar(2),
        uf_emitente varchar(2),
        uf_ultima_passagem varchar(2),
        ultimo_evento_manifestacao char(1) check (ultimo_evento_manifestacao in ('CIENCIA','CONFIRMACAO','DESCONHECIMENTO','NAO_REALIZADA')),
        valor_base_calculo_icms numeric(38,2),
        valor_base_calculo_icmsst numeric(38,2),
        valor_confins numeric(38,2),
        valor_frete_nota numeric(38,2),
        valor_icms numeric(38,2),
        valor_icmsst numeric(38,2),
        valor_ipi numeric(38,2),
        valor_pis numeric(38,2),
        valor_total_desconto numeric(38,2),
        valor_total_nota numeric(38,2) not null,
        valor_total_produtos numeric(38,2),
        c_mun_destinatario varchar(7),
        c_mun_emitente varchar(7),
        x_mun_destinatario varchar(7),
        x_mun_emitente varchar(7),
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_autorizacao timestamp(6) with time zone,
        data_emissao timestamp(6) with time zone not null,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        data_ultimo_evento_manifestacao timestamp(6) with time zone,
        id_entidade bigint not null,
        id_instancia bigint not null,
        id_nfe bigserial not null,
        id_uf bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        numero_nota bigint not null,
        numero_serie bigint not null,
        cpf_cnpj_destinatario varchar(14),
        cpf_cnpj_emitente varchar(14),
        ie_destinatario varchar(14),
        ie_emitente varchar(14),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        versao varchar(20),
        chave_acesso varchar(44) not null,
        nome_destinatario varchar(100),
        nome_emitente varchar(100),
        razao_social_destinatario varchar(100),
        razao_social_emitente varchar(100),
        finalidade_emissao_nfe varchar(255),
        status_nfe varchar(255) not null check (status_nfe in ('AUTORIZADA','DENEGADA','CANCELADA','NAO_VERIFICADA','VALIDA_SCHEMA','INVALIDA_SCHEMA','INVALIDA_SEFAZ','REJEICAO')),
        tipo_operacao varchar(255) not null check (tipo_operacao in ('ENTRADA','SAIDA')),
        primary key (id_nfe)
    );

    create table nfe.tb_nfe_item (
        cfop varchar(4) not null,
        ipi_devol numeric(38,2),
        orig varchar(2),
        percent_ipi_devol numeric(38,2),
        peso_bruto numeric(19,4),
        peso_liquido numeric(19,4),
        quantidade_comercial numeric(19,4) not null,
        valor_desconto numeric(38,2),
        valor_frete numeric(19,4),
        valor_icms_deson numeric(20,10),
        valor_icms_st_retido numeric(20,10),
        valor_outro numeric(38,2),
        valor_total numeric(20,10) not null,
        valor_unitario numeric(19,10) not null,
        unidade_comercial varchar(6),
        codigo_produto_anp bigint,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_nfe bigint not null,
        id_nfe_item bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        ncm varchar(8),
        numero_item bigint not null,
        numero_item_pedido bigint,
        cest varchar(14),
        ean varchar(14),
        ean_tributavel varchar(14),
        numero_pedido varchar(15),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        codigo_produto varchar(60) not null,
        descricao_produto varchar(120) not null,
        primary key (id_nfe_item)
    );

    create table nfe.tb_nfe_xml (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_nfe bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        versao varchar(20),
        xml_nfe bytea,
        primary key (id_nfe)
    );

    create table nfe.tb_servico_sefaz_nfe (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_servico_sefaz_nfe bigserial not null,
        id_uf bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        codigo_regiao varchar(10) not null,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        descricao_regiao varchar(50) not null,
        primary key (id_servico_sefaz_nfe)
    );

    create table nfe.tb_url_servico_sefaz_nfe (
        producao boolean not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_servico_sefaz_nfe bigint not null,
        id_url_servico_sefaz_nfe bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        versao varchar(10) not null,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        servico varchar(50) not null,
        url varchar(300) not null,
        primary key (id_url_servico_sefaz_nfe)
    );

    alter table if exists configuracao.tb_certificado 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint FKm4xtvy0clx5kjn7o1by212myf 
       foreign key (id_certificado) 
       references configuracao.tb_certificado;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint FK3csm1rokayk6h4ky1nxqayrpc 
       foreign key (id_entidade) 
       references configuracao.tb_entidade;

    alter table if exists configuracao.tb_email_smtp 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_email_smtp 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_email_smtp 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_entidade 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_entidade 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_entidade 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_entidade 
       add constraint FK138k76ajhyc1wbid71klvkh5s 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists configuracao.tb_entidade 
       add constraint FKq0u3g7h81o54itc9031hou4fu 
       foreign key (id_uf) 
       references configuracao.tb_uf;

    alter table if exists configuracao.tb_fornecedor 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_fornecedor 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_fornecedor 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_fornecedor 
       add constraint FK484cg6sn8jjwuwewx8hmt3f8y 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists configuracao.tb_parametro_sistema 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_parametro_sistema 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_parametro_sistema 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_uf 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_uf 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_uf 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint fk_instancia_usuario_responsavel 
       foreign key (id_usuario_responsavel) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_menu 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_menu 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_menu 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_menu 
       add constraint fk_menu_menu_pai 
       foreign key (id_menu_pai) 
       references controle_acesso.tb_menu;

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

    alter table if exists controle_acesso.tb_usuario 
       add constraint fk_usuario_instancia 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists nfe.tb_evento_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_evento_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_evento_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_evento_nfe 
       add constraint FKcbwuftkp89ryqxf899nkpe1iv 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists nfe.tb_evento_nfe 
       add constraint FK8fe1j77ylkntse1k4a3axfrbh 
       foreign key (id_nfe) 
       references nfe.tb_nfe;

    alter table if exists nfe.tb_evento_nfe 
       add constraint FKqy3eyca0cdd00jm880eb6pxa6 
       foreign key (id_uf) 
       references configuracao.tb_uf;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint FKa51tnkfxfc1qavjsc0o7qm6th 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint FKkcviuhg48023ffcj6b29v2ouk 
       foreign key (id_nfe) 
       references nfe.tb_nfe;

    alter table if exists nfe.tb_log_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_log_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_log_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe 
       add constraint FKhojscmxpq5malhrij1q0q6u7o 
       foreign key (id_entidade) 
       references configuracao.tb_entidade;

    alter table if exists nfe.tb_nfe 
       add constraint FK7syntf9rhnq7179nq6inpmpvb 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists nfe.tb_nfe 
       add constraint FK16b1iovtdtpce4vgtpircif6 
       foreign key (id_uf) 
       references configuracao.tb_uf;

    alter table if exists nfe.tb_nfe_item 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_item 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_item 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_item 
       add constraint FKh0jmmfh8j9v3961rdp1jarpfu 
       foreign key (id_nfe) 
       references nfe.tb_nfe;

    alter table if exists nfe.tb_nfe_xml 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_xml 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_xml 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       add constraint FKfrvuymqnp2bw7ka9o9qtgvrrh 
       foreign key (id_uf) 
       references configuracao.tb_uf;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       add constraint FKsbvw1jwj1hp6qkc510rqxlccq 
       foreign key (id_servico_sefaz_nfe) 
       references nfe.tb_servico_sefaz_nfe;

    create schema configuracao;

    create schema controle_acesso;

    create schema nfe;

    create table configuracao.tb_certificado (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_certificado bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        vencimento timestamp(6) with time zone not null,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        senha varchar(255) not null,
        arquivo bytea not null,
        primary key (id_certificado)
    );

    create table configuracao.tb_certificado_entidade (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_certificado bigint not null,
        id_certificado_entidade bigserial not null,
        id_entidade bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        primary key (id_certificado_entidade)
    );

    create table configuracao.tb_email_smtp (
        porta_smtp integer not null,
        starttls_smtp varchar(1) not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_email_smtp bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        tls_version varchar(20),
        host_smtp varchar(50) not null,
        senha_email varchar(50) not null,
        usuario_email varchar(100) not null,
        primary key (id_email_smtp)
    );

    create table configuracao.tb_entidade (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_entidade bigserial not null,
        id_instancia bigint not null,
        id_uf bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        ultimo_nsu_nfe bigint not null,
        cep varchar(9),
        nr_endereco varchar(10),
        cpf varchar(11),
        cnpj varchar(14),
        flag_cpf_cnpj varchar(14),
        nr_inscr_municipal varchar(15),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        telefone varchar(20),
        email varchar(100),
        endereco varchar(125),
        bairro varchar(200),
        complemento varchar(200),
        nome varchar(400) not null,
        razao_social varchar(400) not null,
        cidade varchar(255),
        primary key (id_entidade)
    );

    create table configuracao.tb_fornecedor (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_fornecedor bigserial not null,
        id_instancia bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        cpf varchar(11),
        cnpj varchar(14),
        flag_cpf_cnpj varchar(14),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        email varchar(100),
        razao_social varchar(400) not null,
        primary key (id_fornecedor)
    );

    create table configuracao.tb_parametro_sistema (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_parametro_sistema bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        descricao varchar(200) not null,
        valor varchar(200) not null,
        funcionalidade varchar(500),
        primary key (id_parametro_sistema)
    );

    create table configuracao.tb_uf (
        codigo_ibge varchar(5) not null,
        sigla varchar(5) not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_uf bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        primary key (id_uf)
    );

    create table controle_acesso.tb_instancia (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_instancia bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        id_usuario_responsavel bigint unique,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        primary key (id_instancia)
    );

    create table controle_acesso.tb_menu (
        ordenacao integer,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_menu bigserial not null,
        id_menu_pai bigint,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        perfil varchar(20) default 'USUARIO' check (perfil in ('ADMIN','USUARIO')),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        codigo varchar(255) not null,
        name varchar(255) not null,
        principal varchar(255) not null,
        primary key (id_menu),
        constraint unqc_codigo_menu unique (codigo)
    );

    create table controle_acesso.tb_usuario (
        qtd_tentativas integer,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        data_nascimento timestamp(6) with time zone,
        id_instancia bigint,
        id_usuario bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        cpf varchar(14) not null,
        perfil varchar(20) default 2 check (perfil in ('ADMIN','USUARIO')),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        email varchar(255) not null,
        ip varchar(255),
        login varchar(255) not null unique,
        nome varchar(255) not null,
        senha varchar(255) not null,
        primary key (id_usuario),
        constraint unqc_cpf_instancia unique (id_instancia, cpf),
        constraint unqc_email_instancia unique (id_instancia, email)
    );

    create table nfe.tb_evento_nfe (
        obtido_do_site boolean,
        codigo_evento varchar(6) not null,
        base bigint,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_evento timestamp(6) with time zone not null,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_evento_nfe bigserial not null,
        id_instancia bigint not null,
        id_nfe bigint not null,
        id_uf bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        numero_protocolo bigint,
        origem bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        chave_acesso varchar(44) not null,
        data_hora_an varchar(100),
        data_hora_local varchar(100),
        descricao_evento varchar(100) not null,
        justificativa_evento varchar(1500),
        xml bytea,
        primary key (id_evento_nfe)
    );

    create table nfe.tb_historico_manifestacao_nfe (
        status_manifestacao varchar(1),
        codigo_evento varchar(6) not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_historico_manifestacao_nfe bigserial not null,
        id_instancia bigint not null,
        id_nfe bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        descricao_evento varchar(100) not null,
        primary key (id_historico_manifestacao_nfe)
    );

    create table nfe.tb_log_nfe (
        level integer not null,
        tipo_problema_comunicacao varchar(1),
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_log_nfe bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        nfe_chave_acesso varchar(44),
        mensagem varchar(500) not null,
        xml bytea,
        primary key (id_log_nfe)
    );

    create table nfe.tb_nfe (
        cfop varchar(4),
        ind_pag_condicao_pagamento varchar(2),
        modelo_documento varchar(2) not null,
        nota_propria boolean,
        nota_propria_de_terceiros boolean,
        operacao_com_exterior boolean,
        origem char(1) not null check (origem in ('SEFAZ','EMAIL','OCR_EMAIL','OCR_UPLOAD','UPLOAD','UPLOAD_EXCEL','PREFEITURA','DIGITADA','SISTEMA_PREFEITURA','XML_NFE','SEFAZ_MANUAL','WEBSERVICE_NFEMASTER','UPLOAD_PORTAL_FORNECEDOR')),
        tipo_frete varchar(2),
        uf_destinatario varchar(2),
        uf_emitente varchar(2),
        uf_ultima_passagem varchar(2),
        ultimo_evento_manifestacao char(1) check (ultimo_evento_manifestacao in ('CIENCIA','CONFIRMACAO','DESCONHECIMENTO','NAO_REALIZADA')),
        valor_base_calculo_icms numeric(38,2),
        valor_base_calculo_icmsst numeric(38,2),
        valor_confins numeric(38,2),
        valor_frete_nota numeric(38,2),
        valor_icms numeric(38,2),
        valor_icmsst numeric(38,2),
        valor_ipi numeric(38,2),
        valor_pis numeric(38,2),
        valor_total_desconto numeric(38,2),
        valor_total_nota numeric(38,2) not null,
        valor_total_produtos numeric(38,2),
        c_mun_destinatario varchar(7),
        c_mun_emitente varchar(7),
        x_mun_destinatario varchar(7),
        x_mun_emitente varchar(7),
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_autorizacao timestamp(6) with time zone,
        data_emissao timestamp(6) with time zone not null,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        data_ultimo_evento_manifestacao timestamp(6) with time zone,
        id_entidade bigint not null,
        id_instancia bigint not null,
        id_nfe bigserial not null,
        id_uf bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        numero_nota bigint not null,
        numero_serie bigint not null,
        cpf_cnpj_destinatario varchar(14),
        cpf_cnpj_emitente varchar(14),
        ie_destinatario varchar(14),
        ie_emitente varchar(14),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        versao varchar(20),
        chave_acesso varchar(44) not null,
        nome_destinatario varchar(100),
        nome_emitente varchar(100),
        razao_social_destinatario varchar(100),
        razao_social_emitente varchar(100),
        finalidade_emissao_nfe varchar(255),
        status_nfe varchar(255) not null check (status_nfe in ('AUTORIZADA','DENEGADA','CANCELADA','NAO_VERIFICADA','VALIDA_SCHEMA','INVALIDA_SCHEMA','INVALIDA_SEFAZ','REJEICAO')),
        tipo_operacao varchar(255) not null check (tipo_operacao in ('ENTRADA','SAIDA')),
        primary key (id_nfe)
    );

    create table nfe.tb_nfe_item (
        cfop varchar(4) not null,
        ipi_devol numeric(38,2),
        orig varchar(2),
        percent_ipi_devol numeric(38,2),
        peso_bruto numeric(19,4),
        peso_liquido numeric(19,4),
        quantidade_comercial numeric(19,4) not null,
        valor_desconto numeric(38,2),
        valor_frete numeric(19,4),
        valor_icms_deson numeric(20,10),
        valor_icms_st_retido numeric(20,10),
        valor_outro numeric(38,2),
        valor_total numeric(20,10) not null,
        valor_unitario numeric(19,10) not null,
        unidade_comercial varchar(6),
        codigo_produto_anp bigint,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_nfe bigint not null,
        id_nfe_item bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        ncm varchar(8),
        numero_item bigint not null,
        numero_item_pedido bigint,
        cest varchar(14),
        ean varchar(14),
        ean_tributavel varchar(14),
        numero_pedido varchar(15),
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        codigo_produto varchar(60) not null,
        descricao_produto varchar(120) not null,
        primary key (id_nfe_item)
    );

    create table nfe.tb_nfe_xml (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_nfe bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        versao varchar(20),
        xml_nfe bytea,
        primary key (id_nfe)
    );

    create table nfe.tb_servico_sefaz_nfe (
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_servico_sefaz_nfe bigserial not null,
        id_uf bigint not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        codigo_regiao varchar(10) not null,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        descricao_regiao varchar(50) not null,
        primary key (id_servico_sefaz_nfe)
    );

    create table nfe.tb_url_servico_sefaz_nfe (
        producao boolean not null,
        data_alteracao timestamp(6) with time zone,
        data_alteracao_situacao timestamp(6) with time zone,
        data_inclusao timestamp(6) with time zone default current_timestamp,
        id_servico_sefaz_nfe bigint not null,
        id_url_servico_sefaz_nfe bigserial not null,
        id_usuario_alteracao bigint,
        id_usuario_alteracao_situacao bigint,
        id_usuario_inclusao bigint,
        versao varchar(10) not null,
        situacao varchar(20) default 'ATIVO' check (situacao in ('ATIVO','INATIVO')),
        servico varchar(50) not null,
        url varchar(300) not null,
        primary key (id_url_servico_sefaz_nfe)
    );

    alter table if exists configuracao.tb_certificado 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint FKm4xtvy0clx5kjn7o1by212myf 
       foreign key (id_certificado) 
       references configuracao.tb_certificado;

    alter table if exists configuracao.tb_certificado_entidade 
       add constraint FK3csm1rokayk6h4ky1nxqayrpc 
       foreign key (id_entidade) 
       references configuracao.tb_entidade;

    alter table if exists configuracao.tb_email_smtp 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_email_smtp 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_email_smtp 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_entidade 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_entidade 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_entidade 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_entidade 
       add constraint FK138k76ajhyc1wbid71klvkh5s 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists configuracao.tb_entidade 
       add constraint FKq0u3g7h81o54itc9031hou4fu 
       foreign key (id_uf) 
       references configuracao.tb_uf;

    alter table if exists configuracao.tb_fornecedor 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_fornecedor 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_fornecedor 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_fornecedor 
       add constraint FK484cg6sn8jjwuwewx8hmt3f8y 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists configuracao.tb_parametro_sistema 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_parametro_sistema 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_parametro_sistema 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_uf 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_uf 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists configuracao.tb_uf 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_instancia 
       add constraint fk_instancia_usuario_responsavel 
       foreign key (id_usuario_responsavel) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_menu 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_menu 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_menu 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists controle_acesso.tb_menu 
       add constraint fk_menu_menu_pai 
       foreign key (id_menu_pai) 
       references controle_acesso.tb_menu;

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

    alter table if exists controle_acesso.tb_usuario 
       add constraint fk_usuario_instancia 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists nfe.tb_evento_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_evento_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_evento_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_evento_nfe 
       add constraint FKcbwuftkp89ryqxf899nkpe1iv 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists nfe.tb_evento_nfe 
       add constraint FK8fe1j77ylkntse1k4a3axfrbh 
       foreign key (id_nfe) 
       references nfe.tb_nfe;

    alter table if exists nfe.tb_evento_nfe 
       add constraint FKqy3eyca0cdd00jm880eb6pxa6 
       foreign key (id_uf) 
       references configuracao.tb_uf;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint FKa51tnkfxfc1qavjsc0o7qm6th 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       add constraint FKkcviuhg48023ffcj6b29v2ouk 
       foreign key (id_nfe) 
       references nfe.tb_nfe;

    alter table if exists nfe.tb_log_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_log_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_log_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe 
       add constraint FKhojscmxpq5malhrij1q0q6u7o 
       foreign key (id_entidade) 
       references configuracao.tb_entidade;

    alter table if exists nfe.tb_nfe 
       add constraint FK7syntf9rhnq7179nq6inpmpvb 
       foreign key (id_instancia) 
       references controle_acesso.tb_instancia;

    alter table if exists nfe.tb_nfe 
       add constraint FK16b1iovtdtpce4vgtpircif6 
       foreign key (id_uf) 
       references configuracao.tb_uf;

    alter table if exists nfe.tb_nfe_item 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_item 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_item 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_item 
       add constraint FKh0jmmfh8j9v3961rdp1jarpfu 
       foreign key (id_nfe) 
       references nfe.tb_nfe;

    alter table if exists nfe.tb_nfe_xml 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_xml 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_nfe_xml 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       add constraint FKfrvuymqnp2bw7ka9o9qtgvrrh 
       foreign key (id_uf) 
       references configuracao.tb_uf;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       add constraint fk_usuario_alteracao 
       foreign key (id_usuario_alteracao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       add constraint fk_usuario_alteracao_situacao 
       foreign key (id_usuario_alteracao_situacao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       add constraint fk_usuario_inclusao 
       foreign key (id_usuario_inclusao) 
       references controle_acesso.tb_usuario;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       add constraint FKsbvw1jwj1hp6qkc510rqxlccq 
       foreign key (id_servico_sefaz_nfe) 
       references nfe.tb_servico_sefaz_nfe;

    alter table if exists configuracao.tb_certificado 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column arquivo set data type bytea;

    alter table if exists configuracao.tb_certificado 
       alter column vencimento set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column porta_smtp set data type integer;

    alter table if exists configuracao.tb_entidade 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_entidade 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_entidade 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_nascimento set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_evento set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column numero_protocolo set data type bigint;

    alter table if exists nfe.tb_evento_nfe 
       alter column xml set data type bytea;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column xml set data type bytea;

    alter table if exists nfe.tb_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_autorizacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_emissao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_ultimo_evento_manifestacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column numero_nota set data type bigint;

    alter table if exists nfe.tb_nfe 
       alter column numero_serie set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column codigo_produto_anp set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column numero_item_pedido set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column peso_bruto set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column peso_liquido set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column quantidade_comercial set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_frete set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_icms_deson set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_icms_st_retido set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_total set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_unitario set data type numeric(19,10);

    alter table if exists nfe.tb_nfe_xml 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column xml_nfe set data type bytea;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column arquivo set data type bytea;

    alter table if exists configuracao.tb_certificado 
       alter column vencimento set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column porta_smtp set data type integer;

    alter table if exists configuracao.tb_entidade 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_entidade 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_entidade 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_nascimento set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_evento set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column numero_protocolo set data type bigint;

    alter table if exists nfe.tb_evento_nfe 
       alter column xml set data type bytea;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column xml set data type bytea;

    alter table if exists nfe.tb_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_autorizacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_emissao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_ultimo_evento_manifestacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column numero_nota set data type bigint;

    alter table if exists nfe.tb_nfe 
       alter column numero_serie set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column codigo_produto_anp set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column numero_item_pedido set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column peso_bruto set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column peso_liquido set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column quantidade_comercial set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_frete set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_icms_deson set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_icms_st_retido set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_total set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_unitario set data type numeric(19,10);

    alter table if exists nfe.tb_nfe_xml 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column xml_nfe set data type bytea;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado 
       alter column arquivo set data type bytea;

    alter table if exists configuracao.tb_certificado 
       alter column vencimento set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_certificado_entidade 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_email_smtp 
       alter column porta_smtp set data type integer;

    alter table if exists configuracao.tb_entidade 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_entidade 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_entidade 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_fornecedor 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_parametro_sistema 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists configuracao.tb_uf 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_instancia 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_menu 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_nascimento set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column data_evento set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_evento_nfe 
       alter column numero_protocolo set data type bigint;

    alter table if exists nfe.tb_evento_nfe 
       alter column xml set data type bytea;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_log_nfe 
       alter column xml set data type bytea;

    alter table if exists nfe.tb_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_autorizacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_emissao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column data_ultimo_evento_manifestacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe 
       alter column numero_nota set data type bigint;

    alter table if exists nfe.tb_nfe 
       alter column numero_serie set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_item 
       alter column codigo_produto_anp set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column numero_item_pedido set data type bigint;

    alter table if exists nfe.tb_nfe_item 
       alter column peso_bruto set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column peso_liquido set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column quantidade_comercial set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_frete set data type numeric(19,4);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_icms_deson set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_icms_st_retido set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_total set data type numeric(20,10);

    alter table if exists nfe.tb_nfe_item 
       alter column valor_unitario set data type numeric(19,10);

    alter table if exists nfe.tb_nfe_xml 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_nfe_xml 
       alter column xml_nfe set data type bytea;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_alteracao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_alteracao_situacao set data type timestamp(6) with time zone;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       alter column data_inclusao set data type timestamp(6) with time zone;
