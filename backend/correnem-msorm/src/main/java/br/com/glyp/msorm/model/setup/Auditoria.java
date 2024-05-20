package br.com.glyp.msorm.model.setup;

import br.com.glyp.msorm.model.Usuario;
import br.com.glyp.msorm.web.enumeration.SituacaoAuditoria;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
public class Auditoria implements Serializable {

  private static final long serialVersionUID = -8825832608506274788L;

  @Column(insertable = false, updatable = false)
  @ColumnDefault(value = "current_timestamp")
  private OffsetDateTime dataInclusao;

  @Column(insertable = false, updatable = true)
  private OffsetDateTime dataAlteracao;

  @Column(insertable = false, updatable = true)
  private OffsetDateTime dataAlteracaoSituacao;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "IdUsuarioInclusao",
    insertable = true,
    updatable = false,
    foreignKey = @ForeignKey(name = "fk_usuario_inclusao")
  )
  private Usuario usuarioInclusao;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "IdUsuarioAlteracao",
    insertable = false,
    updatable = true,
    foreignKey = @ForeignKey(name = "fk_usuario_alteracao")
  )
  private Usuario usuarioAlteracao;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "IdUsuarioAlteracaoSituacao",
    insertable = false,
    updatable = true,
    foreignKey = @ForeignKey(name = "fk_usuario_alteracao_situacao")
  )
  private Usuario usuarioAlteracaoSituacao;

  @Column(insertable = false, length = 20)
  @ColumnDefault(value = "'ATIVO'")
  @Enumerated(EnumType.STRING)
  private SituacaoAuditoria situacao;

  public OffsetDateTime getDataInclusao() {
    return dataInclusao;
  }

  public void setDataInclusao(OffsetDateTime dataInclusao) {
    this.dataInclusao = dataInclusao;
  }

  public OffsetDateTime getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(OffsetDateTime dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
  }

  public OffsetDateTime getDataAlteracaoSituacao() {
    return dataAlteracaoSituacao;
  }

  public void setDataAlteracaoSituacao(OffsetDateTime dataAlteracaoSituacao) {
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

  public SituacaoAuditoria getSituacao() {
    return situacao;
  }

  public void setSituacao(SituacaoAuditoria situacao) {
    this.situacao = situacao;
  }
}
