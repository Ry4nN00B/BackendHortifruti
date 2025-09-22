package me.ry4nn00b.hortifruti.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collation = "product")
public class ProductModel {

    //Product Attribute's
    @Id
    private String id;
    private String name;
    private Double price;
    private Integer amount;
    private String categoryId;
    private String supplierId;
    private LocalDate validity;

    public ProductModel() {}

    public ProductModel(String id, String name, Double price, Integer amount,
                   String categoryId, String supplierId, LocalDate validity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.validity = validity;
    }

    //Getter's and Setter's
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
    public LocalDate getValidity() { return validity; }
    public void setValidity(LocalDate validity) { this.validity = validity; }
}
