package task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    @Test
    void testAddSingleTask() {
        TaskService service = new TaskService();
        Task task = new Task("1", "Homework", "Finish Unit Tests");

        service.addTask(task);

        assertNotNull(service.getTask("1"));
        assertEquals("Homework", service.getTask("1").getName());
    }

    @Test
    void testAddMultipleTasks() {
        TaskService service = new TaskService();
        Task t1 = new Task("1", "Homework", "Finish Unit Tests");
        Task t2 = new Task("2", "Laundry", "Wash and dry clothes");

        service.addTask(t1);
        service.addTask(t2);

        assertNotNull(service.getTask("1"));
        assertNotNull(service.getTask("2"));
        assertEquals("Laundry", service.getTask("2").getName());
    }

    @Test
    void testAddNullTaskRejected() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.addTask(null));
    }

    @Test
    void testDuplicateTaskIdRejected() {
        TaskService service = new TaskService();
        Task t1 = new Task("1", "Homework", "Finish Unit Tests");
        Task t2 = new Task("1", "Laundry", "Wash and dry clothes");

        service.addTask(t1);

        assertThrows(IllegalArgumentException.class, () -> service.addTask(t2));
    }

    @Test
    void testUpdateTaskFields() {
        TaskService service = new TaskService();
        Task task = new Task("1", "Homework", "Finish Unit Tests");
        service.addTask(task);

        service.updateName("1", "Project");
        service.updateDescription("1", "Complete TaskService milestone");

        Task updated = service.getTask("1");
        assertEquals("Project", updated.getName());
        assertEquals("Complete TaskService milestone", updated.getDescription());
    }

    @Test
    void testDeleteTask() {
        TaskService service = new TaskService();
        Task task = new Task("1", "Homework", "Finish Unit Tests");
        service.addTask(task);

        service.deleteTask("1");

        assertNull(service.getTask("1"));
    }

    @Test
    void testUpdateNonexistentTaskRejected_Name() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.updateName("DOES_NOT_EXIST", "Project"));
    }

    @Test
    void testUpdateNonexistentTaskRejected_Description() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.updateDescription("DOES_NOT_EXIST", "Desc"));
    }
}

