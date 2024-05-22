package br.com.glyp.llm.service;

import br.com.glyp.llm.dao.RedacaoDao;
import org.springframework.stereotype.Service;

@Service
public class RedacaoService {

  private final RedacaoDao redacaoDao;

  public RedacaoService(RedacaoDao redacaoDao) {
    this.redacaoDao = redacaoDao;
  }

}
