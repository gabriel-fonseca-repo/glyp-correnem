
    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao set data type timestamp(6);

    alter table if exists controle_acesso.tb_instancia 
       alter column data_alteracao_situacao set data type timestamp(6);

    alter table if exists controle_acesso.tb_instancia 
       alter column data_inclusao set data type timestamp;
       
    alter table if exists controle_acesso.tb_instancia 
       alter column data_inclusao set default current_timestamp;

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario 
       alter column data_alteracao_situacao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario 
       alter column data_inclusao set data type timestamp;

	alter table if exists controle_acesso.tb_usuario 
       alter column data_inclusao set default current_timestamp;

    alter table if exists controle_acesso.tb_usuario_instancia 
       alter column data_alteracao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario_instancia 
       alter column data_alteracao_situacao set data type timestamp(6);

    alter table if exists controle_acesso.tb_usuario_instancia 
       alter column data_inclusao set data type timestamp;

    alter table if exists controle_acesso.tb_usuario_instancia 
       alter column data_inclusao set default current_timestamp;
