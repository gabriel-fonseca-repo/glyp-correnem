package br.com.glyp.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = { "br.com.glyp.*" })
@EntityScan({ "br.com.glyp.*" })
public class GLYPCorrenemBaseApplication {

  public static void main(String[] args) {
    SpringApplication.run(GLYPCorrenemBaseApplication.class, args);
  }
}
