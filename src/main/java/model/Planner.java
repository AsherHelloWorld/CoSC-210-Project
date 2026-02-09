package model;
import java.util.*;

// Represents a weekly planner that contains a list of tasks.
public class Planner implements Searchable {
    ArrayList<Task> taskList = new ArrayList<Task>();

    // MODIFIES: this
    // EFFECTS: adds a new task to this planner by asking the user for the task information.
    public void addTask(){
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
        s.close();
             
        taskList.add(new Task(name, date, time, description, location));
    }

    // REQUIRES: t is not null
    // MODIFIES: this
    // EFFECTS: adds the given task to this planner.
    public void addTask(Task t){
        taskList.add(t);
    }

    // EFFECTS: returns the list of all tasks in this planner. 
    public ArrayList<Task> getTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : taskList) {
                System.out.println("Task number: " + (taskList.indexOf(task) + 1));
                task.display();
                System.out.println();
            }
        }    
        return taskList;
    }

    @Override
    // EFFECTS: searchs for a task with a given keyword, and then displays it. 
    public void search(String keyword) {
        boolean found = false;
        for (Task task : taskList) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                task.display();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with the keyword: " + keyword);
        }
    }
}
