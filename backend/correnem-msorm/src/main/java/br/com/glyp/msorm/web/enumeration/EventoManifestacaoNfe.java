package br.com.glyp.msorm.web.enumeration;

public enum EventoManifestacaoNfe {
  CIENCIA("210210", "Ciência da Operação", "Ciencia da Operacao"),
  CONFIRMACAO("210200", "Confirmação da Operação", "Confirmacao da Operacao"),
  DESCONHECIMENTO(
    "210220",
    "Desconhecimento da Operação",
    "Desconhecimento da Operacao"
  ),
  NAO_REALIZADA("210240", "Operação não Realizada", "Operacao nao Realizada");

  private final String valor;
  private final String descricao;
  private final String descricaoSemAcentos;

  EventoManifestacaoNfe(
    String valor,
    String descricao,
    String descricaoSemAcentos
  ) {
    this.valor = valor;
    this.descricao = descricao;
    this.descricaoSemAcentos = descricaoSemAcentos;
  }

  public static String getDescricao(String valor) {
    for (EventoManifestacaoNfe item : EventoManifestacaoNfe.values()) {
      if (item.getValor().equals(valor)) {
        return item.getDescricao();
      }
    }
    return null;
  }

  public static EventoManifestacaoNfe getEnumPorValor(String valor) {
    for (EventoManifestacaoNfe item : EventoManifestacaoNfe.values()) {
      if (item.getValor().equals(valor)) {
        return item;
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

  public String getDescricaoSemAcentos() {
    return descricaoSemAcentos;
  }
}
