package br.com.glyp.redacao.dao;

import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedacaoDao extends JpaRepository<Redacao, Long> {

  Page<Redacao> findByUsuario(Usuario usuario, Pageable pageable);

}
