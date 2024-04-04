package com.example.Transfers.controller;


import com.example.Transfers.model.Card;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.service.card.CardService;
import com.example.Transfers.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class CardsController {


    @Autowired
    private CardService cardService;

    @Autowired
    private TransferService transferService;


    @PostMapping("/{id}/cards")
    public ResponseEntity<?> createCard(@PathVariable Long id, @RequestBody Card card) {
        if (card.getCardNumber() == null || card.getCardType() == null
                || card.getCvv() == null || card.getExpirationDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
        }
        try {
            int response = cardService.saveCard(id, card);
            if (response == 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Card already exists");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Card created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}/cards")
    public ResponseEntity<?> deleteCard(@PathVariable Long id, @RequestBody Card card) {
        if (cardService.findCardByNumber(card.getCardNumber()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }
        try {
            cardService.deleteCard(id, card);
            return ResponseEntity.status(HttpStatus.OK).body("Card deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @GetMapping("/{id}/cards/{cardNumber}")
    public ResponseEntity<?> getCard(@PathVariable Long id, @PathVariable String cardNumber) {
        if (transferService.findUserById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        if (cardService.findCardByNumber(cardNumber) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(cardService.findCardByNumber(cardNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<?> getUserCards(@PathVariable Long id) {
        if (transferService.findUserById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cardService.getAllCards(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/{data}/user")
    public ResponseEntity<?> getUserCards(@PathVariable String data) {
        UserDto userDto = transferService.findUserForTransfer(data);

        if (userDto != null) {
            String fullName = userDto.getName().substring(0, 1).toUpperCase() + userDto.getName().substring(1) +
                    " " + userDto.getLastName().substring(0,1).toUpperCase() + userDto.getLastName().substring(1);
            return ResponseEntity.status(HttpStatus.OK).body(fullName);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cuenta inexistente.");
        }

    }

}
