package br.com.glyp.msorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = { "br.com.glyp.*" })
@EntityScan({ "br.com.glyp.*" })
@EnableCaching
public class GLYPCorrenemMSORMApplication {

  public static void main(String[] args) {
    SpringApplication.run(GLYPCorrenemMSORMApplication.class, args);
  }
}
