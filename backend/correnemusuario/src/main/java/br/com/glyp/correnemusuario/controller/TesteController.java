package br.com.glyp.correnemusuario.controller;

import br.com.glyp.correnemmsorm.web.HmitHeaders;
import br.com.glyp.correnemmsorm.web.UsuarioResponsavelHeader;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

  @GetMapping("/teste")
  public ResponseEntity<Map<String, Object>> teste(
    @Value("${local.server.port}") String porta,
    @Value("${spring.application.name}") String nomeMs,
    @RequestHeader(HmitHeaders.CLAIMS_USUARIO) UsuarioResponsavelHeader claims
  ) {
    return ResponseEntity.ok(
      Map.of(
        "message",
        String.format(
          "Olá, %s, o microsserviço %s está rodando na porta %s!",
          claims.login(),
          nomeMs,
          porta
        )
      )
    );
  }
}
