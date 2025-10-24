package me.ry4nn00b.hortifruti.Model.DTOs;

public class CategoryResponseDTO {

    //Category Attribute's
    private String id;
    private String name;
    private String description;

    public CategoryResponseDTO() {}

    public CategoryResponseDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //Getter's and Setter's
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
