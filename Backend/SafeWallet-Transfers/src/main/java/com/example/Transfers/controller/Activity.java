package com.example.Transfers.controller;


import com.example.Transfers.model.UserDto;
import com.example.Transfers.service.transfer.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class Activity {

    @Autowired
    private TransferService transferService;

    @Autowired
    public Activity(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/{id}/transactions")
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
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }



    }

}
