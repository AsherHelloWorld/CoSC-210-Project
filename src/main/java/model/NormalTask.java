package model;

import org.json.JSONObject;

// Represents a normal (non-permanent) task.
public class NormalTask extends Task implements Displayable, Searchable {

    // REQUIRES: name, date, description, and location are not null;
    //           time is a positive integer (in hours)
    // MODIFIES: this
    // EFFECTS: constructs a normal task with the given details.
    public NormalTask(String name, String date, int time,
                      String description, String location) {
        super(name, date, time, description, location);
    }

    // MODIFIES: this
    // EFFECTS: constructs a default normal task.
    public NormalTask() {
        super("Default Task", "Monday", 1,
              "No Description", "No Location");
    }

    @Override
    // EFFECTS: searches for a given keyword in the name of this task,
    //          and returns the task details if found; otherwise returns null.
    // REQUIRES: keyword is not null
    public String search(String keyword) {
        if (name.toLowerCase().contains(keyword.toLowerCase())) {
            return display();
        } else {
            return null;
        }
    }

    // REQUIRES: json contains all required task fields
    // MODIFIES: this
    // EFFECTS: constructs a normal task from JSON data
    public NormalTask(JSONObject json) {
        super(
            json.getString("name"),
            json.getString("date"),
            json.getInt("time"),
            json.getString("description"),
            json.getString("location")
        );
    }
}