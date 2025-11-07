package com.fincore.budgetapp.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public Console() {
    }

    public static String readString (String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    public static double readDouble (String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number");
                scanner.nextLine();
            }
        }
    }

    public static int readInt(int min, int max) {
        while (true) {
            System.out.printf("Enter a number (%d–%d): ", min, max);
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    scanner.nextLine(); // clear leftover newline
                    return input;
                } else {
                    System.out.printf("Invalid choice! Pick option (%d–%d)%n", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number");
                scanner.nextLine(); // clear invalid input
            }
        }
    }


    public static boolean readYesNo(String prompt) {
        while (true) {
            System.out.println(prompt + " (y/n) ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;
            System.out.println("Please enter 'y' or 'n'.");
        }
    }

    public static void close() {
        scanner.close();
    }
}
