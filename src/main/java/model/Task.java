package model;

import org.json.JSONObject;

// Represents a task with a name, date, time, description, and location.
public abstract class Task {

    protected String name;
    protected String date;
    protected int time;
    protected String description;
    protected String location;

    // REQUIRES: name, date, description, and location are not null;
    //           time is a positive integer (in hours)
    // MODIFIES: this
    // EFFECTS: constructs a task with the given name, date, time,
    //          description, and location.
    public Task(String name, String date, int time,
                String description, String location) {
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

    // REQUIRES: date is not null
    // MODIFIES: this
    // EFFECTS: sets the date of this task to the given date.
    public void setDate(String date) {
        this.date = date;
    }

    // EFFECTS: returns the time of this task.
    public int getTime() {
        return time;
    }

    // REQUIRES: time is a positive integer (in hours)
    // MODIFIES: this
    // EFFECTS: sets the time of this task to the given time.
    public void setTime(int time) {
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
    // EFFECTS: searches for a given keyword in the name of this task, and
    //          returns the task details if found; otherwise returns null
    // REQUIRES: keyword is not null
}