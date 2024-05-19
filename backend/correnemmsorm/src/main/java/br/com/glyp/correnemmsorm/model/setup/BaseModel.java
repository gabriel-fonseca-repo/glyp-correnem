package br.com.glyp.correnemmsorm.model.setup;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {

  @Embedded
  private Auditoria auditoria;

  public Auditoria getAuditoria() {
    return auditoria;
  }

  public void setAuditoria(Auditoria auditoria) {
    this.auditoria = auditoria;
  }
}
