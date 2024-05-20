package br.com.glyp.msorm.web;

import br.com.glyp.msorm.web.enumeration.Perfil;
import java.util.Date;

public record UsuarioResponsavelHeader(
  String login,
  Long idUsuario,
  boolean isResponsavel,
  Long idInstancia,
  String iss,
  Date iat,
  Date exp,
  Perfil perfil
) {}
