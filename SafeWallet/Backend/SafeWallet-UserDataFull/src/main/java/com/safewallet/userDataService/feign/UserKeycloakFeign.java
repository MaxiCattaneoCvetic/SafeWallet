package com.safewallet.userDataService.feign;

import com.safewallet.userDataService.model.UpdatesModel;
import com.safewallet.userDataService.model.UserDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "register-keycloak")
public interface UserKeycloakFeign {


    @PostMapping("/userKeycloak/create")
    void createUserKeycloak(UserDto userDto);

    @DeleteMapping("/userKeycloak/{email}")
    void deleteUserKeycloak(String email);

    @GetMapping("/account/user/{username}")
    List<UserRepresentation> getUserKeycloak(@PathVariable String username);

    @PutMapping("/account/update/{email}")
    void updateUserKeycloack(@PathVariable("email") String email, @RequestBody UpdatesModel updatesModel);









}
