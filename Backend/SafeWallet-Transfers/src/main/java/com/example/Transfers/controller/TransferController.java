package com.example.Transfers.controller;

import com.example.Transfers.exception.MessageException;
import com.example.Transfers.model.TransferInformation;
import com.example.Transfers.model.UpdatesModel;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.service.transfer.TransferService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class TransferController {

    @Autowired
    private TransferService transferService;
    private String transferencia_realizada_con_extito;
    private StringBuilder stringBuilder;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<?> createNewBalanceAccount(@RequestBody UserDto userDto) {
        try {
            transferService.createBalanceAccount(userDto);
            return ResponseEntity.status(HttpStatus.OK).body("Cuenta de usuario creada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo crear la cuenta del usuario: " + e.getMessage());
        }
    }

    @PostMapping("/claimgift/{cbu}")
    public ResponseEntity<?> getWelcomeGift(@PathVariable String cbu) throws MessageException {
        UserDto userDto = transferService.findUserByCbu(cbu);
        if (userDto != null) {
            int response = transferService.getGifts(userDto.getCbu());
            if (response == 1) {
                return ResponseEntity.status(HttpStatus.OK).body("Reclamaste con éxito tu premio en Safe Wallet!! Que lo disfrutes 😎😎");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Usuario ya reclamo el premio");
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }
    }

    @GetMapping("/{id}")
    public UserDto getUserAccount(@PathVariable Long id) {
        UserDto user = null;
        try {
            user = transferService.findUserById(id);
            return user;
        } catch (NullPointerException e) {
            e.getMessage();
            return null;
        }
    }

    @GetMapping("/getcbu/{cbu}")
    public ResponseEntity<?> getUserCbu(@PathVariable String cbu) throws MessageException {
        UserDto userDto = transferService.findUserByCbu(cbu);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


    @PutMapping("/send")
    public ResponseEntity<?> sendMoney(@RequestBody TransferInformation transferInformation) {
        UserDto userDto = transferService.findUserByCbu(transferInformation.getCbuFrom());
        Double balance = userDto.getBalance();
        if (balance < transferInformation.getMonto()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente");
        }
        if (transferInformation.getMonto() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El monto debe ser mayor a 0");
        }
        try {
            String from = transferInformation.getCbuFrom();
            String to = transferInformation.getCbuTo();
            Double amount = transferInformation.getMonto();
            transferService.sendMoney(amount, from, to);
            transferencia_realizada_con_extito = new StringBuilder()
                    .append("Transferencia realizada con extito")
                    .append("Transferiste: $")
                    .append(transferInformation.getMonto()).toString();
            String messageTransaction = transferencia_realizada_con_extito;
            return ResponseEntity.status(HttpStatus.OK).body(messageTransaction);
        } catch (Exception e) {
            System.out.println("Entre al catch");
            // Si hay un error, responde con un código de error apropiado y un mensaje opcional
            String message = stringBuilder
                    .append("Error en la transferencia: ")
                    .append(e.getMessage()).toString();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        try {
            transferService.deleteByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar el usuario: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdatesModel updates) {
        System.out.println("Entre a patch y recibi" + id);
        UserDto userDto = null;

        try {
            userDto = transferService.findUserById(id);

            if (userDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            for (Map.Entry<String, String> fieldUpdate : updates.getType().entrySet()) {

                switch (fieldUpdate.getKey()) {
                    case "name":
                        userDto.setName(fieldUpdate.getValue());
                        break;
                    case "lastName":
                        userDto.setLastName(fieldUpdate.getValue());
                        break;
                    case "email":
                        userDto.setEmail(fieldUpdate.getValue());
                        break;
/*                    case "password":
                        userDto.setPassword(fieldUpdate.getValue());
                        break;*/
                    case "cbu":
                        userDto.setCbu(fieldUpdate.getValue());
                        break;
                    case "alias":
                        userDto.setAlias(fieldUpdate.getValue());
                        break;
                    default:
                        ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo no permitido");
                        break;
                }
                transferService.updateUser(userDto);


            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo actualizar el usuario: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuario actualizado");

    }
}



