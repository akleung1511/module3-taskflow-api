
package sg.edu.ntu.taskflowapi.service;

import java.util.Collection;

// Import the Task model and TaskRepository.
import sg.edu.ntu.taskflowapi.model.Task;
import sg.edu.ntu.taskflowapi.repository.TaskRepository;

// @Service tells Spring that this class
// contains the application's business logic.
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    // Repository used to store and retrieve tasks.
    private final TaskRepository taskRepository;

    // Constructor Injection:
    // Spring automatically supplies TaskRepository
    // when it creates TaskService.
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. FIND ALL TASKS
    // Retrieve all tasks from the repository.
    public Collection<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    // 2. FIND TASK BY ID
    // Return the task matching the given ID.
    // Returns null if the task does not exist.
    public Task findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // 3. CREATE TASK
    // Set ID to null so the repository generates
    // a new unique ID.
    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    // 4. UPDATE TASK
    // First find the existing task.
    // Update its fields, then save it.
    public Task updateTask(Long id, Task task) {

        Task existingTask = taskRepository.findById(id);

        if (existingTask == null) {
            return null;
        }

        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted(task.isCompleted());

        return taskRepository.save(existingTask);
    }

    // 5. DELETE TASK
    // Remove the task using its ID.
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // 6. MARK TASK AS COMPLETE
    // Find the task, set completed to true,
    // and save it back to the repository.
    public Task markTaskAsComplete(Long id) {

        Task task = taskRepository.findById(id);

        if (task == null) {
            return null;
        }

        task.setCompleted(true);

        return taskRepository.save(task);
    }
}
