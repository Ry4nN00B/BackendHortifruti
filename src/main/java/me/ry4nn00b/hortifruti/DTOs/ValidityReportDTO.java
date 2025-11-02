package me.ry4nn00b.hortifruti.DTOs;

import java.time.LocalDate;

public class ValidityReportDTO {

    //Report
    private String productName;
    private double amount;
    private LocalDate validity;
    private String status;

    public ValidityReportDTO(){}

    public ValidityReportDTO(String productName, double amount, LocalDate validity, String status) {
        this.productName = productName;
        this.amount = amount;
        this.validity = validity;
        this.status = status;
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

    public LocalDate getValidity() {
        return validity;
    }
    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
