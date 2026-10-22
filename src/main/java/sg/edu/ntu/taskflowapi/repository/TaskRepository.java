
package sg.edu.ntu.taskflowapi.repository;

// HashMap stores data as key-value pairs.
// Map is the interface implemented by HashMap.
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

// Import our Task model from Task.java.
import sg.edu.ntu.taskflowapi.model.Task;

// @Repository tells Spring that this class
// is responsible for storing and retrieving data.
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {

    // Store tasks in memory using a HashMap.
    // Key   = Long (task ID)
    // Value = Task (task object)
    //
    // Example:
    // 1 -> Task(1, "Study Java", false)
    // 2 -> Task(2, "Finish assignment", true)
    //
    // Initialise immediately so it is never null.
    private final Map<Long, Task> tasks = new HashMap<>();

    // Counter used to generate unique task IDs.
    private long nextId = 1L;

    // Return all stored tasks.
    public Collection<Task> findAll() {
        return tasks.values();
    }

    // Find a task using its ID.
    // Returns null if the ID does not exist.
    public Task findById(Long id) {
        return tasks.get(id);
    }

    // Save a task.
    // New tasks receive a generated ID.
    // Existing tasks keep their ID.
    public Task save(Task task) {

        if (task.getId() == null) {
            task.setId(nextId++);
        }

        tasks.put(task.getId(), task);

        return task;
    }

    // Delete a task using its ID.
    public void deleteById(Long id) {
        tasks.remove(id);
    }

}
