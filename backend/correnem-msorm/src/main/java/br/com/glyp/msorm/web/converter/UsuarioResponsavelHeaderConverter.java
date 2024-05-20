package br.com.glyp.msorm.web.converter;

import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import br.com.glyp.msorm.web.enumeration.Perfil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResponsavelHeaderConverter
  implements Converter<String, UsuarioResponsavelHeader> {

  private final ObjectMapper json;

  public UsuarioResponsavelHeaderConverter() {
    this.json = new ObjectMapper();
  }

  @Override
  public UsuarioResponsavelHeader convert(@NonNull String source) {
    try {
      HashMap<String, Object> resource = json.readValue(source, HashMap.class);
      return new UsuarioResponsavelHeader(
        Objects.toString(resource.get("login")),
        Long.parseLong(Objects.toString(resource.get("idUsuario"))),
        Boolean.parseBoolean(Objects.toString(resource.get("isResponsavel"))),
        Long.parseLong(Objects.toString(resource.get("idInstancia"))),
        Objects.toString(resource.get("iss")),
        new Date(Long.parseLong(Objects.toString(resource.get("iat")))),
        new Date(Long.parseLong(Objects.toString(resource.get("exp")))),
        Perfil.valueOf(Objects.toString(resource.get("perfil")))
      );
    } catch (Exception ignored) {
      return null;
    }
  }
}
