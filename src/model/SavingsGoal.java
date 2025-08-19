package model;

import java.time.LocalDate;

public class SavingsGoal {
    private String name;           // goal title/name
    private double targetAmount;   // total amount to reach
    private double amountSaved;    // amount accumulated so far
    private LocalDate dueDate;     // when it should be completed

    public SavingsGoal() {}

    public SavingsGoal(String goalName, double target, LocalDate due) {
        this.name = goalName;
        this.targetAmount = target;
        this.dueDate = due;
    }

    public String getName() { return name; }

    public double getTargetAmount() { return targetAmount; }

    public double getAmountSaved() { return amountSaved; }

    public LocalDate getDueDate() { return dueDate; }

    // Adding savings
    public void addSavings(Double amount){
        this.amountSaved += amount;
    }

    public double getProgressPercentage() {
        return (amountSaved / targetAmount) * 100;
    }

    // Adding progress
    public void showProgress() {
        int progress = (int) getProgressPercentage();
        int bars = progress / 5; // each bar = 5%
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 20; i++) {
            if (i < bars) bar.append("█");
            else bar.append("-");
        }
        bar.append("] ").append(progress).append("%");
        System.out.println("Savings Goal Progress: " + bar);
    }
}
