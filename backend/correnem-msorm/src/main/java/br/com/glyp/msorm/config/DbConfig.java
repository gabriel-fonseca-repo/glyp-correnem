package br.com.glyp.msorm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

  @Value("${POSTGRES_HOST}")
  private String host;

  @Value("${POSTGRES_PORT}")
  private Integer porta;

  @Value("${POSTGRES_DB}")
  private String banco;

  @Value("${POSTGRES_USER}")
  private String usuario;

  @Value("${POSTGRES_PASSWORD}")
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
