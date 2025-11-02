package me.ry4nn00b.hortifruti.DTOs;

import java.math.BigDecimal;

public class FinancialReportDTO {

    //Report Attribute's
    private String productName;
    private double amount;
    private BigDecimal unitPrice;
    private BigDecimal totalValue;

    public FinancialReportDTO() {}

    public FinancialReportDTO(String productName, double amount, BigDecimal unitPrice, BigDecimal totalValue) {
        this.productName = productName;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.totalValue = totalValue;
    }

    //Getter's and Setter's
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

}
