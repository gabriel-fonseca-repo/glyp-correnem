package br.com.glyp.msorm.query;

import java.time.OffsetDateTime;

public interface RedacaoDashboardQuery {
  Long getId();

  String getFinalScore();

  String getTitle();

  OffsetDateTime getDataInclusao();

  Boolean getIsFinished();
}
