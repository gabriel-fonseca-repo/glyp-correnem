package br.com.glyp.correnemusuario.controller;

import br.com.glyp.correnemmsorm.query.IdInstanciaQuery;
import br.com.glyp.correnemmsorm.web.HmitCookies;
import br.com.glyp.correnemmsorm.web.HmitHeaders;
import br.com.glyp.correnemmsorm.web.UsuarioResponsavelHeader;
import br.com.glyp.correnemmsorm.web.dto.jwt.JwtCreationRequest;
import br.com.glyp.correnemmsorm.web.dto.jwt.JwtCreationResponse;
import br.com.glyp.correnemmsorm.web.dto.jwt.JwtValidationRequest;
import br.com.glyp.correnemmsorm.web.dto.jwt.JwtValidationResponse;
import br.com.glyp.correnemmsorm.web.dto.usuario.InstanciasUsuarioRequest;
import br.com.glyp.correnemmsorm.web.dto.usuario.InstanciasUsuarioResponse;
import br.com.glyp.correnemmsorm.web.enumeration.ResponseAction;
import br.com.glyp.correnemusuario.service.InstanciaService;
import br.com.glyp.correnemusuario.service.JwtService;
import br.com.glyp.correnemusuario.service.MenuService;
import br.com.glyp.correnemusuario.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final ObjectMapper json;
  private final JwtService jwtService;
  private final MenuService menuService;
  private final UsuarioService usuarioService;
  private final InstanciaService instanciaService;

  public AuthController(
    JwtService jwtService,
    UsuarioService usuarioService,
    ObjectMapper json,
    MenuService menuService,
    InstanciaService instanciaService
  ) {
    this.jwtService = jwtService;
    this.menuService = menuService;
    this.usuarioService = usuarioService;
    this.instanciaService = instanciaService;
    this.json = json;
  }

  @GetMapping("/carregarinstanciasusuario")
  public ResponseEntity<InstanciasUsuarioResponse> carregarInstancias(
    @RequestBody(required = false) InstanciasUsuarioRequest req
  ) {
    boolean loginInvalido = usuarioService.isLoginFormInvalido(req);

    var response = new InstanciasUsuarioResponse();
    response.setAction(ResponseAction.NONE);

    if (loginInvalido) {
      response.setMessage("Formulário de login com campos ausentes.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    List<String> instancias = instanciaService.obterInstanciasDoUsuario(
      req.cpf()
    );
    if (instancias.isEmpty()) {
      response.setMessage(
        "O usuario informado não está cadastrado em uma instância."
      );
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    response.setInstanciasUsuario(instancias);

    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtCreationResponse> gerarToken(
    @RequestBody(required = false) JwtCreationRequest req
  ) {
    var response = new JwtCreationResponse();
    response.setAction(ResponseAction.NONE);

    if (usuarioService.isLoginFormInvalido(req)) {
      response.setMessage("Formulário de login com campos ausentes.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    return usuarioService
      .consultarUsuarioPorCpf(req.cpf())
      .map(usuario -> {
        if (!BCrypt.checkpw(req.senha(), usuario.getSenha())) {
          response.setMessage(
            "Não foi encontrado usuário cadastrado com as credenciais fornecidas."
          );
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        Optional<IdInstanciaQuery> id = instanciaService.consultarIdPorNome(
          req.nomeInstancia()
        );
        if (id.isPresent()) {
          response.setMessage("Login realizado com sucesso.");
          response.setIdUsuario(usuario.getId());
          String tokenJwt = jwtService.gerarTokenJwt(usuario, id.get().getId());
          HttpCookie cookie = ResponseCookie
            .from(HmitCookies.JWT, tokenJwt)
            // .secure(true)
            .httpOnly(true)
            .maxAge(Duration.ofMinutes(60))
            .path("/")
            .sameSite(Cookie.SameSite.STRICT.attributeValue())
            .build();
          return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(response);
        }

        response.setMessage("Erro inesperado ao selecionar a instância.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      })
      .orElseGet(() -> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      });
  }

  @PostMapping("/validartoken")
  public ResponseEntity<JwtValidationResponse> validarToken(
    @RequestBody(required = false) JwtValidationRequest req
  ) {
    var response = new JwtValidationResponse();

    //TODO: aaaaaa
    jwtService.validarTokenJwt(req.token()).get("instancia");

    try {
      String claimsUsuario = json.writeValueAsString(
        jwtService.validarTokenJwt(req.token())
      );
      response.setMessage("Token válido.");
      response.setClaims(claimsUsuario);
      return ResponseEntity.ok(response);
    } catch (ExpiredJwtException e) {
      response.setMessage("Sessão expirada. Faça login novamente.");
      response.setAction(ResponseAction.LOGOUT);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    } catch (
      UnsupportedJwtException
      | MalformedJwtException
      | SignatureException
      | IllegalArgumentException e
    ) {
      response.setMessage("Autenticação inválida. Faça login novamente.");
      response.setAction(ResponseAction.LOGOUT);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    } catch (JsonProcessingException e) {
      response.setMessage("Erro interno do servidor. Tente novamente.");
      response.setAction(ResponseAction.NONE);
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(response);
    }
  }

  @GetMapping("/invalidartoken")
  public ResponseEntity<Map<String, Object>> deslogar() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .header(
        HttpHeaders.SET_COOKIE,
        ResponseCookie
          .from(HmitCookies.JWT, "")
          // .secure(true)
          // .sameSite(Cookie.SameSite.LAX.attributeValue())
          .httpOnly(true)
          .maxAge(Duration.ofMinutes(60))
          .path("/")
          .build()
          .toString()
      )
      .body(
        Map.of(
          "message",
          "Logout realizado com sucesso.",
          "action",
          ResponseAction.LOGOUT
        )
      );
  }

  @GetMapping("/consultaracesso")
  public ResponseEntity<?> consultarAcesso(
    @RequestHeader(HmitHeaders.CLAIMS_USUARIO) UsuarioResponsavelHeader claims,
    @RequestParam("tela") String tela
  ) {
    return menuService
      .findByPrincipalAndPerfil(tela, claims.perfil())
      .map((menu -> ResponseEntity.noContent().build()))
      .orElse(
        ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body(
            Map.of("message", "Você não possui acesso a essa funcionalidade.")
          )
      );
  }
}
