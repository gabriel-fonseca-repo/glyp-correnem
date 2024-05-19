package br.com.glyp.correnemmsorm.model.setup;

import br.com.glyp.correnemmsorm.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
public class Auditoria implements Serializable {

  private static final long serialVersionUID = -8825832608506274788L;

  @Column(insertable = false, updatable = false)
  @ColumnDefault(value = "current_timestamp")
  private LocalDateTime dataInclusao;

  @Column(insertable = false)
  private LocalDateTime dataAlteracao;

  @Column(insertable = false)
  private LocalDateTime dataAlteracaoSituacao;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdUsuarioInclusao", insertable = true, updatable = false)
  private Usuario usuarioInclusao;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdUsuarioAlteracao", insertable = false, updatable = true)
  private Usuario usuarioAlteracao;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "IdUsuarioAlteracaoSituacao",
    insertable = false,
    updatable = true
  )
  private Usuario usuarioAlteracaoSituacao;

  @Column(insertable = false, length = 2)
  @ColumnDefault(value = "current_timestamp")
  private String situacao;

  public LocalDateTime getDataInclusao() {
    return dataInclusao;
  }

  public void setDataInclusao(LocalDateTime dataInclusao) {
    this.dataInclusao = dataInclusao;
  }

  public LocalDateTime getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(LocalDateTime dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
  }

  public LocalDateTime getDataAlteracaoSituacao() {
    return dataAlteracaoSituacao;
  }

  public void setDataAlteracaoSituacao(LocalDateTime dataAlteracaoSituacao) {
    this.dataAlteracaoSituacao = dataAlteracaoSituacao;
  }

  public Usuario getUsuarioInclusao() {
    return usuarioInclusao;
  }

  public void setUsuarioInclusao(Usuario usuarioInclusao) {
    this.usuarioInclusao = usuarioInclusao;
  }

  public Usuario getUsuarioAlteracao() {
    return usuarioAlteracao;
  }

  public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
    this.usuarioAlteracao = usuarioAlteracao;
  }

  public Usuario getUsuarioAlteracaoSituacao() {
    return usuarioAlteracaoSituacao;
  }

  public void setUsuarioAlteracaoSituacao(Usuario usuarioAlteracaoSituacao) {
    this.usuarioAlteracaoSituacao = usuarioAlteracaoSituacao;
  }

  public String getSituacao() {
    return situacao;
  }

  public void setSituacao(String situacao) {
    this.situacao = situacao;
  }
}
