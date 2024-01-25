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

    public UserDto(String email, String cbu, Double balance) {
        this.email = email;
        this.cbu = cbu;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", cbu='" + cbu + '\'' +
                ", balance=" + balance +
                '}';
    }
}
