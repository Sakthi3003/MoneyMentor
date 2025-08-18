package ui;

import java.util.Scanner;
import model.Role;
import model.User;
import model.YoungAdultUser;
import model.repo.UserManager;
import service.AuthService;
import util.InputValidator;

public class Main {
    private static User currentUser;

    public static void main(String[] args) {
        showWelcomeMessage();
        initializeValues();

        try (Scanner scan = new Scanner(System.in)) {
            runApplication(scan);
        }
    }

    private static void runApplication(Scanner scan) {
        Boolean exit = false;
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
                case 4 -> printuser();
                default -> System.out.println("Invalid choice");
            }

            if (currentUser != null) {
                if (currentUser.getRole().equals(Role.USER)) {
                    userMenu(scan);
                } else if (currentUser.getRole().equals(Role.ADMIN)) {
                    adminMenu(scan);
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
    private static void showMainMenu() {
        System.out.println("1. Register as a New User");
        System.out.println("2. Login with Existing Account");
        System.out.println("3. Exit");
    }

    // Initializing Hardcoded values
    private static void initializeValues() {

    }

    // Printing Welcome message
    private static void showWelcomeMessage() {

        System.out.println("=================================");
        System.out.println("      Welcome to MoneyMentor     ");
        System.out.println("=================================");
        System.out.println("        Learn | Save | Grow      ");
        System.out.println("---------------------------------");
    }

    // admin menu
    private static void adminMenu(Scanner scan) {
        System.out.println("========================================");
        System.out.println("           Welcome Aboard, Admin!       ");
        System.out.println("========================================");


    }

    // user menu
    public static void userMenu(Scanner scan){
        YoungAdultUser user = (YoungAdultUser) currentUser;
        System.out.println("========================================");
        System.out.println("        Welcome Aboard, " + user.getName() + "!       ");
        System.out.println("========================================");

        while(true) {

            System.out.println("What would you like to do today? \n");
            System.out.println("1. Add Budget");
            System.out.println("2. Add an Expense");
            System.out.println("3. Show Budget Summary");
            System.out.println("4. Show Expense Summary");
            System.out.println("4. Add a New Saving Goal");
            System.out.println("5. Track Your Old Saving Goals");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");
            System.out.print("👉 Enter your choice: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("   ➡ Adding Budget...");
                    user.createBudget(scan);
                    break;
                case 2:
                    System.out.println("   ➡ Adding Expense...");
                    String month = InputValidator.getStringInput(scan, "Enter month : ");
                    Integer year = InputValidator.getIntegerInput(scan, "Enter year : ");
                    user.addExpense(month, year, scan);
                    break;
                case 3:
                    System.out.println("   ➡ Showing Budget Summary...");
                    user.showBudgetSummary();
                    break;
                case 4:
                    System.out.println("   ➡ Show Expense Summary...");
                    break;
                case 5:
                    System.out.println("   ➡ Add Saving Goals...");
                    user.addSavingGoals(scan);
                    break;
                case 6:
                    System.out.println("   ➡ Generating Report...");
                    break;
                case 7:
                    System.out.println("✅ Thank you for using Expense Tracker. Goodbye!");
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }


    private static void printuser() {
        UserManager.getAllUsers().forEach(user -> System.out.println(user.getName()));
    }





}