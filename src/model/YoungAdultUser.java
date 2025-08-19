package model;

import util.InputValidator;
import util.PrettyPrinter;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

public class YoungAdultUser extends User{
  private List<Budget> budget = new ArrayList<>();
  private static Integer budgetId = 1;
  private List<SavingsGoal> savingsGoals = new ArrayList<>();

  public YoungAdultUser(Long userId, String name, String username, String password){
      super(userId, name, username, password, Role.USER);
  }


  public void createBudget(Scanner scan) {
    String month = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    int year = LocalDate.now().getYear();

    Optional<Budget> isavailable = budget.stream().filter(budget ->  budget.getMonth().equals(month)).findFirst();
    if(budget.isEmpty() || !isavailable.isPresent()) {
      System.out.println("            Let's create a smart Budget for " + month + "!");
      System.out.println("------------------------------------------------------------");

      System.out.print("Enter total income: ");
      double income = scan.nextDouble();
      scan.nextLine();

      Budget newBudget = new Budget(budgetId++, month, year,  income);

      System.out.println("➡ Now, let's add categories!");
      while (true) {
        System.out.print("Enter category name (or 'done' to finish): ");
        String categoryName = scan.nextLine();
        if (categoryName.equalsIgnoreCase("done")){

          System.out.println();
          break;
        }

        System.out.print("Enter allocated amount: ");
        double catAmount = scan.nextDouble();
        scan.nextLine();


        Category cat = new Category(categoryName, catAmount);
        newBudget.addCategory(cat);
        System.out.printf("✅ Category %s added \n\n",cat.getName());
        Double remaining = income - catAmount;
        System.out.println(remaining + "");
      }

      budget.add(newBudget);
      System.out.println("✅ Budget created successfully for " + month);

      // 🔹 Adding expenses
      while (true) {
        System.out.print("\nDo you want to add an expense? (yes/no): ");
        String ans = scan.nextLine();
        if (ans.equalsIgnoreCase("no"))
          break;

        System.out.print("Enter category name: ");
        String catName = scan.nextLine();

        System.out.print("Enter expense amount: ");
        double expAmount = scan.nextDouble();
        scan.nextLine();

        System.out.print("Enter description : ");
        String description = scan.nextLine();

        newBudget.addExpenseToCategory(catName, description, expAmount);
        newBudget.showSummary();
      }

      System.out.print("Do you want to set a savings goal? (y/n): ");
      String saveAns = scan.nextLine();
      if(saveAns.equalsIgnoreCase("y")) {
        String goalName = InputValidator.getStringInput(scan, "Enter your goal: ");
        double target = InputValidator.getDoubleInput(scan, "Target amount: ");
        LocalDate due = InputValidator.getDateInput(scan, "Due date: ");
        double initial = InputValidator.getDoubleInput(scan, "How much to save now: ");

        if (initial > newBudget.getRemainingIncome()) {
          System.out.println("⚠ Not enough income left!");
        } else {
          SavingsGoal goal = new SavingsGoal(goalName, target, due);
          goal.addSavings(initial);
          savingsGoals.add(goal);

          newBudget.setIncome(newBudget.getIncome() - initial); // deduct savings
          System.out.println("✅ Goal created: " + goalName);
        }
      }
    }else{
      System.out.printf("\n        Budget for %s month is already created\n\n", month);
    }
  }

