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

    @GetMapping("api/tasks/{id}")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.findTaskById(taskId));
    }

    @GetMapping("api/tasks/project/{id}")
    public ResponseEntity<List<TaskDTO>> findTaskByProjectId(@PathVariable Integer projectId) {
        return ResponseEntity.ok(taskService.getAllTasksInProject(projectId));
    }

    @GetMapping("api/tasks/users/{id}")
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

    @PatchMapping("api/tasks/update/{id}")
    public ResponseEntity<String> updateTaskById(@PathVariable Integer taskId, @RequestBody TaskDTO taskDTO) {
        taskService.updateTaskData(taskId, taskDTO);
        return ResponseEntity.ok("Task with ID: " + taskId + " was updated!");
    }

//    @DeleteMapping("api/tasks/remove/{id}")
//    public ResponseEntity<String> removeTaskById(@PathVariable Integer id) {
//        taskService.removeTaskById(id);
//        return ResponseEntity.ok("Task was successfully removed!");
//    }

}
