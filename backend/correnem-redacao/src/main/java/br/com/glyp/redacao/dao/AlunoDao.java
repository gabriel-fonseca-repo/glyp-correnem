package br.com.glyp.redacao.dao;

import br.com.glyp.msorm.model.Aluno;
import br.com.glyp.msorm.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoDao extends JpaRepository<Aluno, Long> {

  Optional<Aluno> findByNome(String nome);

  List<Aluno> findAllByUsuario(Usuario usuario);

}
