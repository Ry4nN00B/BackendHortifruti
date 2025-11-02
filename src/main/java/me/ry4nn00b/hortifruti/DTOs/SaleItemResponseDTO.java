package me.ry4nn00b.hortifruti.DTOs;

import java.math.BigDecimal;

public class SaleItemResponseDTO {

    //Sale Item Attribute's
    private String productId;
    private Double amount;
    private BigDecimal unitPrice;

    public SaleItemResponseDTO() {}

    public SaleItemResponseDTO(String productId, Double amount, BigDecimal unitPrice) {
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    //Getter's and Setter's
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

}