  public void addExpense(String month, Integer year, Scanner scan) {
    Optional<Budget> existingBudget = budget.stream()
            .filter(budget -> budget.getMonth().equalsIgnoreCase(month) && budget.getYear() == year)
            .findFirst();

    while(true){
    if (existingBudget.isPresent()) {
      System.out.print("\nDo you want to create a new category ? (y/n) : \n\n");
      Character ch = scan.next().trim().charAt(0);

      scan.nextLine();

      if (ch.equals('y')) {
        String name = InputValidator.getStringInput(scan, "Enter category name: ");

        Double amount = InputValidator.getDoubleInput(scan, "Enter allocated amount: ");

        Category category = new Category(name, amount);

        // add expense
        String description = InputValidator.getStringInput(scan, "Enter expense description : ");

        Double amountSpent = InputValidator.getDoubleInput(scan, "Enter amount spent : ");

        LocalDate date = InputValidator.getDateInput(scan, "Enter date ");

        category.addExpense(description, amountSpent, date);

        existingBudget.get().addCategory(category);
        break;

      } else {
        System.out.println("-----------------------------------------");
        System.out.println("        Existing categories : ");
        System.out.println("-----------------------------------------");
        budget.stream()
                .flatMap(b -> b.getCategories().stream())
                .map(Category::getName)
                .forEach(name -> System.out.println("-> " + name));

        scan.nextLine();
        String categoryName = InputValidator.getStringInput(scan, "Enter the category name : ");

        Optional<Category> category = budget.stream()
                .flatMap(b -> b.getCategories().stream())
                .filter(category1 -> category1.getName().equalsIgnoreCase(categoryName))
                .findFirst();

        if (category.isPresent()) {
          Category exitingCategory = category.get();
          String description = InputValidator.getStringInput(scan, "Enter expense description : ");

          Double amountSpent = InputValidator.getDoubleInput(scan, "Enter amount spent : ");

          LocalDate date = InputValidator.getDateInput(scan, "Enter date ");

          Expense expense = new Expense(description, amountSpent, date);
          exitingCategory.addExpense(description, amountSpent, date);
          break;
        } else {
          System.out.println("Category does not exist! Try again or Create new Category!");
        }
      }
    } else {
      System.out.println("       **Budget doesn't exists!**\n");
      break;
    }
  }
  }



  public void showBudgetSummary() {
      for(Budget budget :  budget){
        PrettyPrinter.printBudget(budget);
      }
  }

  public void addSavingGoals(Scanner scan) {
    String name = InputValidator.getStringInput(scan, "Hello, What is ur saving for ? " );
    Double targetAmount = InputValidator.getDoubleInput(scan, "Enter Your target amount : ");
    LocalDate dueDate = InputValidator.getDateInput(scan, "Enter your due date : ");

    System.out.println("Do you want to add amount now (y/n) : ");
    Character ch = InputValidator.getStringInput(scan, "Do you want to add amount now (y/n)").charAt(0);

    if(ch.equals('y')){
      // show the available amount
      Double savingAmount = InputValidator.getDoubleInput(scan, "Enter the amount you want to save");

      // show the saving amount
      // show the remaining amount present


    }

  }



  public void generateMonthlyReport(String month, int year) {
    Optional<Budget> reportBudget = budget.stream()
            .filter(b -> b.getMonth().equalsIgnoreCase(month) && b.getYear() == year)
            .findFirst();

    if (reportBudget.isPresent()) {
      Budget b = reportBudget.get();

      System.out.println("📊 Financial Health Report - " + month + " " + year);
      System.out.println("-------------------------------------------------");
      double savings = savingsGoals.stream().mapToDouble(SavingsGoal::getAmountSaved).sum();
      double savingsRatio = savings / b.getIncome();
      System.out.printf("Savings Ratio: %.2f%%\n", savingsRatio * 100);

      // Check budget adherence
      double totalSpent = b.getCategories().stream()
              .mapToDouble(Category::totalSpent)
              .sum();
      double totalAllocated = b.getCategories().stream()
              .mapToDouble(Category::getAllocatedAmount)
              .sum();

      double adherence = (1 - (Math.abs(totalSpent - totalAllocated) / totalAllocated)) * 100;
      System.out.printf("Budget Adherence: %.2f%%\n", adherence);

      // Alerts for overspending
      System.out.println("\nOverspending Alerts:");
      b.getCategories().forEach(c -> {
        if (c.totalSpent() > c.getAllocatedAmount()) {
          System.out.printf("⚠ Category %s exceeded budget! (Spent: %.2f, Allocated: %.2f)\n",
                  c.getName(), c.totalSpent(), c.getAllocatedAmount());
        }
      });
    } else {
      System.out.println("❌ No budget found for " + month + " " + year);
    }
  }

  public List<Budget> getBudget() {
    return budget;
  }

  public List<SavingsGoal> getSavingsGoals() {
    return savingsGoals;
  }
}
