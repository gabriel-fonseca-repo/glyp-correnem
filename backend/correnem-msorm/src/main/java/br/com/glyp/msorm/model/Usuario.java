package br.com.glyp.msorm.model;

import br.com.glyp.msorm.model.setup.BaseModel;
import br.com.glyp.msorm.web.enumeration.Perfil;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_usuario", schema = "controle_acesso", uniqueConstraints = {@UniqueConstraint(name = "unqc_cpf", columnNames = {"cpf"}),})
public class Usuario extends BaseModel {

  public static final String PERFIL_USUARIO_STRING = "'USUARIO'";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Long id;

  @Column(length = 14, nullable = false)
  private String cpf;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String senha;

  @Column
  private String ip;

  @Column
  private Integer qtdTentativas = 0;

  @Column(updatable = false)
  private OffsetDateTime dataNascimento;

  @Column(insertable = false, length = 20, nullable = false)
  @ColumnDefault(value = PERFIL_USUARIO_STRING)
  @Enumerated(EnumType.STRING)
  private Perfil perfil = Perfil.USUARIO;

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

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Perfil getPerfil() {
    return perfil;
  }

  public void setPerfil(Perfil perfil) {
    this.perfil = perfil;
  }
}
