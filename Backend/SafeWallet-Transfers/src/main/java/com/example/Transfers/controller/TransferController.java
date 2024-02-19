package com.example.Transfers.controller;

import com.example.Transfers.exception.MessageException;
import com.example.Transfers.model.TransferInformation;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
@CrossOrigin
public class TransferController {

    @Autowired
    private TransferService transferService;

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

    @GetMapping("/{email}")
    public UserDto getUserAccount(@PathVariable String email) {
        UserDto user = null;
        try {
            return user = transferService.findUserByEmail(email);
        } catch (NullPointerException e) {
            e.getMessage();
            return null;
        }


    }

    @GetMapping("/balance/{id}")
    public Double getBalance(@PathVariable Long id) {
        Double balance = transferService.getSaldo(id);
        if (balance > 0) {
            return balance;
        } else {
            return 0.0;
        }
    }

    @PutMapping("/send")
    public ResponseEntity<?> sendMoney(@RequestBody TransferInformation transferInformation) {
        if(transferInformation.getMonto() < 0 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El monto no puede ser negativo..");
        }
        String messageTransaction = new StringBuilder()
                .append("Transferencia realizada con extito")
                .append("Transferiste: $")
                .append(transferInformation.getMonto()).toString();
        try {
            String from = transferInformation.getCbuFrom();
            String to = transferInformation.getCbuTo();
            Double monto = transferInformation.getMonto();
            transferService.sendMoney(monto, from, to);
            return ResponseEntity.status(HttpStatus.OK).body(messageTransaction);
        } catch (Exception e) {
            // Si hay un error, responde con un código de error apropiado y un mensaje opcional
            String  message = new StringBuilder()
                    .append("Error en la transferencia: ")
                    .append(e.getMessage()).toString();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        List<UserDto> allUser = transferService.findAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(allUser);
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

}





