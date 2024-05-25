package br.com.glyp.msorm.web.exceptions;

public class GlypBackendException extends RuntimeException {

  private Integer status;

  public GlypBackendException(String message, Integer status) {
    super(message);
    this.status = status;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}
