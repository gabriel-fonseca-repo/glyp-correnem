package br.com.glyp.redacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"br.com.glyp.*"})
@EntityScan({"br.com.glyp.*"})
public class GLYPCorrenemRedacaoApplication {

  public static void main(String[] args) {
    SpringApplication.run(GLYPCorrenemRedacaoApplication.class, args);
  }
}
