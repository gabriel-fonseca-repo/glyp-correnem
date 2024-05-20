package br.com.glyp.msorm.web.enumeration;

public enum TipoOperacaoNfe {
  ENTRADA("E", "Entrada"), //
  SAIDA("S", "Saída");

  private final String valor;
  private final String descricao;

  TipoOperacaoNfe(String valor, String descricao) {
    this.valor = valor;
    this.descricao = descricao;
  }

  public static String getDescricao(String valor) {
    for (TipoOperacaoNfe item : TipoOperacaoNfe.values()) {
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
