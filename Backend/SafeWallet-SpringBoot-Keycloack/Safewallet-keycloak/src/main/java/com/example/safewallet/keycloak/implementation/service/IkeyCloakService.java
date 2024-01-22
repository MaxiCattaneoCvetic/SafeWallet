package com.example.safewallet.keycloak.implementation.service;
import com.example.safewallet.keycloak.DTO.UserDto;
import org.keycloak.representations.idm.UserRepresentation;
import java.util.List;
import java.util.Optional;

public interface IkeyCloakService {

    // userrepresentation es lo que representa los user en key
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUserName(String username);

    String createUser(UserDto userDto);
    void deleteUser(String userId);
    void updateUser(String userId, UserDto userDto);




}
