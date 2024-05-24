package br.com.glyp.usuario.dao;

import br.com.glyp.msorm.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

  <T> Optional<T> findByEmail(String email, Class<T> type);

  @Query(value = """
          SELECT EXISTS (
              SELECT 1
              FROM Usuario u
              WHERE u.email = :email
          )
      """)
  boolean isEmailJaCadastrado(String email);

}
