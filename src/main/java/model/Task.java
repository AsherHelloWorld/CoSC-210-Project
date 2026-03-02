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

    // EFFECTS: returns the location of this event.
    public abstract String getLocation();
    
}
