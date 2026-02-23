import model.Planner;
import model.Task;
import persistence.*;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class Persistence {
    
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

   @Test
    void testPersistenceEmptyPlanner() {
        try {
            Planner p = new Planner();
            JsonWriter writer = new JsonWriter("./data/testEmptyPlanner.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyPlanner.json");
            Planner loadedPlanner = reader.read();
            // CRITICAL: You must assert that the state is actually empty
            assertEquals(0, loadedPlanner.getTasks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testPersistenceGeneralPlanner() {
        try {
            Planner p = new Planner();
            p.addTask(new Task("Work", "Monday", 9, "Coding", "Office", true));
            
            JsonWriter writer = new JsonWriter("./data/testGeneralPlanner.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneralPlanner.json");
            Planner loadedPlanner = reader.read();
            
            // Verify the content matches exactly
            assertEquals(1, loadedPlanner.getTasks().size());
            Task loadedTask = loadedPlanner.getTasks().get(0);
            assertEquals("Work", loadedTask.getName());
            assertTrue(loadedTask.isPermanent());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
