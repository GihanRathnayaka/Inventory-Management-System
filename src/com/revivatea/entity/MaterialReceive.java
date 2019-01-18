package com.revivatea.entity;

import java.time.LocalDate;

public class MaterialReceive extends SuperEntity {
    private String receiveID ;
    private LocalDate receiveDate ;
    private String description;
    private Double total ;
    private String supplierID ;

    public MaterialReceive() {
    }

    public MaterialReceive(String receiveID, LocalDate receiveDate, String description, Double total, String supplierID) {
        this.receiveID = receiveID;
        this.receiveDate = receiveDate;
        this.description = description;
        this.total = total;
        this.supplierID = supplierID;
    }

    public String getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(String receiveID) {
        this.receiveID = receiveID;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "MaterialReceive{" +
                "receiveID='" + receiveID + '\'' +
                ", receiveDate=" + receiveDate +
                ", description='" + description + '\'' +
                ", total=" + total +
                ", supplierID='" + supplierID + '\'' +
                '}';
    }
}
