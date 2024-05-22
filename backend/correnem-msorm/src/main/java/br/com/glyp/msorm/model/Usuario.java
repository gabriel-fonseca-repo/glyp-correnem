package br.com.glyp.msorm.model;

import br.com.glyp.msorm.model.setup.BaseModel;
import br.com.glyp.msorm.web.enumeration.Perfil;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
import java.util.Set;

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
  private String email;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String senha;

  @Column
  private Integer qtdTentativas = 0;

  @Column(updatable = false)
  private OffsetDateTime dataNascimento;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Redacao> redacoes;

  @Column(insertable = false, length = 20, nullable = false)
  @ColumnDefault(value = PERFIL_USUARIO_STRING)
  @Enumerated(EnumType.STRING)
  private Perfil perfil = Perfil.USUARIO;

}
