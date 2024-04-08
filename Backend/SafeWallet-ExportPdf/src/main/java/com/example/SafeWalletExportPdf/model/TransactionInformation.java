package com.example.SafeWalletExportPdf.model;

import java.time.LocalDateTime;

public class TransactionInformation {

    private String nameFrom;
    private String accountFrom;
    private String nameTo;
    private String accountTo;
    private Double amount;
    private LocalDateTime date;
    private String detail;

    public TransactionInformation(String nameFrom, String accountFrom, String nameTo, String accountTo, Double amount, LocalDateTime date, String detail) {
        this.nameFrom = nameFrom;
        this.accountFrom = accountFrom;
        this.nameTo = nameTo;
        this.accountTo = accountTo;
        this.amount = amount;
        this.date = date;
        this.detail = detail;
    }


    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}


