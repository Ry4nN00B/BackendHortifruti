package me.ry4nn00b.hortifruti.DTOs.UserAuthDTO;

import java.util.Set;

public class UserResponseDTO {

    //User Attribute's
    private String id;
    private String name;
    private String email;
    private Set<String> roles;

    public UserResponseDTO() {}

    public UserResponseDTO(String id, String name, String email, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
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
}
