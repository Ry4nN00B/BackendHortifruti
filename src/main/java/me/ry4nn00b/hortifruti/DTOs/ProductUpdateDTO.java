package me.ry4nn00b.hortifruti.DTOs;

import java.math.BigDecimal;

public class ProductUpdateDTO {

    //Product Attribute's
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryId;
    private String supplierId;
    private Boolean soldByWeight;

    public ProductUpdateDTO() {}

    public ProductUpdateDTO(String name, String description, BigDecimal price, String categoryId, String supplierId, Boolean soldByWeight){
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

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

    public Boolean getSoldByWeight() { return soldByWeight; }
    public void setSoldByWeight(Boolean soldByWeight) { this.soldByWeight = soldByWeight; }

}
