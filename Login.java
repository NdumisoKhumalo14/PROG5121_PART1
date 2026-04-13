package com.mycompany.pt1prog;

public class Login {

    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    // Constructor
    public Login(String username, String password, String cellPhone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Username must contain "_" and be <= 5 characters
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Password rules
    public boolean checkPasswordComplexity() {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[^a-zA-Z0-9].*");
    }

    // Must include international code (e.g. +27...)
    public boolean checkCellPhoneNumber() {
        return cellPhone.matches("^\\+\\d{10,13}$");
    }

    // Registration logic
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted.";
        }

        return "User successfully registered.";
    }

    // Login check
    public String returnLoginStatus() {
        if (checkUserName() && checkPasswordComplexity()) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect.";
        }
    }
}