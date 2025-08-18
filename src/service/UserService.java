package service;

import model.User;
import model.YoungAdultUser;
import util.InputValidator;

import java.util.Scanner;

public class UserService {

    // User menu
    public static void userMenu(Scanner scan, User currentUser){
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
            System.out.println("6. Generate Month Financial Report");
            System.out.println("7. Take Financial Literacy Quiz");
            System.out.println("8. Exit");
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
}
