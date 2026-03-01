package task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testTaskIdShorterThanTenAccepted() {
        Task task = new Task("12345", "Homework", "Finish Unit Tests");
        assertNotNull(task);
    }

    @Test
    void testTaskIdExactlyTenAccepted() {
        Task task = new Task("1234567890", "Homework", "Finish Unit Tests");
        assertNotNull(task);
    }

    @Test
    void testTaskIdLongerThanTenRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "Homework", "Finish Unit Tests");
        });
    }

    @Test
    void testTaskIdNullRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Homework", "Finish Unit Tests");
        });
    }

    @Test
    void testNameTooLongRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "123456789012345678901", "Finish Unit Tests"); // 21 chars
        });
    }

    @Test
    void testNameNullRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", null, "Finish Unit Tests");
        });
    }

    @Test
    void testDescriptionTooLongRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "Homework",
                    "123456789012345678901234567890123456789012345678901"); // 51 chars
        });
    }

    @Test
    void testDescriptionNullRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "Homework", null);
        });
    }

@Test
    void testBoundaryLengthsAccepted() {
        String name20 = "12345678901234567890"; // 20 chars
        String desc50 = "12345678901234567890123456789012345678901234567890"; // 50 chars
        Task t = new Task("1", name20, desc50);
        assertEquals(name20, t.getName());
        assertEquals(desc50, t.getDescription());
    }

    @Test
    void testSettersValidateAndUpdate() {
        Task t = new Task("1", "Homework", "Finish Unit Tests");

        // valid
        t.setName("Project");
        t.setDescription("Complete milestone tests");
        assertEquals("Project", t.getName());
        assertEquals("Complete milestone tests", t.getDescription());

        // invalid
        assertThrows(IllegalArgumentException.class, () -> t.setName(null));
        assertThrows(IllegalArgumentException.class, () -> t.setName("123456789012345678901")); // 21
        assertThrows(IllegalArgumentException.class, () -> t.setDescription(null));
        assertThrows(IllegalArgumentException.class, () ->
            t.setDescription("123456789012345678901234567890123456789012345678901") // 51
        );
    }
}