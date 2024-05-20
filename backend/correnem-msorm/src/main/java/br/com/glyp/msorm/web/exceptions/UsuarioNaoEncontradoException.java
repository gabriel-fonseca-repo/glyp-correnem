package br.com.glyp.msorm.web.exceptions;

public class UsuarioNaoEncontradoException extends Exception {

  public UsuarioNaoEncontradoException(String loginUsuario) {
    super("O usuário de login '" + loginUsuario + "' não foi encontrado.");
  }
}
