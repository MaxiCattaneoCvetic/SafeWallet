package com.example.Transfers.model;

import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class UserTransactionsDto {

    @Transient
    public static final String SEQUENCE_NAME = "transactions_sequence";

    private Long id;
    private String from;
    private String to;
    private Double amount;
    private LocalDateTime date;

    private UserTransactionsDto() {
    }

    public UserTransactionsDto(Long id,String from, String to, Double amount, LocalDateTime date) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
