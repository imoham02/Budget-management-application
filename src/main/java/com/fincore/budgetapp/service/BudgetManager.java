package com.fincore.budgetapp.service;

import com.fincore.budgetapp.model.BudgetEntry;
import com.fincore.budgetapp.model.User;

import java.util.ArrayList;

public class BudgetManager implements IBudgetManager {
    private ArrayList<BudgetEntry> budgetAccounts;
    private BudgetEntry currentAccount;

    public BudgetManager() {
        budgetAccounts = new ArrayList<>();
    }

    public BudgetEntry getCurrentAccount() {
        return currentAccount;
    }

    public void deposit() {
        try {
            double amount = Console.readDouble("Enter deposit amount: £");
            currentAccount.deposit(amount);
            System.out.printf("Deposit successful!%nNew Balance: £%.2f%n", (currentAccount.getBalance()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void withdraw() {
        try {
            double amount = Console.readDouble("Enter withdrawal amount: £");
            currentAccount.withdraw(amount);
            System.out.printf("Withdrawal successful!%nNew Balance: £%.2f%n", (currentAccount.getBalance()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkBalance() {
        System.out.println("=== Account Balance ===");
        System.out.println("Account Holder: " + currentAccount.getUser().name());
        System.out.println("Current Balance: £" + String.format("%.2f", currentAccount.getBalance()));
        boolean viewTransactions = Console.readYesNo("Would you like to see transaction history?");
        if (viewTransactions) listTransactions();
    }

    public void listTransactions() {
        System.out.println("Transaction history for " + currentAccount.getUser().name());
        ArrayList<Double> transactions = currentAccount.getTransactions();
        for (double transaction : transactions) {
            if (transaction < 0) {
                System.out.printf("Withdrawn: £%.2f", -transaction);
                System.out.println();
            } else {
                System.out.printf("Deposited: £%.2f", transaction);
                System.out.println();
            }
        }
    }

    public void loginToBudgetAccount (String name) {
        BudgetEntry account = findBudgetAccount(name);
        if (account == null) {
            User newUser = new User(name);
            account = new BudgetEntry(newUser);
            budgetAccounts.add(account);
        }
        currentAccount = account;
    }

    public void logoutOfBudgetAccount () {
        System.out.println("Thank you for using FinCore CLI Banking. Goodbye " + currentAccount.getUser().name() + "!");
        currentAccount = null;
    }

    private BudgetEntry findBudgetAccount (String name) {
        for (BudgetEntry account : budgetAccounts) {
            if (account.getUser().name().equalsIgnoreCase(name)) {
                return account;
            }
        }
        return null;
    }
}
