package com.tekwill.Final_Project.service;

import com.tekwill.Final_Project.converter.ProjectDtoModelConverter;
import com.tekwill.Final_Project.dto.ProjectDTO;
import com.tekwill.Final_Project.model.ProjectModel;
import com.tekwill.Final_Project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects(){
        return ((List<ProjectModel>) projectRepository.findAll()).stream()
                .map(e -> ProjectDtoModelConverter.projectToDTO(e))
                .toList();
    }

    public ProjectDTO findProjectById(Integer id){
        return ProjectDtoModelConverter.projectToDTO(projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public void addProject(ProjectDTO projectDTO){
        projectRepository.save(ProjectDtoModelConverter.projectToModel(projectDTO));
    }

    public void updateProjectData(Integer id, ProjectDTO projectDTO){
        ProjectModel updatedProjectModel = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(projectDTO.getName() != null) updatedProjectModel.setName(projectDTO.getName());
        if(projectDTO.getDesc() != null) updatedProjectModel.setDescription(projectDTO.getDesc());
        if(projectDTO.getStartDate() != null) updatedProjectModel.setStartDate(projectDTO.getStartDate());
        if(projectDTO.getExpDate() != null) updatedProjectModel.setExpirationDate(projectDTO.getExpDate());
        projectRepository.save(updatedProjectModel);
    }


    public void removeProjectById(Integer id){
        projectRepository.deleteById(id);
    }
}
