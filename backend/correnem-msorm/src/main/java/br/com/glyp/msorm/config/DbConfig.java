package br.com.glyp.msorm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

  @Value("${banco.host}")
  private String host;

  @Value("${banco.porta}")
  private Integer porta;

  @Value("${banco.database}")
  private String banco;

  @Value("${banco.usuario}")
  private String usuario;

  @Value("${banco.senha}")
  private String senha;

  @Bean
  DataSource getDataSource() {
    HikariConfig config = new HikariConfig();

    String url = "jdbc:postgresql://" + host + ":" + porta + "/" + banco;

    config.setJdbcUrl(url);
    config.setUsername(usuario);
    config.setPassword(senha);

    return new HikariDataSource(config);
  }
}
