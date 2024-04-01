package com.example.Transfers.controller;


import com.example.Transfers.model.UserDto;
import com.example.Transfers.model.UserTransactionsDto;
import com.example.Transfers.service.transfer.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class Activity {

    @Autowired
    private TransferService transferService;


    @Autowired
    public Activity(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/{id}/activity")
    public ResponseEntity<?> getTransactions(@PathVariable Long id, HttpServletRequest request) {

        if(request.getHeader("Authorization") == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }
        UserDto userDto = transferService.findUserById(id);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userDto.getTransactions());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }


    }

    @GetMapping("/{id}/activity/{transactionId}")
    public ResponseEntity<?> getTransactionDetail(@PathVariable Long id, @PathVariable int transactionId, HttpServletRequest request) {
        if (request.getHeader("Authorization") == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");

        }
        try {
            //chequeamos que exista el user
            UserDto userDto = transferService.findUserById(id);
            if (userDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
            }

            // si existe el user vamoos a sus transferencias y buscamos la que deseamos
            List<UserTransactionsDto> transactions = userDto.getTransactions();
            Optional<UserTransactionsDto> optionalTransaction = transactions.stream()
                    .filter(transaction -> transaction.getId() == transactionId)
                    .findFirst();


            if (optionalTransaction.isPresent()) {

                return ResponseEntity.status(HttpStatus.OK).body(optionalTransaction.get());
            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacción no encontrada");
            }


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacción no encontrada");
        }
    }


}


