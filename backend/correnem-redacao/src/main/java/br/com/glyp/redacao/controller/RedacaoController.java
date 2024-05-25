package br.com.glyp.redacao.controller;

import br.com.glyp.redacao.service.RedacaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redacao")
public class RedacaoController {

  private final RedacaoService redacaoService;

  public RedacaoController(RedacaoService redacaoService) {
    this.redacaoService = redacaoService;
  }

}
