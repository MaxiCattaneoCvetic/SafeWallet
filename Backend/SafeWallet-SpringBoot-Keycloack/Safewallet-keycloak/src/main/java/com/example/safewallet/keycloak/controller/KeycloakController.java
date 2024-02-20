package com.example.safewallet.keycloak.controller;

import com.example.safewallet.keycloak.DTO.UserDto;
import com.example.safewallet.keycloak.implementation.service.IkeyCloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/userKeycloak")
public class KeycloakController {

    @Autowired
    private IkeyCloakService keycloakService;

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        keycloakService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

/*
    @GetMapping("/logout")
    public String logout() {
        // Invalidar la sesión actual del usuario
        SecurityContextHolder.getContext().setAuthentication(null);
        // Redirigir al endpoint de logout de Keycloak
        return "redirect:/realms/safewallet/protocol/openid-connect/logout";
    }*/



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

//    @PostMapping("/create/user/feign")
//    @PermitAll()
//    public ResponseEntity<?> createFeign(@RequestBody UserDto userDTO) throws URISyntaxException {
//        try {
//            String response = keycloakService.createUser(userDTO);
//            return ResponseEntity.ok(response);
//        }catch (Error e){
//            return ResponseEntity.badRequest().body("Usuario existente");
//        }
//    }

    @PostMapping("/logout/{email}")
    @PermitAll()
    public ResponseEntity<?> logout(@PathVariable String email) throws URISyntaxException {
        System.out.println("me llamaron");
        try {
            return keycloakService.logOut(email);
        }catch (Error e){
            return ResponseEntity.badRequest().body("ERROR EN LOGOUT");
        }
    }


    @GetMapping("/{email}")
    @PermitAll()
    public List<UserRepresentation> search(@PathVariable String email) throws URISyntaxException {
        try {
            return keycloakService.searchUserByUserName(email);
        }catch (Error e){
            return null;
        }
    }






}
