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
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
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
    public WebFilter corsFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders headers = response.getHeaders();
                HttpHeaders requestHeaders = request.getHeaders();
                String origin = request.getHeaders().getOrigin();



              try{
                    List<String> token = headers.get(HttpHeaders.AUTHORIZATION);
                    for (String mytoken: token) {
                        headers.set(HttpHeaders.AUTHORIZATION,mytoken);
                        System.out.println("TOKEN RECIBIDO " + token);
                    }
                }catch (Exception e){
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


                headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "OPTIONS, GET, PUT, POST, DELETE");
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
                        .anyExchange().authenticated()
                        .and()
                        .oauth2Login()// Requiere autenticación para todas las demás rutas
                );

        return http.build();
    }

}






