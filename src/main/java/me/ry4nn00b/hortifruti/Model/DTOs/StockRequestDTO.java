package me.ry4nn00b.hortifruti.Model.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class StockRequestDTO {

    //Stock Attribute's
    @NotBlank(message = "Hortifruti Erro: O ID do produto é obrigatório.")
    private String productId;

    @NotNull(message = "Hortifruti Erro: A quantidade é obrigatória.")
    @Positive(message = "Hortifruti Erro: A quantidade deve ser positiva.")
    private Double amount;

    @NotNull(message = "Hortifruti Erro: A validade é obrigatória.")
    private LocalDate validity;

    public StockRequestDTO() {}

    public StockRequestDTO(String productId, Double amount, LocalDate validity) {
        this.productId = productId;
        this.amount = amount;
        this.validity = validity;
    }

    //Getter's and Setter's
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getValidity() { return validity; }
    public void setValidity(LocalDate validity) { this.validity = validity; }

}
