package br.com.glyp.msorm.web.dto.jwt;

import br.com.glyp.msorm.web.dto.BaseResponse;

public class JwtCreationResponse extends BaseResponse {

  private Long idUsuario;

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }
}
