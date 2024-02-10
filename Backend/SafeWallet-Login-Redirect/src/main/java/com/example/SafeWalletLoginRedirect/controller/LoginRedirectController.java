package com.example.SafeWalletLoginRedirect.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginRedirectController {


    /*
    Este controller esta protegido por nuestro gateway + keycloak.
    En el caso de pasar la seguridad redirige al panel del usuario.
    Si no anda lanza 505
     */

    @GetMapping
    public void getMyAccount(HttpServletResponse response) throws IOException {
        try {
            response.sendRedirect("http://google.com");
        }catch (IOException e){
            e.printStackTrace();
            response.sendError(505,"Page not found");
        }


    }

}
