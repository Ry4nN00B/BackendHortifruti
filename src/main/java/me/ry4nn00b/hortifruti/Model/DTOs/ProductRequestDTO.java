package me.ry4nn00b.hortifruti.Model.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductRequestDTO {

    //Product Attribute's
    @NotBlank(message = "Hortifruti Erro: O nome do produto é obrigatório.")
    @Size(min = 3, max = 100, message = "Hortifruti Erro: O nome do produto deve ter entre 3 e 100 caracteres.")
    private String name;

    @Size(max = 500, message = "Hortifruti Erro: A descrição pode ter no máximo 500 caracteres.")
    private String description;

    @NotNull(message = "Hortifruti Erro: O preço é obrigatório.")
    @Positive(message = "Hortifruti Erro: O preço deve ser positivo.")
    private Double price;

    @NotBlank(message = "Hortifruti Erro: O ID da categoria é obrigatório.")
    private String categoryId;

    @NotBlank(message = "Hortifruti Erro: O ID do fornecedor é obrigatório.")
    private String supplierId;

    private Boolean soldByWeight;

    public ProductRequestDTO() {}

    public ProductRequestDTO(String name, String description, Double price, String categoryId, String supplierId, Boolean soldByWeight){
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.soldByWeight = soldByWeight;
    }

    //Getter's and Setter's
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

    public Boolean getSoldByWeight() { return soldByWeight; }
    public void setSoldByWeight(Boolean soldByWeight) { this.soldByWeight = soldByWeight; }

}
