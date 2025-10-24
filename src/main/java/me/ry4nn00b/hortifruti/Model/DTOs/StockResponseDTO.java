package me.ry4nn00b.hortifruti.Model.DTOs;

import java.time.LocalDate;

public class StockResponseDTO {

    //Stock Attribute's
    private String id;
    private String productId;
    private Double amount;
    private LocalDate entryDate;
    private LocalDate validity;

    public StockResponseDTO() {}

    public StockResponseDTO(String id, String productId, Double amount, LocalDate entryDate, LocalDate validity) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
        this.entryDate = entryDate;
        this.validity = validity;
    }

    //Getter's and Setter's
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }

    public LocalDate getValidity() { return validity; }
    public void setValidity(LocalDate validity) { this.validity = validity; }

}
