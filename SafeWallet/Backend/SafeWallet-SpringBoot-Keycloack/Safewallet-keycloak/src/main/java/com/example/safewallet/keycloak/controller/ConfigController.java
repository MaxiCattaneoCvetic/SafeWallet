/*
package com.example.safewallet.keycloak.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("safewallet/config")
@RefreshScope //Refresca los todos los datos ARROBADOS CON @VALUE cuando hagamos una peticion AL ACTUATOR REFRESH
public class ConfigController {
    // Le decimos que vamos a inyectar el valor de una prop que se llamara app.testProp
    @Value("${app.testProp}")
    private String testProp;


    @GetMapping("test-prop")
    @PermitAll
    public String getTestProp() {
        return this.testProp;
    }

    public void setTestProp(String testProp) {
        this.testProp = testProp;
    }


}
*/
