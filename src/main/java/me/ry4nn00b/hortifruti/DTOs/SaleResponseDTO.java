package me.ry4nn00b.hortifruti.DTOs;

import me.ry4nn00b.hortifruti.Enum.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleResponseDTO {

    //Sale Attribute's
    private String id;
    private LocalDateTime dateTime;
    private String operatorId;
    private List<SaleItemResponseDTO> items;
    private BigDecimal total;
    private String paymentMethod;
    private SaleStatus status;

    public SaleResponseDTO() {}

    public SaleResponseDTO(String id, LocalDateTime dateTime, String operatorId, List<SaleItemResponseDTO> items, BigDecimal total, String paymentMethod, SaleStatus status) {
        this.id = id;
        this.dateTime = dateTime;
        this.operatorId = operatorId;
        this.items = items;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    //Getter's and Setter's
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getOperatorId() { return operatorId; }
    public void setOperatorId(String operatorId) { this.operatorId = operatorId; }

    public List<SaleItemResponseDTO> getItems() { return items; }
    public void setItems(List<SaleItemResponseDTO> items) { this.items = items; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public SaleStatus getStatus() { return status; }
    public void setStatus(SaleStatus status) { this.status = status; }

}
