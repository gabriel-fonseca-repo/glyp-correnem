package br.com.glyp.correnemusuario.dao;

import br.com.glyp.correnemmsorm.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
  <T> Optional<T> findByCpf(String login, Class<T> type);
}
