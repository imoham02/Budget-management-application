package com.fincore.budgetapp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetEntryTest {

    private BudgetEntry budgetEntry;

    @BeforeEach
    void setUp() {
        budgetEntry = new BudgetEntry(new User("Test"));
    }

    @Nested
    @DisplayName("Deposit tests")
    class DepositTests {

        @Test
        @DisplayName("Should increase the balance when the amount deposited is positive")
        void shouldIncreaseTheBalance() {
            double initialBalance = budgetEntry.getBalance();
            double depositAmount = 100.55;
            budgetEntry.deposit(depositAmount);
            assertEquals(initialBalance + depositAmount, budgetEntry.getBalance());
        }

        @Test
        @DisplayName("Should handle consecutive positive deposits")
        void shouldHandleMultipleDeposits() {
            budgetEntry.deposit(37.75);
            budgetEntry.deposit(456.88);
            budgetEntry.deposit(925.33);

            assertEquals(2419.96, budgetEntry.getBalance(), 0.001);
        }

        @Test
        @DisplayName("Should add successful transaction to transaction history")
        void shouldAddSuccessfulTransactionToTransactionHistory() {
            int initialTransactionCount = budgetEntry.getTransactions().size();
            double depositAmount = 78.99;
            budgetEntry.deposit(depositAmount);

            assertEquals(initialTransactionCount + 1, budgetEntry.getTransactions().size());
        }

        @Test
        @DisplayName("Should add successful transaction with correct amount to transaction history")
        void shouldAddCorrectAmountTransactionToTransactionHistory() {
            double depositAmount = 78.99;
            budgetEntry.deposit(depositAmount);
            Transaction transaction = budgetEntry.getTransactions().getFirst();

            assertEquals(depositAmount, transaction.getAmount());
        }

        @Test
        @DisplayName("Should add successful transaction with correct type to transaction history")
        void shouldAddCorrectTypeTransactionToTransactionHistory() {
            double depositAmount = 78.99;
            budgetEntry.deposit(depositAmount);
            Transaction transaction = budgetEntry.getTransactions().getFirst();

            assertEquals("DEPOSIT", transaction.getType());
        }


        @Test
        @DisplayName("Should maintain transaction order after successful deposits")
        void shouldMaintainTransactionOrder() {
            budgetEntry.deposit(37.75);
            budgetEntry.deposit(456.88);
            budgetEntry.deposit(925.33);

            assertEquals(925.33, budgetEntry.getTransactions().getFirst().getAmount());
            assertEquals(456.88, budgetEntry.getTransactions().get(1).getAmount());
            assertEquals(37.75, budgetEntry.getTransactions().get(2).getAmount());

        }


        @Test
        @DisplayName("Should throw an IllegalArgumentException when the amount deposited is negative")
        void shouldThrowIllegalArgumentExceptionWithANegativeDeposit() {
            double negativeDepositAmount = -66.87;
            assertThrows(IllegalArgumentException.class, () -> {
                budgetEntry.deposit(negativeDepositAmount);
            });
        }

        @Test
        @DisplayName("Should throw an IllegalArgumentException when the amount deposited is zero")
        void shouldThrowIllegalArgumentExceptionWithAZeroDeposit() {
            double zeroDepositAmount = 0.00;
            assertThrows(IllegalArgumentException.class, () -> {
                budgetEntry.deposit(zeroDepositAmount);
            });
        }

        @Test
        @DisplayName("Should not change the balance when an exception is thrown")
        void shouldNotChangeBalanceWhenExceptionThrown() {
            double initialBalance = budgetEntry.getBalance();

            assertThrows(IllegalArgumentException.class, () -> budgetEntry.deposit(-22.00));

            assertEquals(initialBalance, budgetEntry.getBalance());
        }

        @Test
        @DisplayName("Should not add transaction when an exception is thrown")
        void shouldNotAddTransactionWhenExceptionThrown() {
            int initialTransactionCount = budgetEntry.getTransactions().size();

            assertThrows(IllegalArgumentException.class, () -> budgetEntry.deposit(-22.00));

            assertEquals(initialTransactionCount, budgetEntry.getTransactions().size());

        }


    }

    @Nested
    @DisplayName("Withdraw tests")
    class WithdrawTest {

        @Test
        @DisplayName("Should decrease the balance when the amount withdrawn is positive")
        void shouldDecreaseTheBalance() {
            double initialBalance = budgetEntry.getBalance();
            double withdrawalAmount = 100.55;
            budgetEntry.withdraw(withdrawalAmount);
            assertEquals(initialBalance - withdrawalAmount, budgetEntry.getBalance());
        }

        @Test
        @DisplayName("Should handle consecutive positive withdrawals")
        void shouldHandleMultipleWithdrawals() {
            budgetEntry.withdraw(37.75);
            budgetEntry.withdraw(456.88);
            budgetEntry.withdraw(225.33);

            assertEquals(280.04, budgetEntry.getBalance(), 0.001);
        }

        @Test
        @DisplayName("Should add successful transaction to transaction history")
        void shouldAddSuccessfulTransactionToTransactionHistory() {
            int initialTransactionCount = budgetEntry.getTransactions().size();
            double withdrawalAmount = 78.99;
            budgetEntry.deposit(withdrawalAmount);

            assertEquals(initialTransactionCount + 1, budgetEntry.getTransactions().size());
        }

        @Test
        @DisplayName("Should add successful transaction with correct amount to transaction history")
        void shouldAddCorrectAmountTransactionToTransactionHistory() {
            double withdrawalAmount = 78.99;
            budgetEntry.deposit(withdrawalAmount);
            Transaction transaction = budgetEntry.getTransactions().getFirst();

            assertEquals(withdrawalAmount, transaction.getAmount());
        }

        @Test
        @DisplayName("Should add successful transaction with correct type to transaction history")
        void shouldAddCorrectTypeTransactionToTransactionHistory() {
            double withdrawalAmount = 78.99;
            budgetEntry.deposit(withdrawalAmount);
            Transaction transaction = budgetEntry.getTransactions().getFirst();

            assertEquals("DEPOSIT", transaction.getType());
        }


        @Test
        @DisplayName("Should maintain transaction order after successful withdrawals")
        void shouldMaintainTransactionOrder() {
            budgetEntry.withdraw(37.75);
            budgetEntry.withdraw(456.88);
            budgetEntry.withdraw(225.33);

            assertEquals(225.33, budgetEntry.getTransactions().getFirst().getAmount());
            assertEquals(456.88, budgetEntry.getTransactions().get(1).getAmount());
            assertEquals(37.75, budgetEntry.getTransactions().get(2).getAmount());

        }


        @Test
        @DisplayName("Should throw an IllegalArgumentException when the amount withdrawn is negative")
        void shouldThrowIllegalArgumentExceptionWithANegativeWithdrawal() {
            double negativeWithdrawalAmount = -66.87;
            assertThrows(IllegalArgumentException.class, () -> {
                budgetEntry.withdraw(negativeWithdrawalAmount);
            });
        }

        @Test
        @DisplayName("Should throw an IllegalArgumentException when the amount withdrawn is zero")
        void shouldThrowIllegalArgumentExceptionWithAZeroWithdrawal() {
            double zeroWithdrawalAmount = 0.00;
            assertThrows(IllegalArgumentException.class, () -> {
                budgetEntry.withdraw(zeroWithdrawalAmount);
            });
        }

        @Test
        @DisplayName("Should not change the balance when an exception is thrown")
        void shouldNotChangeBalanceWhenNegativeWithdrawalExceptionThrown() {
            double initialBalance = budgetEntry.getBalance();

            assertThrows(IllegalArgumentException.class, () -> budgetEntry.withdraw(-22.00));

            assertEquals(initialBalance, budgetEntry.getBalance());
        }

        @Test
        @DisplayName("Should not add transaction when an exception is thrown")
        void shouldNotAddTransactionWhenNegativeWithdrawalExceptionThrown() {
            int initialTransactionCount = budgetEntry.getTransactions().size();

            assertThrows(IllegalArgumentException.class, () -> budgetEntry.withdraw(-22.00));

            assertEquals(initialTransactionCount, budgetEntry.getTransactions().size());

        }

        @Test
        @DisplayName("Should throw an IllegalArgumentException when the amount withdrawn is greater than current balance")
        void shouldThrowIllegalArgumentExceptionWhenWithdrawalAmountExceedsBalance() {
            double excessWithdrawalAmount = budgetEntry.getBalance() + 66;
            assertThrows(IllegalArgumentException.class, () -> {
                budgetEntry.withdraw(excessWithdrawalAmount);
            });
        }

        @Test
        @DisplayName("Should not change the balance when an exception is thrown")
        void shouldNotChangeBalanceWhenInsufficientFundsExceptionThrown() {
            double initialBalance = budgetEntry.getBalance();

            assertThrows(IllegalArgumentException.class, () -> budgetEntry.withdraw(budgetEntry.getBalance() + 77.00));

            assertEquals(initialBalance, budgetEntry.getBalance());
        }

        @Test
        @DisplayName("Should not add transaction when an exception is thrown")
        void shouldNotAddTransactionWhenInsufficientFundsExceptionThrown() {
            int initialTransactionCount = budgetEntry.getTransactions().size();

            assertThrows(IllegalArgumentException.class, () -> budgetEntry.withdraw(budgetEntry.getBalance() + 55.00));

            assertEquals(initialTransactionCount, budgetEntry.getTransactions().size());

        }

    }
}