package br.com.glyp.llm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = { "br.com.glyp.*" })
@EntityScan({ "br.com.glyp.*" })
public class GLYPCorrenemLLMApplication {

  public static void main(String[] args) {
    SpringApplication.run(GLYPCorrenemLLMApplication.class, args);
  }
}
