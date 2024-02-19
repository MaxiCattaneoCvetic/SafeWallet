package com.example.safewallet.keycloak.implementation;

import com.example.safewallet.keycloak.DTO.UserDto;
import com.example.safewallet.keycloak.KeycloakProvider;
import com.example.safewallet.keycloak.implementation.service.IkeyCloakService;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class KeycloakRepository implements IkeyCloakService {



    @Override
    public List<UserRepresentation> findAllUsers() {
        return KeycloakProvider.getRealmResource().users().list();
    }

    @Override
    public List<UserRepresentation> searchUserByUserName(String username) {
        return KeycloakProvider.getRealmResource().users().searchByUsername(username, true); // el true significa que le vamos a enviar el nombre EXACTO del user y no un aprox

    }


    private UserDto fromRepresentation(UserRepresentation userRepresentation){
        /*return new UserDto(userRepresentation.getId(),userRepresentation.getUsername(),userRepresentation.getFirstName(),userRepresentation.getLastName(),userRepresentation.getEmail());*/
        return new UserDto(userRepresentation.getId(),userRepresentation.getUsername(),userRepresentation.getEmail());


    }

    @Override
    public String createUser(@NotNull UserDto userDto) {
        int status = 0; // este es el codigo http de la respuesta
        UsersResource usersResource = KeycloakProvider.getUserResource();

        //construimos el objeto
        UserRepresentation userRepresentation = new UserRepresentation(); // recordemos que los usuarios se representan de esta manera
/*        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());*/
        userRepresentation.setEmail(userDto.getEmail());
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);


        // Agregar atributos personalizados
/*        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("DNI", List.of("value1"));
        attributes.put("CBU", List.of("value2"));
        attributes.put("ALIAS", List.of("value2"));
        attributes.put("PHONE", List.of("value2"));
        userRepresentation.singleAttribute("Attributes", attributes.toString());*/


        Response response = usersResource.create(userRepresentation);
        status = response.getStatus();
        // seteamos la pw

        if (status == 201) {
            String path = response.getLocation().getPath(); // recuperamos el path de la creacion y el id del user
            String userId = path.substring(path.lastIndexOf("/") + 1); // el user se encuentra despues del ultimo /
            // Crear la credencial y asignarla al usuario
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(userDto.getPassword());

            usersResource.get(userId).resetPassword(credentialRepresentation);

            // Asignacion de roles

            RealmResource realmResource = KeycloakProvider.getRealmResource();
            List<RoleRepresentation> rolesRepresentation = null;

            if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
                rolesRepresentation = List.of(realmResource.roles().get("user").toRepresentation()); // seteamos el def user
            } else {
                rolesRepresentation = realmResource.roles() // obtengo todos los roles y convierto a un strem y los filtro
                        .list()
                        .stream()
                        .filter(role -> userDto.getRoles()
                                .stream()
                                .anyMatch(roleName -> roleName.equalsIgnoreCase(role.getName())))
                        .toList();// si alguno de los nombres coincide con el rol lo recuperamos, sino se descarta

            }
            realmResource.users().get(userId).roles().realmLevel().add(rolesRepresentation); // aca le asignamos los roles al user
            return "usuario creado";

        } else if (status == 409) {
            log.error("El DNI o el correo ya fue registrado");
            return "El DNI o el correo ya fue registrado";
        } else {
            log.error("Error creating user, please contact with the administrator.");
            return "Error creating user, please contact with the administrator.";
        }


    }

    @Override
    public void deleteUser(String userId) {
        KeycloakProvider.getUserResource().get(userId).remove();


    }

    @Override
    public void updateUser(String userId, @NotNull UserDto userDto) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(userDto.getPassword());

        //construimos el objeto
        UserRepresentation userRepresentation = new UserRepresentation(); // recordemos que los usuarios se representan de esta manera
/*        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());*/
        userRepresentation.setEmail(userDto.getEmail());
/*        userRepresentation.setUsername(userDto.getUsername());*/
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        UserResource userResource = KeycloakProvider.getUserResource().get(userId);
        userResource.update(userRepresentation);


    }
}
