package com.example.Transfers.model;

public class TransferInformation {

    private String cbuFrom;
    private String cbuTo;
    private Double amount;

    private String cardNumber;


    public TransferInformation() {
    }

    public TransferInformation(String cbuFrom, String cbuTo, Double amount) {
        this.cbuFrom = cbuFrom;
        this.cbuTo = cbuTo;
        this.amount = amount;

    }

    public TransferInformation(String cbuTo, Double amount, String cardNumber, String transferDetail) {
        this.cbuTo = cbuTo;
        this.amount = amount;
        this.cardNumber = cardNumber;


    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCbuFrom() {
        return cbuFrom;
    }

    public void setCbuFrom(String cbuFrom) {
        this.cbuFrom = cbuFrom;
    }

    public String getCbuTo() {
        return cbuTo;
    }

    public void setCbuTo(String cbuTo) {
        this.cbuTo = cbuTo;
    }

    public Double getMonto() {
        return amount;
    }

    public void setMonto(Double monto) {
        this.amount = monto;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }




}
