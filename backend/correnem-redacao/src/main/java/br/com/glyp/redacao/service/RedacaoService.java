package br.com.glyp.redacao.service;

import br.com.glyp.redacao.dao.RedacaoDao;
import org.springframework.stereotype.Service;

@Service
public class RedacaoService {

  private final RedacaoDao redacaoDao;

  public RedacaoService(RedacaoDao redacaoDao) {
    this.redacaoDao = redacaoDao;
  }

}
