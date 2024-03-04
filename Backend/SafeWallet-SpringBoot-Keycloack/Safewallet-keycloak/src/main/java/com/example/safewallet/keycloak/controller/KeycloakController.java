package com.example.safewallet.keycloak.controller;

import com.example.safewallet.keycloak.DTO.UserDto;
import com.example.safewallet.keycloak.implementation.service.IkeyCloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        UserRepresentation userRepresentation = (UserRepresentation) keycloakService.searchUserByUserName(email);
        if(keycloakService.searchUserByUserName(email) == null){
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        String userId = userRepresentation.getId();
        keycloakService.deleteUser(userId);
        return ResponseEntity.ok("Usuario eliminado");
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



    @PostMapping("/logout/{email}")
    @PermitAll()
    public ResponseEntity<?> logout(@PathVariable String email) throws URISyntaxException {
        System.out.println("me llamaron");
        try {
            return keycloakService.logOut(email);
        }catch (Error e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un problema con el servidor, por favor contacte con el administrador.");
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
