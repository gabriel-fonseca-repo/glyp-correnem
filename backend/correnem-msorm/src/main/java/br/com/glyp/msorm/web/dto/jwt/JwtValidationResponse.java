package br.com.glyp.msorm.web.dto.jwt;

import br.com.glyp.msorm.web.dto.BaseResponse;

public final class JwtValidationResponse extends BaseResponse {

  private String claims;

  public String getClaims() {
    return claims;
  }

  public void setClaims(String claims) {
    this.claims = claims;
  }
}
