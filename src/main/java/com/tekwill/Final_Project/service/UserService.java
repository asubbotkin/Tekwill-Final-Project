package com.tekwill.Final_Project.service;

import com.tekwill.Final_Project.converter.UserDtoModelConverter;
import com.tekwill.Final_Project.dto.UserDTO;
import com.tekwill.Final_Project.model.UserModel;
import com.tekwill.Final_Project.repository.TaskRepository;
import com.tekwill.Final_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;


    public List<UserDTO> getAllUsers() {
        return ((List<UserModel>) userRepository.findAll()).stream()
                .map(e -> UserDtoModelConverter.userToDTO(e))
                .toList();
    }

    public UserDTO findUserById(int id) {
        return UserDtoModelConverter.userToDTO(userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
    public void addUser(UserDTO userDTO) {
        userRepository.save(UserDtoModelConverter.userToModel(taskRepository, userDTO));
    }

    public void updateUserData(Integer id, UserDTO userDTO) {
        UserModel updatedUserModel = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(userDTO.getUserName() != null) updatedUserModel.setUserName(userDTO.getUserName());
        if(userDTO.getPassword() != null) updatedUserModel.setPassword(userDTO.getPassword());
        userRepository.save(updatedUserModel);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
