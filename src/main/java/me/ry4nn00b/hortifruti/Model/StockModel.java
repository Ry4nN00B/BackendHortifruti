package me.ry4nn00b.hortifruti.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "stock")
public class StockModel {

    //Stock Attribute's
    @Id
    private String id;
    private String productId;
    private Double amount;
    private LocalDate entryDate;
    private LocalDate validity;

    public StockModel() {}

    public StockModel(String productId, Double amount, LocalDate entryDate, LocalDate validity) {
        this.productId = productId;
        this.amount = amount;
        this.entryDate = entryDate;
        this.validity = validity;
    }

    //Getter's and Setter's
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getValidity() {
        return validity;
    }
    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

}
