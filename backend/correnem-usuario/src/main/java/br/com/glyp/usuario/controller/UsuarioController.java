package br.com.glyp.usuario.controller;

import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.web.dto.usuario.CadastrarUsuarioRequest;
import br.com.glyp.msorm.web.dto.usuario.CadastrarUsuarioResponse;
import br.com.glyp.msorm.web.enumeration.ResponseAction;
import br.com.glyp.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
