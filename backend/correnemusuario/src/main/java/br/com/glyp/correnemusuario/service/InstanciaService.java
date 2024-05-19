package br.com.glyp.correnemusuario.service;

import br.com.glyp.correnemmsorm.query.IdInstanciaQuery;
import br.com.glyp.correnemusuario.dao.InstanciaDao;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class InstanciaService {

  private final InstanciaDao instanciaDao;

  public InstanciaService(InstanciaDao instanciaDao) {
    this.instanciaDao = instanciaDao;
  }

  public Optional<IdInstanciaQuery> consultarIdPorNome(String nomeInstancia) {
    return instanciaDao.findByNome(nomeInstancia, IdInstanciaQuery.class);
  }

  public List<String> obterInstanciasDoUsuario(String cpf) {
    return instanciaDao.findAllNomeByCpfUsuario(cpf);
  }
}
