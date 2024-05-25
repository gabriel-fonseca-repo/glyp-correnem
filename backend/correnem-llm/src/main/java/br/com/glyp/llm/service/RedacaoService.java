package br.com.glyp.llm.service;

import br.com.glyp.llm.dao.RedacaoDao;
import br.com.glyp.llm.dao.UsuarioDao;
import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.dto.redacao.CorrecaoRedacaoRequest;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RedacaoService {

  private final RedacaoDao redacaoDao;

  private final UsuarioDao usuarioDao;

  private final GeminiService geminiService;

  public RedacaoService(RedacaoDao redacaoDao, UsuarioDao usuarioDao, GeminiService geminiService) {
    this.redacaoDao = redacaoDao;
    this.usuarioDao = usuarioDao;
    this.geminiService = geminiService;
  }

  public Redacao corrigirRedacao(String tema, String titulo, String texto, UsuarioResponsavelHeader claims) {
    Redacao redacao = new Redacao();

    Optional<Usuario> usuario = usuarioDao.findById(claims.idUsuario());
    if (usuario.isPresent()) {
      redacao.setPrompt(tema);
      redacao.setTitle(titulo);
      redacao.setText(texto);
      redacao.setUsuario(usuario.get());
      return this.corrigirRedacao(redacao);
    } else {
      throw new GlypBackendException("Usuário de id '" + claims.idUsuario() + "' não encontrado.", HttpStatus.SC_NOT_FOUND);
    }

  }

  public Redacao corrigirRedacao(Redacao redacao) {
    redacao = redacaoDao.saveAndFlush(redacao);
    String correcao = geminiService.corrigirRedacao(redacao);
    redacao = this.extrairSalvarDadosCorrecao(correcao, redacao);
    return redacao;
  }

  private Redacao extrairSalvarDadosCorrecao(String correcao, Redacao redacao) {
    Pattern pattern = Pattern.compile("COMPENTENCIA_1: (\\d+)\\nCOMPENTENCIA_2: (\\d+)\\nCOMPENTENCIA_3: (\\d+)\\nCOMPENTENCIA_4: (\\d+)\\nCOMPENTENCIA_5: (\\d+)\\n\\nCOMENTARIOS: (.*)");
    Matcher matcher = pattern.matcher(correcao);

    if (matcher.find()) {
      redacao.setCriteriaScore1(Integer.parseInt(matcher.group(1).trim()));
      redacao.setCriteriaScore2(Integer.parseInt(matcher.group(2).trim()));
      redacao.setCriteriaScore3(Integer.parseInt(matcher.group(3).trim()));
      redacao.setCriteriaScore4(Integer.parseInt(matcher.group(4).trim()));
      redacao.setCriteriaScore5(Integer.parseInt(matcher.group(5).trim()));

      String comments = correcao.split("COMENTARIOS:")[1].trim();

      redacao.setComments(comments);
      redacao.computeAndSetFinalScore();
      redacao.setReviewed(true);

      return redacaoDao.saveAndFlush(redacao);
    } else {
      throw new GlypBackendException("Erro interno de sistema. Tente novamente.", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
  }

  public void validarFormCorrigirRedacao(CorrecaoRedacaoRequest correcaoRedacaoRequest) {
    List<String> camposInvalidos = new ArrayList<>();

    if (correcaoRedacaoRequest.tema() == null || correcaoRedacaoRequest.tema().isBlank()) camposInvalidos.add("tema");
    if (correcaoRedacaoRequest.titulo() == null || correcaoRedacaoRequest.titulo().isBlank())
      camposInvalidos.add("titulo");
    if (correcaoRedacaoRequest.texto() == null || correcaoRedacaoRequest.texto().isBlank())
      camposInvalidos.add("texto");

    if (!camposInvalidos.isEmpty()) {
      if (camposInvalidos.size() == 1)
        throw new GlypBackendException("O campo " + String.join(", ", camposInvalidos) + " está em branco ou é inválido.", HttpStatus.SC_BAD_REQUEST);
      else
        throw new GlypBackendException("Os campos " + String.join(", ", camposInvalidos) + " estão em branco ou são inválidos.", HttpStatus.SC_BAD_REQUEST);
    }
  }


}
