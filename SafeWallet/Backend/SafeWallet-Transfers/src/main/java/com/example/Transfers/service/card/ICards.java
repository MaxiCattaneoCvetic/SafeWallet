package com.example.Transfers.service.card;

import com.example.Transfers.model.Card;

import java.util.List;

public interface ICards {

    Card findCardByNumber(String number);

    int saveCard(Long id, Card card);

    void deleteCard(Long id, Card card);


    List<Card> getAllCards(Long id);

}
