package com.revivatea.entity;

import java.time.LocalDate;

public class Product extends SuperEntity {
    private String  proID ;
    private String  name ;
    private LocalDate req_Date;
    private String description ;
    private double AviQty ;

    public Product() {
    }

    public Product(String proID, String name, LocalDate req_Date, String description, double aviQty) {
        this.proID = proID;
        this.name = name;
        this.req_Date = req_Date;
        this.description = description;
        AviQty = aviQty;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReq_Date() {
        return req_Date;
    }

    public void setReq_Date(LocalDate req_Date) {
        this.req_Date = req_Date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAviQty() {
        return AviQty;
    }

    public void setAviQty(double aviQty) {
        AviQty = aviQty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "proID='" + proID + '\'' +
                ", name='" + name + '\'' +
                ", req_Date=" + req_Date +
                ", description='" + description + '\'' +
                ", AviQty=" + AviQty +
                '}';
    }
}
