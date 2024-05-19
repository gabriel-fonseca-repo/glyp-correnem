package br.com.glyp.correnemusuario.service;

import br.com.glyp.correnemmsorm.query.UsuarioJwtCreationQuery;
import br.com.glyp.correnemmsorm.web.dto.jwt.JwtCreationRequest;
import br.com.glyp.correnemmsorm.web.dto.usuario.InstanciasUsuarioRequest;
import br.com.glyp.correnemmsorm.web.dto.usuario.PermissoesUsuarioResponse;
import br.com.glyp.correnemmsorm.web.enumeration.ResponseAction;
import br.com.glyp.correnemusuario.dao.InstanciaDao;
import br.com.glyp.correnemusuario.dao.MenuDao;
import br.com.glyp.correnemusuario.dao.UsuarioDao;
import br.com.glyp.correnemusuario.dao.UsuarioInstanciaDao;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private final UsuarioDao usuarioDao;
  private final MenuDao menuDao;
  private final InstanciaDao instanciaDao;
  private final UsuarioInstanciaDao usuarioInstanciaDao;

  public UsuarioService(
    UsuarioDao usuarioDao,
    MenuDao menuDao,
    UsuarioInstanciaDao usuarioInstanciaDao,
    InstanciaDao instanciaDao
  ) {
    this.usuarioDao = usuarioDao;
    this.menuDao = menuDao;
    this.usuarioInstanciaDao = usuarioInstanciaDao;
    this.instanciaDao = instanciaDao;
  }

  public boolean isLoginFormInvalido(InstanciasUsuarioRequest req) {
    return (
      (req == null) ||
      (req.cpf() == null || req.cpf().isBlank()) ||
      (req.senha() == null || req.senha().isBlank())
    );
  }

  public boolean isLoginFormInvalido(JwtCreationRequest req) {
    return (
      (req == null) ||
      (req.cpf() == null || req.cpf().isBlank()) ||
      (req.senha() == null || req.senha().isBlank()) ||
      (req.nomeInstancia() == null || req.nomeInstancia().isBlank())
    );
  }

  public PermissoesUsuarioResponse carregarPermissoesUsuario(
    Long idUsuario,
    Long idInstancia
  ) {
    return usuarioDao
      .findById(idUsuario)
      .flatMap(usuario ->
        instanciaDao
          .findById(idInstancia)
          .flatMap(instancia ->
            usuarioInstanciaDao.findByUsuarioAndInstancia(usuario, instancia)
          )
      )
      .map(usuarioInstancia -> {
        PermissoesUsuarioResponse permissoes = new PermissoesUsuarioResponse();
        permissoes.setPerfil(usuarioInstancia.getPerfil());
        permissoes.setAction(ResponseAction.NONE);
        permissoes.setAcessos(
          menuDao.findAllByPerfil(usuarioInstancia.getPerfil())
        );
        return permissoes;
      })
      .orElseThrow(() ->
        new RuntimeException("Relacionamento com instancia não encontrado.")
      );
  }

  public Optional<UsuarioJwtCreationQuery> consultarUsuarioPorCpf(String cpf) {
    return usuarioDao.findByCpf(cpf, UsuarioJwtCreationQuery.class);
  }
}
