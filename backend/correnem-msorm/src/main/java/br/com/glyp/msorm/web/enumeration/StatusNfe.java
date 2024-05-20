package br.com.glyp.msorm.web.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public enum StatusNfe {
  AUTORIZADA("A", "AUTORIZADA", "#10cf33"),
  DENEGADA("D", "DENEGADA", "#b0131e"),
  CANCELADA("C", "CANCELADA", "#ff0000"),
  NAO_VERIFICADA("N", "NÃO VERIFICADA", "#ffea00"),
  VALIDA_SCHEMA("V", "VÁLIDA (SCHEMA)", "#2fc4c9"),
  INVALIDA_SCHEMA("I", "INVÁLIDA (SCHEMA)", "#ff5500"),
  INVALIDA_SEFAZ("S", "INVÁLIDA SEFAZ", "#f58b00"),
  REJEICAO("R", "REJEIÇÃO CONSULTA SEFAZ", "#f5bc58");

  private final String codigo;
  private final String descricao;
  private final String cor;

  StatusNfe(String codigo, String descricao, String cor) {
    this.codigo = codigo;
    this.descricao = descricao;
    this.cor = cor;
  }

  public static String getDescricao(String codigo) {
    for (StatusNfe item : StatusNfe.values()) {
      if (item.getCodigo().equals(codigo)) {
        return item.getDescricao();
      }
    }
    return null;
  }

  public static StatusNfe getEnumPorCodigo(String codigo) {
    for (StatusNfe item : StatusNfe.values()) {
      if (item.getCodigo().equals(codigo)) {
        return item;
      }
    }
    return null;
  }

  public static String getCor(String valor) {
    for (StatusNfe item : StatusNfe.values()) {
      if (item.getCodigo().equals(valor)) {
        return item.getCor();
      }
    }
    return null;
  }

  public static Collection<StatusNfe> getCollection() {
    return Arrays.asList(StatusNfe.values());
  }

  public static Collection<StatusNfe> getCollectionNfe20Graus() {
    Collection<StatusNfe> lista = new ArrayList<>();

    lista.add(StatusNfe.AUTORIZADA);
    lista.add(StatusNfe.CANCELADA);

    return lista;
  }

  public String getCodigo() {
    return codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public String getCor() {
    return cor;
  }
}
