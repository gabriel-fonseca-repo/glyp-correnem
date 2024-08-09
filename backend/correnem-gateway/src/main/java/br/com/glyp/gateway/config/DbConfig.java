package br.com.glyp.gateway.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.lang.NonNull;

@Configuration
public class DbConfig extends AbstractR2dbcConfiguration {

  private static final Logger LOG = LogManager.getLogger(DbConfig.class);

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

  @Override
  @Bean
  @NonNull
  public ConnectionFactory connectionFactory() {
    // @formatter:off
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
            .host(host)
            .port(porta)
            .username(usuario)
            .password(senha)
            .database(banco)
            .build());
    // @formatter:on
  }
}
