package com.fincore.budgetapp.model;

import java.util.Date;

public class Transaction  {
    private String type;
    private double amount;
    private Date dateTime;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        dateTime = new Date();
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDateTime() {
        return dateTime;
    }
}

