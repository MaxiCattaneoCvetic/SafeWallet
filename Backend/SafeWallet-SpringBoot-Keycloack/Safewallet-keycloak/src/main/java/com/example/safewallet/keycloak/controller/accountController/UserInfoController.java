package com.example.safewallet.keycloak.controller.accountController;

import com.example.safewallet.keycloak.DTO.UpdateModelDto;
import com.example.safewallet.keycloak.DTO.UserDto;
import com.example.safewallet.keycloak.implementation.service.IkeyCloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class UserInfoController {

    @Autowired
    private IkeyCloakService keycloakService;


    @GetMapping("/user/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String username) {
        System.out.println("me consultaron");
        List<UserRepresentation> user = null;
        try {
            user = keycloakService.searchUserByUserName(username);
            System.out.println(user);
            if (user.isEmpty() || user.size() == 0 || user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no se encuentra.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no se encuentra.");
        }
        return ResponseEntity.ok(user);
    }


    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UpdateModelDto updateModelDto) {
        System.out.println("recibi el update" + email);
        System.out.println("recibi el update" + updateModelDto);


        try {
            List<UserRepresentation> userRepresentations = keycloakService.searchUserByUserName(email);
            if(userRepresentations.size() == 0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no se encuentra.");
            }
            String id = userRepresentations.get(0).getId();
            keycloakService.updateUser(userRepresentations, updateModelDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no se encuentra. (catch)");
        }

        return null;
    }


}
