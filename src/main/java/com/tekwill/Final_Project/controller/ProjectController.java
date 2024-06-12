package com.tekwill.Final_Project.controller;

import com.tekwill.Final_Project.dto.ProjectDTO;
import com.tekwill.Final_Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/api/projects/all")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){

        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("api/projects/{projectId}")
    public ResponseEntity<ProjectDTO> findProjectById(@PathVariable Integer projectId){
        return ResponseEntity.ok(projectService.findProjectById(projectId));
    }

    @PostMapping("api/projects/new")
    public ResponseEntity<String> addProject(@RequestBody ProjectDTO projectDTO){
        projectService.addProject(projectDTO);
        return ResponseEntity.ok("New project was added!");
    }

    @PatchMapping("api/projects/update/{projectId}")
    public ResponseEntity<String> updateProjectById(@PathVariable Integer projectId, @RequestBody ProjectDTO projectDTO){
        projectService.updateProjectData(projectId, projectDTO);
        return ResponseEntity.ok("Project with ID: " + projectId + " was updated!");
    }

    //    @PatchMapping("api/projects/{projectId}/tasks/new")
//    public ResponseEntity<String> newTaskInProject(@PathVariable Integer projectId, @RequestBody TaskDTO taskDTO){
//        projectService.createTaskInProject(projectId, taskDTO);
//        return ResponseEntity.ok("New task was added!");
//    }

    @DeleteMapping("api/projects/{projectId}/remove/task/{taskId}")
    public ResponseEntity<String> deleteTaskFromProject(@PathVariable Integer projectId, @PathVariable Integer taskId){
        projectService.removeTaskFromProject(projectId, taskId);
        return ResponseEntity.ok("Task with ID: " + taskId + " was removed from project with ID: " + projectId);
    }

    @DeleteMapping("api/projects/remove/{projectId}")
    public ResponseEntity<String> removeProjectById(@PathVariable Integer projectId){
        projectService.removeProjectById(projectId);
        return ResponseEntity.ok("Project with ID: " + projectId + " was successfully removed!");
    }

}
