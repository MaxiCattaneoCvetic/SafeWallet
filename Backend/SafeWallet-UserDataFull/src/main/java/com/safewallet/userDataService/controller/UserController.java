package com.safewallet.userDataService.controller;

import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @PermitAll()
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        // Crea el usuario
        try {
            userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body("usuario creado");

        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El DNI o el correo ya fue registrado");
        }


    }

    @GetMapping
    public ResponseEntity<?>  findAllUsers() {
        List<UserDto> listUser = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listUser);

    }
    @GetMapping("/hola")
    public ResponseEntity<?>  testGet() {
        return ResponseEntity.ok("hola");

    }
}


