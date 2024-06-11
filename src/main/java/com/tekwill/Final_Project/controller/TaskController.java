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
    private ResponseEntity<List<TaskDTO>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());

    }
    @GetMapping("api/tasks/{id}")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable Integer id){
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @GetMapping("api/tasks/project/{id}")
    public ResponseEntity<List<TaskDTO>> findTaskByProjectId(@PathVariable Integer id){
        return ResponseEntity.ok(taskService.getAllTasksInProject(id));
    }

    @GetMapping("api/tasks/user/{id}")
    public ResponseEntity<List<TaskDTO>> findTasksByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(taskService.getAllTasksOfUser(id));
    }

    @PostMapping("api/tasks/new")
    public ResponseEntity<String> addTask(@RequestBody TaskDTO taskDTO){
        taskService.addTask(taskDTO);
        return ResponseEntity.ok("New Task was added!");
    }

    @PatchMapping("api/tasks/{taskId}/users/{userId}")
    public ResponseEntity<String> assignTaskToUser(@PathVariable Integer taskId, @PathVariable Integer userId){
        taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok("Task Assigned!");
    }

    @PatchMapping("api/tasks/{taskId}/projects/{projectId}")
    public ResponseEntity<String> assignTaskProject(@PathVariable Integer taskId, @PathVariable Integer projectId){
        taskService.assignTaskToProject(taskId, projectId);
        return ResponseEntity.ok("Task Assigned!");
    }

    //    @PatchMapping("api/tasks/update/{id}")
//    public ResponseEntity<String> updateTaskById(@PathVariable Integer id, @RequestBody TaskDTO){
//        taskService.updateTask(id, taskDTO);
//        return ResponseEntity.ok("Task was updated!");
//    }

    @DeleteMapping("api/tasks/remove/{id}")
    public ResponseEntity<String> removeTaskById(@PathVariable Integer id){
        taskService.removeTaskById(id);
        return ResponseEntity.ok("Task was successfully removed!");
    }

}
