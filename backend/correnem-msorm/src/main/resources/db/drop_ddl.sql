
    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists FKm4xtvy0clx5kjn7o1by212myf;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists FK3csm1rokayk6h4ky1nxqayrpc;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists FK138k76ajhyc1wbid71klvkh5s;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists FKq0u3g7h81o54itc9031hou4fu;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists FK484cg6sn8jjwuwewx8hmt3f8y;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_inclusao;

    drop table if exists configuracao.tb_certificado cascade;

    drop table if exists configuracao.tb_certificado_entidade cascade;

    drop table if exists configuracao.tb_email_smtp cascade;

    drop table if exists configuracao.tb_entidade cascade;

    drop table if exists configuracao.tb_fornecedor cascade;

    drop table if exists configuracao.tb_parametro_sistema cascade;

    drop table if exists configuracao.tb_uf cascade;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_instancia_usuario_responsavel;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_menu_menu_pai;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_instancia;

    drop table if exists controle_acesso.tb_instancia cascade;

    drop table if exists controle_acesso.tb_menu cascade;

    drop table if exists controle_acesso.tb_usuario cascade;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FKcbwuftkp89ryqxf899nkpe1iv;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FK8fe1j77ylkntse1k4a3axfrbh;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FKqy3eyca0cdd00jm880eb6pxa6;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists FKa51tnkfxfc1qavjsc0o7qm6th;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists FKkcviuhg48023ffcj6b29v2ouk;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FKhojscmxpq5malhrij1q0q6u7o;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FK7syntf9rhnq7179nq6inpmpvb;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FK16b1iovtdtpce4vgtpircif6;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists FKh0jmmfh8j9v3961rdp1jarpfu;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists FKfrvuymqnp2bw7ka9o9qtgvrrh;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists FKsbvw1jwj1hp6qkc510rqxlccq;

    drop table if exists nfe.tb_evento_nfe cascade;

    drop table if exists nfe.tb_historico_manifestacao_nfe cascade;

    drop table if exists nfe.tb_log_nfe cascade;

    drop table if exists nfe.tb_nfe cascade;

    drop table if exists nfe.tb_nfe_item cascade;

    drop table if exists nfe.tb_nfe_xml cascade;

    drop table if exists nfe.tb_servico_sefaz_nfe cascade;

    drop table if exists nfe.tb_url_servico_sefaz_nfe cascade;

    drop schema configuracao;

    drop schema controle_acesso;

    drop schema nfe;

    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists FKm4xtvy0clx5kjn7o1by212myf;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists FK3csm1rokayk6h4ky1nxqayrpc;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists FK138k76ajhyc1wbid71klvkh5s;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists FKq0u3g7h81o54itc9031hou4fu;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists FK484cg6sn8jjwuwewx8hmt3f8y;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_inclusao;

    drop table if exists configuracao.tb_certificado cascade;

    drop table if exists configuracao.tb_certificado_entidade cascade;

    drop table if exists configuracao.tb_email_smtp cascade;

    drop table if exists configuracao.tb_entidade cascade;

    drop table if exists configuracao.tb_fornecedor cascade;

    drop table if exists configuracao.tb_parametro_sistema cascade;

    drop table if exists configuracao.tb_uf cascade;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_instancia_usuario_responsavel;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_menu_menu_pai;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_instancia;

    drop table if exists controle_acesso.tb_instancia cascade;

    drop table if exists controle_acesso.tb_menu cascade;

    drop table if exists controle_acesso.tb_usuario cascade;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FKcbwuftkp89ryqxf899nkpe1iv;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FK8fe1j77ylkntse1k4a3axfrbh;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FKqy3eyca0cdd00jm880eb6pxa6;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists FKa51tnkfxfc1qavjsc0o7qm6th;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists FKkcviuhg48023ffcj6b29v2ouk;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FKhojscmxpq5malhrij1q0q6u7o;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FK7syntf9rhnq7179nq6inpmpvb;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FK16b1iovtdtpce4vgtpircif6;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists FKh0jmmfh8j9v3961rdp1jarpfu;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists FKfrvuymqnp2bw7ka9o9qtgvrrh;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists FKsbvw1jwj1hp6qkc510rqxlccq;

    drop table if exists nfe.tb_evento_nfe cascade;

    drop table if exists nfe.tb_historico_manifestacao_nfe cascade;

    drop table if exists nfe.tb_log_nfe cascade;

    drop table if exists nfe.tb_nfe cascade;

    drop table if exists nfe.tb_nfe_item cascade;

    drop table if exists nfe.tb_nfe_xml cascade;

    drop table if exists nfe.tb_servico_sefaz_nfe cascade;

    drop table if exists nfe.tb_url_servico_sefaz_nfe cascade;

    drop schema configuracao;

    drop schema controle_acesso;

    drop schema nfe;

    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_certificado 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists FKm4xtvy0clx5kjn7o1by212myf;

    alter table if exists configuracao.tb_certificado_entidade 
       drop constraint if exists FK3csm1rokayk6h4ky1nxqayrpc;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_email_smtp 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists FK138k76ajhyc1wbid71klvkh5s;

    alter table if exists configuracao.tb_entidade 
       drop constraint if exists FKq0u3g7h81o54itc9031hou4fu;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_fornecedor 
       drop constraint if exists FK484cg6sn8jjwuwewx8hmt3f8y;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_parametro_sistema 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists configuracao.tb_uf 
       drop constraint if exists fk_usuario_inclusao;

    drop table if exists configuracao.tb_certificado cascade;

    drop table if exists configuracao.tb_certificado_entidade cascade;

    drop table if exists configuracao.tb_email_smtp cascade;

    drop table if exists configuracao.tb_entidade cascade;

    drop table if exists configuracao.tb_fornecedor cascade;

    drop table if exists configuracao.tb_parametro_sistema cascade;

    drop table if exists configuracao.tb_uf cascade;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_instancia 
       drop constraint if exists fk_instancia_usuario_responsavel;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_menu 
       drop constraint if exists fk_menu_menu_pai;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists controle_acesso.tb_usuario 
       drop constraint if exists fk_usuario_instancia;

    drop table if exists controle_acesso.tb_instancia cascade;

    drop table if exists controle_acesso.tb_menu cascade;

    drop table if exists controle_acesso.tb_usuario cascade;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FKcbwuftkp89ryqxf899nkpe1iv;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FK8fe1j77ylkntse1k4a3axfrbh;

    alter table if exists nfe.tb_evento_nfe 
       drop constraint if exists FKqy3eyca0cdd00jm880eb6pxa6;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists FKa51tnkfxfc1qavjsc0o7qm6th;

    alter table if exists nfe.tb_historico_manifestacao_nfe 
       drop constraint if exists FKkcviuhg48023ffcj6b29v2ouk;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_log_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FKhojscmxpq5malhrij1q0q6u7o;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FK7syntf9rhnq7179nq6inpmpvb;

    alter table if exists nfe.tb_nfe 
       drop constraint if exists FK16b1iovtdtpce4vgtpircif6;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_nfe_item 
       drop constraint if exists FKh0jmmfh8j9v3961rdp1jarpfu;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_nfe_xml 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_servico_sefaz_nfe 
       drop constraint if exists FKfrvuymqnp2bw7ka9o9qtgvrrh;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_alteracao_situacao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists fk_usuario_inclusao;

    alter table if exists nfe.tb_url_servico_sefaz_nfe 
       drop constraint if exists FKsbvw1jwj1hp6qkc510rqxlccq;

    drop table if exists nfe.tb_evento_nfe cascade;

    drop table if exists nfe.tb_historico_manifestacao_nfe cascade;

    drop table if exists nfe.tb_log_nfe cascade;

    drop table if exists nfe.tb_nfe cascade;

    drop table if exists nfe.tb_nfe_item cascade;

    drop table if exists nfe.tb_nfe_xml cascade;

    drop table if exists nfe.tb_servico_sefaz_nfe cascade;

    drop table if exists nfe.tb_url_servico_sefaz_nfe cascade;

    drop schema configuracao;

    drop schema controle_acesso;

    drop schema nfe;
