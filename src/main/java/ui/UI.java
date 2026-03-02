package ui;

import java.util.Scanner;

import model.Planner;
import model.Task;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents the user interface for the weekly planner application.
public class UI {
    public Planner p;
    public Scanner s;

    // file used by the JsonReader and JsonWriter to save and load the planner
    private static final String JSON_STORE = "./data/planner.json";

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
                    //instantiate JsonWriter and save the planner to file
                    JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
                    try {
                        jsonWriter.open();
                        jsonWriter.write(p);
                        jsonWriter.close();
                        System.out.println("Planner saved successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while saving the planner: " + e.getMessage());
                    }
                    break;
                case 4:
                    // load using JsonReader
                    JsonReader jsonReader = new JsonReader(JSON_STORE);
                    try {
                        p = jsonReader.read();
                        System.out.println("Planner loaded successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while loading the planner: " + e.getMessage());
                    }
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Prints the main menu options
    private void printMenu() {
        System.out.println();
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Save Planner");
        System.out.println("4. Load Planner");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Prompts user for task details and adds the task to the planner
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
        System.out.print("Is this task permanent? (true/false): ");
        boolean permanent = s.nextBoolean();
        s.nextLine(); // consume newline

        Task newTask = new Task(name, date, time, description, location);
        p.addTask(newTask);
        System.out.println("Task added successfully!");
    }

    // Prints out a list of all tasks in the planner
    private void viewTasks() {
        System.out.println();
        System.out.println("Tasks in the planner:");
        p.getTasks().forEach(Task::display);
    }

}
