package com.fincore.budgetapp.service;

import com.fincore.budgetapp.model.BudgetEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetManagerTest {

    BudgetManager budgetManager;

    @BeforeEach
    public void setup() {
        budgetManager = new BudgetManager();
    }

    @Nested
    @DisplayName("Login/Logout functionality tests")
    class Login {
        @Test
        @DisplayName("Should create a new account for new user")
        void shouldCreateANewAccountForNewUser() {
            budgetManager.loginToBudgetAccount("Test User");
            assertNotNull(budgetManager.getCurrentAccount());
            assertEquals("Test User", budgetManager.getCurrentAccount().getUser().name());
        }

        @Test
        @DisplayName("Should retrieve existing account if account exists")
        void shouldRetrieveExistingUserIfExists() {
            budgetManager.loginToBudgetAccount("test");
            BudgetEntry loginOne = budgetManager.getCurrentAccount();
            budgetManager.logoutOfBudgetAccount();
            budgetManager.loginToBudgetAccount("test");
            BudgetEntry loginTwo = budgetManager.getCurrentAccount();
            assertSame(loginOne, loginTwo);
        }

        @Test
        @DisplayName("Should be case-insensitive when retrieving existing account if account exists")
        void shouldBeCaseInsensitiveWhenRetrievingExistingUserIfExists() {
            budgetManager.loginToBudgetAccount("test");
            budgetManager.logoutOfBudgetAccount();
            budgetManager.loginToBudgetAccount("TeSt");
            assertNotNull(budgetManager.getCurrentAccount());
            assertEquals("test", budgetManager.getCurrentAccount().getUser().name());
        }

        @Test
        @DisplayName("Should set current account to null after logout")
        void shouldSetCurrentAccountToNullAfterLogout() {
            budgetManager.loginToBudgetAccount("test");
            assertNotNull(budgetManager.getCurrentAccount());
            budgetManager.logoutOfBudgetAccount();
            assertNull(budgetManager.getCurrentAccount());
        }
    }

    // I am not sure how to test the other methods as they require the Console class and user input


}