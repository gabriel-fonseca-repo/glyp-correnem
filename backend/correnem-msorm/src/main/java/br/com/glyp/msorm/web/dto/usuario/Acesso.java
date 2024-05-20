package br.com.glyp.msorm.web.dto.usuario;

import java.util.Objects;

public record Acesso(String menu, String menuUrl) {
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Acesso) obj;
    return (
      Objects.equals(this.menu, that.menu) &&
      Objects.equals(this.menuUrl, that.menuUrl)
    );
  }

  @Override
  public String toString() {
    return "Acesso[" + "menu=" + menu + ", " + "menuUrl=" + menuUrl + ']';
  }
}
