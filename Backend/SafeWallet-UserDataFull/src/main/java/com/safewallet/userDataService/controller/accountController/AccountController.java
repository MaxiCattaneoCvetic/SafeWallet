package com.safewallet.userDataService.controller.accountController;

import com.netflix.discovery.converters.Auto;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.service.UserService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/useraccount")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsername(username));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?>  findAllUsers() {
        List<UserDto> listUser = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listUser);

    }


}
