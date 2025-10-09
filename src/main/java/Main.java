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
                case 1, 2, 3 -> System.out.println();
                case 4 -> {
                    System.out.println("Thank you for using FinCore CLI Banking. Goodbye " + name + "!");
                    hasExited = true;
                }

            }
        }

        scanner.close();
    }
}
