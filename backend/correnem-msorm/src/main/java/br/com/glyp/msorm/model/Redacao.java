package br.com.glyp.msorm.model;

import br.com.glyp.msorm.model.setup.BaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_redacao", schema = "conteudo")
public class Redacao extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_redacao")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_usuario_redacao_usuario"))
  private Usuario usuario;

  @Column
  private String title;

  @Column
  private String tema;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String text;

  @Column
  private int finalScore;

  @Column
  private int criteriaScore1;

  @Column
  private int criteriaScore2;

  @Column
  private int criteriaScore3;

  @Column
  private int criteriaScore4;

  @Column
  private int criteriaScore5;

  @Column(columnDefinition = "TEXT")
  private String comments;

}
