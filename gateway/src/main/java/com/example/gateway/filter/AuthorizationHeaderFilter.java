package com.example.gateway.filter;

import com.example.gateway.config.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@Slf4j

public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>{
    public String SECRET = "eyJhbGciOiJIUzI1NiJ9.aGVsbG8gd29ybGQ.-aGmFI1PnL6hrGpVCapG31O5FZKeo6K-w09wY-GIh8o";
    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    public static class Config {
        // application.yml 파일에서 지정한 filer의 Argument값을 받는 부분
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }
//            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//            String jwt = authorizationHeader.replace("Bearer", "");
//            if (!isJwtValid(jwt)) {
//                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
//            }
            return chain.filter(exchange);
        };
    }
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }
    private boolean isJwtValid(String jwt) {
        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return !Strings.isBlank(subject);
    }
}
