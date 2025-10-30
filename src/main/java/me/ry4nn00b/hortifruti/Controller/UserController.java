package me.ry4nn00b.hortifruti.Controller;

import jakarta.validation.Valid;
import me.ry4nn00b.hortifruti.Mapper.UserMapper;
import me.ry4nn00b.hortifruti.Model.DTOs.UserAuthDTO.LoginRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.UserAuthDTO.LoginResponseDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.UserAuthDTO.UserRequestDTO;
import me.ry4nn00b.hortifruti.Model.DTOs.UserAuthDTO.UserResponseDTO;
import me.ry4nn00b.hortifruti.Service.Interface.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    public UserController(IUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //ENDPOINT - TEST API
    @GetMapping("/")
    public String home() {
        return "API DE USUÁRIOS FUNCIONANDO!";
    }

    //ENDPOINT - Register User
    @PostMapping("/registrar")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody UserRequestDTO requestDTO,
            @RequestHeader("Authorization") String authHeader) {

        //Extract Token Header
        String token = authHeader.replace("Bearer ", "");

        //Send Token to Service
        UserResponseDTO responseDTO = userService.register(requestDTO, token);
        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - Login User
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO) {
        LoginResponseDTO responseDTO = userService.login(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    //ENDPOINT - Get User List
    @GetMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        List<UserResponseDTO> users = userService.listAllUsers();
        return ResponseEntity.ok(users);
    }

    //ENDPOINT - Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ENDPOINT - Delete User By ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        return userService.findUserById(id)
                .map(existing -> {
                    userService.deleteUser(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
