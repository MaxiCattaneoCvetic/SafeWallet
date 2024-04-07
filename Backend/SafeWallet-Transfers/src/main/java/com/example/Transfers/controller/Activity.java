package com.example.Transfers.controller;


import com.example.Transfers.model.UserDto;
import com.example.Transfers.model.UserTransactionsDto;
import com.example.Transfers.service.transfer.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class Activity {

    @Autowired
    private TransferService transferService;


    @Autowired
    public Activity(TransferService transferService) {
        this.transferService = transferService;
    }


    /*    @GetMapping("/{id}/activity")
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

    }*/


/*    @GetMapping("/{id}/activity") // this is a default filter
    public ResponseEntity<?> getTransactionsOrderByDate(@PathVariable Long id, HttpServletRequest request,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {

        // Si no esta el header Authorization devolvemos un error de autorización
        if (request.getHeader("Authorization") == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }

        try {
            UserDto userDto = transferService.findUserById(id);
            if (userDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay transacciones para este usuario");
            }

            //Obtenemos la lista de transacciones del usuario.
            List<UserTransactionsDto> transactions = userDto.getTransactions();
            transactions.sort(Comparator.comparing(UserTransactionsDto::getDate).reversed());

            //transactions.sort(Comparator.comparing(UserTransactionsDto::getAmount));

            //-----Comenzamos a generar la paginacion----

            //definimos el punto de partida.
            *//* Si page es 0 entonces comenzamos desde el inicio, si es 0 empieza desde el inicio hasta el size*//*
            int start = page * size;

            // definimos el fin de la paginacion
            // Math.min es para que el fin sea el minimo que el tamaño de la lista
            int end = Math.min(start + size, transactions.size());
            if (start > end) {
               new PageImpl<>(transactions.subList(start, end), PageRequest.of(page, size), transactions.size());
            }

            PageImpl<UserTransactionsDto> userTransactionsDtos = new PageImpl<>(transactions.subList(start, end), PageRequest.of(page, size), transactions.size());


            return ResponseEntity.status(HttpStatus.OK).body(userTransactionsDtos);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay transacciones para este usuario");
        }


    }*/


    @GetMapping("/{id}/activity")
    public ResponseEntity<?> getTransactionsOrderByAmount(@PathVariable Long id, HttpServletRequest request,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size,
                                                          @RequestParam(defaultValue = "0") int filterNumber
                                                          //@RequestParam(defaultValue = "0") int amount)
    )


    {

/*
        if(filterNumber > 5 || filterNumber <= 0){
            ResponseEntity<?> transactionsOrderByDate = getTransactionsOrderByDate(id, request, page, size);
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(transactionsOrderByDate);
        }
*/

        // Si no esta el header Authorization devolvemos un error de autorización
        if (request.getHeader("Authorization") == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }

        try {
            UserDto userDto = transferService.findUserById(id);
            if (userDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay transacciones para este usuario");
            }

            /*
             * FilterNumber Options
             *  1 = 0 - 1000
             *  2 = 1000 - 5000
             *  3 = 5000 - 20000
             *  4 = 20000 - 100000
             *  5 = > 100000
             *  6 = ingresos
             *  7 = egresos
             * */

            //Obtenemos la lista de transacciones del usuario.
            List<UserTransactionsDto> transactions = userDto.getTransactions();
            transactions.sort(Comparator.comparing(UserTransactionsDto::getDate).reversed());





            switch (filterNumber) {
                case 0 -> {
                    break;
                }
                case 1 -> {
                    transactions = transactions.stream()
                            .sorted(Comparator.comparing(UserTransactionsDto::getAmount))
                            .filter(transaction -> transaction.getAmount() >= 0 && transaction.getAmount() <= 1000)
                            .collect(Collectors.toList());
                }
                case 2 -> {
                    transactions = transactions.stream()
                            .sorted(Comparator.comparing(UserTransactionsDto::getAmount))
                            .filter(transaction -> transaction.getAmount() >= 1000 && transaction.getAmount() <= 5000)
                            .collect(Collectors.toList());

                }
                case 3 -> {
                    transactions = transactions.stream()
                            .sorted(Comparator.comparing(UserTransactionsDto::getAmount))
                            .filter(transaction -> transaction.getAmount() >= 5000 && transaction.getAmount() <= 20000)
                            .collect(Collectors.toList());

                }
                case 4 -> {
                    transactions = transactions.stream()
                            .sorted(Comparator.comparing(UserTransactionsDto::getAmount))
                            .filter(transaction -> transaction.getAmount() >= 20000 && transaction.getAmount() <= 100000)
                            .collect(Collectors.toList());

                }
                case 5 -> {
                    transactions = transactions.stream()
                            .sorted(Comparator.comparing(UserTransactionsDto::getAmount))
                            .filter(transaction -> transaction.getAmount() >= 100000)
                            .collect(Collectors.toList());

                }
                case 6 -> { // INGRESOS
                    transactions = transactions.stream()
                            .sorted(Comparator.comparing(UserTransactionsDto::getAmount))
                            .filter(transaction -> transaction.getAmount() > 0)
                            .collect(Collectors.toList());

                }
                case 7 -> { // EGRESOS
                    transactions = transactions.stream()
                            .sorted(Comparator.comparing(UserTransactionsDto::getAmount))
                            .filter(transaction -> transaction.getAmount() < 0)
                            .collect(Collectors.toList());

                }
//                case 8 -> {
//                    transactions.sort(Comparator.comparingInt(transaction -> (int) Math.abs(transaction.getAmount() - amount)));
//                    return ResponseEntity.status(HttpStatus.OK).body(transactions);
//                }

            }

            //-----Comenzamos a generar la paginacion----

            //definimos el punto de partida.
            /* Si page es 0 entonces comenzamos desde el inicio, si es 0 empieza desde el inicio hasta el size*/
            int start = page * size;

            // definimos el fin de la paginacion
            // Math.min es para que el fin sea el minimo que el tamaño de la lista
            int end = Math.min(start + size, transactions.size());
            if (start > end) {
                new PageImpl<>(transactions.subList(start, end), PageRequest.of(page, size), transactions.size());
            }

            PageImpl<UserTransactionsDto> userTransactionsDtos = new PageImpl<>(transactions.subList(start, end), PageRequest.of(page, size), transactions.size());


            return ResponseEntity.status(HttpStatus.OK).body(userTransactionsDtos);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay transacciones para este usuario");
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


