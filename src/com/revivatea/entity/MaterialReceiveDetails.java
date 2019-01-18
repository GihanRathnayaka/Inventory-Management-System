package com.revivatea.entity;

import java.time.LocalDate;

public class MaterialReceiveDetails extends SuperEntity {
    private int id;
    private LocalDate madeDate ;
    private LocalDate expireDate ;
    private double price ;
    private double Qty;
    private double aviableQty ;
    private MaterialReceiveDetailsFK materialReceiveDetailsFK;
    private int packSize;
    private String unitType;

    public MaterialReceiveDetails() {
    }

    public MaterialReceiveDetails(LocalDate madeDate, LocalDate expireDate, double price, double qty, double aviableQty, MaterialReceiveDetailsFK materialReceiveDetailsFK,String rowMatID, String matreceiveID,int PackSize,String packType)  {
        this.setMadeDate(madeDate);
        this.setExpireDate(expireDate);
        this.setPrice(price);
        setQty(qty);
        this.setAviableQty(aviableQty);
        this.setMaterialReceiveDetailsFK(new MaterialReceiveDetailsFK(matreceiveID,rowMatID));
        this.setPackSize(PackSize);
        this.setUnitType(packType);
    }

    public MaterialReceiveDetails(int id,LocalDate madeDate, LocalDate expireDate, double price, double qty, double aviableQty, MaterialReceiveDetailsFK materialReceiveDetailsFK ,int PackSize,String packType) {
        this.id=id;
        this.setMadeDate(madeDate);
        this.setExpireDate(expireDate);
        this.setPrice(price);
        setQty(qty);
        this.setAviableQty(aviableQty);
        this.setMaterialReceiveDetailsFK(materialReceiveDetailsFK);
        this.setPackSize(PackSize);
        this.setUnitType(packType);
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

    public MaterialReceiveDetailsFK getMaterialReceiveDetailsFK() {
        return materialReceiveDetailsFK;
    }

    public void setMaterialReceiveDetailsFK(MaterialReceiveDetailsFK materialReceiveDetailsFK) {
        this.materialReceiveDetailsFK = materialReceiveDetailsFK;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MaterialReceiveDetails{" +
                "id=" + id +
                ", madeDate=" + madeDate +
                ", expireDate=" + expireDate +
                ", price=" + price +
                ", Qty=" + Qty +
                ", aviableQty=" + aviableQty +
                ", materialReceiveDetailsFK=" + materialReceiveDetailsFK +
                ", packSize=" + packSize +
                ", unitType='" + unitType + '\'' +
                '}';
    }
}
