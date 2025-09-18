package me.ry4nn00b.hortifruti.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "user")
public class UserModel {

    //User Attribute's
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String function;

    public UserModel(){}

    public UserModel(String id, String name, String email, String password, String function){

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.function = function;

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

    public String getFunction() {
        return function;
    }
    public void setFunction(String function) {
        this.function = function;
    }

}
