package br.com.glyp.redacao.service;

import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import br.com.glyp.redacao.dao.RedacaoDao;
import br.com.glyp.redacao.dao.UsuarioDao;
import org.apache.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedacaoService {

  private final RedacaoDao redacaoDao;

  private final UsuarioDao usuarioDao;

  public RedacaoService(RedacaoDao redacaoDao, UsuarioDao usuarioDao) {
    this.redacaoDao = redacaoDao;
    this.usuarioDao = usuarioDao;
  }


  public Page<Redacao> findByPageableUserId(UsuarioResponsavelHeader claims, Pageable pageable) {
    Optional<Usuario> usuario = usuarioDao.findById(claims.idUsuario());
    if (usuario.isPresent()) {
      return redacaoDao.findByUsuario(usuario.get(), pageable);
    } else {
      throw new GlypBackendException("Usuário de id '" + claims.idUsuario() + "' não encontrado.", HttpStatus.SC_NOT_FOUND);
    }
  }
}
