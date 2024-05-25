package br.com.glyp.llm.controller;

import br.com.glyp.llm.service.RedacaoService;
import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.web.GlypHeaders;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.dto.redacao.CorrecaoRedacaoRequest;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/llm")
public class LLMController {

  private final RedacaoService redacaoService;

  public LLMController(RedacaoService redacaoService) {
    this.redacaoService = redacaoService;
  }

  @PostMapping("/corrigir-redacao")
  public ResponseEntity<Map<String, Object>> corrigirRedacao(
      @RequestHeader(GlypHeaders.CLAIMS_USUARIO) UsuarioResponsavelHeader claims,
      @RequestBody CorrecaoRedacaoRequest correcaoRedacaoRequest
  ) {
    try {
      redacaoService.validarFormCorrigirRedacao(correcaoRedacaoRequest);

      Redacao correcao = redacaoService.corrigirRedacao(
          correcaoRedacaoRequest.tema(),
          correcaoRedacaoRequest.titulo(),
          correcaoRedacaoRequest.texto(),
          claims
      );

      return ResponseEntity.ok(
          Map.of("redacao", correcao)
      );
    } catch (GlypBackendException ge) {
      return ResponseEntity.status(ge.getStatus()).body(Map.of("message", ge.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
    }
  }

}
