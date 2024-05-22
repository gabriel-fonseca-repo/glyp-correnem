package br.com.glyp.usuario.service;

import br.com.glyp.msorm.query.UsuarioJwtCreationQuery;
import br.com.glyp.msorm.web.dto.jwt.JwtCreationRequest;
import br.com.glyp.msorm.web.dto.usuario.CadastrarUsuarioRequest;
import br.com.glyp.usuario.dao.UsuarioDao;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

  private final UsuarioDao usuarioDao;

  public UsuarioService(UsuarioDao usuarioDao) {
    this.usuarioDao = usuarioDao;
  }

  public boolean isLoginFormInvalido(JwtCreationRequest req) {
    return ((req == null) || (req.cpf() == null || req.cpf().isBlank()) || (req.senha() == null || req.senha().isBlank()));
  }

  public boolean isCadastroFormComCamposNaoPreenchidos(CadastrarUsuarioRequest req) {
    return ((req == null) ||
        (req.cpf() == null || req.cpf().isBlank()) ||
        (req.senha() == null || req.senha().isBlank())) ||
        (req.email() == null || req.email().isBlank()) ||
        (req.nome() == null || req.nome().isBlank());
  }

  public Optional<UsuarioJwtCreationQuery> consultarUsuarioPorCpf(String cpf) {
    return usuarioDao.findByCpf(cpf, UsuarioJwtCreationQuery.class);
  }

  public boolean isCadastroFormComCamposInvalidos(CadastrarUsuarioRequest req) {

  }

}
