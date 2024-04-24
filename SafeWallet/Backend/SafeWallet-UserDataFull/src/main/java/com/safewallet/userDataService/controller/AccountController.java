package com.safewallet.userDataService.controller;

import com.safewallet.userDataService.exception.MessageException;
import com.safewallet.userDataService.model.UpdatesModel;
import com.safewallet.userDataService.model.UserDto;
import com.safewallet.userDataService.service.UserService;
import jakarta.ws.rs.NotFoundException;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        UserDto userDto = null;
        try {
            userDto = userService.findByUsername(username);
            if(userDto == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
            }
            return  ResponseEntity.status(HttpStatus.OK).body(userDto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
        } catch (MessageException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUsers() {
        List<UserDto> listUser = userService.findAll();
        if(listUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios");
        }

        return ResponseEntity.status(HttpStatus.OK).body(listUser);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUserData(@PathVariable Long id, @RequestBody Map<String, String> updates) {

        try {
            UserDto userDto = userService.findById(id);
            if (userDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
            }
            UpdatesModel updatesModel = new UpdatesModel();
            updatesModel.setId(id);
            updatesModel.setType(updates);
            String oldEmail = userDto.getEmail();
            for (Map.Entry<String, String> update : updates.entrySet()) {

                switch (update.getKey()) {
                    case "name":
                        userDto.setName(update.getValue());
                        break;
                    case "lastName":
                        userDto.setLastName(update.getValue());
                        break;
                    case "phone":
                        userDto.setPhone(update.getValue());
                        break;
                    case "dni":
                        if(userDto.getDni() == null || userDto.getDni().length() == 0){
                            userDto.setDni(update.getValue());
                            break;
                        }
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede modificar el DNI.");
                    case "cbu":
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede modificar el CBU.");
                    case "alias":
                        if (userService.userExists(update.getValue(), update.getKey())) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Alias ya existe.");
                        }
                        userDto.setAlias(update.getValue());
                        break;
                    case "email":
                        if (!update.getValue().contains("@")) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email no es valido.");
                        }

                        if (userService.userExists(update.getValue(), update.getKey())) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya existe.");
                        }
                        userDto.setUsername(update.getValue());
                        userDto.setEmail(update.getValue());
                        break;
                    default:
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo no válido: " + update.getKey());
                }
            }
            userService.updateUser(userDto, updatesModel, oldEmail);
            return ResponseEntity.status(HttpStatus.OK).body("Modificaste tus datos con éxito.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
        } catch (Exception e) {
            // Manejar otros posibles errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }


}
