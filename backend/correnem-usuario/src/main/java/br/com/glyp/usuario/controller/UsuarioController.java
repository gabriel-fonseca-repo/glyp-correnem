package br.com.glyp.usuario.controller;

import br.com.glyp.msorm.web.GlypCookies;
import br.com.glyp.msorm.web.dto.jwt.JwtCreationResponse;
import br.com.glyp.msorm.web.dto.usuario.CadastrarUsuarioRequest;
import br.com.glyp.msorm.web.enumeration.ResponseAction;
import br.com.glyp.usuario.service.UsuarioService;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping("/cadastro")
  public ResponseEntity<JwtCreationResponse> cadastrarUsuario(@RequestBody CadastrarUsuarioRequest req) {
    var response = new JwtCreationResponse();
    response.setAction(ResponseAction.NONE);

    if (usuarioService.isCadastroFormComCamposNaoPreenchidos(req)) {
      response.setMessage("Formulário de cadastro com campos ausentes.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    if (usuarioService.isCadastroFormComCamposInvalidos(req)) {
      response.setMessage("Formulário de cadastro com campos ausentes.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    return usuarioService.consultarUsuarioPorCpf(req.cpf()).map(usuario -> {
      if (!BCrypt.checkpw(req.senha(), usuario.getSenha())) {
        response.setMessage("Não foi encontrado usuário cadastrado com as credenciais fornecidas.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
      }

      response.setMessage("Login realizado com sucesso.");
      response.setIdUsuario(usuario.getId());
      String tokenJwt = jwtService.gerarTokenJwt(usuario);
      HttpCookie cookie = ResponseCookie.from(GlypCookies.JWT, tokenJwt)
          // .secure(true)
          .httpOnly(true).maxAge(Duration.ofMinutes(60)).path("/").sameSite(Cookie.SameSite.STRICT.attributeValue()).build();
      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);

    }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
  }

}
