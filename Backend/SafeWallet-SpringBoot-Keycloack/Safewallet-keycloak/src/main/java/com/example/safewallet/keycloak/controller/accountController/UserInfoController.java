package com.example.safewallet.keycloak.controller.accountController;

import com.example.safewallet.keycloak.DTO.UserDto;
import com.example.safewallet.keycloak.implementation.service.IkeyCloakService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserInfoController {

    private IkeyCloakService keycloakService;


    @GetMapping("/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(keycloakService.searchUserByUserName(username));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDto userDTO){
        keycloakService.updateUser(userId, userDTO);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        keycloakService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
