package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Expense {
   private final String description;
   private final Double price;
   private final LocalDate date;

   public Expense(String description, Double price, LocalDate date){
       this.description = description;
       this.price = price;
       this.date = date;
   }

   public String getDescription(){
       return this.description;
   }

   public Double getPrice(){
       return this.price;
   }

   public LocalDate getDate(){
       return this.date;
   }

   public String toString(){
       return date + " | " + description + " | ₹" + price;
   }
}
