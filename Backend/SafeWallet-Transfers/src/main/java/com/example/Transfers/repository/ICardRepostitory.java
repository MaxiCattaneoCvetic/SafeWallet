package com.example.Transfers.repository;

import com.example.Transfers.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICardRepostitory extends MongoRepository<Card, String> {

    Card findCardByCardNumber(String cardNumber);

    void deleteCardByCardNumber(String cardNumber);


}
