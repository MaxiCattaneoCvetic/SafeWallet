package com.example.Transfers.model;

public class TransferInformation {

    private String cbuFrom;
    private String cbuTo;
    private Double monto;

    public TransferInformation() {
    }

    public TransferInformation(String cbuFrom, String cbuTo, Double monto) {
        this.cbuFrom = cbuFrom;
        this.cbuTo = cbuTo;
        this.monto = monto;
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
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
