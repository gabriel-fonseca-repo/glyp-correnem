package br.com.glyp.usuario.clirunner;

import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class CreateAdminUserRunner implements CommandLineRunner {

  private final String email;

  private final String nome;

  private final String senha;

  private final UsuarioService usuarioService;

  public CreateAdminUserRunner(
    @Value("${ADM_USER_EMAIL}") String email,
    @Value("${ADM_USER_NAME}") String nome,
    @Value("${ADM_USER_PASSWD}") String senha,
    UsuarioService usuarioService
  ) {
    this.email = email;
    this.nome = nome;
    this.senha = senha;
    this.usuarioService = usuarioService;
  }

  @Override
  public void run(String... args) throws Exception {

    if (usuarioService.isEmailJaCadastrado(this.email)) {
      Usuario usuario = usuarioService.consultarUsuarioPorEmail(this.email).get();
      usuario.setNome(this.nome);
      usuario.setSenha(BCrypt.hashpw(this.senha, BCrypt.gensalt()));
      usuarioService.save(usuario);
      return;
    }

    Usuario novoUsuario = new Usuario();
    novoUsuario.setEmail(this.email);
    novoUsuario.setNome(this.nome);
    novoUsuario.setSenha(BCrypt.hashpw(this.senha, BCrypt.gensalt()));

    this.usuarioService.save(novoUsuario);
  }

}
