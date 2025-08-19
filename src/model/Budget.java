package model;

import java.util.ArrayList;
import java.util.List;

public class Budget {
    private int budgetId;
    private int year;
    private String month;
    private double income;
    private List<Category> categories = new ArrayList<>();

    public Budget(int budgetId, String month, int year, double income) {
        this.budgetId = budgetId;
        this.month = month;
        this.income = income;
        this.year = year;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public String getMonth() {
        return month;
    }

    public double getIncome() {
        return income;
    }

    public List<Category> getCategories(){
        return categories;
    }

    public int getYear() {
        return year;
    }

    public void addCategory(Category cat) {
        categories.add(cat);
    }

    public void addExpenseToCategory(String categoryName,String description, double amount) {
        for (Category cat : categories) {
            if (cat.getName().equalsIgnoreCase(categoryName)) {
                cat.addExpense(description,amount);
                return;
            }
        }
        System.out.println("❌ Category '" + categoryName + "' not found in this budget.");
    }

    public void showSummary() {
        System.out.println("\n📊 Budget Summary for " + month);
        for (Category cat : categories) {
            System.out.println(cat);
        }
    }

    public double getTotalAllocated() {
        return categories.stream().mapToDouble(Category::getAllocatedAmount).sum();
    }

    public double getRemainingIncome() {
        return income - getTotalAllocated();
    }


    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId +
                ", month='" + month + '\'' +
                ", income=" + income +
                ", categories=" + categories +
                '}';
    }

    public void setIncome(double v) {
        this.income = income;
    }
}
