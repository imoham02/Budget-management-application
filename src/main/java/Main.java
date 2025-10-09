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
    }
}
