package com.example.safewallet.keycloak.controller;

import com.example.safewallet.keycloak.DTO.UserDto;
import com.example.safewallet.keycloak.implementation.service.IkeyCloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/userKeycloak")
public class KeycloakController {

    @Autowired
    private IkeyCloakService keycloakService;


    @GetMapping("/all")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }
    @GetMapping("/test")
    public ResponseEntity<?> testKeyloack(){
        return ResponseEntity.ok().body("Hello Keycloak");
    }


    @PostMapping("/create")
    @PermitAll()
    public ResponseEntity<?> createUser(@RequestBody UserDto userDTO) throws URISyntaxException {
        try {
            String response = keycloakService.createUser(userDTO);
            return ResponseEntity.ok(response);
        }catch (Error e){
            return ResponseEntity.badRequest().body("Usuario existente");
        }
    }





}
