package com.example.SafeWalletGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Configuration
public class CorsGlobalFilterConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public WebFilter corsFilter(ServerHttpSecurity http) {

        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders headers = response.getHeaders();
                HttpHeaders requestHeaders = request.getHeaders();
                String origin = "https://safewallet-teal.vercel.app";

                // Verifica si hay un encabezado de autorización en la solicitud
                List<String> authorizationHeaders = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
                if (authorizationHeaders != null && !authorizationHeaders.isEmpty()) {
                    // Obtén el token de autorización
                    String token = authorizationHeaders.get(0);
                    // Imprime el token
                    System.out.println("TOKEN RECIBIDO: " + token);
                    headers.set(HttpHeaders.AUTHORIZATION, token);
                    // Puedes hacer lo que necesites con el token aquí
                } else {
                    // Si no hay un encabezado de autorización en la solicitud
                    System.out.println("No hay token en la solicitud");
                }

                // Elimina cualquier valor existente
                headers.remove(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
                // Establece el nuevo valor
                do {
                    System.out.println("seteando el header");
                    headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
                } while (headers.get(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN) == null);


                headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "OPTIONS, GET, PUT, POST, DELETE,PATCH");
                headers.set(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

                System.out.println("my headeres: " + headers.get(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(exchange);
        };
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/userKeycloak/**", "/userKeycloak/create").permitAll()
                        .pathMatchers("/user/**").permitAll()
                        .pathMatchers("/accounts/**").permitAll()
                        .pathMatchers("/doc/**").permitAll()
                        .anyExchange().permitAll()
                );

        return http.build();
    }

}