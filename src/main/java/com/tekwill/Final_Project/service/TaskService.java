package com.tekwill.Final_Project.service;

import com.tekwill.Final_Project.converter.TaskDtoModelConverter;
import com.tekwill.Final_Project.dto.TaskDTO;
import com.tekwill.Final_Project.model.TaskModel;
import com.tekwill.Final_Project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

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
        taskRepository.save(TaskDtoModelConverter.taskToModel(taskDTO));
    }


    public void removeTaskById(Integer id){
        taskRepository.deleteById(id);
    }


}
