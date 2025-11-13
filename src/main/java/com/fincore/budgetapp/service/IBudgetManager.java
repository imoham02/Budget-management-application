package com.fincore.budgetapp.service;

import com.fincore.budgetapp.model.BudgetEntry;
import com.fincore.budgetapp.model.Transaction;

import java.util.List;

public interface IBudgetManager {
    void deposit();
    void withdraw();
    void checkBalance();
    void listTransactions(List<Transaction> transactionList);
    void filterTransactions(int option);
    void sortTransactions(List<Transaction> transactionList);
    BudgetEntry getCurrentAccount();
}
