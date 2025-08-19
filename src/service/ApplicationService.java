package service;

import model.Role;
import model.User;
import repo.UserManager;
import util.InputValidator;

import java.util.Scanner;

public class ApplicationService {

    public static void runApplication(Scanner scan, User currentUser) {
        boolean exit = false;
        while (!exit) {

            showMainMenu();

            int option = InputValidator.getIntegerInput(scan, "👉 Enter your choice: ");

            switch(option){
                case 1 -> AuthService.register(scan);
                case 2 -> currentUser = AuthService.login(scan);
                case 3 -> {
                    showThankYouMessage();
                    exit = true;
                }
                case 4 -> printUsers();
                default -> System.out.println("\n       *** Invalid choice. Try Again!  ***\n");
            }

            if (currentUser != null) {
                if (currentUser.getRole().equals(Role.USER)) {
                    UserService.userMenu(scan, currentUser);
                } else if (currentUser.getRole().equals(Role.ADMIN)) {
                    AdminService.adminMenu(scan, currentUser);
                }
            }

        }
    }

    // Thank you message
    private static void showThankYouMessage() {
        System.out.println("======================================");
        System.out.println("👋 Thank you for using MoneyMentor!");
        System.out.println("======================================");
    }

    // Main menu
    public static void showMainMenu() {
        System.out.println("1. Register as a New User");
        System.out.println("2. Login with Existing Account");
        System.out.println("3. Exit");
    }

    // Printing Welcome message
    public static void showWelcomeMessage() {

        System.out.println("=================================");
        System.out.println("      Welcome to MoneyMentor     ");
        System.out.println("=================================");
        System.out.println("        Learn | Save | Grow      ");
        System.out.println("---------------------------------");
    }

    public static void printUsers() {
        UserManager.getAllUsers().forEach(user -> System.out.println(user.getName()));
    }
}
