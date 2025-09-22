package me.ry4nn00b.hortifruti.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "promotion")
public class PromotionModel {

    //Promotion Attribute's
    @Id
    private String id;
    private String productId;
    private String type;
    private Double value;
    private LocalDate startDate;
    private LocalDate endDate;

    public PromotionModel() {}

    public PromotionModel(String id, String productId, String type, Double value, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getter's and Setter's
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(java.time.LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(java.time.LocalDate endDate) { this.endDate = endDate; }
}
