package br.com.glyp.llm.service;

import br.com.glyp.llm.dao.AlunoDao;
import br.com.glyp.llm.dao.RedacaoDao;
import br.com.glyp.llm.dao.UsuarioDao;
import br.com.glyp.msorm.model.Aluno;
import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

  private final UsuarioDao usuarioDao;

  private final AlunoDao alunoDao;

  private final RedacaoDao redacaoDao;

  public AlunoService(AlunoDao alunoDao, UsuarioDao usuarioDao, RedacaoDao redacaoDao) {
    this.alunoDao = alunoDao;
    this.usuarioDao = usuarioDao;
    this.redacaoDao = redacaoDao;
  }

  public Redacao cadastrarAluno(String nomeAluno, Redacao correcao, UsuarioResponsavelHeader claims) {
    Optional<Usuario> usuario = usuarioDao.findById(claims.idUsuario());
    Aluno aluno = null;

    if (usuario.isPresent()) {

      Optional<Aluno> optAluno = alunoDao.findByNome(nomeAluno);

      if (optAluno.isPresent()) {
        aluno = optAluno.get();
        aluno.getRedacoes().add(correcao);
      } else {
        aluno = new Aluno();
        aluno.setNome(nomeAluno);
        aluno.getRedacoes().add(correcao);
        aluno.setUsuario(usuario.get());
      }

      aluno = alunoDao.saveAndFlush(aluno);
      correcao.setAluno(aluno);
      return redacaoDao.saveAndFlush(correcao);
    } else {
      throw new GlypBackendException("Usuário de id '" + claims.idUsuario() + "' não encontrado.",
        HttpStatus.SC_NOT_FOUND);
    }

  }

}
