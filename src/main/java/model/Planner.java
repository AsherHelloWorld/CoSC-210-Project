package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.InvalidTaskDayException;
import exceptions.InvalidTaskDurationException;

// Represents a weekly planner that contains a list of tasks.
public class Planner implements Searchable {

    private List<Task> taskList = new ArrayList<>();

    // REQUIRES: jsonArray is not null
    // MODIFIES: this
    // EFFECTS: constructs a planner from a JSON array of tasks
    public Planner(JSONArray jsonArray) throws InvalidTaskDayException, InvalidTaskDurationException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            boolean isPermanent = obj.optBoolean("permanent", false);
            if (isPermanent) {
                taskList.add(new PermTask(obj));
            } else {
                taskList.add(new NormalTask(obj));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs an empty planner
    public Planner() {
    }

    // REQUIRES: t is not null
    // MODIFIES: this
    // EFFECTS: adds the given task to this planner
    public void addTask(Task t) {
        taskList.add(t);
    }

    // EFFECTS: returns the list of all tasks in this planner
    public List<Task> getTasks() {
        return taskList;
    }

    @Override
    // EFFECTS: searches for a task with a given keyword, and returns its display
    //          string if found; returns null if no task is found
    public String search(String keyword) {
        for (Task task : taskList) {
            String result = task.search(keyword);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: clears all non-permanent tasks from the planner
    public void clearTasks() {
        for (int i = taskList.size() - 1; i >= 0; i--) {
            if (!(taskList.get(i) instanceof PermTask)) {
                taskList.remove(i);
            }
        }
    }

    // EFFECTS: returns a JSON array representation of the tasks in this planner
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();
        for (Task t : taskList) {
            JSONObject obj = t.toJson();
            // Add a flag for permanent tasks
            if (t instanceof PermTask) {
                obj.put("permanent", true);
            } else {
                obj.put("permanent", false);
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }
}