package model;
import java.io.*;

// Represents a task with a name, date, time, description, and location.
public class Task implements Displayable, Searchable, Serializable {
  
    private static final long serialVersionUID = 1L;

    private String name;
    private String date;
    private int time;
    private String description;
    private String location;

    public Task(String name, String date, int time, String description, String location){

        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.location = location;
        
    }

    public Task() {
        this.name = "Default Task";
        this.date = "Monday";
        this.time = 1;
        this.description = "No Description";
        this.location = "No Location";
    }

    // EFFECTS: returns the name of this task.
    public String getName(){
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of this task to the given name.
    // REQUIRES: name is not null
    public void setName(String name){
        this.name = name;
    }

    // EFFECTS: returns the date of this task.
    public String getDate(){
        return this.date;
    }

    // REQUIRES: date is not null
    // MODIFIES: this
    // EFFECTS: sets the date of this task to the given date.
    public void setDate(String date){
        this.date = date;
    }

    // EFFECTS: returns the time of this task.
    public int getTime(){
        return this.time;
    }

    // REQUIRES: time is a positive integer (in hours)
    // MODIFIES: this
    // EFFECTS: sets the time of this task to the given time.
    public void setTime(int time){
        this.time = time;
    }

    // EFFECTS: returns the description of this task.
    public String getDescription(){
        return this.description;
    }

    // REQUIRES: description is not null
    // MODIFIES: this
    // EFFECTS: sets the description of this task to the given description.
    public void setDescription(String description){
        this.description = description;
    }

    // EFFECTS: returns the location of this task.
    public String getLocation(){
        return this.location;
    }

    // REQUIRES: location is not null
    // MODIFIES: this
    // EFFECTS: sets the location of this task to the given location.
    public void setLocation(String location){
        this.location = location;
    }

    public void info() {
        System.out.println("Task Name: " + this.name);
        System.out.println("Date: " + this.date);
        System.out.println("Time: " + this.time);
        System.out.println("Description: " + this.description);
        System.out.println("Location: " + this.location);
    }

    @Override
    // EFFECTS: searchs for a keyword in the name of this task, and then displays it if found.
    public void search(String keyword) {
        if (this.name.toLowerCase().contains(keyword.toLowerCase())) {
            this.display();
        } else {
            System.out.println("No tasks found with the keyword: " + keyword);
        }
    }
}