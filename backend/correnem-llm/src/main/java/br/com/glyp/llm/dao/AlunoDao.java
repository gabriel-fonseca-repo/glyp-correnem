package br.com.glyp.llm.dao;

import br.com.glyp.msorm.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoDao extends JpaRepository<Aluno, Long> {

  Optional<Aluno> findByNome(String nome);

}
