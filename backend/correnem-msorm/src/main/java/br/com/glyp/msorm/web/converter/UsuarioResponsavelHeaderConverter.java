package br.com.glyp.msorm.web.converter;

import br.com.glyp.msorm.web.UsuarioResponsavelHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Component
public class UsuarioResponsavelHeaderConverter implements Converter<String, UsuarioResponsavelHeader> {

  private final ObjectMapper json;

  public UsuarioResponsavelHeaderConverter() {
    this.json = new ObjectMapper();
  }

  @Override
  public UsuarioResponsavelHeader convert(@NonNull String source) {
    try {
      HashMap<String, Object> resource = json.readValue(source, HashMap.class);
      System.out.println(resource);
      return new UsuarioResponsavelHeader(
          Long.parseLong(Objects.toString(resource.get("idUsuario"))),
          Objects.toString(resource.get("nome")),
          Objects.toString(resource.get("email")),
          Objects.toString(resource.get("iss")),
          new Date(Long.parseLong(Objects.toString(resource.get("iat")))),
          new Date(Long.parseLong(Objects.toString(resource.get("exp"))))
      );
    } catch (Exception ignored) {
      return null;
    }
  }
}
