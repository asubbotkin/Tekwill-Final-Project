package com.tekwill.Final_Project.converter;

import com.tekwill.Final_Project.dto.UserDTO;
import com.tekwill.Final_Project.model.UserModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDtoModelConverter {

    public UserDTO userToDTO(UserModel model){
        return UserDTO.builder()
                .userId(model.getUser_id())
                .userName(model.getUserName())
                .password(model.getPassword())
                .build();
    }

    public UserModel userToModel(UserDTO dto){
        return UserModel.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .build();
    }
}
