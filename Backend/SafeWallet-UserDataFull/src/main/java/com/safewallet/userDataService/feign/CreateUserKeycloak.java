package com.safewallet.userDataService.feign;

import com.safewallet.userDataService.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="register-keycloak") // url = "localhost:8086")
public interface CreateUserKeycloak  {


    @PostMapping("/userKeycloak/create")
    void createUserKeycloak(UserDto userDto);

    @DeleteMapping("/userKeycloak/{email}")
    void deleteUserKeycloak(String email);






}
