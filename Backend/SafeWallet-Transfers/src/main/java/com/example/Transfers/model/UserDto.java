package com.example.Transfers.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("users_account")
public class UserDto {

    @Id
    private long id;
    private String email;

    private String cbu;
    private Double balance;
    private Boolean welcomeGift;


    public UserDto(String email, String cbu, Double balance, Boolean welcomeGift) {
        this.email = email;
        this.cbu = cbu;
        this.balance = balance;
        this.welcomeGift = welcomeGift;
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

}
