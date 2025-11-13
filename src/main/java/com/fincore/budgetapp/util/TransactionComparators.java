package com.fincore.budgetapp.util;

import com.fincore.budgetapp.model.Transaction;

import java.util.Comparator;

public class TransactionComparators {

    public static Comparator<Transaction> byDateTime(boolean ascending) {
        Comparator<Transaction> comp = Comparator.comparing(Transaction::getDateTime);
        return ascending ? comp : comp.reversed();
    }

    public static Comparator<Transaction> byAmount(boolean ascending) {
        Comparator<Transaction> comp = Comparator.comparing(Transaction::getAmount);
        return ascending ? comp : comp.reversed();
    }
}
