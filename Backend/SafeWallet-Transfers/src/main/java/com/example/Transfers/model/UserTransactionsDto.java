package com.example.Transfers.model;

import java.time.LocalDate;



public class UserTransactionsDto {


    private String from;
    private String to;
    private Double amount;
    private LocalDate date;

    private UserTransactionsDto() {
    }

    public UserTransactionsDto(String from, String to, Double amount, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }




}
