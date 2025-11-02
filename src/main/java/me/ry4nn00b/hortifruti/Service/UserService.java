package me.ry4nn00b.hortifruti.Service;

import me.ry4nn00b.hortifruti.Security.TokenManager;
import me.ry4nn00b.hortifruti.Enum.Role;
import me.ry4nn00b.hortifruti.Mapper.UserMapper;
import me.ry4nn00b.hortifruti.DTOs.UserAuthDTO.LoginRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.UserAuthDTO.LoginResponseDTO;
import me.ry4nn00b.hortifruti.DTOs.UserAuthDTO.UserRequestDTO;
import me.ry4nn00b.hortifruti.DTOs.UserAuthDTO.UserResponseDTO;
import me.ry4nn00b.hortifruti.Model.UserModel;
import me.ry4nn00b.hortifruti.Repository.IUserRepository;
import me.ry4nn00b.hortifruti.Service.Interface.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(IUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //Register User
    public UserResponseDTO register(UserRequestDTO registerRequestDTO, String managerToken) {

        String managerEmail = TokenManager.getEmailFromToken(managerToken);

        UserModel manager = userRepository.findByEmail(managerEmail)
                .orElseThrow(() -> new RuntimeException("Hortifruti Erro: Gerente não encontrado."));

        //Check Role
        if (manager.getRoles().stream().noneMatch(r -> r == Role.GERENTE)) {
            throw new RuntimeException("Hortifruti Erro: Apenas gerentes podem registrar novos usuários.");
        }

        //Check Email Already Exists
        if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Hortifruti Erro: Este email já foi cadastrado.");
        }

        //Validity Roles
        Set<String> allowedRoles = Set.of("GERENTE", "ESTOQUISTA", "OPERADOR");
        if (registerRequestDTO.getRoles() != null) {
            for (String role : registerRequestDTO.getRoles()) {
                if (!allowedRoles.contains(role.toUpperCase())) {
                    throw new RuntimeException("Hortifruti Erro: Cargo inválido! Apenas GERENTE, ESTOQUISTA ou OPERADOR.");
                }
            }
        }

        //DTO -> Model
        UserModel user = userMapper.toModel(registerRequestDTO);

        //Encrypt Password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = registerRequestDTO.getRoles() != null
                ? registerRequestDTO.getRoles().stream()
                .map(String::toUpperCase)
                .map(Role::valueOf)
                .collect(Collectors.toSet())
                : new HashSet<>();

        user.setRoles(roles);

        userRepository.save(user);

        return userMapper.toResponseDTO(user);
    }

    //Login User
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        //Find User By ID
        UserModel user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Hortifruti Erro: Usuário ou senha inválidos."));

        //Check Password
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Hortifruti Erro: Usuário ou senha inválidos.");
        }

        //Generate Token
        String token = TokenManager.generateToken(
                user.getEmail(),
                user.getRoles().stream().map(Role::name).collect(Collectors.toList())
        );

        //Response DTO
        return new LoginResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles().stream().map(Role::name).collect(Collectors.toSet()),
                token
        );
    }

    //Get User List
    @Override
    public List<UserResponseDTO> listAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    //Get User By ID
    @Override
    public Optional<UserResponseDTO> findUserById(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::toResponseDTO);
    }

    //Delete User By ID
    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
