public class Task{
    private String name;
    private String date;
    private int time;
    private String description;
    private String location;
    public Task(String name, String date, int time, String description, String location){

        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.location = location;
        
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public int getTime(){
        return this.time;
    }
    public void setTime(int time){
        this.time = time;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public void info() {
        System.out.println("Task Name: " + this.name);
        System.out.println("Date: " + this.date);
        System.out.println("Time: " + this.time);
        System.out.println("Description: " + this.description);
        System.out.println("Location: " + this.location);
    }
}