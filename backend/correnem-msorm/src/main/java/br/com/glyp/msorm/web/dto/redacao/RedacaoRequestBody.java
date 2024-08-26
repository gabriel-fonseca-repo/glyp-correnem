package br.com.glyp.msorm.web.dto.redacao;

public record RedacaoRequestBody(
  Long id,
  String title,
  String prompt,
  String text,
  boolean reviewed,
  int finalScore,
  int criteriaScore1,
  int criteriaScore2,
  int criteriaScore3,
  int criteriaScore4,
  int criteriaScore5,
  String comments,
  String nomeAluno
) {
}
