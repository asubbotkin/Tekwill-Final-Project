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
    public TaskDTO findTaskById(Integer id){
        return TaskDtoModelConverter.taskToDTO(taskRepository.findById(id)
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

    public List<TaskDTO> getAllTasksInProject(Integer id){

        return taskRepository.findAllTasksInProject(id
                ).stream()
                .map(e -> TaskDtoModelConverter.taskToDTO(e))
                .toList();
    }

    public List<TaskDTO> getAllTasksOfUser(Integer id){
        return taskRepository.findAllTasksOfUser(id).stream()
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

    public void removeTaskById(Integer id){
        taskRepository.deleteById(id);
    }
}
