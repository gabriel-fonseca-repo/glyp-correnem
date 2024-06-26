package br.com.glyp.msorm.model;

import br.com.glyp.msorm.model.setup.BaseModel;
import br.com.glyp.msorm.web.enumeration.Perfil;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tb_usuario", schema = "controle_acesso")
public class Usuario extends BaseModel {

  public static final String PERFIL_USUARIO_STRING = "'USUARIO'";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String senha;

  @Column
  private Integer qtdTentativas = 0;

  @Column(updatable = false)
  private OffsetDateTime dataNascimento;

  @OneToMany(
    mappedBy = "usuario",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private Set<Redacao> redacoes;

  @Column(insertable = false, length = 20, nullable = false)
  @ColumnDefault(value = PERFIL_USUARIO_STRING)
  @Enumerated(EnumType.STRING)
  private Perfil perfil = Perfil.USUARIO;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Integer getQtdTentativas() {
    return qtdTentativas;
  }

  public void setQtdTentativas(Integer qtdTentativas) {
    this.qtdTentativas = qtdTentativas;
  }

  public OffsetDateTime getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(OffsetDateTime dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public Set<Redacao> getRedacoes() {
    return redacoes;
  }

  public void setRedacoes(Set<Redacao> redacoes) {
    this.redacoes = redacoes;
  }

  public Perfil getPerfil() {
    return perfil;
  }

  public void setPerfil(Perfil perfil) {
    this.perfil = perfil;
  }
}
