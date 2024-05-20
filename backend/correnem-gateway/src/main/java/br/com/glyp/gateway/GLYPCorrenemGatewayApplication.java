package br.com.glyp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class GLYPCorrenemGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GLYPCorrenemGatewayApplication.class, args);
  }
}
