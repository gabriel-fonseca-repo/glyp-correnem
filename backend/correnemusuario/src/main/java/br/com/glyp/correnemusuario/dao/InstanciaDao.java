package br.com.glyp.correnemusuario.dao;

import br.com.glyp.correnemmsorm.model.Instancia;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanciaDao extends JpaRepository<Instancia, Long> {
  <T> Optional<T> findByNome(String nome, Class<T> type);

  @Query(
    value = """
    SELECT i.nome FROM controle_acesso.tb_instancia i
    	INNER JOIN controle_acesso.tb_usuario_instancia ui
    	ON i.id_instancia = ui.id_instancia
    	INNER JOIN controle_acesso.tb_usuario u
    	ON u.cpf = :cpf AND ui.id_usuario = u.id_usuario;
    """,
    nativeQuery = true
  )
  List<String> findAllNomeByCpfUsuario(String cpf);
}
