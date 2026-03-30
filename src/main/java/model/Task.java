package model;

import java.util.Arrays;
import java.util.List;

import exceptions.InvalidTaskDayException;
import exceptions.InvalidTaskDurationException;
import org.json.JSONObject;

// Represents a task with a name, date, time, description, and location.
public abstract class Task {

    protected static final List<String> VALID_DAYS = Arrays.asList( 
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    );

    protected String name;
    protected String date;
    protected int time;
    protected String description;
    protected String location;

    // REQUIRES: name, date, description, and location are not null
    // MODIFIES: this
    // EFFECTS: constructs a task with the given name, date, time, description, and location.
    //          throws InvalidTaskDayException if date is not a valid weekday;
    //          throws InvalidTaskDurationException if time is not a positive integer
    public Task(String name, String date, int time,
                String description, String location)
            throws InvalidTaskDayException, InvalidTaskDurationException {

        if (!VALID_DAYS.contains(date)) {
            throw new InvalidTaskDayException(
                    "\"" + date + "\" is not a valid weekday. Must be one of: " + VALID_DAYS
            );
        }
        if (time <= 0 || time > 24) {
            throw new InvalidTaskDurationException(
                    "Task duration must be a positive integer between 1 and 24, but got: " + time
            );
        }

        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.location = location;
    }

    // EFFECTS: returns the name of this task.
    public String getName() {
        return name;
    }

    // REQUIRES: name is not null
    // MODIFIES: this
    // EFFECTS: sets the name of this task to the given name.
    public void setName(String name) {
        this.name = name;
    }

    public abstract String search(String keyword);

    // EFFECTS: returns the date of this task.
    public String getDate() {
        return date;
    }

    // REQUIRES: date is a valid weekday string
    // MODIFIES: this
    // EFFECTS: sets the date of this task to the given date;
    //          throws InvalidTaskDayException if date is not a valid weekday
    public void setDate(String date) throws InvalidTaskDayException {
        if (!VALID_DAYS.contains(date)) {
            throw new InvalidTaskDayException(
                    "\"" + date + "\" is not a valid weekday. Must be one of: " + VALID_DAYS
            );
        }
        this.date = date;
    }

    // EFFECTS: returns the time of this task.
    public int getTime() {
        return time;
    }

    // REQUIRES: time is a positive integer (in hours)
    // MODIFIES: this
    // EFFECTS: sets the time of this task to the given time;
    //          throws InvalidTaskDurationException if time is not positive
    public void setTime(int time) throws InvalidTaskDurationException {
        if (time <= 0) {
            throw new InvalidTaskDurationException(
                    "Task duration must be a positive integer, but got: " + time
            );
        }
        this.time = time;
    }

    // EFFECTS: returns the description of this task.
    public String getDescription() {
        return description;
    }

    // REQUIRES: description is not null
    // MODIFIES: this
    // EFFECTS: sets the description of this task to the given description.
    public void setDescription(String description) {
        this.description = description;
    }

    // EFFECTS: returns the location of this task.
    public String getLocation() {
        return location;
    }

    // REQUIRES: location is not null
    // MODIFIES: this
    // EFFECTS: sets the location of this task to the given location.
    public void setLocation(String location) {
        this.location = location;
    }

    // EFFECTS: returns this task as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("time", time);
        json.put("description", description);
        json.put("location", location);
        return json;
    }

    // EFFECTS: displays the details of this task.
    public String display() {

        String day = date.substring(0,3).toUpperCase();
        String formattedTime = String.format("%02d:00", time);
    
        String marker = (this instanceof PermTask) ? "★ " : "    ";
    
        return marker + day + " " + formattedTime + " | " + name;
    }

    // MODIFIES: this
    // EFFECTS: updates the details of this task with the given name, date, time, description, and location;
    public void update(String name, String date, int time, String description, String location)
            throws InvalidTaskDayException, InvalidTaskDurationException {
        setName(name);
        setDate(date);
        setTime(time);
        setDescription(description);
        setLocation(location);
    }
}