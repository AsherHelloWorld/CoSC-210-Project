package ui;

import java.util.Scanner;
import java.util.ArrayList;

import model.Planner;
import model.Task;

public class UI {
    public Planner p;
    public Scanner s;

    public UI() {
        p = new Planner();
        s = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println();
            printMenu();
            int choice = s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                   p.saveToFile();
                    break;
                case 4:
                    p = Planner.loadFromFile();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //Prints the main menu options
    private void printMenu() {
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Save Planner");
        System.out.println("4. Load Planner");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    //Prompts user for task details and adds the task to the planner
    private void addTask() {
        System.out.println();
        System.out.println("Adding a new task:");
        System.out.print("Enter task name: ");
        String name = s.nextLine();
        System.out.print("Enter task date: ");
        String date = s.nextLine();
        System.out.print("Enter task time (in hours): ");
        int time = s.nextInt();
        s.nextLine(); // consume newline
        System.out.print("Enter task description: ");
        String description = s.nextLine();
        System.out.print("Enter task location: ");
        String location = s.nextLine();

        Task newTask = new Task(name, date, time, description, location);
        p.addTask(newTask);
        System.out.println("Task added successfully!");
    }

    //Prints out a list of all tasks in the planner
    private void viewTasks() {
        System.out.println();
        System.out.println("Tasks in the planner:");
        p.getTasks();
    }

}