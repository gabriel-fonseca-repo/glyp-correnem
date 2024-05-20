package br.com.glyp.usuario.service;

import br.com.glyp.msorm.query.UsuarioJwtCreationQuery;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

  private final SecretKey jwtSecret;

  private final String issuer;

  public JwtService(@Value("${hmit.jwt.secret}") String jwtSecret, @Value("${spring.application.name}") String issuer) {
    this.jwtSecret = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    this.issuer = issuer;
  }

  public Claims validarTokenJwt(String token) {
    return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
  }

  public String gerarTokenJwt(UsuarioJwtCreationQuery usuario) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("idUsuario", usuario.getId());
    claims.put("cpf", usuario.getCpf());

    return Jwts.builder().setSubject(usuario.getCpf()).setClaims(claims).setIssuer(issuer).setIssuedAt(new Date())
        .setExpiration(Date.from(LocalDateTime.now().plusMinutes(60L) // Tempo de expiração do JWT de 60 minutos.
            .atZone(ZoneId.systemDefault()).toInstant())).signWith(jwtSecret, SignatureAlgorithm.HS256).compact();
  }
}
