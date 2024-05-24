package br.com.glyp.llm.controller;

import br.com.glyp.llm.service.RedacaoService;
import br.com.glyp.msorm.web.GlypHeaders;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/llm")
public class LLMController {

  private final RedacaoService redacaoService;

  public LLMController(RedacaoService redacaoService) {
    this.redacaoService = redacaoService;
  }

  @GetMapping("/corrigir-redacao")
  public ResponseEntity<Map<String, Object>> corrigirRedacao(
      @RequestHeader(GlypHeaders.CLAIMS_USUARIO) UsuarioResponsavelHeader claims,
      @RequestBody HashMap<String, String> dadosRedacao
  ) {
    return ResponseEntity.ok(
        Map.of("correcao", redacaoService.corrigirRedacao(
            dadosRedacao.get("tema"),
            dadosRedacao.get("titulo"),
            dadosRedacao.get("texto"))
        )
    );
  }

}
