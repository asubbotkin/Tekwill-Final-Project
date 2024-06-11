package com.tekwill.Final_Project.converter;

import com.tekwill.Final_Project.dto.UserDTO;
import com.tekwill.Final_Project.model.TaskModel;
import com.tekwill.Final_Project.model.UserModel;
import com.tekwill.Final_Project.repository.TaskRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@UtilityClass
public class UserDtoModelConverter {

    public UserDTO userToDTO(UserModel model){
        return UserDTO.builder()
                .userId(model.getUser_id())
                .userName(model.getUserName())
                .password(model.getPassword())
                .build();
    }

    public UserModel userToModel(TaskRepository taskRepository, UserDTO dto){
        return UserModel.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .build();
    }
}
