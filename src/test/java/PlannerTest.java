import model.Planner;
import model.Task;

import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {

    private Planner planner;
    private Task testTask;
    private static final String TEST_FILE = "data/planner.ser";

    @BeforeEach
    void setUp() {
        // Sample task
        testTask = new Task("Tasker", "Monday", 2, "Test task", "YMH");

        // Planner setup
        planner = new Planner();
        planner.addTask(testTask);
        planner.addTask(new Task("Task 1", "Monday", 1, "Desc 1", "Loc 1"));
        planner.addTask(new Task("Task 2", "Tuesday", 2, "Desc 2", "Loc 2"));
    }

    @AfterEach
    void tearDown() {
        // Delete the test file so it doesn't interfere with real planner
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddTask() {
        Task newTask = new Task("Another", "Wednesday", 3, "Desc", "Loc");
        planner.addTask(newTask);
        assertTrue(planner.getTasks().contains(newTask), "New task should be added");
    }

    @Test
    void testGetTasks() {
        ArrayList<Task> tasks = planner.getTasks();
        assertEquals(3, tasks.size(), "Planner should have 3 tasks initially");
        assertEquals("Tasker", tasks.get(0).getName());
    }

    @Test
    void testSearchFound() {
        // Should not throw an exception when task exists
        assertDoesNotThrow(() -> planner.search("Tasker"));
    }

    @Test
    void testSearchNotFound() {
        // Should throw exception (if your search method is implemented that way)
        assertThrows(IllegalArgumentException.class, () -> planner.search("NonExistingTask"));
    }

    @Test
    void testSaveAndLoadPlanner() {
        // Save planner to file
        assertDoesNotThrow(() -> planner.saveToFile());

        // Load planner from file
        Planner loaded = assertDoesNotThrow(() -> Planner.loadFromFile());

        // Check tasks are preserved
        ArrayList<Task> tasks = loaded.getTasks();
        assertEquals(3, tasks.size(), "Loaded planner should have 3 tasks");
        assertEquals("Tasker", tasks.get(0).getName());
        assertEquals("Task 1", tasks.get(1).getName());
        assertEquals("Task 2", tasks.get(2).getName());
    }

    @Test
    void testLoadNonExistentPlanner() {
        // Ensure file does not exist
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();

        // Load should return a new empty planner
        Planner loaded = assertDoesNotThrow(() -> Planner.loadFromFile());
        assertNotNull(loaded, "Loaded planner should not be null");
        assertEquals(0, loaded.getTasks().size(), "New planner should have 0 tasks");
    }
}