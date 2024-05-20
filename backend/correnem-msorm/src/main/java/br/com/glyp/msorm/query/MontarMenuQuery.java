package br.com.glyp.msorm.query;

import java.util.List;

public interface MontarMenuQuery {
  String getName();

  String getPrincipal();

  Integer getOrdenacao();

  List<MontarMenuQuery> getMenusFilhos();
}
