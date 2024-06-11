package com.tekwill.Final_Task.controller;

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

    @PostMapping("api/tasks/new")
    public ResponseEntity<String> addTask(@RequestBody TaskDTO taskDTO){
        taskService.addTask(taskDTO);
        return ResponseEntity.ok("New Task was added!");
    }

//    @PatchMapping("api/tasks/update/{id}")
//    public ResponseEntity<String> updateTaskById(@PathVariable Integer id, @RequestBody TaskDTO TaskDTO){
//        taskService.updateTask(id, TaskDTO);
//        return ResponseEntity.ok("Task was updated!");
//    }

    @DeleteMapping("api/tasks/remove/{id}")
    public ResponseEntity<String> removeTaskById(@PathVariable Integer id){
        taskService.removeTaskById(id);
        return ResponseEntity.ok("Task was successfully removed!");
    }

}
