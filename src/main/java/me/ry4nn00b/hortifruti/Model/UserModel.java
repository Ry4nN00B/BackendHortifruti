package me.ry4nn00b.hortifruti.Model;

import me.ry4nn00b.hortifruti.Enum.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "user")
public class UserModel {

    //User Attribute's
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;

    public UserModel(){}

    public UserModel(String id, String name, String email, String password, Set<Role> roles){

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;

    }

    //Getter's and Setter's
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
