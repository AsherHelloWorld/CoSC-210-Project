import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import model.Planner;
import model.Task;

public class PlannerTest {
    Planner testPlanner;
    Task testTask;

    @BeforeEach
    void setup() {
        testTask = new Task("Tasker", "Monday", 2, "Test task", "YMH");
        testPlanner = new Planner();
    }

    @Test
    void testAddTask() {
        testPlanner.addTask(testTask);
        assertTrue(testPlanner.getTasks().contains(testTask));
    }

    @Test
    void testGetTasks() {
        testPlanner.addTask(testTask);
        assertEquals(1, testPlanner.getTasks().size());
        assertEquals(testTask, testPlanner.getTasks().get(0));
    }
    
    @Test 
    void testSearch() {
        testPlanner.addTask(testTask);
        testPlanner.search("Tasker");

        //Makes sure that the search method does not throw an exception when searching for an existing task.
        assertDoesNotThrow(() -> testPlanner.search("Tasker"));
    }

    @Test
    void testSearchNotFound() {
        testPlanner.addTask(testTask);
        assertThrows(IllegalArgumentException.class, () -> testPlanner.search("NonExistingTask"));
    }
}

