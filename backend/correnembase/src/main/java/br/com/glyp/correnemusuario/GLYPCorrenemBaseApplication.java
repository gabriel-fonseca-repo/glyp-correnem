package br.com.glyp.correnemusuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = { "br.com.hmit.*" })
@EntityScan({ "br.com.hmit.*" })
public class GLYPCorrenemBaseApplication {

  public static void main(String[] args) {
    SpringApplication.run(GLYPCorrenemBaseApplication.class, args);
  }
}
