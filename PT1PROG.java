package com.mycompany.pt1prog;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PT1PROG {

    public static final Logger LOGGER = Logger.getLogger(PT1PROG.class.getName());

    public static String registeredFirstName = "";
    public static String registeredLastName  = "";
    public static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        boolean running = true;

        printSeparator();
        System.out.println("        WELCOME");
        printSeparator();

        while (running) {
            try {
                printMenu();

                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine().trim());

                System.out.println();

                switch (choice) {
                    case 1 -> handleRegistration(scanner);
                    case 2 -> handleLogin(scanner);
                    case 3 -> {
                        running = false;
                        printSeparator();
                        System.out.println("  Thank you. Goodbye!");
                        printSeparator();
                        LOGGER.log(Level.INFO, "Application exited by user.");
                    }
                    default -> {
                        System.out.println("  Invalid option. Please enter 1, 2, or 3.");
                        System.out.println();
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("  Invalid input. Please enter a number (1, 2, or 3).");
                System.out.println();
                LOGGER.log(Level.WARNING, "Non-numeric menu input received.", e);

            } catch (InputMismatchException e) {
                System.out.println("  An unexpected input error occurred. Please try again.");
                System.out.println();
                LOGGER.log(Level.SEVERE, "Unexpected input mismatch encountered.", e);
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("  MAIN MENU");
        System.out.println("  ---------");
        System.out.println("  1. Register User");
        System.out.println("  2. Login");
        System.out.println("  3. Exit");
        System.out.println();
    }

    private static void handleRegistration(Scanner scanner) {
        printSeparator();
        System.out.println("  REGISTER USER");
        printSeparator();
        System.out.println();

        System.out.print("  Enter your first name : ");
        String firstName = scanner.nextLine().trim();

        System.out.print("  Enter your last name  : ");
        String lastName = scanner.nextLine().trim();

        System.out.println("  Enter a username");
        System.out.println("  (must contain _ and be no more than 5 characters)");
        System.out.print("  > ");
        String username = scanner.nextLine().trim();

        System.out.println("  Enter a password");
        System.out.println("  (min 8 chars, 1 capital letter, 1 number, 1 special character)");
        System.out.print("  > ");
        String password = scanner.nextLine().trim();

        System.out.println("  Enter your cell phone number");
        System.out.println("  (must include international code, e.g. +27838968976)");
        System.out.print("  > ");
        String cellPhone = scanner.nextLine().trim();

        Login registrationLogin = new Login(username, password, cellPhone, firstName, lastName);

        System.out.println();
        System.out.println("  [ VALIDATION RESULTS ]");

        if (registrationLogin.checkUserName()) {
            System.out.println("  Username   : Username successfully captured.");
        } else {
            System.out.println("  Username   : Username is not correctly formatted.");
        }

        if (registrationLogin.checkPasswordComplexity()) {
            System.out.println("  Password   : Password successfully captured.");
        } else {
            System.out.println("  Password   : Password is not correctly formatted.");
        }

        if (registrationLogin.checkCellPhoneNumber()) {
            System.out.println("  Cell phone : Cell phone number successfully added.");
        } else {
            System.out.println("  Cell phone : Cell phone number incorrectly formatted.");
        }

        System.out.println();
        System.out.println("  [ REGISTRATION RESULT ]");
        String result = registrationLogin.registerUser();
        System.out.println("  " + result.replace("\n", "\n  "));

        if (result.contains("Username successfully captured")) {
            registeredFirstName = firstName;
            registeredLastName  = lastName;
        }

        System.out.println();
        printSeparator();

        LOGGER.log(Level.INFO, "Registration attempt completed for username: {0}", username);
    }

    private static void handleLogin(Scanner scanner) {
        printSeparator();
        System.out.println("  LOGIN");
        printSeparator();
        System.out.println();

        System.out.print("  Enter your username : ");
        String loginUsername = scanner.nextLine().trim();

        System.out.print("  Enter your password : ");
        String loginPassword = scanner.nextLine().trim();

        Login loginAttempt = new Login(
                loginUsername,
                loginPassword,
                "",
                registeredFirstName,
                registeredLastName
        );

        System.out.println();
        System.out.println("  [ LOGIN RESULT ]");
        System.out.println("  " + loginAttempt.returnLoginStatus());

        System.out.println();
        printSeparator();

        LOGGER.log(Level.INFO, "Login attempt completed for username: {0}", loginUsername);
    }

    private static void printSeparator() {
        System.out.println("--------------------------------------------------");
    }
}