package ui;

import java.util.Scanner;

import exceptions.InvalidTaskDayException;
import exceptions.InvalidTaskDurationException;
import model.NormalTask;
import model.PermTask;
import model.Planner;
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
                    p.clearTasks();
                    System.out.println("All non-permanent tasks cleared from the planner.");
                    break;
                case 4:
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
                case 5:
                    JsonReader jsonReader = new JsonReader(JSON_STORE);
                    try {
                        p = jsonReader.read();
                        System.out.println("Planner loaded successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while loading the planner: " + e.getMessage());
                    }
                    break;
                case 6:
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
        System.out.println("3. Clear Non-Permanent Tasks");
        System.out.println("4. Save Planner");
        System.out.println("5. Load Planner");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    // MODIFIES: this
    // EFFECTS: prompts user for task details and adds the task to the planner;
    //          re-prompts if an invalid day or duration is entered
    private void addTask() {
        System.out.println();
        System.out.println("Adding a new task:");
        System.out.print("Enter task name: ");
        String name = s.nextLine();
        System.out.print("Enter task date (e.g. Monday): ");
        String date = s.nextLine();
        System.out.print("Enter task time (positive integer, in hours): ");
        int time = s.nextInt();
        s.nextLine();
        System.out.print("Enter task description: ");
        String description = s.nextLine();
        System.out.print("Enter task location: ");
        String location = s.nextLine();
        System.out.print("Is this task permanent? (true/false): ");
        boolean isPermanent = s.nextBoolean();

        try {
            if (isPermanent) {
                p.addTask(new PermTask(name, date, time, description, location));
                System.out.println("Permanent task added successfully!");
            } else {
                p.addTask(new NormalTask(name, date, time, description, location));
                System.out.println("Task added successfully!");
            }
        } catch (InvalidTaskDayException e) {
            System.out.println("Could not add task — invalid day: " + e.getMessage());
        } catch (InvalidTaskDurationException e) {
            System.out.println("Could not add task — invalid duration: " + e.getMessage());
        } finally {
            s.nextLine(); // Clear the input buffer
        }
    }

    // Prints out a list of all tasks in the planner
    private void viewTasks() {
        System.out.println();
        System.out.println("Weekly tasks:");
        p.getTasks().forEach(task -> System.out.println(task.display()));
    }
}