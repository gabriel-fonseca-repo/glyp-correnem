package br.com.glyp.llm.service;

import br.com.glyp.llm.dao.RedacaoDao;
import br.com.glyp.msorm.model.Redacao;
import org.springframework.stereotype.Service;

@Service
public class RedacaoService {

  private final RedacaoDao redacaoDao;

  private final GeminiService geminiService;

  public RedacaoService(RedacaoDao redacaoDao, GeminiService geminiService) {
    this.redacaoDao = redacaoDao;
    this.geminiService = geminiService;
  }

  public String corrigirRedacao(String tema, String titulo, String texto) {
    Redacao redacao = new Redacao();
    redacao.setPrompt(tema);
    redacao.setTitle(titulo);
    redacao.setText(texto);
    return this.corrigirRedacao(redacao);
  }

  public String corrigirRedacao(Redacao redacao) {
    redacaoDao.saveAndFlush(redacao);
    return geminiService.corrigirRedacao(redacao);
  }


}
