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

    @GetMapping("/{email}")
    public UserDto getUserAccount(@PathVariable String email) {
        UserDto user = null;
        try {
            user = transferService.findUserByEmail(email);
            return user;
        } catch (NullPointerException e) {
            e.getMessage();
            return null;
        }
    }

    @GetMapping("/{id}/transactions")
    public List<?> getTransactions(@PathVariable Long id) {
        UserDto userDto = transferService.findUserById(id);
        if(userDto == null){
            return null;
        }

       return userDto.getTransactions();

    }

    @PutMapping("/send")
    public ResponseEntity<?> sendMoney(@RequestBody TransferInformation transferInformation) {
        UserDto userDto = transferService.findUserByCbu(transferInformation.getCbuFrom());
        Double balance = userDto.getBalance();
        if(balance < transferInformation.getMonto()){
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

/*    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        List<UserDto> allUser = transferService.findAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(allUser);
    }*/


    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        try {
            transferService.deleteByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/claimgift/{cbu}")
    public ResponseEntity<?> getWelcomeGift(@PathVariable String cbu) throws MessageException {
        UserDto userDto = transferService.findUserByCbu(cbu);
        if(userDto !=null){
            int response = transferService.getGifts(userDto.getCbu());
            if(response == 1){
                return ResponseEntity.status(HttpStatus.OK).body("Reclamaste con éxito tu premio en Safe Wallet!! Que lo disfrutes 😎😎");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Usuario ya reclamo el premio");
            }

        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }
    }

    @GetMapping("/getcbu/{cbu}")
    public ResponseEntity<?> getUserCbu(@PathVariable String cbu) throws MessageException {
        UserDto userDto = transferService.findUserByCbu(cbu);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }






}



