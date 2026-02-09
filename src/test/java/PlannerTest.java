

import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;

import model.Planner;
import model.Task;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    private Planner planner;
    private static final String TEST_FILE = "data/planner.ser";

    @BeforeEach
    void setUp() {
        planner = new Planner();
        planner.addTask(new Task("Task 1", "Monday", 1, "Desc 1", "Loc 1"));
        planner.addTask(new Task("Task 2", "Tuesday", 2, "Desc 2", "Loc 2"));
    }

    @AfterEach
    void tearDown() {
        // Delete the file after tests so it doesn't interfere with real planner
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoadPlanner() {
        // Save
        planner.saveToFile();

        // Load
        Planner loaded = Planner.loadFromFile();

        // Check tasks are preserved
        ArrayList<Task> tasks = loaded.getTasks();
        assertEquals(2, tasks.size(), "Number of tasks should match");

        assertEquals("Task 1", tasks.get(0).getName());
        assertEquals("Task 2", tasks.get(1).getName());
        assertEquals("Monday", tasks.get(0).getDate());
        assertEquals("Tuesday", tasks.get(1).getDate());
    }

    @Test
    void testLoadNonExistentPlanner() {
        // Ensure file does not exist
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();

        // Should return a new Planner without crashing
        Planner loaded = Planner.loadFromFile();
        assertNotNull(loaded, "Loaded planner should not be null");
        assertEquals(0, loaded.getTasks().size(), "New planner should have 0 tasks");
    }
}
