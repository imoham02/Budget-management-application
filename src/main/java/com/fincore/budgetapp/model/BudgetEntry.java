package com.fincore.budgetapp.model;

import java.util.ArrayList;
import java.util.List;

public class BudgetEntry {
    private User user;
    private double balance;
    private List<Transaction> transactions;

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction (String type, double amount) {
        Transaction transaction = new Transaction(type, amount);
        transactions.addFirst(transaction);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount deposited has to be a positive number");
        }
        balance += amount;
        addTransaction("DEPOSIT", amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount withdrawn has to be a positive number");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        addTransaction("WITHDRAW", amount);
    }
}
