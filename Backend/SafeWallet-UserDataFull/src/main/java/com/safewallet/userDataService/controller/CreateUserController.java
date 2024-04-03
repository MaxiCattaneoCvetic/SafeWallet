package com.safewallet.userDataService.controller;

import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;


@RestController
@RequestMapping("/user")
public class CreateUserController {

    @Autowired
    private UserService userService;


    public CreateUserController(UserService userService) {
        this.userService = userService;
    }

    public CreateUserController() {
    }

    @PostMapping()
    @PermitAll()
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {
        String email = userDto.getEmail();
        String dni = userDto.getDni();
        // Primero vamos a ver si existe un user- Check del DNI o el email
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email es obligatorio.");
        }
        UserDto user = userService.findByUsername(email);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con ese email.");
        }

        user = userService.findByDni(dni);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con ese DNI.");
        }
        try {
            userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDto.toString());
        } catch (Exception e) {
            //userService.deleteUser(email);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un problema con el servidor, por favor contacte con el administrador.");
        }


    }


}


