package br.com.glyp.redacao.service;

import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.query.RedacaoDashboardQuery;
import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.dto.redacao.EditarRedacaoRequest;
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

  public Page<RedacaoDashboardQuery> findByPageableUserId(UsuarioResponsavelHeader claims, Pageable pageable) {
    Optional<Usuario> usuario = usuarioDao.findById(claims.idUsuario());
    if (usuario.isPresent()) {
      return redacaoDao.findByUsuario(usuario.get().getId(), pageable);
    } else {
      throw new GlypBackendException("Usuário de id '" + claims.idUsuario() + "' não encontrado.",
        HttpStatus.SC_NOT_FOUND);
    }
  }

  public Redacao findById(UsuarioResponsavelHeader claims, Long idRedacao) {
    Optional<Usuario> usuario = usuarioDao.findById(claims.idUsuario());
    if (usuario.isPresent()) {
      Optional<Redacao> redacao = redacaoDao.findById(idRedacao);
      if (redacao.isPresent()) {
        if (redacao.get().getUsuario().getId().equals(usuario.get().getId())) {
          return redacao.get();
        } else {
          throw new GlypBackendException("Redação de id '" + idRedacao + "' não pertence ao usuário logado.",
            HttpStatus.SC_FORBIDDEN);
        }
      } else {
        throw new GlypBackendException("Redação de id '" + idRedacao + "' não encontrado.", HttpStatus.SC_NOT_FOUND);
      }
    } else {
      throw new GlypBackendException("Usuário de id '" + claims.idUsuario() + "' não encontrado.",
        HttpStatus.SC_NOT_FOUND);
    }
  }

  public Redacao updateRedacaoById(
    UsuarioResponsavelHeader claims,
    Long idRedacao,
    EditarRedacaoRequest editRedacaoReq
  ) {
    Redacao redacao = this.findById(claims, idRedacao);
    redacao.setReviewed(true);
    redacao.setFinalScore(editRedacaoReq.redacao().finalScore());
    redacao.setCriteriaScore1(editRedacaoReq.redacao().criteriaScore1());
    redacao.setCriteriaScore2(editRedacaoReq.redacao().criteriaScore2());
    redacao.setCriteriaScore3(editRedacaoReq.redacao().criteriaScore3());
    redacao.setCriteriaScore4(editRedacaoReq.redacao().criteriaScore4());
    redacao.setCriteriaScore5(editRedacaoReq.redacao().criteriaScore5());
    redacao.setComments(editRedacaoReq.redacao().comments());
    return redacaoDao.save(redacao);
  }

  public Redacao finishRedacao(UsuarioResponsavelHeader claims, Long idRedacao) {
    Redacao redacao = this.findById(claims, idRedacao);
    redacao.setFinished(true);
    return redacaoDao.save(redacao);
  }
}
