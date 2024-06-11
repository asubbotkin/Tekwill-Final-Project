package com.tekwill.Final_Project.converter;

import com.tekwill.Final_Project.dto.TaskDTO;
import com.tekwill.Final_Project.model.TaskModel;
import com.tekwill.Final_Project.repository.ProjectRepository;
import com.tekwill.Final_Project.repository.UserRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@UtilityClass
public class TaskDtoModelConverter {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;

    public TaskDTO taskToDTO(TaskModel model){
        return TaskDTO.builder()
                .taskId(model.getTaskId())
                .name(model.getName())
                .desc(model.getDescription())
                .daysPerTask(model.getDaysPerTask())
                .status(model.getStatus())
                .projectId(model.getProjectModel() == null ? null : model.getProjectModel().getProjectId())
                .userId((model.getUserModel() == null ? null : model.getUserModel().getUserId()))
                .build();
    }

    public TaskModel taskToModel(TaskDTO dto){
        return TaskModel.builder()
                .name(dto.getName())
                .description(dto.getDesc())
                .daysPerTask(dto.getDaysPerTask())
                .status(dto.getStatus())
                .projectModel(projectRepository.findById(dto.getProjectId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .userModel(userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .build();
    }

//    public TaskModel newTaskToModel(TaskDTO dto){
//        return TaskModel.builder()
//                .name(dto.getName())
//                .description(dto.getDesc())
//                .daysPerTask(dto.getDaysPerTask())
//                .status(dto.getStatus())
//                .build();
//    }
}
