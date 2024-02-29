package com.safewallet.userDataService.service.serviceInterface;

import com.safewallet.userDataService.model.UserDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();
    void createUser(UserDto userDto) throws Exception;

    UserDto findByUsername(String username);

    void deleteUser(String email) throws Exception;

    UserDto findById(Long id);

    void updateUser(UserDto userDto);
    UserDto findByCbu(String cbu);
    UserDto findByAlias(String alias);





}

