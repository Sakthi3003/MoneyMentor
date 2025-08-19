package service;

import model.*;
import repo.ResourceManager;
import repo.UserManager;
import util.InputValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminService {
    
    public static void showWelcomeMessage() {
        System.out.println("========================================");
        System.out.println("           Welcome Aboard, Admin!       ");
        System.out.println("========================================");
    }



    public static void viewAllResources() {
        ResourceManager
                .getAllResources()
                .forEach(System.out::println);
    }

    public static void viewResourcesByCategory(String category) {

        ResourceCategory resourceCategory = null;
        try{
            resourceCategory = ResourceCategory.valueOf(category);
        }catch (IllegalArgumentException e){
            System.out.println("           ****  Category Does not exist  ****\n");
            return;
        }
        ResourceCategory finalResourceCategory = resourceCategory;
        List<Resource> resourceList = ResourceManager
                .getAllResources()
                .stream()
                .filter(resource -> resource.getCategory().equals(finalResourceCategory))
                .toList();

        if (resourceList.isEmpty()) {
            System.out.println("\n         ❌ No resources found for category: " + category);
        } else {
            resourceList.forEach(System.out::println);
        }
    }

    public static void showAvailableCategory() {
        System.out.println("    Available Categories ");
        System.out.println("-----------------------------");
        ResourceManager
                .getAllResources()
                .stream()
                .map(Resource::getCategory)
                .distinct()
                .forEach(category -> System.out.println(" -> " + category));
    }

    public static void addResource(Scanner scan) {
        showAllCategory();

        String category = InputValidator.getStringInput(scan, "Enter Category : ");

        String title = InputValidator.getStringInput(scan, "Enter Title : ");

        String content = InputValidator.getStringInput(scan, "Enter Content : ");

        Character ch = InputValidator.getStringInput(scan, "Do you want to add a link ? (y/n) : ").charAt(0);
        String link;
       if(ch.equals("y")){
           link = InputValidator.getStringInput(scan, "Enter Link (option) : ");
       }else{
           link = "Link not provided";
       }

       ResourceCategory resourceCategory = ResourceCategory.valueOf(category);
       ResourceManager.addResource(title,content, link, resourceCategory);
       System.out.println("\n         ✅ Resource Added successfully!");
    }

    private static void showAllCategory() {
        System.out.println("       **** Available Categories ****");
        for(ResourceCategory category : ResourceCategory.values()){
            System.out.println(" -> " +category);
        }
    }

    public static void editResource(Scanner scan) {
        viewAllResources();

        Long id = (long) InputValidator.getIntegerInput(scan, "Enter Id : ");
        if(checkId(id)) {

            String category = InputValidator.getStringInput(scan, "Enter Category : ");

            String title = InputValidator.getStringInput(scan, "Enter Title : ");

            String content = InputValidator.getStringInput(scan, "Enter content : ");

            // Optional Link
            String link = null;
            String ch = InputValidator.getStringInput(scan, "Do you want to add a link? (y/n): ");
            if (ch.equalsIgnoreCase("y")) {
                link = InputValidator.getStringInput(scan, "Enter Link (optional): ");
            }

            ResourceCategory resourceCategory = ResourceCategory.valueOf(category);
            ResourceManager.editResource(id, title, content, link, resourceCategory);

            System.out.println("\n         ✅ Category edited successfully!");
        }else{
            System.out.println("\n         ❌ Invalid ID! Please try again with a correct Resource ID.");
        }
    }

    // Checking Id
    private static boolean checkId(Long id) {
        if (id == null) return false;
        return ResourceManager.getAllResources()
                .stream()
                .anyMatch(resource -> id.equals(resource.getId()));
    }


    // Deleting Resources
    public static void deleteResource(Scanner scan) {
        // Show all resources first
        viewAllResources();

        // Prompt user
        Long id = (long) InputValidator.getIntegerInput(scan, "Enter Resource ID to delete: ");

        // Attempt deletion
        boolean isDeleted = ResourceManager.deleteResource(id);

        // Feedback messages
        if (isDeleted) {
            System.out.println("\n         ✅ Resource deleted successfully!");
        } else {
            System.out.println("\n         ❌ Resource not found. Please check the ID and try again.");
        }
    }

    public static void showAdminMenu() {
        System.out.println("\n                **** Admin Menu **** \n");
        System.out.println("1. View All Resources");
        System.out.println("2. View Resources by Category");
        System.out.println("3. Add Resource");
        System.out.println("4. Edit Resource");
        System.out.println("5. Delete Resource");
        System.out.println("6. Financial Trends");
        System.out.println("7. View All Summaries");
        System.out.println("8. Show Insights");
        System.out.println("9. Logout");
    }

    // admin menu
    public static void adminMenu(Scanner scan, User currentUser) {
        Admin admin = (Admin) currentUser;

        AdminService.showWelcomeMessage();

        Boolean exit = false;
        while (!exit) {
            AdminService.showAdminMenu();

            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); // consume newline

            switch (choice) {
                // View All Resources
                case 1 -> AdminService.viewAllResources();

                // View By category
                case 2 -> {
                    AdminService.showAvailableCategory();
                    String category = InputValidator.getStringInput(scan, "Enter category : ");
                    AdminService.viewResourcesByCategory(category);
                }

                // Add resources
                case 3 -> AdminService.addResource(scan);

                // Edit resources
                case 4 -> AdminService.editResource(scan);

                // Delete Resources
                case 5 -> AdminService.deleteResource(scan);

                // Financial Trends
                case 6 -> AdminService.financialTrends(scan);

                // Show Users Summary
                case 7 -> AdminService.showSummary();
                
                // Show common insights
                case 8 -> AdminService.showCommonInsights();
                
                // Logout
                case 9 -> {
                    System.out.println("👋 Logged out from Admin Menu!");
                    exit = true;
                }
                default -> System.out.println("⚠️ Invalid option! Please try again.");
            }
        }
    }

    public static void showCommonInsights() {
        System.out.println("📊 Admin Insights");

        // Most spent category across all users
        Map<String, Double> categoryTotals = new HashMap<>();
        for (User u : UserManager.getAllUsers()) {
            if (u instanceof YoungAdultUser) {  // check if it's actually a YoungAdultUser
                YoungAdultUser yau = (YoungAdultUser) u; // safe cast

                for (Budget b : yau.getBudget()) {  // assuming it's a list of budgets
                    for (Category c : b.getCategories()) {
                        categoryTotals.put(
                                c.getName(),
                                categoryTotals.getOrDefault(c.getName(), 0.0) + c.totalSpent()
                        );
                    }
                }
            }
        }


        String maxCategory = categoryTotals.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("None");

        System.out.println("Most spent category across users: " + maxCategory);

        for (User u : UserManager.getAllUsers()) {
            // Only process if user is a YoungAdultUser
            if (u instanceof YoungAdultUser) {
                YoungAdultUser yau = (YoungAdultUser) u;

                // Loop through savings goals
                for (SavingsGoal g : yau.getSavingsGoals()) {
                    if (g.getAmountSaved() < g.getTargetAmount()) {
                        System.out.printf("User %s has not met savings goal %s\n",
                                u.getName(), g.getName());
                    }
                }
            }
        }

    }


    private static void showSummary() {
        UserManager.getAllUsers().stream()
                .filter(user -> user instanceof YoungAdultUser)
                .map(user -> (YoungAdultUser) user)
                .forEach(YoungAdultUser::showBudgetSummary);
    }

    private static void financialTrends(Scanner scan) {
    }

}
