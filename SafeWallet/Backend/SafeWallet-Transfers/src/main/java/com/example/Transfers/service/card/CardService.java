package com.example.Transfers.service.card;

import com.example.Transfers.model.Card;
import com.example.Transfers.model.UserDto;
import com.example.Transfers.repository.ICardRepostitory;
import com.example.Transfers.repository.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService implements ICards {

    @Autowired
    private ICardRepostitory cardRepostitory;

    @Autowired
    private ITransferRepository transferRepository;


    @Override
    public Card findCardByNumber(String cardNumber) {
        Card card;
        try {
            card = cardRepostitory.findCardByCardNumber(cardNumber);
            if (card == null) {
                System.out.println("Card not found");
                return null;
            }
            return card;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int saveCard(Long id, Card card) {
        Card cardDto = findCardByNumber(card.getCardNumber());
        if (cardDto == null) {
            UserDto user = transferRepository.findUserById(id);
            if (user.getCards() == null) {
                List<Card> cards = new ArrayList<>();
                cards.add(card);
                user.setCards(cards);
                cardRepostitory.save(card);
                transferRepository.save(user);
                return 1;
            }

            user.getCards().add(card);
            cardRepostitory.save(card);
            transferRepository.save(user);
            return 1;
        }
        return 0;
    }

    @Override
    public void deleteCard(Long id, Card card) {
        try {
            UserDto user = transferRepository.findUserById(id);
            List<Card> cards = user.getCards();
            for (Card c: cards) {
                if (c.getCardNumber().equals(card.getCardNumber())) {
                    cards.remove(c);
                    break;
                }
            }
            cardRepostitory.deleteCardByCardNumber(card.getCardNumber());
            transferRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Card> getAllCards(Long id) {
        try {
            UserDto user = transferRepository.findUserById(id);
            return user.getCards();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
