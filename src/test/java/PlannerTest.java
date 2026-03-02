import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.NormalTask;
import model.Planner;
import model.Task;
import persistence.JsonReader;
import persistence.JsonWriter;

public class PlannerTest {

    private Planner planner;
    private NormalTask testTask;
    private static final String TEST_FILE = "data/planner.ser";

    @BeforeEach
    void setUp() {
        // Sample task
        testTask = new NormalTask("Tasker", "Monday", 2, "Test task", "YMH");

        // Planner setup
        planner = new Planner();
        planner.addTask(testTask);
        planner.addTask(new NormalTask("Task 1", "Monday", 1, "Desc 1", "Loc 1"));
        planner.addTask(new NormalTask("Task 2", "Tuesday", 2, "Desc 2", "Loc 2"));
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
        NormalTask newTask = new NormalTask("Another", "Wednesday", 3, "Desc", "Loc");
        planner.addTask(newTask);
        assertTrue(planner.getTasks().contains(newTask), "New task should be added");
    }

    @Test
    void testGetTasks() {
        ArrayList<NormalTask> tasks = planner.getTasks();
        assertEquals(3, tasks.size(), "Planner should have 3 tasks initially");
        assertEquals("Tasker", tasks.get(0).getName());
    }

    @Test
    void testSearchFound() {
        String result = planner.search("Tasker");
        assertNotNull(result);
        assertTrue(result.contains("Tasker"));
    }

    @Test
    void testSearchNotFound() {
        assertNull(planner.search("Nonexistent"));
    }

    @Test
    void testWriterGeneralPlanner() {
        try {
            Planner p = new Planner();
            p.addTask(new NormalTask("Study", "Monday", 2, "Read Chapter 1", "Library"));

            // Use JsonWriter instead of p.saveToFile()
            JsonWriter writer = new JsonWriter("./data/testGeneralPlanner.json");
            writer.open();
            writer.write(p);
            writer.close();

            // Then, use JsonReader to read it back and verify the data
            JsonReader reader = new JsonReader("./data/testGeneralPlanner.json");
            p = reader.read();
            assertEquals(1, p.getTasks().size());
            assertEquals("Study", p.getTasks().get(0).getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testClearTasks() {
        // Add a permanent task
        NormalTask permanentTask = new NormalTask("Permanent", "Friday", 4, "Permanent task", "Loc");
        planner.addTask(permanentTask);
        planner.clearTasks();
        assertEquals(1, planner.getTasks().size(), "Should only have 1 permanent task");
        assertEquals("Permanent", planner.getTasks().get(0).getName(), "Permanent task should be preserved");
    }

    @Test
    void testClearTasksNoPermanent() {
        planner.clearTasks();
        assertEquals(0, planner.getTasks().size(), "All tasks should be cleared when no permanent tasks exist");
    }

    @Test
    void testClearTasksAllPermanent() {
        // Clear existing tasks and add only permanent tasks
        planner.clearTasks();
        NormalTask permanentTask1 = new NormalTask("Permanent 1", "Friday", 4, "Permanent task 1", "Loc");
        NormalTask permanentTask2 = new NormalTask("Permanent 2", "Saturday", 5, "Permanent task 2", "Loc");
        planner.addTask(permanentTask1);
        planner.addTask(permanentTask2);
        planner.clearTasks();
        assertEquals(2, planner.getTasks().size(), "All permanent tasks should be preserved");
    }

    @Test
    void testClearTasksEmptyPlanner() {
        planner.clearTasks();
        assertEquals(0, planner.getTasks().size(), "Clearing an already empty planner should not cause errors");
    }

}