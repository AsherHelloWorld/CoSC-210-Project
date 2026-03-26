import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Event;

// NOTE: these tests might fail if lines (1) and (2) don't execute in the same millisecond.
public class EventTest {
    private Event e;
    private Date d;

    @BeforeEach
    public void runBefore() {
        e = new Event("task added to planner: Study [Monday at 2h]");  // (1)
        d = Calendar.getInstance().getTime();                           // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("task added to planner: Study [Monday at 2h]", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "task added to planner: Study [Monday at 2h]",
                e.toString());
    }
}
