package me.ry4nn00b.hortifruti.Model;

public class SaleItemModel {

    //SaleItem Attribute's
    private String productId;
    private Double amount;
    private Double unitPrice;

    public SaleItemModel() {}

    public SaleItemModel(String productId, Double amount, Double unitPrice) {
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    //Getter's and Setter's
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

}
