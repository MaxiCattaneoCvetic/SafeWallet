package com.example.Transfers.controller;

import com.example.Transfers.model.TransferInformation;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/balance")
    public Double getBalance(@RequestParam Long id) {
        Double balance = transferService.getSaldo(id);
        if (balance > 0) {
            return balance;
        } else {
            return 0.0;
        }
    }

//    @PutMapping
//    public ResponseEntity<?> sendMoney(@RequestBody TransferInformation transferInformation) {
//        try {
//            String from = transferInformation.getCbuFrom();
//            String to = transferInformation.getCbuTo();
//            Double monto = transferInformation.getMonto();
//
//
//            transferService.sendMoney(monto, from, to);
//
//
//            return ResponseEntity.status(HttpStatus.OK).body("Transferencia realizada con exito");
//        } catch (Exception e) {
//            // Si hay un error, responde con un código de error apropiado y un mensaje opcional
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la transferencia: " + e.getMessage());
//        }
//    }
}





