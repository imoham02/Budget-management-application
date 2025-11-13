package com.fincore.budgetapp;

import com.fincore.budgetapp.service.BudgetManager;
import com.fincore.budgetapp.util.Console;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to FinCore CLI Banking!");
        String name = Console.readString("Hello! What is your full name?");

        BudgetManager accountManager = new BudgetManager();
        accountManager.loginToBudgetAccount(name);

        System.out.println("Account Holder: " + accountManager.getCurrentAccount().getUser().name());
        System.out.println("Initial balance: £" + String.format("%.2f", accountManager.getCurrentAccount().getBalance()));

        boolean hasExited = false;
        while (!hasExited) {
            String bankTextBlock = """
                    \n=== Fincore CLI Banking Menu ===
                    1. Deposit
                    2. Withdraw
                    3. Check Balance
                    4. Exit
                    Please select an option (1-4):
                    """;
            System.out.println(bankTextBlock);

            int option = Console.readInt(1, 4);

            switch (option) {
                case 1 -> {
                    accountManager.deposit();
                }
                case 2 -> {
                    accountManager.withdraw();
                }
                case 3 -> accountManager.checkBalance();
                case 4 -> {
                    accountManager.logoutOfBudgetAccount();
                    hasExited = true;
                }

            }
        }
        Console.close();
    }


}

