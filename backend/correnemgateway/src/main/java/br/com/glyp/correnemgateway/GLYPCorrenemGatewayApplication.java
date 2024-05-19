package br.com.glyp.correnemgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GLYPCorrenemGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GLYPCorrenemGatewayApplication.class, args);
  }
}

@RestController
class RestHelloWorld {

  @GetMapping("/teste")
  public String teste(
    @Value("${local.server.port}") String porta,
    @Value("${spring.application.name}") String nomeMs
  ) {
    return String.format(
      "Olá, o microsserviço %s está rodando na porta %s!",
      nomeMs,
      porta
    );
  }
}
