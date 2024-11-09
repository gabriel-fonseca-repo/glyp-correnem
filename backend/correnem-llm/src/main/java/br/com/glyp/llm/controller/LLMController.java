package br.com.glyp.llm.controller;

import br.com.glyp.llm.service.AlunoService;
import br.com.glyp.llm.service.OcrService;
import br.com.glyp.llm.service.RedacaoService;
import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.web.GlypHeaders;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.dto.redacao.CorrecaoRedacaoRequest;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/llm")
public class LLMController {

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LLMController.class);

  private final RedacaoService redacaoService;

  private final AlunoService alunoService;

  private final OcrService ocrService;

  public LLMController(RedacaoService redacaoService, AlunoService alunoService, OcrService ocrService) {
    this.redacaoService = redacaoService;
    this.alunoService = alunoService;
    this.ocrService = ocrService;
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

      if (correcaoRedacaoRequest.nomeAluno() != null) {
        correcao = alunoService.cadastrarAluno(correcaoRedacaoRequest.nomeAluno(), correcao, claims);
      }

      correcao.getAluno().setRedacoes(null);

      return ResponseEntity.ok(
        Map.of(
          "redacao", correcao,
          "aluno", correcao.getAluno()
        )
      );
    } catch (GlypBackendException ge) {
      return ResponseEntity.status(ge.getStatus()).body(Map.of("message", ge.getMessage()));
    } catch (Exception e) {
      logger.error("Erro ao corrigir redação.", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
    }
  }

  @PostMapping("/extrair-texto")
  public ResponseEntity<Map<String, Object>> corrigirRedacao(@RequestParam(value = "image") MultipartFile file) {
    try {
      if (file.isEmpty()) {
        throw new GlypBackendException("Erro na leitura do arquivo.", HttpStatus.BAD_REQUEST.value());
      }

      return ResponseEntity.ok(
        Map.of(
          "extractedText", ocrService.extrairTexto(file)
        )
      );
    } catch (GlypBackendException ge) {
      return ResponseEntity.status(ge.getStatus()).body(Map.of("message", ge.getMessage()));
    } catch (Exception e) {
      logger.error("Erro ao corrigir redação.", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
    }
  }

}
