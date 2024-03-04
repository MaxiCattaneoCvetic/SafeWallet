package com.example.safewallet.keycloak.implementation.service;
import com.example.safewallet.keycloak.DTO.UpdateModelDto;
import com.example.safewallet.keycloak.DTO.UserDto;
import org.apache.coyote.Response;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IkeyCloakService {

    // userrepresentation es lo que representa los user en key
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUserName(String username);

    String createUser(UserDto userDto);
    void deleteUser(String userId);
    void updateUser(List<UserRepresentation> userRepresentations, UpdateModelDto updateModelDto);

    ResponseEntity<?> logOut(String username);




}
