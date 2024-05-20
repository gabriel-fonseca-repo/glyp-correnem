package br.com.glyp.msorm.model.setup;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreType
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
