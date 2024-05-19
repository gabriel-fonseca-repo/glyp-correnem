package br.com.glyp.correnemusuario.controller;

import br.com.glyp.correnemmsorm.web.HmitHeaders;
import br.com.glyp.correnemmsorm.web.UsuarioResponsavelHeader;
import br.com.glyp.correnemmsorm.web.dto.usuario.PermissoesUsuarioResponse;
import br.com.glyp.correnemusuario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping("/carregarpermissoes")
  public ResponseEntity<PermissoesUsuarioResponse> carregarPermissoes(
    @RequestHeader(HmitHeaders.CLAIMS_USUARIO) UsuarioResponsavelHeader claims
  ) {
    return ResponseEntity.ok(
      usuarioService.carregarPermissoesUsuario(
        claims.idUsuario(),
        claims.idInstancia()
      )
    );
  }
}
