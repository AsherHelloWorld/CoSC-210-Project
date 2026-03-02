package model;

import java.lang.reflect.Array;
import java.util.*;

import org.json.JSONArray;

// Represents a weekly planner that contains a list of tasks.
public class Planner implements Searchable {

    private ArrayList<NormalTask> taskList = new ArrayList<NormalTask>();

    // REQUIRES: jsonArray is not null
    // MODIFIES: this
    // EFFECTS: constructs a planner from a JSON array of tasks.
    public Planner(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            NormalTask t = new NormalTask(jsonArray.getJSONObject(i));
            taskList.add(t);
        }
    }

    public Planner() {
        // Default constructor
    }

    // REQUIRES: t is not null
    // MODIFIES: this
    // EFFECTS: adds the given task to this planner.
    public void addTask(NormalTask t) {
        taskList.add(t);
    }

    public void addTask(PermaTask t) {
        taskList.add(t);
    }

    // REQUIRES: taskList is not null
    // EFFECTS: returns the list of all tasks in this planner.
    public ArrayList<NormalTask> getTasks() {
        return taskList;
    }

    @Override
    // EFFECTS: searchs for a task with a given keyword, and then displays it.
    // returns null if no task is found with the given keyword.
    public String search(String keyword) {
        for (NormalTask task : taskList) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                return task.display();
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: clears all non-permanent tasks from the planner.
    public void clearTasks() {
        for (int i = taskList.size() - 1; i >= 0; i--) {
            if (!(taskList.get(i) instanceof PermaTask)) {
                taskList.remove(i);
            }
        }
    }

    // EFFECTS: returns a JSON array representation of the tasks in this planner.
    // REQUIRES: taskList is not null
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();
        for (NormalTask t : taskList) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
