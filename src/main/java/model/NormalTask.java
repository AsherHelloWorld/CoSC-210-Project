package model;

import exceptions.InvalidTaskDayException;
import exceptions.InvalidTaskDurationException;
import org.json.JSONObject;

// Represents a normal (non-permanent) task.
public class NormalTask extends Task implements Displayable, Searchable {

    // REQUIRES: name, date, description, and location are not null
    // MODIFIES: this
    // EFFECTS: constructs a normal task with the given details;
    //          throws InvalidTaskDayException if date is not a valid weekday;
    //          throws InvalidTaskDurationException if time is not a positive integer
    public NormalTask(String name, String date, int time, String description, String location)
            throws InvalidTaskDayException, InvalidTaskDurationException {
        super(name, date, time, description, location);
    }

    // MODIFIES: this
    // EFFECTS: constructs a default normal task.
    public NormalTask() throws InvalidTaskDayException, InvalidTaskDurationException {
        super("Default Task", "Monday", 1,
              "No Description", "No Location");
    }

    // REQUIRES: json contains all required task fields with valid values
    // MODIFIES: this
    // EFFECTS: constructs a normal task from JSON data;
    //          throws InvalidTaskDayException if the stored date is not a valid weekday;
    //          throws InvalidTaskDurationException if the stored time is not positive
    public NormalTask(JSONObject json) throws InvalidTaskDayException, InvalidTaskDurationException {
        super(
            json.getString("name"),
            json.getString("date"),
            json.getInt("time"),
            json.getString("description"),
            json.getString("location")
        );
    }

    @Override
    // EFFECTS: displays the details of this task.
    public String display() {
        return "Normal Task: " + name + " on " + date
                + " at " + time + ":00. Description: "
                + description + ". Location: " + location;
    }

    @Override
    // REQUIRES: keyword is not null
    // EFFECTS: searches for a given keyword in the name of this task,
    //          and returns the task details if found; otherwise returns null.
    public String search(String keyword) {
        if (name.toLowerCase().contains(keyword.toLowerCase())) {
            return display();
        }
        return null;
    }
}