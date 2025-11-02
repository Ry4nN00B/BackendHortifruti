package me.ry4nn00b.hortifruti.Service.Interface;

import me.ry4nn00b.hortifruti.DTOs.UserAuthDTO.LoginRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.UserAuthDTO.LoginResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.UserAuthDTO.UserRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.UserAuthDTO.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserResponseDTO register(UserRequestDTO registerRequestDTO, String managerEmail);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    List<UserResponseDTO> listAllUsers();
    Optional<UserResponseDTO> findUserById(String userId);
    void deleteUser(String userId);

}
