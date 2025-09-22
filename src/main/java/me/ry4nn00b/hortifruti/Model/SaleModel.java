package me.ry4nn00b.hortifruti.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "sale")
public class SaleModel {

    @Id
    private String id;
    private LocalDateTime dateTime;
    private String operatorId;
    private List<SaleItemModel> itens;
    private Double total;
    private String paymentMethod;

    public SaleModel() {}

    public SaleModel(String id, LocalDateTime dateTime, String operatorId, List<SaleItemModel> itens, Double total, String paymentMethod) {
        this.id = id;
        this.dateTime = dateTime;
        this.operatorId = operatorId;
        this.itens = itens;
        this.total = total;
        this.paymentMethod = paymentMethod;
    }

    //Getter's and Setter's
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getOperatorId() { return operatorId; }
    public void setOperatorId(String operatorId) { this.operatorId = operatorId; }

    public List<SaleItemModel> getItens() { return itens; }
    public void setItens(List<SaleItemModel> itens) { this.itens = itens; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
