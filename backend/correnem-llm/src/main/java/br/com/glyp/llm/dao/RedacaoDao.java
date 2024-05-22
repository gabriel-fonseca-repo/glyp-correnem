package br.com.glyp.llm.dao;

import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedacaoDao extends JpaRepository<Redacao, Long> {

  Optional<Redacao> findByUsuario(Usuario usuario);

}
