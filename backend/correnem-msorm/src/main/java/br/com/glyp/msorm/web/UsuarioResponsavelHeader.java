package br.com.glyp.msorm.web;

import java.util.Date;

public record UsuarioResponsavelHeader(Long idUsuario, String nome, String email, String iss, Date iat, Date exp) {
}
