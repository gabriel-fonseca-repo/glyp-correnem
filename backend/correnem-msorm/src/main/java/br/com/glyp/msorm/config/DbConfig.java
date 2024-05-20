package br.com.glyp.msorm.config;

import br.com.glyp.msorm.crypto.Crypto;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

  @Value("${banco.senha}")
  private String senha;

  @Value("${banco.url}")
  private String url;

  @Value("${banco.usuario}")
  private String usuario;

  @Bean
  DataSource getDataSource() throws Exception {
    Crypto encrypter = new Crypto();
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(url);
    config.setUsername(usuario);
    config.setPassword(encrypter.decrypt(senha));
    return new HikariDataSource(config);
  }
}
