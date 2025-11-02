package me.ry4nn00b.hortifruti.DTOs.UserAuthDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserRequestDTO {

    //User Attribute's
    @NotBlank(message = "Hortifruti Erro: O nome é obrigatório.")
    @Size(min = 3, max = 50, message = "Hortifruti Erro: O nome deve ter entre 3 e 50 caracteres.")
    private String name;

    @NotBlank(message = "Hortifruti Erro: O email é obrigatório.")
    @Email(message = "Hortifruti Erro: Email inválido.")
    private String email;

    @NotBlank(message = "Hortifruti Erro: A senha é obrigatória.")
    @Size(min = 6, max = 100, message = "Hortifruti Erro: A senha deve ter entre 6 e 100 caracteres.")
    private String password;

    private Set<String> roles;

    public UserRequestDTO() {}

    public UserRequestDTO(String name, String email, String password, Set<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    //Getter's and Setter's
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

}
