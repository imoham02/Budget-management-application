public class BudgetManager {
    private BudgetEntry account;

    public BudgetManager(BudgetEntry account) {
        this.account = account;
    }

    public void deposit(double amount) {
        try {
            account.deposit(amount);
            System.out.printf("Deposit successful!%nNew Balance: £%.2f%n", (account.getBalance()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void withdraw(double amount) {
        try {
            account.withdraw(amount);
            System.out.printf("Withdrawal successful!%nNew Balance: £%.2f%n", (account.getBalance()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkBalance() {
        System.out.println("=== Account Balance ===");
        System.out.println("Account Holder: " + account.getAccountHolderName());
        System.out.println("Current Balance: £" + String.format("%.2f", account.getBalance()));
    }
}
