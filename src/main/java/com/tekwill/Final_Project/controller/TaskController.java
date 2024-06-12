package com.tekwill.Final_Project.controller;

import com.tekwill.Final_Project.dto.TaskDTO;
import com.tekwill.Final_Project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("api/tasks/all")
    private ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());

    }

    @GetMapping("api/tasks/{taskId}")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.findTaskById(taskId));
    }

    @GetMapping("api/tasks/project/{projectId}")
    public ResponseEntity<List<TaskDTO>> findTaskByProjectId(@PathVariable Integer projectId) {
        return ResponseEntity.ok(taskService.getAllTasksInProject(projectId));
    }

    @GetMapping("api/tasks/users/{userId}")
    public ResponseEntity<List<TaskDTO>> findTasksByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(taskService.getAllTasksOfUser(userId));
    }

    @PostMapping("api/tasks/new")
    public ResponseEntity<String> addTask(@RequestBody TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
        return ResponseEntity.ok("New Task was added!");
    }

    @PatchMapping("api/tasks/{taskId}/assign/users/{userId}")
    public ResponseEntity<String> assignTaskToUser(@PathVariable Integer taskId, @PathVariable Integer userId) {
        taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok("Task with ID: " + taskId + " assigned to user with ID: " + userId);
    }

    @PatchMapping("api/tasks/{taskId}/assign/projects/{projectId}")
    public ResponseEntity<String> assignTaskProject(@PathVariable Integer taskId, @PathVariable Integer projectId) {
        taskService.assignTaskToProject(taskId, projectId);
        return ResponseEntity.ok("Task with ID: " + taskId + " assigned to project with ID: " + projectId);
    }

    @PatchMapping("api/tasks/update/{taskId}")
    public ResponseEntity<String> updateTaskById(@PathVariable Integer taskId, @RequestBody TaskDTO taskDTO) {
        taskService.updateTaskData(taskId, taskDTO);
        return ResponseEntity.ok("Task with ID: " + taskId + " was updated!");
    }

    @PatchMapping("api/tasks/{taskId}/done")
    public ResponseEntity<String> setTaskStatusDone(@PathVariable Integer taskId) {
        taskService.taskDone(taskId);
        return ResponseEntity.ok("Task with ID: " + taskId + " finished!");
    }

    @PatchMapping("api/tasks/{taskId}/start")
    public ResponseEntity<String> setTaskStatusInProgress(@PathVariable Integer taskId) {
        taskService.startTask(taskId);
        return ResponseEntity.ok("Task with ID: " + taskId + " started!");
    }
    @DeleteMapping("api/tasks/remove/{taskId}")
    public ResponseEntity<String> removeTaskById(@PathVariable Integer taskId) {
        taskService.removeTaskById(taskId);
        return ResponseEntity.ok("Task was successfully removed!");
    }

}
