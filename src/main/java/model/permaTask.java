package model;

import org.json.JSONObject;

// Represents a permanent task that survives planner wipes
public class PermaTask extends Task implements Displayable, Searchable {

    // REQUIRES: name, date, description, and location are not null;
    //           time is a positive integer (in hours)
    // MODIFIES: this
    // EFFECTS: constructs a permanent task with the given details
    public PermaTask(String name, String date, int time,
                     String description, String location) {
        super(name, date, time, description, location);
    }

    // REQUIRES: json contains all required task fields
    // MODIFIES: this
    // EFFECTS: constructs a permanent task from JSON data
    public PermaTask(JSONObject json) {
        super(
            json.getString("name"),
            json.getString("date"),
            json.getInt("time"),
            json.getString("description"),
            json.getString("location")
        );
    }

    // MODIFIES: this
    // EFFECTS: constructs a default permanent task
    public PermaTask() {
        super("Default Permanent Task", "Monday", 1,
              "No Description", "No Location");
    }

    @Override
    // EFFECTS: displays the details of this permanent task
    // REQUIRES: this task has valid details (name, date, time, description, location)
    public String display() {
        return "Permanent Task: " + name + " on " + date
                + " at " + time + ":00. Description: "
                + description + ". Location: " + location;
    }

    @Override
    // EFFECTS: searches for a given keyword in the name of this task,
    //          and returns the task details if found; otherwise returns null
    // REQUIRES: keyword is not null
    public String search(String keyword) {
        if (name.toLowerCase().contains(keyword.toLowerCase())) {
            return display();
        }
        return null;
    }
}