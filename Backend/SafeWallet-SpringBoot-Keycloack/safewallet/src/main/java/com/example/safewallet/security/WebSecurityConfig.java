package com.example.safewallet.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    public static final String ADMIN = "admin";
    public static final String USER = "user";



    @Autowired
    private JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                //el request recibo 3 parametros, el metodo, el recurso que se quiere consumir y el permiso que validamos, en este caso **
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST).permitAll());
        http.csrf(csrf -> csrf.disable());


//                .requestMatchers(HttpMethod.GET, "/test/admin","/test/admin/**").hasRole(ADMIN)
//                .requestMatchers(HttpMethod.GET, "/test/user","/test/user/**").hasAnyRole(USER,ADMIN)

        // aca consiguramos el converter, vamos a tomar toda la informacion desde nuestro jwt
        http.oauth2ResourceServer( oauth2ResourceServer->
                oauth2ResourceServer
                .jwt( jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthConverter)));
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }



}
