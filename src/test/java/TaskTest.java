import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.NormalTask;
import model.Task;


public class TaskTest {

    NormalTask tester;

    @BeforeEach
    void setup() {
        tester = new NormalTask("Tasker", "Monday", 2, "Test task", "YMH");
    }

    @Test
    public void testName() {
        assertEquals("Tasker", tester.getName());
    }

    @Test
    public void testDate() {
        assertEquals("Monday", tester.getDate());
    }

    @Test
    public void testTime() {
        assertEquals(2, tester.getTime());
    }

    @Test 
    public void testDescription() {
        assertEquals("Test task", tester.getDescription());
    }

    @Test
    public void testLocation() {
        assertEquals("YMH", tester.getLocation());
    }

    @Test
    public void testSetName() {
        tester.setName("NewTasker");
        assertEquals("NewTasker", tester.getName());
    }

    @Test
    public void testSetDate() {
        tester.setDate("Tuesday");
        assertEquals("Tuesday", tester.getDate());
    }

    @Test
    public void testSetTime() {
        tester.setTime(3);
        assertEquals(3, tester.getTime());
    }

    @Test
    public void testSetDescription() {
        tester.setDescription("Updated task");
        assertEquals("Updated task", tester.getDescription());
    }

    @Test
    public void testSetLocation() {
        tester.setLocation("NewYMH");
        assertEquals("NewYMH", tester.getLocation());
    }

    @Test
    public void testDisplay() {
        tester.display();   // just check it runs
    }
}
