package model;

import org.json.JSONObject;

// Represents a task with a name, date, time, description, and location.
public class Task implements Displayable, Searchable {

    private String name;
    private String date;
    private int time;
    private String description;
    private String location;
    private boolean permanent;

    public Task(String name, String date, int time, String description, String location, boolean permanent) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.location = location;
        this.permanent = permanent;
    }

    public Task() {
        this.name = "Default Task";
        this.date = "Monday";
        this.time = 1;
        this.description = "No Description";
        this.location = "No Location";
        this.permanent = false;
    }

    // EFFECTS: returns the name of this task.
    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of this task to the given name.
    // REQUIRES: name is not null
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns the date of this task.
    public String getDate() {
        return this.date;
    }

    // REQUIRES: date is not null
    // MODIFIES: this
    // EFFECTS: sets the date of this task to the given date.
    public void setDate(String date) {
        this.date = date;
    }

    // EFFECTS: returns the time of this task.
    public int getTime() {
        return this.time;
    }

    // REQUIRES: time is a positive integer (in hours)
    // MODIFIES: this
    // EFFECTS: sets the time of this task to the given time.
    public void setTime(int time) {
        this.time = time;
    }

    // EFFECTS: returns the description of this task.
    public String getDescription() {
        return this.description;
    }

    // REQUIRES: description is not null
    // MODIFIES: this
    // EFFECTS: sets the description of this task to the given description.
    public void setDescription(String description) {
        this.description = description;
    }

    // EFFECTS: returns the location of this task.
    public String getLocation() {
        return this.location;
    }

    // REQUIRES: location is not null
    // MODIFIES: this
    // EFFECTS: sets the location of this task to the given location.
    public void setLocation(String location) {
        this.location = location;
    }

    // EFFECTS: returns whether this task is permanent.
    public boolean isPermanent() {
        return permanent;
    }

    // MODIFIES: this
    // EFFECTS: sets whether this task is permanent.
    // REQUIRES: permanent is a boolean value
    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: displays the details of this task.
    // REQUIRES: this task has valid details (name, date, time, description, location)
    public String display() {
        return "Task Name: " + this.name + "\nDate: " + this.date + "\nTime: " + this.time + "\nDescription: " + this.description + "\nLocation: " + this.location + "\nPermanent: " + this.permanent;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: searches for a given keyword in the name of this task, and then displays the task if found.
    // REQUIRES: keyword is not null
    public String search(String keyword) {
        if (this.name.toLowerCase().contains(keyword.toLowerCase())) {
            return this.display();
        } else {
            return null;
        }
    }

    // EFFECTS: returns this task as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("time", time);
        json.put("description", description);
        json.put("location", location);
        json.put("permanent", permanent);
        return json;
    }

    // REQUIRES: json contains all required task fields
    // EFFECTS: constructs a task from JSON data
    public Task(JSONObject json) {
        this.name = json.getString("name");
        this.date = json.getString("date");
        this.time = json.getInt("time");
        this.description = json.getString("description");
        this.location = json.getString("location");
        this.permanent = json.getBoolean("permanent");
    }
}