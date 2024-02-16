package com.example.SafeWalletLoginRedirect.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/myAccount/login")
@CrossOrigin
public class LoginRedirectController {


    /*
    Este controller esta protegido por nuestro gateway + keycloak.
    En el caso de pasar la seguridad redirige al panel del usuario.
    Si no anda lanza 505
     */

    @GetMapping
    public String getMyAccount(HttpServletResponse response) throws IOException {
        System.out.println("LLEGUE ACA");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Eliminar "Bearer " del encabezado
        }
        System.out.println(token);
        // Aquí puedes realizar cualquier manipulación adicional del token si es necesario

        return token;

    }

}
