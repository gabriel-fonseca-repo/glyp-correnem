package br.com.glyp.redacao.dao;

import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.query.RedacaoDashboardQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedacaoDao extends JpaRepository<Redacao, Long> {

  @Query("""
        SELECT  r.id as id,
                r.finalScore as finalScore,
                r.title as title,
                r.finished as isFinished,
                al.nome as aluno,
                a.dataInclusao as dataInclusao
        FROM    Redacao r
        JOIN    r.auditoria a
        JOIN    r.aluno al
        WHERE   r.usuario.id = :idUsuario
    """)
  Page<RedacaoDashboardQuery> findByUsuario(Long idUsuario, Pageable pageable);

  @Query("SELECT r FROM Redacao r JOIN FETCH r.aluno WHERE r.id = :idRedacao")
  Optional<Redacao> getRedacaoWithAlunoById(@Param("idRedacao") Long idRedacao);

}
