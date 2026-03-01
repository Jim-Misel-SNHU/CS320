package task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {

    private Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Task ID already exists");
        }
        tasks.put(task.getTaskId(), task);
    }

    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }

    // Helpful for testing "adding and retrieving a task"
    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    public void updateName(String taskId, String name) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task ID not found");
        }
        task.setName(name);
    }

    public void updateDescription(String taskId, String description) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task ID not found");
        }
        task.setDescription(description);
    }
}

