package br.com.glyp.msorm.web.dto.usuario;

public record CadastrarUsuarioRequest(String nome, String cpf, String email, String senha) {
}
