package br.com.glyp.msorm.web.enumeration;

public enum FinalidadeEmissaoNfe {
  NORMAL("1", "NF-e normal"), //
  COMPLEMENTAR("2", "NF-e complementar"), //
  AJUSTE("3", "NF-e de ajuste"), //
  DEVOLUCAO_RETORNO("4", "Devolução/Retorno");

  private final String valor;
  private final String descricao;

  FinalidadeEmissaoNfe(String valor, String descricao) {
    this.valor = valor;
    this.descricao = descricao;
  }

  public static String getDescricao(String valor) {
    for (FinalidadeEmissaoNfe item : FinalidadeEmissaoNfe.values()) {
      if (item.getValor().equals(valor)) {
        return item.getDescricao();
      }
    }
    return null;
  }

  public String getValor() {
    return valor;
  }

  public String getDescricao() {
    return descricao;
  }
}
