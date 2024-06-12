package com.tekwill.Final_Project.service;

import com.tekwill.Final_Project.converter.TaskDtoModelConverter;
import com.tekwill.Final_Project.dto.TaskDTO;
import com.tekwill.Final_Project.model.TaskModel;
import com.tekwill.Final_Project.repository.ProjectRepository;
import com.tekwill.Final_Project.repository.TaskRepository;
import com.tekwill.Final_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

    public List<TaskDTO> getAllTasks(){
        return ((List<TaskModel>) taskRepository.findAll()).stream()
                .map(e -> TaskDtoModelConverter.taskToDTO(e))
                .toList();
    }
    public TaskDTO findTaskById(Integer taskId){
        return TaskDtoModelConverter.taskToDTO(taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public void addTask(TaskDTO taskDTO){
        TaskModel newTask = TaskModel.builder()
                .name(taskDTO.getName())
                .description(taskDTO.getDesc())
                .daysPerTask(taskDTO.getDaysPerTask())
                .status(taskDTO.getStatus())
                .build();
        taskRepository.save(newTask);

//        taskRepository.save(TaskDtoModelConverter.taskToModel(taskDTO));
    }

    public List<TaskDTO> getAllTasksInProject(Integer projectId){

        return taskRepository.findAllTasksInProject(projectId
                ).stream()
                .map(e -> TaskDtoModelConverter.taskToDTO(e))
                .toList();
    }

    public List<TaskDTO> getAllTasksOfUser(Integer userId){
        return taskRepository.findAllTasksOfUser(userId).stream()
                .map(e -> TaskDtoModelConverter.taskToDTO(e))
                .toList();
    }

    public void assignTaskToUser(Integer taskId, Integer userId){
        TaskModel newTask = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newTask.setUserModel(userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        taskRepository.save(newTask);
    }

    public void assignTaskToProject(Integer taskId, Integer projectId){
        TaskModel newTask = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newTask.setProjectModel(projectRepository.findById(projectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        taskRepository.save(newTask);
    }

    public void updateTaskData(Integer taskId, TaskDTO taskDTO) {
        TaskModel updatedTaskModel = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (taskDTO.getName() != null) updatedTaskModel.setName(taskDTO.getName());
        if (taskDTO.getDesc() != null) updatedTaskModel.setDescription(taskDTO.getDesc());
        if (taskDTO.getDaysPerTask() != null) updatedTaskModel.setDaysPerTask(taskDTO.getDaysPerTask());
        if (taskDTO.getStatus() != null) updatedTaskModel.setStatus(taskDTO.getStatus());
        taskRepository.save(updatedTaskModel);
    }


//    public void removeTaskById(Integer id){
//        taskRepository.deleteById(id);
//    }
}
