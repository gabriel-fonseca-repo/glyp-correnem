package br.com.glyp.gateway.exception;

import br.com.glyp.msorm.web.dto.BaseResponse;
import br.com.glyp.msorm.web.enumeration.ResponseAction;
import org.springframework.http.HttpStatus;

public class AuthGatewayFilterException extends Exception {

  private HttpStatus status;

  private String message;

  private ResponseAction action;

  public AuthGatewayFilterException(HttpStatus status, BaseResponse br) {
    setMessage(br.getMessage());
    setAction(br.getAction());
    this.status = status;
  }

  public AuthGatewayFilterException(
    String message,
    ResponseAction action,
    HttpStatus status
  ) {
    this.status = status;
    this.message = message;
    this.action = action;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  @Override
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
