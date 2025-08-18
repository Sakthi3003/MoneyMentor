package util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static String getEmailInput(Scanner scan, String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = scan.nextLine().trim();

            Matcher matcher = EMAIL_PATTERN.matcher(input);
            if (matcher.matches()) {
                return input; // valid email
            } else {
                System.out.println("Invalid email. Please enter a valid email address.");
            }
        }
    }

    public static int getIntegerInput(Scanner scan, String message) {
        int option = -1;

        while(true){
            try {
                System.out.print(message);
                option = Integer.parseInt(scan.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Invalid choice. Please try again!");
            }
        }
        return option;
    }

    public static Double getDoubleInput(Scanner scan, String message) {
        Double option = -1.0;

        while(true){
            try {
                System.out.print(message);
                option = Double.parseDouble(scan.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Invalid choice. Please try again!");
            }
        }
        return option;
    }

    public static String getPasswordInput(Scanner scan, String message){
        String password = "";
        while(true){
            try{
                System.out.print(message);
                password = scan.nextLine().trim();
                if(password.length() < 6){
                    System.out.println("Password length must be atleast 6 digits");
                }else{
                    return password;
                }
            }catch(Exception e){
                System.out.println("Something went wrong. Try again!");
            }
        }
    }

    public static String getStringInput(Scanner scan, String message) {
        String input = "";
        while(true){
            try{
                System.out.print(message);
                input = scan.nextLine().trim();
                if(!input.isEmpty()){
                    return input;
                }else{
                    System.out.println("Input must not be empty");
                }

            }catch (Exception e){
                System.out.println("Invalid Input. Please try again");
            }
        }
    }

    public static LocalDate getDateInput(Scanner scan, String message) {
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = null;
        Boolean isValid = false;

        while(!isValid){
            System.out.print(message + " (dd-MM-yyyy): ");
            String input = scan.next().trim();
            try{
                date = LocalDate.parse(input, dateTime);
                isValid = true;
            }catch (DateTimeException e){
                System.out.println("Invalid Date Format. Try again!");
            }
        }
        return date;
    }
}
