package br.com.glyp.correnemusuario.dao;

import br.com.glyp.correnemmsorm.model.Menu;
import br.com.glyp.correnemmsorm.query.MontarMenuQuery;
import br.com.glyp.correnemmsorm.web.enumeration.Perfil;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaRepository<Menu, Long> {
  @Cacheable("menus_por_perfil")
  @Query(
    value = """
      SELECT DISTINCT m
      FROM Menu m
      JOIN FETCH m.menusFilhos mf
      WHERE (m.perfil = :perfil) AND (mf.perfil = :perfil) AND (m.menuPai is null)
      ORDER BY m.ordenacao
    """
  )
  List<MontarMenuQuery> findAllByPerfil(Perfil perfil);

  Optional<Menu> findByPrincipalAndPerfil(String principal, Perfil perfil);
}
