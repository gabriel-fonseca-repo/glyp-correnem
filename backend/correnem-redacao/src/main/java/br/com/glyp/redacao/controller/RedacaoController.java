package br.com.glyp.redacao.controller;

import br.com.glyp.msorm.util.DadosValUtil;
import br.com.glyp.msorm.web.GlypHeaders;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import br.com.glyp.redacao.service.RedacaoService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redacao")
public class RedacaoController {

  private final RedacaoService redacaoService;

  public RedacaoController(RedacaoService redacaoService) {
    this.redacaoService = redacaoService;
  }

  @GetMapping("/redacoes/{size}/{page}")
  public ResponseEntity<Map<String, Object>> paginarRedacoes(
      @RequestHeader(GlypHeaders.CLAIMS_USUARIO) UsuarioResponsavelHeader claims,
      @PathVariable String page, @PathVariable String size) {
    try {

      if (DadosValUtil.isNotNumeric(page) || DadosValUtil.isNotNumeric(size)) {
        throw new GlypBackendException("Os valores de paginação não são válidos.", HttpStatus.BAD_REQUEST.value());
      }

      Pageable pageable = PageRequest.of(
          Integer.parseInt(page),
          Integer.parseInt(size),
          Sort.by(Sort.Direction.DESC, "dataInclusao")
      );

      return ResponseEntity.ok(
          Map.of(
              "pagina", redacaoService.findByPageableUserId(claims, pageable)
          )
      );
    } catch (GlypBackendException ge) {
      return ResponseEntity.status(ge.getStatus()).body(Map.of("message", ge.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
    }
  }

}
