package br.com.glyp.gateway.config;

import br.com.glyp.msorm.crypto.Crypto;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
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

  @Value("${hmit.banco.host}")
  private String host;

  @Value("${hmit.banco.porta}")
  private Integer porta;

  @Value("${hmit.banco.database}")
  private String banco;

  @Value("${hmit.banco.usuario}")
  private String usuario;

  @Value("${hmit.banco.senha}")
  private String senha;

  @Override
  @Bean
  @NonNull
  public ConnectionFactory connectionFactory() {
    String senhaDescriptografada = null;
    try {
      senhaDescriptografada = new Crypto().decrypt(senha);
    } catch (GeneralSecurityException | UnsupportedEncodingException e) {
      LOG.error(
        "Não foi possível se conectar com o banco: {}, Erro: {}",
        banco,
        e.getMessage()
      );
    }
    // @formatter:off
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
            .host(host)
            .port(porta)
            .username(usuario)
            .password(senhaDescriptografada)
            .database(banco)
            .build());
    // @formatter:on
  }
}
