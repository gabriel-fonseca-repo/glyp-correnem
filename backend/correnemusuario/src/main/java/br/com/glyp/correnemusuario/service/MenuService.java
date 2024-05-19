package br.com.glyp.correnemusuario.service;

import br.com.glyp.correnemmsorm.model.Menu;
import br.com.glyp.correnemmsorm.web.enumeration.Perfil;
import br.com.glyp.correnemusuario.dao.MenuDao;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

  private final MenuDao menuDao;

  public MenuService(MenuDao menuDao) {
    this.menuDao = menuDao;
  }

  public Optional<Menu> findByPrincipalAndPerfil(
    String principal,
    Perfil perfil
  ) {
    return menuDao.findByPrincipalAndPerfil(principal, perfil);
  }
}
