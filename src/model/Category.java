package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private Double allocatedAmount;
    private List<Expense> expenses = new ArrayList<>();

    public Category(String name, Double allocatedAmount){
        this.name = name;
        this.allocatedAmount = allocatedAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAllocatedAmount() {
        return allocatedAmount;
    }

    public void setAllocatedAmount(Double allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void addExpense(String desc, Double amount){
        expenses.add(new Expense(desc, amount, LocalDate.now()));
        System.out.println("           ***Expense Added Successfully!***");
    }


    public void addExpense(String desc, Double amount, LocalDate date){
        expenses.add(new Expense(desc, amount, date));
        System.out.println("           ***Expense Added Successfully!***");
    }

    public double totalSpent() {
        return expenses.stream().mapToDouble(Expense::getPrice).sum();
    }

    public double remaining() {
        return allocatedAmount - totalSpent();
    }

    public void printSummary() {
        System.out.println("Category: " + name);
        System.out.println("Allocated: ₹" + allocatedAmount);
        System.out.println("Spent: ₹" + totalSpent());
        System.out.println("Remaining: ₹" + remaining());
        System.out.println("Expenses:");
        expenses.forEach(System.out::println);
        System.out.println("----------------------------");
    }

    @Override
    public String toString() {
        return "Category " +
                "name = " + name  +
                ", allocatedAmount = " + allocatedAmount +
                ", spent = " + totalSpent() +
                ", remaining = " + (allocatedAmount - totalSpent());

    }
}
