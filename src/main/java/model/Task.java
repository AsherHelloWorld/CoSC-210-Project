package model;

public abstract class Task {
    private String name;
    private String date;
    private int time;
    private String description;
    private String location;

    public Task(String name, String date, int time, String description, String location) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.location = location;
    }

    public Task() {
    
    }

    // EFFECTS: returns the name of this event.
    public abstract String getName();

    // EFFECTS: returns the date of this event.
    public abstract String getDate();
    
    // EFFECTS: returns the time of this event.
    public abstract int getTime();
    

    // EFFECTS: returns the description of this event.
    public abstract String getDescription();
  
    // REQUIRES: date is not null
    // MODIFIES: this
    // EFFECTS: sets the date of this task to the given date.
    public abstract void setDate(String date);

    // REQUIRES: time is a positive integer (in hours)
    // MODIFIES: this
    // EFFECTS: sets the time of this task to the given time.
    public abstract void setTime(int time);

    // REQUIRES: description is not null
    // MODIFIES: this
    // EFFECTS: sets the description of this task to the given description.
    public abstract void setDescription(String description);

    // EFFECTS: returns the location of this task.
    public abstract String getLocation();

    // REQUIRES: location is not null
    // MODIFIES: this
    // EFFECTS: sets the location of this task to the given location.
    public abstract void setLocation(String location);

    @Override
    // MODIFIES: this
    // EFFECTS: displays the details of this task.
    // REQUIRES: this task has valid details (name, date, time, description, location)
    public abstract String display();

    @Override
    // MODIFIES: this
    // EFFECTS: searches for a given keyword in the name of this task, and then displays the task if found.
    // REQUIRES: keyword is not null
    public abstract String search(String keyword);
