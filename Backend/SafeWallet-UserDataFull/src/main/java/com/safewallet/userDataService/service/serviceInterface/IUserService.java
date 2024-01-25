package com.safewallet.userDataService.service.serviceInterface;

import com.safewallet.userDataService.model.UserDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();
    void createUser(UserDto userDto) throws Exception;

    ResponseEntity<?> findByUsername(String username);

}

