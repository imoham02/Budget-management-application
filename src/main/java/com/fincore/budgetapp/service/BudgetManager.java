package com.fincore.budgetapp.service;

import com.fincore.budgetapp.model.BudgetEntry;
import com.fincore.budgetapp.model.Transaction;
import com.fincore.budgetapp.util.Console;
import com.fincore.budgetapp.util.TransactionComparators;
import com.fincore.budgetapp.model.User;

import java.util.ArrayList;
import java.util.List;

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
        if (viewTransactions) {
            String bankTextBlock = """
                    1. View all transactions
                    2. View all deposits
                    3. View all withdrawals
                    Please select an option (1-3):""";
            System.out.println(bankTextBlock);

            int option = Console.readInt(1, 3);
            switch (option) {
                case 1 -> listTransactions(currentAccount.getTransactions());
                case 2,3 -> filterTransactions(option);
            }
        };
    }

    public void listTransactions(List<Transaction> transactionList) {
        System.out.println("Transaction history for " + currentAccount.getUser().name());
        for (Transaction transaction : transactionList) {
            System.out.printf("%s | £%.2f | %s %n", transaction.getType().toUpperCase(), transaction.getAmount(), transaction.getDateTime());
        }
        boolean sortTransactionsList = Console.readYesNo("Would you like to see transaction history sorted by date/time or amount?");
        if (sortTransactionsList) sortTransactions(transactionList);
    }

    public void filterTransactions(int option) {
        String type = (option == 2) ? "DEPOSIT" : "WITHDRAW";
        System.out.println(type + " history for " + currentAccount.getUser().name());
        List<Transaction> transactions = currentAccount.getTransactions().stream().filter(transaction -> transaction.getType().equals(type)).toList();
        listTransactions(transactions);
    }

    public void sortTransactions(List<Transaction> transactionList) {
        List<Transaction> transactions = new ArrayList<>(transactionList);
        String bankTextBlock = """
                    1. Date/Time (ASC)
                    2. Date/Time (DESC)
                    3. Amount (ASC)
                    4. Amount (DESC)
                    Please select an option (1-4):""";
        System.out.println(bankTextBlock);

        int option = Console.readInt(1, 4);
        switch (option) {
            case 1 -> transactions.sort(TransactionComparators.byDateTime(true));
            case 2 -> transactions.sort(TransactionComparators.byDateTime(false));
            case 3 -> transactions.sort(TransactionComparators.byAmount(true));
            case 4 -> transactions.sort(TransactionComparators.byAmount(false));
        }
        listTransactions(transactions);
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
