package com.example.GateWay.ApiGateWayConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
//configuracion de los filtros del gateway
// lo que hago es ver si el cliente esta autenticado, si este no lo esta redirigo a el login *A
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity http){
           http
                   //Si el user esta autenticado lo dejamos pasar, aun no estamos validando roles
                   .authorizeExchange()
                   .anyExchange()
                   .authenticated()
                   .and()
                   .oauth2Login(); // *A SI EL USUER NO ESTA AUTENTICADO LO REDIRIGO AL LOGIN
           return http.build();

    }
}
