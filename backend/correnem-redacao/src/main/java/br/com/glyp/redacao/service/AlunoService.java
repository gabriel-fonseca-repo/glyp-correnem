package br.com.glyp.redacao.service;

import br.com.glyp.msorm.model.Aluno;
import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import br.com.glyp.redacao.dao.AlunoDao;
import br.com.glyp.redacao.dao.UsuarioDao;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

  private final UsuarioDao usuarioDao;

  private final AlunoDao alunoDao;

  public AlunoService(AlunoDao alunoDao, UsuarioDao usuarioDao) {
    this.alunoDao = alunoDao;
    this.usuarioDao = usuarioDao;
  }

  public List<Aluno> findByUsuario(UsuarioResponsavelHeader claims) {
    Optional<Usuario> usuario = usuarioDao.findById(claims.idUsuario());
    if (usuario.isPresent()) {
      return alunoDao.findAllByUsuario(usuario.get());
    } else {
      throw new GlypBackendException("Usuário de id '" + claims.idUsuario() + "' não encontrado.",
        HttpStatus.SC_NOT_FOUND);
    }
  }
}
