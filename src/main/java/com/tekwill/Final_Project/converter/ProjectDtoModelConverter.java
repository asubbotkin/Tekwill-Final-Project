package com.tekwill.Final_Project.converter;

import com.tekwill.Final_Project.dto.ProjectDTO;
import com.tekwill.Final_Project.model.ProjectModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectDtoModelConverter {
    public ProjectDTO projectToDTO(ProjectModel  model){
        return ProjectDTO.builder()
                .projectId(model.getProjectId())
                .name(model.getName())
                .desc(model.getDescription())
                .startDate(model.getStartDate())
                .expDate(model.getExpirationDate())
                .build();

    }

    public ProjectModel projectToModel(ProjectDTO dto){
        return ProjectModel.builder()
                .name(dto.getName())
                .description(dto.getDesc())
                .startDate(dto.getStartDate())
                .expirationDate(dto.getExpDate())
                .build();
    }
}
