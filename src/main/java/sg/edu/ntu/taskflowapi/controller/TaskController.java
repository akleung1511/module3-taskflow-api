
package sg.edu.ntu.taskflowapi.controller;

// Import Spring AI's ChatClient.
// ChatClient allows our application to send prompts to an AI model
// and receive AI-generated responses.
// In Task 6, we use it to summarise completed and pending tasks.
import org.springframework.ai.chat.client.ChatClient;

// Import the Task model and Service layer.
import sg.edu.ntu.taskflowapi.model.Task;
import sg.edu.ntu.taskflowapi.service.TaskService;

import java.util.Collection;

// Import Spring annotations for REST APIs.
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController allows this class to handle HTTP requests.
// @RequestMapping sets the base URL for all endpoints.
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ChatClient chatClient;

    // Constructor injection:
    // Spring provides both TaskService and ChatClient.
    public TaskController(TaskService taskService, ChatClient chatClient) {
        this.taskService = taskService;
        this.chatClient = chatClient;
    }

    // 1. GET /api/tasks
    // Retrieve all tasks.
    @GetMapping
    public ResponseEntity<Collection<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    // 2. GET /api/tasks/{id}
    // Retrieve one task using its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        Task task = taskService.findTaskById(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    // 3. POST /api/tasks
    // Create a new task.
    // Return HTTP 201 Created.
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {

        Task createdTask = taskService.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTask);
    }

    // 4. PUT /api/tasks/{id}
    // Update an existing task.
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {

        Task updatedTask = taskService.updateTask(id, task);

        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTask);
    }

    // 5. DELETE /api/tasks/{id}
    // Delete a task.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        if (taskService.findTaskById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        taskService.deleteTask(id);

        return ResponseEntity.ok().build();
    }

    // 6. PUT /api/tasks/{id}/complete
    // Mark an existing task as completed.
    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {

        Task completedTask = taskService.markTaskAsComplete(id);

        if (completedTask == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(completedTask);
    }

    // TASK 6: SPRING AI SUMMARY ENDPOINT
    // GET /api/tasks/summary
    // Summarise completed and pending tasks using AI.
    @GetMapping("/summary")
    public ResponseEntity<String> getTaskSummary() {

        // Retrieve all tasks from our service layer.
        Collection<Task> tasks = taskService.findAllTasks();

        // Build instructions to send to the AI model.
        String prompt = """
                Summarise the following tasks in short, plain English.
                Explain which tasks are completed and which are pending.

                Tasks:
                """ + tasks.toString();

        // Send the task list to OpenAI using Spring AI.
        String summary = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        // Return the AI-generated summary with HTTP 200.
        return ResponseEntity.ok(summary);
    }

}
