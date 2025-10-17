import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! What is your full name?");
        String name = scanner.nextLine();

        BudgetEntry account = new BudgetEntry(name, 1000.00);
        BudgetManager accountManager = new BudgetManager(account);

        System.out.println("Welcome to FinCore CLI Banking!");
        System.out.println("Account Holder: " + name);
        System.out.println("Initial balance: £" + String.format("%.2f", account.getBalance()));

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

            int option = getValidOption(scanner);

            switch (option) {
                case 1 -> {
                    double amount = getValidAmount(scanner, "Enter deposit amount: £");
                    accountManager.deposit(amount);
                }
                case 2 -> {
                    double amount = getValidAmount(scanner, "Enter withdrawal amount: £");
                    accountManager.withdraw(amount);
                }
                case 3 -> accountManager.checkBalance();
                case 4 -> {
                    System.out.println("Thank you for using FinCore CLI Banking. Goodbye " + name + "!");
                    hasExited = true;
                }

            }
        }
        scanner.close();
    }

    public static int getValidOption(Scanner scanner) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input < 1 || input > 4) {
                    throw new NumberFormatException();
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Pick option (1-4): ");
            }
        }
    }

    public static double getValidAmount(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
            }
        }
    }
}

