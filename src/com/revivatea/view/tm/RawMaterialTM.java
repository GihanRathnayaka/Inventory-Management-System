package com.revivatea.view.tm;

import com.revivatea.entity.SuperEntity;

import java.time.LocalDate;

public class RawMaterialTM extends SuperEntity {
    private String rowMatID ;
    private String  name ;
    private LocalDate regDate ;
    private double aviableQTY;
    private String descriptiion;

    public RawMaterialTM() {
    }

    public RawMaterialTM(String rowMatID, String name, LocalDate regDate, double aviableQTY, String descriptiion) {
        this.rowMatID = rowMatID;
        this.name = name;
        this.regDate = regDate;
        this.aviableQTY = aviableQTY;
        this.descriptiion = descriptiion;
    }

    public String getRowMatID() {
        return rowMatID;
    }

    public void setRowMatID(String rowMatID) {
        this.rowMatID = rowMatID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public double getAviableQTY() {
        return aviableQTY;
    }

    public void setAviableQTY(double aviableQTY) {
        this.aviableQTY = aviableQTY;
    }

    public String getDescriptiion() {
        return descriptiion;
    }

    public void setDescriptiion(String descriptiion) {
        this.descriptiion = descriptiion;
    }

    @Override
    public String toString() {
        return "RawMaterialDTO{" +
                "rowMatID='" + rowMatID + '\'' +
                ", name='" + name + '\'' +
                ", regDate=" + regDate +
                ", aviableQTY=" + aviableQTY +
                ", descriptiion='" + descriptiion + '\'' +
                '}';
    }
}
