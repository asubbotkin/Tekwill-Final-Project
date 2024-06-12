package com.tekwill.Final_Project.service;

import com.tekwill.Final_Project.converter.ProjectDtoModelConverter;
import com.tekwill.Final_Project.dto.ProjectDTO;
import com.tekwill.Final_Project.model.ProjectModel;
import com.tekwill.Final_Project.repository.ProjectRepository;
import com.tekwill.Final_Project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TaskRepository taskRepository;

    public List<ProjectDTO> getAllProjects() {
        return ((List<ProjectModel>) projectRepository.findAll()).stream()
                .map(e -> ProjectDtoModelConverter.projectToDTO(e))
                .toList();
    }

    public ProjectDTO findProjectById(Integer projectId) {
        return ProjectDtoModelConverter.projectToDTO(projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public void addProject(ProjectDTO projectDTO) {
        projectRepository.save(ProjectDtoModelConverter.projectToModel(projectDTO));
    }

    public void updateProjectData(Integer projectId, ProjectDTO projectDTO) {
        ProjectModel updatedProjectModel = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (projectDTO.getName() != null) updatedProjectModel.setName(projectDTO.getName());
        if (projectDTO.getDesc() != null) updatedProjectModel.setDescription(projectDTO.getDesc());
        if (projectDTO.getStartDate() != null) updatedProjectModel.setStartDate(projectDTO.getStartDate());
        if (projectDTO.getExpDate() != null) updatedProjectModel.setExpirationDate(projectDTO.getExpDate());
        projectRepository.save(updatedProjectModel);
    }

    public void removeTaskFromProject(Integer projectId, Integer taskId) {
        ProjectModel updatedProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updatedProject.getProjectTasks().remove(taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        projectRepository.save(updatedProject);
    }

//    public void createTaskInProject(Integer projectId, TaskDTO taskDTO) {
//        ProjectModel updatedProject = projectRepository.findById(projectId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        TaskModel newTask = TaskDtoModelConverter.newTaskToModel(taskDTO);
//        taskRepository.save(newTask);
//        updatedProject.getProjectTasks().add(newTask);
//        projectRepository.save(updatedProject);
//    }

    public void removeProjectById(Integer projectId) {
        projectRepository.deleteById(projectId);
    }
}
