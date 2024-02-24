package com.safewallet.userDataService.controller;

import com.safewallet.userDataService.feign.feignService.FeignService;
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

    @Autowired
    private FeignService feignService;

    public UserController(UserService userService, FeignService feignService) {
        this.userService = userService;
        this.feignService = feignService;
    }

    @PostMapping()
    @PermitAll()
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {

        System.out.println(userDto.toString());

        // Primero vamos a ver si existe un user- Check del DNI o el email
        String email = userDto.getEmail();
        String dni = userDto.getDni();
        UserDto user = userService.findByUsername(email);
        if(user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con ese email.");
        }

        user = userService.findByDni(dni);
        if(user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con ese DNI.");
        }

        try {
            userService.createUser(userDto);
            feignService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body("usuario creado");

        }catch (Exception e ){
            userService.deleteUser(email);
            //feignService.deleteUser(email);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un problema con el servidor, por favor contacte con el administrador.");
        }


    }



}


