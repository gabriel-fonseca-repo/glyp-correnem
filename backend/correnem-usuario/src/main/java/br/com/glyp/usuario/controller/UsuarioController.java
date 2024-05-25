package br.com.glyp.usuario.controller;

import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.web.GlypHeaders;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.dto.usuario.CadastrarUsuarioRequest;
import br.com.glyp.msorm.web.dto.usuario.CadastrarUsuarioResponse;
import br.com.glyp.msorm.web.enumeration.ResponseAction;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import br.com.glyp.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping("/cadastro")
  public ResponseEntity<CadastrarUsuarioResponse> cadastrarUsuario(@RequestBody CadastrarUsuarioRequest req) {
    CadastrarUsuarioResponse response = new CadastrarUsuarioResponse();
    response.setAction(ResponseAction.NONE);
    try {

      usuarioService.isCadastroFormComCamposInvalidos(req);

      if (usuarioService.isEmailJaCadastrado(req)) {
        response.setMessage("E-mail já cadastrado(s).");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
      }

      Usuario novoUsuario = new Usuario();
      novoUsuario.setEmail(req.email());
      novoUsuario.setNome(req.nome());
      novoUsuario.setSenha(BCrypt.hashpw(req.senha(), BCrypt.gensalt()));

      usuarioService.save(novoUsuario);
      response.setMessage("Usuário cadastrado com sucesso.");

      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

  }

  @GetMapping("/dados-perfil")
  public ResponseEntity<Map<String, Object>> consultarPerfil(
      @RequestHeader(GlypHeaders.CLAIMS_USUARIO) UsuarioResponsavelHeader claims
  ) {
    try {
      return ResponseEntity.ok(
          Map.of(
              "perfil", usuarioService.getDadosPerfil(claims)
          )
      );
    } catch (GlypBackendException ge) {
      return ResponseEntity.status(ge.getStatus()).body(Map.of("message", ge.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
    }

  }

  @PostMapping("/editar-perfil")
  public ResponseEntity<Map<String, Object>> editarPerfil(
      @RequestHeader(GlypHeaders.CLAIMS_USUARIO) UsuarioResponsavelHeader claims,
      @RequestBody Map<String, String> req
  ) {
    try {
      Usuario usuario = usuarioService.getUsuario(claims);

      req.computeIfPresent("nome", (k, v) -> {
        usuario.setNome(v);
        return k;
      });
      req.computeIfPresent("senhanova", (k, v) -> {
        if (!req.containsKey("senhaantiga")) {
          throw new GlypBackendException("Para alterar sua senha, você deve informar sua senha atual.", HttpStatus.BAD_REQUEST.value());
        }
        if (!BCrypt.checkpw(req.get("senhaantiga"), usuario.getSenha())) {
          throw new GlypBackendException("A senha informada está incorreta.", HttpStatus.BAD_REQUEST.value());
        }
        usuario.setSenha(BCrypt.hashpw(v, BCrypt.gensalt()));
        return k;
      });

      usuarioService.save(usuario);

      return ResponseEntity.ok(
          Map.of(
              "message", "Dados do perfil alterados com sucesso!"
          )
      );
    } catch (GlypBackendException ge) {
      return ResponseEntity.status(ge.getStatus()).body(Map.of("message", ge.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
    }

  }

}
