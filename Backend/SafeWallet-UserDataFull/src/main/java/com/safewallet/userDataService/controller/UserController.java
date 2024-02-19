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
import java.util.Set;

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
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {


        try {
            userService.createUser(userDto);
            feignService.createUser(userDto);
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

}


