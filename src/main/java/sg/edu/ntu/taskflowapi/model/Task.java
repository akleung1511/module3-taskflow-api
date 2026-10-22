package sg.edu.ntu.taskflowapi.model;

// Import Lombok annotations.
// Lombok helps reduce repetitive Java code (boilerplate).

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data automatically generates:
// 1. Getters - read values from private fields
// 2. Setters - update values in private fields
// 3. toString() - display object information
// 4. equals() and hashCode() - compare objects
@Data

// Generates an empty constructor.
// Example: Task task = new Task();
@NoArgsConstructor

// Generates a constructor with all fields.
// Example: Task task = new Task(1L, "Study Java", false);
@AllArgsConstructor

// This is a POJO (Plain Old Java Object).
// It represents one task in our TaskFlow application.
//
// This is the MODEL layer.
// Its responsibility is to hold task data,
// not to handle API requests or business logic.
public class Task {

    // Unique identifier for each task.
    // Long is an object type and can hold null.
    // Example: 1L, 2L, 3L
    private Long id;

    // The name or description of the task.
    // Example: "Complete Java Assignment"
    private String title;

    // Stores whether the task is completed.
    // false = Task is pending
    // true  = Task is completed
    private boolean completed;

}
