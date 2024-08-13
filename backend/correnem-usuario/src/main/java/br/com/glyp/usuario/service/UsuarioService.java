package br.com.glyp.usuario.service;

import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.query.UsuarioJwtCreationQuery;
import br.com.glyp.msorm.query.UsuarioPerfilQuery;
import br.com.glyp.msorm.util.DadosValUtil;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.dto.jwt.JwtCreationRequest;
import br.com.glyp.msorm.web.dto.usuario.CadastrarUsuarioRequest;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import br.com.glyp.usuario.dao.UsuarioDao;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

  private final UsuarioDao usuarioDao;

  private final String email;

  private final String nome;

  private final String senha;

  public UsuarioService(
    UsuarioDao usuarioDao,
    @Value("${ADM_USER_EMAIL}") String email,
    @Value("${ADM_USER_NAME}") String nome,
    @Value("${ADM_USER_PASSWD}") String senha
  ) {
    this.usuarioDao = usuarioDao;
    this.email = email;
    this.nome = nome;
    this.senha = senha;
  }

  public void save(Usuario usuario) {
    usuarioDao.saveAndFlush(usuario);
  }

  public boolean isLoginFormInvalido(JwtCreationRequest req) {
    return ((req == null) || (req.email() == null || req.email().isBlank()) || (req.senha() == null || req.senha().isBlank()));
  }

  public Optional<UsuarioJwtCreationQuery> consultarUsuarioPorEmail(String email) {
    return usuarioDao.findByEmail(email, UsuarioJwtCreationQuery.class);
  }

  public boolean isEmailJaCadastrado(CadastrarUsuarioRequest req) {
    return usuarioDao.isEmailJaCadastrado(req.email());
  }

  public void isCadastroFormComCamposInvalidos(CadastrarUsuarioRequest req) {

    List<String> camposInvalidos = new ArrayList<>();

    if ((req.email() == null || req.email().isBlank()) || !DadosValUtil.isEmailValido(req.email()))
      camposInvalidos.add("Email");
    if ((req.nome() == null || req.nome().isBlank())) camposInvalidos.add("Nome");
    if ((req.senha() == null || req.senha().isBlank())) camposInvalidos.add("Senha");

    if (!camposInvalidos.isEmpty()) {
      if (camposInvalidos.size() == 1)
        throw new RuntimeException("O campo " + String.join(", ", camposInvalidos) + " está em branco ou é inválido.");
      else
        throw new RuntimeException("Os campos " + String.join(", ", camposInvalidos) + " estão em branco ou são " +
          "inválidos.");
    }

  }

  public UsuarioPerfilQuery getDadosPerfil(UsuarioResponsavelHeader claims) {
    Optional<UsuarioPerfilQuery> dadosUsuario = usuarioDao.findById(claims.idUsuario(), UsuarioPerfilQuery.class);
    if (dadosUsuario.isPresent()) {
      return dadosUsuario.get();
    } else {
      throw new GlypBackendException("Usuário de id '" + claims.idUsuario() + "' não encontrado.",
        HttpStatus.SC_NOT_FOUND);
    }
  }

  public Usuario getUsuario(UsuarioResponsavelHeader claims) {
    Optional<Usuario> dadosUsuario = usuarioDao.findById(claims.idUsuario());
    if (dadosUsuario.isPresent()) {
      return dadosUsuario.get();
    } else {
      throw new GlypBackendException("Usuário de id '" + claims.idUsuario() + "' não encontrado.",
        HttpStatus.SC_NOT_FOUND);
    }
  }

  @EventListener(ApplicationReadyEvent.class)
  public void createDefaultAdminUser() {
    Usuario novoUsuario = new Usuario();
    novoUsuario.setEmail(this.email);
    novoUsuario.setNome(this.nome);
    novoUsuario.setSenha(BCrypt.hashpw(this.senha, BCrypt.gensalt()));

    this.save(novoUsuario);
  }
}
