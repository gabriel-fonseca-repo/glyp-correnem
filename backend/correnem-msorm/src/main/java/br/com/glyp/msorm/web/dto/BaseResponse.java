package br.com.glyp.msorm.web.dto;

import br.com.glyp.msorm.web.enumeration.ResponseAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseResponse {

  private String message;
  private ResponseAction action;

  @JsonIgnore
  public BaseResponse getBaseResponse() {
    var br = new BaseResponse();
    br.setMessage(getMessage());
    br.setAction(getAction());
    return br;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ResponseAction getAction() {
    return action;
  }

  public void setAction(ResponseAction action) {
    this.action = action;
  }
}
