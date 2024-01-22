package com.example.Transfers.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("users_account")
public class UserDto {

    @Id
    private long id;
    private String email;
    private String DNI;
    @Field("CBU")
    private String CBU;
    private Double balance;

    public UserDto(String email, String DNI, String CBU, Double balance) {
        this.email = email;
        this.DNI = DNI;
        this.CBU = CBU;
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

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCBU() {
        return CBU;
    }

    public void setCBU(String CBU) {
        this.CBU = CBU;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
