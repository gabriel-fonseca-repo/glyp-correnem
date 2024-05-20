package br.com.glyp.gateway.config;

import br.com.glyp.gateway.exception.AuthGatewayFilterException;
import br.com.glyp.msorm.web.GlypCookies;
import br.com.glyp.msorm.web.GlypHeaders;
import br.com.glyp.msorm.web.dto.jwt.JwtValidationResponse;
import br.com.glyp.msorm.web.enumeration.ResponseAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class AuthGatewayFilter extends AbstractGatewayFilterFactory<AuthGatewayFilter.Config> {

  private static final Logger log = Logger.getLogger(AuthGatewayFilter.class);

  private final ObjectMapper json;
  private final List<Pattern> rotaPatterns;
  private final WebClient.Builder wcBuilder;
  private final String jwtValidationUri;

  public AuthGatewayFilter(@Value("${rotas.publicas}") List<String> rotas, @Value("${rotas.jwt.verification}") String jwtValidationUri, ObjectMapper json, WebClient.Builder wcBuilder) {
    super(Config.class);
    this.rotaPatterns = rotas.stream().map(Pattern::compile).toList();
    this.jwtValidationUri = jwtValidationUri;
    this.json = json;
    this.wcBuilder = wcBuilder;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      try {
        if (ehRotaPublica(exchange)) return chain.filter(exchange);
        else return authFilter(exchange, chain);
      } catch (AuthGatewayFilterException webException) {
        return escreverRespostaDeErro(exchange, webException);
      }
    };
  }

  private boolean ehRotaPublica(ServerWebExchange exchange) {
    for (Pattern pttn : this.rotaPatterns) {
      if (pttn.asMatchPredicate().test(exchange.getRequest().getPath().toString())) {
        return true;
      }
    }
    return false;
  }

  public Mono<Void> authFilter(ServerWebExchange exchange, GatewayFilterChain chain) throws AuthGatewayFilterException {
    return requestValidacaoMsUsuario(pegarJwtDaRequisicao(exchange.getRequest().getCookies())).flatMap(response -> modificarRepassarRequisicao(response, exchange, chain)).onErrorResume(AuthGatewayFilterException.class, error -> escreverRespostaDeErro(exchange, error));
  }

  private String pegarJwtDaRequisicao(MultiValueMap<String, HttpCookie> cookies) throws AuthGatewayFilterException {
    if (!cookies.containsKey(GlypCookies.JWT)) {
      throw new AuthGatewayFilterException("Credenciais de autenticação ausêntes. Faça login novamente.", ResponseAction.LOGOUT, HttpStatus.UNAUTHORIZED);
    }
    return cookies.getFirst(GlypCookies.JWT).getValue();
  }

  public Mono<ResponseEntity<JwtValidationResponse>> requestValidacaoMsUsuario(String jwt) {
    return wcBuilder.build().post().uri(jwtValidationUri).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(Map.of("token", jwt))).retrieve().toEntity(JwtValidationResponse.class).onErrorMap(WebClientResponseException.class, this::mapearErroAutenticacao);
  }

  private Throwable mapearErroAutenticacao(WebClientResponseException errorResponse) {
    new Thread(() -> log.error("Erro na validação de token.", errorResponse)).start();
    JwtValidationResponse respostaValidacaoErro = errorResponse.getResponseBodyAs(JwtValidationResponse.class);
    return new AuthGatewayFilterException(HttpStatus.resolve(errorResponse.getStatusCode().value()), respostaValidacaoErro.getBaseResponse());
  }

  public Mono<Void> modificarRepassarRequisicao(ResponseEntity<JwtValidationResponse> response, ServerWebExchange exchange, GatewayFilterChain chain) {
    exchange.getRequest().mutate().header(GlypHeaders.CLAIMS_USUARIO, response.getBody().getClaims()).header(HttpHeaders.COOKIE, null, null).header(HttpHeaders.SET_COOKIE, null, null);
    return chain.filter(exchange);
  }

  public Mono<Void> escreverRespostaDeErro(ServerWebExchange exchange, AuthGatewayFilterException wfe) {
    byte[] bytes;
    try {
      bytes = json.writeValueAsBytes(Map.of("message", wfe.getMessage(), "action", wfe.getAction()));
    } catch (JsonProcessingException e) {
      bytes = """
            {
              "message": "Erro interno do servidor. Tente novamente.",
              "action": "NONE"
            }
          """.getBytes();
      wfe.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
    exchange.getResponse().setStatusCode(wfe.getStatus());
    return exchange.getResponse().writeWith(Flux.just(buffer));
  }

  protected static class Config {
  }
}
