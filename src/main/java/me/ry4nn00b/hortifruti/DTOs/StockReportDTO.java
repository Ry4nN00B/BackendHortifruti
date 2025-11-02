package me.ry4nn00b.hortifruti.DTOs;

import java.time.LocalDate;

public class StockReportDTO {

    //Report Attribute's
    private String productName;
    private double amount;
    private LocalDate entryDate;
    private LocalDate validity;

    private Long remainingDays;

    public StockReportDTO() {}

    public StockReportDTO(String productName, double amount, LocalDate entryDate, LocalDate validity, Long remainingDays) {
        this.productName = productName;
        this.amount = amount;
        this.entryDate = entryDate;
        this.validity = validity;
        this.remainingDays = remainingDays;
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

    public Long getRemainingDays() {
        return remainingDays;
    }
    public void setRemainingDays(Long remainingDays) {
        this.remainingDays = remainingDays;
    }

}
