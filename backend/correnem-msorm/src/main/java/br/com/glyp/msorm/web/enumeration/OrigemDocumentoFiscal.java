package br.com.glyp.msorm.web.enumeration;

public enum OrigemDocumentoFiscal {
  SEFAZ("S", "SEFAZ"),
  EMAIL("E", "E-mail"),
  OCR_EMAIL("O", "OCR email"),
  OCR_UPLOAD("L", "OCR upload"),
  UPLOAD("U", "Upload"),
  UPLOAD_EXCEL("C", "Upload Excel"),
  PREFEITURA("F", "PREFEITURA"),
  DIGITADA("D", "Digitada"),
  SISTEMA_PREFEITURA("P", "SISTEMA PREFEITURA"),
  XML_NFE("X", "XML DE NF-E"),
  SEFAZ_MANUAL("M", "SEFAZ MANUAL"),
  WEBSERVICE_NFEMASTER("W", "Web Service NF-e Master"),
  UPLOAD_PORTAL_FORNECEDOR("T", "Upload Portal do Fornecedor");

  private final String valor;
  private final String descricao;

  OrigemDocumentoFiscal(String valor, String descricao) {
    this.valor = valor;
    this.descricao = descricao;
  }

  public static String getDescricao(String valor) {
    for (OrigemDocumentoFiscal item : OrigemDocumentoFiscal.values()) {
      if (item.getValor().equals(valor)) {
        return item.getDescricao();
      }
    }
    return null;
  }

  public static OrigemDocumentoFiscal getEnumPorValor(String valor) {
    for (OrigemDocumentoFiscal item : OrigemDocumentoFiscal.values()) {
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
}
