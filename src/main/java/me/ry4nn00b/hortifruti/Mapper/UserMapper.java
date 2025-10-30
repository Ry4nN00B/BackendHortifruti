package me.ry4nn00b.hortifruti.Mapper;

import me.ry4nn00b.hortifruti.Model.DTOs.UserAuthDTO.LoginResponseDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.UserAuthDTO.UserRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.UserAuthDTO.UserResponseDTO;
import me.ry4nn00b.hortifruti.Model.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    //UserRequestDTO -> UserModel
    public UserModel toModel(UserRequestDTO dto) {
        return modelMapper.map(dto, UserModel.class);
    }

    //UserModel -> UserResponseDTO
    public UserResponseDTO toResponseDTO(UserModel model) {
        return modelMapper.map(model, UserResponseDTO.class);
    }

    //UserModel -> LoginResponseDTO
    public LoginResponseDTO toLoginResponseDTO(UserModel model, String token) {
        LoginResponseDTO dto = modelMapper.map(model, LoginResponseDTO.class);
        dto.setToken(token);
        return dto;
    }

}
