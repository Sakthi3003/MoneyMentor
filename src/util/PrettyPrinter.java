package util;

import model.Budget;
import model.Category;
import model.Expense;

import java.time.format.DateTimeFormatter;

public class PrettyPrinter {

    public static void printBudget(Budget budget) {
        System.out.println("\n===== Budget Report =====");

        for (Category category : budget.getCategories()) {
            System.out.println("\nCategory: " + category.getName());
            System.out.println("--------------------------------");

            if (category.getExpenses().isEmpty()) {
                System.out.println("  No expenses recorded.");
            } else {
                for (Expense expense : category.getExpenses()) {
                    System.out.println("  Expense: " + expense.getDescription());
                    System.out.println("    Amount : " + expense.getPrice());
                    System.out.println("    Date   : " + expense.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    System.out.println();
                }
            }
        }

        System.out.println("==========================\n");
    }
}

