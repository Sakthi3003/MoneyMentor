package service;

import model.User;
import repo.UserManager;
import util.InputValidator;

import java.util.Optional;
import java.util.Scanner;

public class AuthService {

    // Login with maximum 3 attempts
    public static User login(Scanner scan) {
        System.out.println("==========================");
        System.out.println("          Login           ");
        System.out.println("==========================");

        int attempts = 3;

        while (attempts > 0) {
            String email = InputValidator.getEmailInput(scan, "Enter email : ");
            String password = InputValidator.getPasswordInput(scan, "Enter password : ");

            Optional<User> user = UserManager.getAllUsers().stream()
                    .filter(u -> u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password))
                    .findFirst();

            if (user.isPresent()) {
                System.out.println("\n        ✅ Login successful!\n");
                return user.get();
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("❌ Invalid username or password. Attempts left: " + attempts + "\n");
                } else {
                    System.out.println("⚠️ Too many failed attempts. Exiting...\n");
                }
            }
        }
        return null;
    }


    public static void register(Scanner scan) {
        System.out.println("==========================");
        System.out.println("         Register         ");
        System.out.println("==========================");

        String name = InputValidator.getStringInput(scan, "Enter name : ");
        String email;

        // check for existing email
        while(true){
            email = InputValidator.getEmailInput(scan, "Enter email : ");
            String finalEmail = email;

            boolean exist = UserManager
                    .getAllUsers()
                    .stream()
                    .anyMatch(user -> user.getEmail().equals(finalEmail));

            if(exist){
                System.out.println("❌ Email already exists. Please try another one!");
            }else {
                break;
            }}

        String password = InputValidator.getPasswordInput(scan, "Enter password : ");

        UserManager.addUser(name, email, password);

        System.out.println("✅ User registration successful!");
    }
}
