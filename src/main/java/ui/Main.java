package ui;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

        System.out.println("Select Interface:");
        System.out.println("1. Terminal UI");
        System.out.println("2. GUI");

        int choice = scanner.nextInt();

        if (choice == 1) {
            UI ui = new UI();
            ui.start();
        } else if (choice == 2) {
            new PlannerGUI();
        } else {
            System.out.println("Invalid choice. Exiting.");
        }
        }
    }
    
}