package ui;

import java.util.Scanner;
import model.User;
import service.ApplicationService;

public class Main {
    private static User currentUser;

    public static void main(String[] args) {

        ApplicationService.showWelcomeMessage();

        try (Scanner scan = new Scanner(System.in)) {
            ApplicationService.runApplication(scan, currentUser);
        }
    }
}