import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import model.Planner;
import persistence.JsonReader;
import persistence.JsonWriter;

public class PersistenceTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
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
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
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
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
}
