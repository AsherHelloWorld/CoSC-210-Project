import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.NormalTask;

public class TaskTest {

    private NormalTask tester;

    @BeforeEach
    void setup() {
        try {
        tester = new NormalTask("Tasker", "Monday", 2, "Test task", "YMH");
        } catch (Exception e) {
            //Never occurs
        }
    }

    @Test
    void testGetters() {
        assertEquals("Tasker", tester.getName());
        assertEquals("Monday", tester.getDate());
        assertEquals(2, tester.getTime());
        assertEquals("Test task", tester.getDescription());
        assertEquals("YMH", tester.getLocation());
    }

    @Test
    void testSetters() {
        try {
        tester.setName("NewTasker");
        tester.setDate("Tuesday");
        tester.setTime(3);
        tester.setDescription("Updated task");
        tester.setLocation("NewYMH");

        assertEquals("NewTasker", tester.getName());
        assertEquals("Tuesday", tester.getDate());
        assertEquals(3, tester.getTime());
        assertEquals("Updated task", tester.getDescription());
        assertEquals("NewYMH", tester.getLocation());
        } catch (Exception e) {
            //Never occurs
        }
    }

    @Test
    void testDisplayAndSearch() {
        String display = tester.display();
        assertNotNull(display);

        String found = tester.search("Tasker");
        assertNotNull(found);
        assertEquals(display, found);

        assertEquals(null, tester.search("Missing"));
    }

    @Test
    void testInvalidDate() {
        try {
            tester.setDate("Funday");
        } catch (Exception e) {
            assertEquals("InvalidTaskDayException", e.getClass().getSimpleName());
            assertEquals("\"Funday\" is not a valid weekday. Must be one of: [Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday]", e.getMessage());
        }
    }

    @Test 
    void testInvalidTime() {
        try {
            tester.setTime(25);
        } catch (Exception e) {
            assertEquals("InvalidTaskDurationException", e.getClass().getSimpleName());
            assertEquals("Task duration must be a positive integer, but got: 25", e.getMessage());
        }
    }

    @Test 
    void testInvalidTimeNegative() {
        try {
            tester.setTime(-5);
        } catch (Exception e) {
            assertEquals("InvalidTaskDurationException", e.getClass().getSimpleName());
            assertEquals("Task duration must be a positive integer, but got: -5", e.getMessage());
        }
    }
}
