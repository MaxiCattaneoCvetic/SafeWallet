package com.example.Transfers.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class UserTransactionsDto {


    private String from;
    private String to;
    private Double amount;
    private LocalDateTime date;

    private UserTransactionsDto() {
    }

    public UserTransactionsDto(String from, String to, Double amount, LocalDateTime date) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }




}
