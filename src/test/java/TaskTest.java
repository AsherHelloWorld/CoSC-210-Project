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
}
