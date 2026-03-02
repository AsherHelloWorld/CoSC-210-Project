import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.NormalTask;
import model.PermTask;
import model.Planner;
import model.Task;
import persistence.JsonReader;
import persistence.JsonWriter;

public class PlannerTest {

    private Planner planner;
    private NormalTask testTask;
    private static final String TEST_FILE = "data/testPlanner.json";

    @BeforeEach
    void setUp() {
        testTask = new NormalTask("Tasker", "Monday", 2, "Test task", "YMH");
        planner = new Planner();
        planner.addTask(testTask);
        planner.addTask(new NormalTask("Task 1", "Monday", 1, "Desc 1", "Loc 1"));
        planner.addTask(new NormalTask("Task 2", "Tuesday", 2, "Desc 2", "Loc 2"));
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void testAddTaskNormalAndPerma() {
        NormalTask n = new NormalTask("Normal", "Wed", 3, "Desc", "Loc");
        PermTask p = new PermTask("Permanent", "Fri", 4, "Desc", "Office");

        planner.addTask(n);
        planner.addTask(p);

        List<Task> tasks = planner.getTasks();
        assertTrue(tasks.contains(n));
        assertTrue(tasks.contains(p));
    }

    @Test
    void testGetTasks() {
        List<Task> tasks = planner.getTasks();
        assertEquals(3, tasks.size());
        assertEquals("Tasker", tasks.get(0).getName());
    }

    @Test
    void testSearch() {
        assertNotNull(planner.search("Tasker"));
        assertNull(planner.search("Missing"));

        PermTask p = new PermTask("Important", "Monday", 1, "Desc", "Loc");
        planner.addTask(p);
        assertEquals(p.display(), planner.search("Important"));
    }

    @Test
    void testClearTasks() {
        PermTask p = new PermTask("Permanent", "Fri", 4, "Desc", "Loc");
        planner.addTask(p);

        planner.clearTasks();

        List<Task> remaining = planner.getTasks();
        assertEquals(1, remaining.size());
        assertTrue(remaining.get(0) instanceof PermTask);
        assertEquals("Permanent", remaining.get(0).getName());
    }

    @Test
    void testClearTasksNoPermanent() {
        planner.clearTasks();
        assertEquals(0, planner.getTasks().size());
    }

    @Test
    void testClearTasksAllPermanent() {
        planner = new Planner();
        PermTask p1 = new PermTask("Permanent 1", "Fri", 4, "Desc1", "Loc");
        PermTask p2 = new PermTask("Permanent 2", "Sat", 5, "Desc2", "Loc");
        planner.addTask(p1);
        planner.addTask(p2);

        planner.clearTasks();
        List<Task> remaining = planner.getTasks();
        assertEquals(2, remaining.size());
        assertTrue(remaining.stream().allMatch(t -> t instanceof PermTask));
    }

    @Test
    void testJsonWriterAndReader() {
        try {
            Planner p = new Planner();
            p.addTask(new NormalTask("Study", "Mon", 2, "Read 1", "Library"));
            p.addTask(new PermTask("Permanent Study", "Tue", 3, "Read 2", "Library"));

            JsonWriter writer = new JsonWriter(TEST_FILE);
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader(TEST_FILE);
            Planner loaded = reader.read();

            assertEquals(2, loaded.getTasks().size());
            assertTrue(loaded.getTasks().stream().anyMatch(t -> t.getName().equals("Study")));
            assertTrue(loaded.getTasks().stream().anyMatch(t -> t.getName().equals("Permanent Study")));
        } catch (IOException e) {
            fail("IOException should not occur");
        }
    }
}