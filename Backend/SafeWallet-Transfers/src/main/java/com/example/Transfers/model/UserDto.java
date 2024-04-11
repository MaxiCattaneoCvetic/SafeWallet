package com.example.Transfers.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Document("users_account")
public class UserDto {

    @Id
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String cbu;
    private String alias;
    private Double balance;
    private Boolean welcomeGift;
    private List<UserTransactionsDto> transactions;

    private List<Card> cards;

    private String cvu;


    //full constructor
    public UserDto(String name,String email,String lastName , String cbu, String alias, Double balance, Boolean welcomeGift,String cvu) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.cbu = cbu;
        this.alias = alias;
        this.balance = balance;
        this.cvu = cvu;
    }



    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", cbu='" + cbu + '\'' +
                ", alias='" + alias + '\'' +
                ", balance=" + balance +
                ", welcomeGift=" + welcomeGift +
                ", transactions=" + transactions +
                ", cards=" + cards +
                ", cvu='" + cvu + '\'' +
                '}';
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserTransactionsDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<UserTransactionsDto> transactions) {
        this.transactions = transactions;
    }

    public UserDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getWelcomeGift() {
        return welcomeGift;
    }

    public void setWelcomeGift(Boolean welcomeGift) {
        this.welcomeGift = welcomeGift;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }
}
