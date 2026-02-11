package auca.ac.rw.question5_task_management_api.controller;

import auca.ac.rw.question5_task_management_api.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        // Pre-loading sample tasks
        tasks.add(new Task(1L, "Complete Assignment", "Finish Spring Boot questions", false, "HIGH", "2024-02-15"));
        tasks.add(new Task(2L, "Grocery Shopping", "Buy milk and bread", false, "MEDIUM", "2024-02-10"));
        tasks.add(new Task(3L, "Call Mom", "Weekly catch up", true, "LOW", "2024-02-09"));
        tasks.add(new Task(4L, "Prepare Presentation", "Slides for Monday meeting", false, "HIGH", "2024-02-12"));
        tasks.add(new Task(5L, "Clean Room", "Organize desk and shelves", true, "LOW", "2024-02-08"));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted() == completed) {
                result.add(t);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) {
                result.add(t);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        tasks.add(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task details) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setTitle(details.getTitle());
                t.setDescription(details.getDescription());
                t.setCompleted(details.isCompleted());
                t.setPriority(details.getPriority());
                t.setDueDate(details.getDueDate());
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskComplete(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setCompleted(true);
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        Task toRemove = null;
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                toRemove = t;
                break;
            }
        }
        
        if (toRemove != null) {
            tasks.remove(toRemove);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}