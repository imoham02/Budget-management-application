import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! What is your full name?");
        String name = scanner.nextLine();
        double balance = 1000.00;
        System.out.println("Welcome to FinCore CLI Banking!");
        System.out.println("Account Holder: " + name);
        System.out.println("Initial balance: £" + String.format("%.2f", balance));
        boolean hasExited = false;
        while (!hasExited) {
            String bankTextBlock = """
                    
                    === Fincore CLI Banking Menu ===
                    1. Deposit
                    2. Withdraw
                    3. Check Balance
                    4. Exit
                    """;
            System.out.println(bankTextBlock);
            System.out.println("Please select an option (1-4):");
            int option = 0;
            boolean validOption = false;
            while (!validOption) {
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option < 1 || option > 4) {
                        System.out.println("Invalid choice. Please select again! (1-4):");
                    } else {
                        validOption = true;
                    }
                } catch (NumberFormatException badUserOption) {
                    System.out.println("Please enter a valid number(1-4):");
                }
            }
            switch (option) {
                case 1 -> balance = deposit(balance, scanner);
                case 2 -> balance = withdraw(balance, scanner);
                case 3 -> {
                    System.out.println("=== Account Balance ===");
                    System.out.println("Account Holder: " + name);
                    System.out.println("Current Balance: £" + String.format("%.2f", balance));
                }
                case 4 -> {
                    System.out.println("Thank you for using FinCore CLI Banking. Goodbye " + name + "!");
                    hasExited = true;
                }

            }
        }
        scanner.close();
    }
    public static double deposit(double balance, Scanner scanner) {
        System.out.println("Enter amount to deposit: £");
        boolean validAmount = false;
        double amount;
        while (!validAmount) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Amount deposited cannot be negative! Please try again: £");
                } else {
                    balance += amount;
                    System.out.println("Deposit successful!");
                    System.out.println("Amount deposited: £" + amount);
                    System.out.println("New balance: £" + balance);
                    validAmount = true;
                }
            } catch (NumberFormatException badUserAmount) {
                System.out.println("Please enter a valid number: £");
            }
        }
        return balance;
    }
    public static double withdraw(double balance, Scanner scanner) {
        System.out.println("Enter amount to withdraw: £");
        boolean validAmount = false;
        double amount;
        while (!validAmount) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Amount withdrawn cannot be negative! Please try again: £");
                } else if (amount > balance) {
                    System.out.println("Insufficient funds!");
                    System.out.println("Current Balance: £" + balance);
                    System.out.println("Attempted Withdrawal: £" + amount);
                    System.out.println("Enter a valid amount: £");
                } else {
                    balance -= amount;
                    System.out.println("Withdrawal successful!");
                    System.out.println("Amount withdrawn: £" + amount);
                    System.out.println("New balance: £" + balance);
                    validAmount = true;
                }
            } catch (NumberFormatException badUserAmount) {
                System.out.println("Please enter a valid number: £");
            }
        }
        return balance;
    }
}
