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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_aluno", foreignKey = @ForeignKey(name = "fk_aluno_redacao_aluno"))
  private Aluno aluno;

  @Column
  private String title;

  @Column
  private String prompt;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String text;

  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean reviewed = false;

  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean finished = false;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String tema) {
    this.prompt = tema;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isReviewed() {
    return reviewed;
  }

  public void setReviewed(boolean reviewed) {
    this.reviewed = reviewed;
  }

  public int getFinalScore() {
    return finalScore;
  }

  public void setFinalScore(int finalScore) {
    this.finalScore = finalScore;
  }

  public int getCriteriaScore1() {
    return criteriaScore1;
  }

  public void setCriteriaScore1(int criteriaScore1) {
    this.criteriaScore1 = criteriaScore1;
  }

  public int getCriteriaScore2() {
    return criteriaScore2;
  }

  public void setCriteriaScore2(int criteriaScore2) {
    this.criteriaScore2 = criteriaScore2;
  }

  public int getCriteriaScore3() {
    return criteriaScore3;
  }

  public void setCriteriaScore3(int criteriaScore3) {
    this.criteriaScore3 = criteriaScore3;
  }

  public int getCriteriaScore4() {
    return criteriaScore4;
  }

  public void setCriteriaScore4(int criteriaScore4) {
    this.criteriaScore4 = criteriaScore4;
  }

  public int getCriteriaScore5() {
    return criteriaScore5;
  }

  public void setCriteriaScore5(int criteriaScore5) {
    this.criteriaScore5 = criteriaScore5;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public void computeAndSetFinalScore() {
    this.finalScore =
      this.criteriaScore1 + this.criteriaScore2 + this.criteriaScore3 + this.criteriaScore4 + this.criteriaScore5;
  }

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }
}
