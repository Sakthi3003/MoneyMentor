package model;

import java.time.LocalDate;

public class SavingsGoal {
    private int id;                // unique identifier
    private String name;           // goal title/name
    private double targetAmount;   // total amount to reach
    private double amountSaved;    // amount accumulated so far
    private LocalDate dueDate;     // when it should be completed

    public SavingsGoal() {}

    public SavingsGoal(int id, String name, double targetAmount, double amountSaved, LocalDate dueDate) {
        this.id = id;
        this.name = name;
        this.targetAmount = targetAmount;
        this.amountSaved = amountSaved;
        this.dueDate = dueDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getTargetAmount() { return targetAmount; }
    public void setTargetAmount(double targetAmount) { this.targetAmount = targetAmount; }

    public double getAmountSaved() { return amountSaved; }
    public void setAmountSaved(double amountSaved) { this.amountSaved = amountSaved; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}
