package me.ry4nn00b.hortifruti.Model.DTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class PromotionRequestDTO {

    //Promotion Attribute's
    @NotBlank(message = "Hortifruti Erro: O ID do produto é obrigatório.")
    private String productId;

    @NotBlank(message = "Hortifruti Erro: O tipo da promoção é obrigatório. (PERCENT ou FIXED)")
    private String type;

    @NotNull(message = "Hortifruti Erro: O valor da promoção é obrigatório.")
    @Positive(message = "Hortifruti Erro: O valor deve ser positivo.")
    private Double value;

    @NotNull(message = "Hortifruti Erro: A data de início é obrigatória.")
    @FutureOrPresent(message = "Hortifruti Erro: A data de início não pode ser no passado.")
    private LocalDate startDate;

    @NotNull(message = "Hortifruti Erro: A data de término é obrigatória.")
    @FutureOrPresent(message = "Hortifruti Erro: A data de término não pode ser no passado.")
    private LocalDate endDate;

    public PromotionRequestDTO() {}

    public PromotionRequestDTO(String productId, String type, Double value, LocalDate startDate, LocalDate endDate) {
        this.productId = productId;
        this.type = type;
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getter's and Setter's
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
