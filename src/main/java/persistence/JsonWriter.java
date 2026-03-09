package persistence;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.JSONArray;

import model.Planner;


// Represents a writer that writes JSON representation of planner to file.
public class JsonWriter {
    
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of planner to file
    public void write(Planner p) {
        JSONArray json = p.toJson();
        saveToFile(json.toString(4));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
