package com.fincore.budgetapp.model;

import java.util.ArrayList;

public class BudgetEntry {
    private User user;
    private double balance;
    private ArrayList<Double> transactions;

    public BudgetEntry(User user) {
        this.user = user;
        balance = 1000;
        transactions = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction (double transaction) {
        transactions.addFirst(transaction);
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount deposited cannot be negative");
        }
        balance += amount;
        addTransaction(amount);
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount withdrawn cannot be negative");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        addTransaction(-amount);
    }
}
