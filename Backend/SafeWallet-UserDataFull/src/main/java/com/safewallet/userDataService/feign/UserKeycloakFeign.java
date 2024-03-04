package com.safewallet.userDataService.feign;

import com.safewallet.userDataService.model.UserDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="register-keycloak") // url = "localhost:8086")
public interface UserKeycloakFeign {


    @PostMapping("/userKeycloak/create")
    void createUserKeycloak(UserDto userDto);

    @DeleteMapping("/userKeycloak/{email}")
    void deleteUserKeycloak(String email);

    @GetMapping("/account/user/{username}")
    List<UserRepresentation> getUserKeycloak(@PathVariable String username);







}
