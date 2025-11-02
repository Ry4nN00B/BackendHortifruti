package me.ry4nn00b.hortifruti.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SaleItemRequestDTO {

    //Sale Item Attribute's
    @NotBlank(message = "Hortifruti Erro: O ID do produto é obrigatório.")
    private String productId;

    @NotNull(message = "Hortifruti Erro: A quantidade é obrigatória.")
    @Positive(message = "Hortifruti Erro: A quantidade deve ser positiva.")
    private Double amount;

    public SaleItemRequestDTO() {}

    public SaleItemRequestDTO(String productId, Double amount) {
        this.productId = productId;
        this.amount = amount;
    }

    //Getter's and Setter's
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

}
