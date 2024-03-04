package com.safewallet.userDataService.service.serviceInterface;

import com.safewallet.userDataService.exception.MessageException;
import com.safewallet.userDataService.model.UpdatesModel;
import com.safewallet.userDataService.model.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();
    void createUser(UserDto userDto) throws Exception;

    UserDto findByUsername(String username) throws MessageException;

    void deleteUser(String email) throws Exception;

    UserDto findById(Long id);

    void updateUser(UserDto userDto, UpdatesModel updatesModel, String oldEmail);

    UserDto findByCbu(String cbu);
    UserDto findByAlias(String alias);

    List<?> findUser(String username);





}

