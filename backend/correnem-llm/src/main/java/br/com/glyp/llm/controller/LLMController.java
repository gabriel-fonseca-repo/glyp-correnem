package br.com.glyp.llm.controller;

import br.com.glyp.llm.service.RedacaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/llm")
public class LLMController {

  private final RedacaoService llmService;

  public LLMController(RedacaoService llmService) {
    this.llmService = llmService;
  }

}
