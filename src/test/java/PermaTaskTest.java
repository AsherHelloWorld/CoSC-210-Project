import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.PermTask;

public class PermaTaskTest {

    private PermTask perma;

    @BeforeEach
    void setup() {
        try {
        perma = new PermTask("Important", "Monday", 2, "Permanent task", "Office");
        } catch (Exception e) {
            //Never occurs
        }
    }

    @Test
    void testGetters() {
        assertEquals("Important", perma.getName());
        assertEquals("Monday", perma.getDate());
        assertEquals(2, perma.getTime());
        assertEquals("Permanent task", perma.getDescription());
        assertEquals("Office", perma.getLocation());
    }

    @Test
    void testSetters() {
        try {
        perma.setName("Critical");
        perma.setDate("Tuesday");
        perma.setTime(3);
        perma.setDescription("Updated permanent");
        perma.setLocation("Home");
        } catch (Exception e) {
            //Never occurs
        }

        assertEquals("Critical", perma.getName());
        assertEquals("Tuesday", perma.getDate());
        assertEquals(3, perma.getTime());
        assertEquals("Updated permanent", perma.getDescription());
        assertEquals("Home", perma.getLocation());
    }

    @Test
    void testDisplayAndSearch() {
        String display = perma.display();
        assertNotNull(display);

        String found = perma.search("Important");
        assertEquals(display, found);

        assertNull(perma.search("Missing"));
    }

    @Test
    void testJsonConstructor() {
        JSONObject obj = new JSONObject();
        obj.put("name", "JSON Task");
        obj.put("date", "Wed");
        obj.put("time", 4);
        obj.put("description", "JSON Desc");
        obj.put("location", "JSON Loc");

        try {
            PermTask p2 = new PermTask(obj);
            assertEquals("JSON Task", p2.getName());
            assertEquals("Wed", p2.getDate());
            assertEquals(4, p2.getTime());
            assertEquals("JSON Desc", p2.getDescription());
            assertEquals("JSON Loc", p2.getLocation());
        } catch (Exception e) {
            //Never occurs
        }

    }
}