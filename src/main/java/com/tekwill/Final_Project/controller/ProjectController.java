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

    @GetMapping("api/projects/{id}")
    public ResponseEntity<ProjectDTO> findProjectById(@PathVariable Integer id){
        return ResponseEntity.ok(projectService.findProjectById(id));
    }

    @PostMapping("api/projects/new")
    public ResponseEntity<String> addProject(@RequestBody ProjectDTO projectDTO){
        projectService.addProject(projectDTO);
        return ResponseEntity.ok("New project was added!");
    }

    @PatchMapping("api/projects/update/{id}")
    public ResponseEntity<String> updateProjectById(@PathVariable Integer id, @RequestBody ProjectDTO projectDTO){
        projectService.updateProjectData(id, projectDTO);
        return ResponseEntity.ok("Project was updated!");
    }

    @DeleteMapping("api/projects/remove/{id}")
    public ResponseEntity<String> removeProjectById(@PathVariable Integer id){
        projectService.removeProjectById(id);
        return ResponseEntity.ok("Project was successfully removed!");
    }

}
