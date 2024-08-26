package br.com.glyp.msorm.model;

import br.com.glyp.msorm.model.setup.BaseModel;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_aluno", schema = "conteudo")
public class Aluno extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_aluno")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_usuario_aluno_usuario"))
  private Usuario usuario;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "aluno")
  private Set<Redacao> redacoes = new HashSet<>();

  @Column(nullable = false, unique = true)
  private String nome;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Set<Redacao> getRedacoes() {
    return redacoes;
  }

  public void setRedacoes(Set<Redacao> redacoes) {
    this.redacoes = redacoes;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
