package model;

public class PermaTask extends NormalTask {
    
        public PermaTask(String name, String date, int time, String description, String location) {
            super(name, date, time, description, location);
        }
    
        public PermaTask() {
            super();
        }
    
        @Override
        // EFFECTS: returns a string representation of this permanent task.
        public String display() {
            return "Permanent Task: " + getName() + " on " + getDate() + " at " + getTime() + ":00. Description: "
                    + getDescription() + ". Location: " + getLocation();
        }
}
