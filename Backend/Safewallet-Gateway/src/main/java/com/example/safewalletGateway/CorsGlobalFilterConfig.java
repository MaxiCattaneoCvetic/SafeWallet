package com.example.SafeWalletGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class CorsGlobalFilterConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public WebFilter corsFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders headers = response.getHeaders();

                // Elimina cualquier valor existente
                headers.remove(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);

                // Establece el nuevo valor
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:5173");
                headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
                headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "OPTIONS, GET, PUT, POST, DELETE");
                headers.set(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }

            return chain.filter(exchange);
        };
    }
}

