package persistence;

import model.Planner;
import org.json.JSONArray;

import exceptions.InvalidTaskDayException;
import exceptions.InvalidTaskDurationException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads planner from JSON data stored in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads planner from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Planner read() throws IOException, InvalidTaskDayException, InvalidTaskDurationException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parsePlanner(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses planner from JSON array and returns it
    private Planner parsePlanner(JSONArray jsonArray) throws InvalidTaskDayException, InvalidTaskDurationException {
        return new Planner(jsonArray);
    }
}