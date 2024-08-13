package br.com.glyp.usuario.controller;

import br.com.glyp.msorm.web.GlypCookies;
import br.com.glyp.msorm.web.dto.jwt.JwtCreationRequest;
import br.com.glyp.msorm.web.dto.jwt.JwtCreationResponse;
import br.com.glyp.msorm.web.dto.jwt.JwtValidationRequest;
import br.com.glyp.msorm.web.dto.jwt.JwtValidationResponse;
import br.com.glyp.msorm.web.enumeration.ResponseAction;
import br.com.glyp.usuario.service.JwtService;
import br.com.glyp.usuario.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final ObjectMapper json;
  private final JwtService jwtService;
  private final UsuarioService usuarioService;

  public AuthController(JwtService jwtService, UsuarioService usuarioService, ObjectMapper json) {
    this.jwtService = jwtService;
    this.usuarioService = usuarioService;
    this.json = json;
  }

  @PostMapping("/criartoken")
  public ResponseEntity<JwtCreationResponse> gerarToken(@RequestBody(required = false) JwtCreationRequest req) {
    var response = new JwtCreationResponse();
    response.setAction(ResponseAction.NONE);

    if (usuarioService.isLoginFormInvalido(req)) {
      response.setMessage("Formulário de login com campos ausentes.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    return usuarioService.consultarJwtUsuarioPorEmail(req.email()).map(usuario -> {
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

  @PostMapping("/validartoken")
  public ResponseEntity<JwtValidationResponse> validarToken(@RequestBody(required = false) JwtValidationRequest req) {
    var response = new JwtValidationResponse();

    jwtService.validarTokenJwt(req.token()).get("instancia");

    try {
      String claimsUsuario = json.writeValueAsString(jwtService.validarTokenJwt(req.token()));
      response.setMessage("Token válido.");
      response.setClaims(claimsUsuario);
      return ResponseEntity.ok(response);
    } catch (ExpiredJwtException e) {
      response.setMessage("Sessão expirada. Faça login novamente.");
      response.setAction(ResponseAction.LOGOUT);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
      response.setMessage("Autenticação inválida. Faça login novamente.");
      response.setAction(ResponseAction.LOGOUT);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    } catch (JsonProcessingException e) {
      response.setMessage("Erro interno do servidor. Tente novamente.");
      response.setAction(ResponseAction.NONE);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping("/invalidartoken")
  public ResponseEntity<Map<String, Object>> deslogar() {
    return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, ResponseCookie.from(GlypCookies.JWT, "")
                                                                                             // .secure(true)
                                                                                             // .sameSite(Cookie
                                                                                             // .SameSite.LAX
                                                                                             // .attributeValue())
                                                                                             .httpOnly(true).maxAge(Duration.ofMinutes(60)).path("/").build().toString()).body(Map.of("message", "Logout realizado com sucesso.", "action", ResponseAction.LOGOUT));
  }

}
