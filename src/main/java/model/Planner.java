package model;
import java.util.*;

public class Planner {
    ArrayList<Task> taskList = new ArrayList<Task>();

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

    public void addTask(Task t){
        taskList.add(t);
    }

    public ArrayList<Task> getTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : taskList) {
                System.out.println("Task number: " + (taskList.indexOf(task) + 1));
                task.info();
                System.out.println();
            }
        }    
        return taskList;
    }
}
