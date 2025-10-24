package me.ry4nn00b.hortifruti.Model.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {

    //Category Attribute's
    @NotBlank(message = "Hortifruti Erro: O nome da categoria é obrigatório.")
    @Size(min = 3, max = 50, message = "Hortifruti Erro: O nome da categoria deve ter entre 3 e 50 caracteres.")
    private String name;

    @Size(max = 200, message = "Hortifruti Erro: A descrição pode ter no máximo 200 caracteres.")
    private String description;

    public CategoryRequestDTO() {}

    public CategoryRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //Getter's and Setter's
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
