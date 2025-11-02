package me.ry4nn00b.hortifruti.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class SaleRequestDTO {

    //Sale Attribute's
    @NotBlank(message = "Hortifruti Erro: O operador é obrigatório.")
    private String operatorId;

    @NotEmpty(message = "Hortifruti Erro: A venda deve conter pelo menos um item.")
    private List<@Valid SaleItemRequestDTO> items;

    @NotBlank(message = "Hortifruti Erro: O método de pagamento é obrigatório.")
    private String paymentMethod;

    public SaleRequestDTO() {}

    public SaleRequestDTO(String operatorId, List<SaleItemRequestDTO> items, String paymentMethod) {
        this.operatorId = operatorId;
        this.items = items;
        this.paymentMethod = paymentMethod;
    }

    //Getter's and Setter's
    public String getOperatorId() { return operatorId; }
    public void setOperatorId(String operatorId) { this.operatorId = operatorId; }

    public List<SaleItemRequestDTO> getItems() { return items; }
    public void setItems(List<SaleItemRequestDTO> items) { this.items = items; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

}
