package br.com.glyp.correnemusuario.dao;

import br.com.glyp.correnemmsorm.model.Instancia;
import br.com.glyp.correnemmsorm.model.Usuario;
import br.com.glyp.correnemmsorm.model.UsuarioInstancia;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioInstanciaDao
  extends JpaRepository<UsuarioInstancia, Long> {
  List<UsuarioInstancia> findAllByUsuario(Usuario usuario);

  Optional<UsuarioInstancia> findByUsuarioAndInstancia(
    Usuario usuario,
    Instancia instancia
  );
  // List<UsuarioInstancia> findAllByUsuario_CpfAndUsuario_Senha(); TODO: Testar essa forma

}
