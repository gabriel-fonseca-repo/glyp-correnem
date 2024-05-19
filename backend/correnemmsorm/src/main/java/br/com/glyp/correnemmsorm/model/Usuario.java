package br.com.glyp.correnemmsorm.model;

import br.com.glyp.correnemmsorm.model.setup.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.util.Date;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "tb_usuario", schema = "controle_acesso")
public class Usuario extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Long id;

  @Column(length = 14, nullable = false)
  private String cpf;

  @Column(length = 1, nullable = false)
  private String perfil;

  @Column(name = "data_nascimento", nullable = false)
  private Date dataNascimento;

  @Column(length = 60)
  @ColumnTransformer(write = "upper(?)")
  private String email;

  @Column(nullable = false, unique = true, length = 22)
  @ColumnTransformer(write = "upper(?)")
  private String login;

  @Column(nullable = false, length = 60)
  @ColumnTransformer(write = "upper(?)")
  private String nome;

  @Column(length = 100)
  private String senha;

  @Column(
    name = "qtd_tentativas",
    nullable = false,
    columnDefinition = "bigint default 0"
  )
  private Integer qtdTentativas;

  @Column(length = 60)
  private String ip;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idInstancia")
  @JoinColumn(name = "id_instancia", referencedColumnName = "id_instancia")
  private Instancia instancia;

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

  public String getPerfil() {
    return perfil;
  }

  public void setPerfil(String perfil) {
    this.perfil = perfil;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
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

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Instancia getInstancia() {
    return instancia;
  }

  public void setInstancia(Instancia instancia) {
    this.instancia = instancia;
  }
}
