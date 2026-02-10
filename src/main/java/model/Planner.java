package model;
import java.util.*;
import java.io.*;

// Represents a weekly planner that contains a list of tasks.
public class Planner implements Serializable, Searchable {

    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "data/planner.ser";


    ArrayList<Task> taskList = new ArrayList<Task>();

    // MODIFIES: this
    // EFFECTS: adds a new task to this planner by asking the user for the task information.
    public void addTask() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter task name:");
        String name = s.nextLine();
        System.out.println("Enter task date:");
        String date = s.nextLine();
        System.out.println("Enter task time (in hours):");
        int time = s.nextInt();
        s.nextLine(); // consume newline
        System.out.println("Enter task description:");
        String description = s.nextLine();
        System.out.println("Enter task location:");
        String location = s.nextLine();
        System.out.println("Is this task permanent? (true/false):");
        boolean permanent = s.nextBoolean();
             
        taskList.add(new Task(name, date, time, description, location, permanent));
    }

    // REQUIRES: t is not null
    // MODIFIES: this
    // EFFECTS: adds the given task to this planner.
    public void addTask(Task t) {
        taskList.add(t);
    }

    // EFFECTS: returns the list of all tasks in this planner. 
    public ArrayList<Task> getTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : taskList) {
                System.out.println("Task number: " + (taskList.indexOf(task) + 1));
                ///task.display();
                System.out.println();
            }
        }    
        return taskList;
    }

    // MODIFIES: this
    // EFFECTS: saves the planner to file.
    public void saveToFile() {
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
        out.writeObject(this);
        out.close();
        } catch (IOException e){
            //System.out.println("Error saving planner to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // EFFECTS: loads the planner from file and returns it. If the file does not exist, returns a new empty planner.
    public static Planner loadFromFile() {
        try{
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH));
        Planner planner = (Planner) in.readObject();
        in.close();
        return planner;
        } catch (IOException e){
            System.out.println("Error loading planner from file: " + e.getMessage());
            return new Planner();
        } catch (ClassNotFoundException e){
            System.out.println("Class not found: " + e.getMessage());
            return new Planner();
        }
    }

    @Override
    // EFFECTS: searchs for a task with a given keyword, and then displays it. 
    public void search(String keyword) {
        boolean found = false;
        for (Task task : taskList) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                //task.display();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with the keyword: " + keyword);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears all non-permanent tasks from the planner.
    public void clearTasks() {
        for(int i = taskList.size() - 1; i >= 0; i--) {
            if(!taskList.get(i).isPermanent()) {
                taskList.remove(i);
            }
        }
    }
}


