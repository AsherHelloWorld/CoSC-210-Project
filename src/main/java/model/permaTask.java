package model;

public class PermaTask extends Task {

     private String name;
    private String date;
    private int time;
    private String description;
    private String location;
    
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

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getDate() {
            return this.date;
        }

        @Override
        public int getTime() {
            return this.time;
        }

        @Override
        public String getDescription() {
            return this.description;
        }

        @Override
        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public void setTime(int time) {
            this.time = time;
        }

        @Override
        public void setDescription(String description) {
            this.description = description;
        }
        }

        @Override
        public String getLocation() {
            return this.location;
        }

        @Override
        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String search(String keyword) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'search'");
        }
}
