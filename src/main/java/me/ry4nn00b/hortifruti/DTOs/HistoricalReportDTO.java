package me.ry4nn00b.hortifruti.DTOs;

import java.time.LocalDateTime;

public class HistoricalReportDTO {

    //Report Attribute's
    private String productName;
    private String getStatus;
    private double amount;
    private LocalDateTime movementDate;
    private String observation;

    public HistoricalReportDTO() {}

    public HistoricalReportDTO(String productName, String getStatus, double amount, LocalDateTime movementDate, String observation) {
        this.productName = productName;
        this.getStatus = getStatus;
        this.amount = amount;
        this.movementDate = movementDate;
        this.observation = observation;
    }

    //Getter's and Setter's
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTypeMovement() {
        return getStatus;
    }
    public void setTypeMovement(String getStatus) {
        this.getStatus = getStatus;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getMovementDate() {
        return movementDate;
    }
    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }

    public String getObservation() {
        return observation;
    }
    public void setObservation(String observation) {
        this.observation = observation;
    }

}
