package com.safewallet.userDataService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// USER FULL

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class CorsConfig implements WebMvcConfigurer {



    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/user/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/accountFull/**")).permitAll()
                )
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf().disable()  // Desactivar CSRF
                .build();
    }

}
