package me.ry4nn00b.hortifruti.DTOs.UserAuthDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    //User Attribute's
    @NotBlank(message = "Hortifruti Erro: O email é obrigatório.")
    @Email(message = "Hortifruti Erro: Email inválido.")
    private String email;

    @NotBlank(message = "Hortifruti Erro: A senha é obrigatória.")
    private String password;

    public LoginRequestDTO() {}

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Getter's and Setter's
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
