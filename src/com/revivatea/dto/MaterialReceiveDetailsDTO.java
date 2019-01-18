package com.revivatea.dto;

import java.time.LocalDate;

public class MaterialReceiveDetailsDTO extends SuperDTO {

    private LocalDate madeDate ;
    private LocalDate expireDate ;
    private double price ;
    private double Qty;
    private double aviableQty ;
    private String matID;
    private double total ;
    private int packSize;
    private String unitType;

    public MaterialReceiveDetailsDTO() {
    }

    public MaterialReceiveDetailsDTO(LocalDate madeDate, LocalDate expireDate, double price, double qty, double aviableQty, String matID, double total, int packSize, String unitType) {
        this.setMadeDate(madeDate);
        this.setExpireDate(expireDate);
        this.setPrice(price);
        setQty(qty);
        this.setAviableQty(aviableQty);
        this.setMatID(matID);
        this.setTotal(total);
        this.setPackSize(packSize);
        this.setUnitType(unitType);
    }

    public LocalDate getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(LocalDate madeDate) {
        this.madeDate = madeDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQty() {
        return Qty;
    }

    public void setQty(double qty) {
        Qty = qty;
    }

    public double getAviableQty() {
        return aviableQty;
    }

    public void setAviableQty(double aviableQty) {
        this.aviableQty = aviableQty;
    }

    public String getMatID() {
        return matID;
    }

    public void setMatID(String matID) {
        this.matID = matID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public int getPackSize() {
        return packSize;
    }

    public void setPackSize(int packSize) {
        this.packSize = packSize;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return "MaterialReceiveDetailsDTO{" +
                "madeDate=" + madeDate +
                ", expireDate=" + expireDate +
                ", price=" + price +
                ", Qty=" + Qty +
                ", aviableQty=" + aviableQty +
                ", matID='" + matID + '\'' +
                ", total=" + total +
                ", packSize=" + packSize +
                ", unitType='" + unitType + '\'' +
                '}';
    }
}
