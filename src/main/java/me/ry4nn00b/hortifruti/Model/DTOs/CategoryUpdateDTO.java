package me.ry4nn00b.hortifruti.Model.DTOs;

public class CategoryUpdateDTO {

    //Category Attribute's
    private String name;
    private String description;

    public CategoryUpdateDTO() {}

    public CategoryUpdateDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //Getter's and Setter's
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
