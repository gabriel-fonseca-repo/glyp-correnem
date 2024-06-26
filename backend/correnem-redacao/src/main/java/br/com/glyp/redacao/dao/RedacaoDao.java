package br.com.glyp.redacao.dao;

import br.com.glyp.msorm.model.Redacao;
import br.com.glyp.msorm.query.RedacaoDashboardQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RedacaoDao extends JpaRepository<Redacao, Long> {

  @Query("""
          SELECT  r.id as id,
                  r.finalScore as finalScore,
                  r.title as title,
                  a.dataInclusao as dataInclusao
          FROM    Redacao r
          JOIN    r.auditoria a
          WHERE   r.usuario.id = :idUsuario
      """)
  Page<RedacaoDashboardQuery> findByUsuario(Long idUsuario, Pageable pageable);

}
