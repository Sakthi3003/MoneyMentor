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
        if (categoryName.equalsIgnoreCase("done")) break;

        System.out.print("Enter allocated amount: ");
        double catAmount = scan.nextDouble();
        scan.nextLine();


        Category cat = new Category(categoryName, catAmount);
        newBudget.addCategory(cat);
        System.out.printf("✅ Category %s added \n\n",cat.getName());
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
}
