package me.ry4nn00b.hortifruti.Model.DTOs.UserAuthDTO;

import java.util.Set;

public class LoginResponseDTO {

    //User Attribute's
    private String id;
    private String name;
    private String email;
    private Set<String> roles;
    private String token;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String id, String name, String email, Set<String> roles, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }

    //Getter's and Setter's
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

}
