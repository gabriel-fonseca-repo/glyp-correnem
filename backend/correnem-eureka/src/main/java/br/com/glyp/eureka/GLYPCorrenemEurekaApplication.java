package br.com.glyp.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GLYPCorrenemEurekaApplication {

  public static void main(String[] args) {
    SpringApplication.run(GLYPCorrenemEurekaApplication.class, args);
  }
}
