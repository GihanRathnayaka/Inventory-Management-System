package com.revivatea.dto;

import com.revivatea.entity.SuperEntity;

public class FifoDTO extends SuperDTO {

  private double   price ;
  private double   reQTy ;
  private double  totalvalue ;
  private int   id ;
  private String matRdID;

    public FifoDTO() {
    }

    public FifoDTO(double price, double reQTy, double totalvalue, int id, String matRdID) {
        this.price = price;
        this.reQTy = reQTy;
        this.totalvalue = totalvalue;
        this.id = id;
        this.matRdID = matRdID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getReQTy() {
        return reQTy;
    }

    public void setReQTy(double reQTy) {
        this.reQTy = reQTy;
    }

    public double getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(double totalvalue) {
        this.totalvalue = totalvalue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatRdID() {
        return matRdID;
    }

    public void setMatRdID(String matRdID) {
        this.matRdID = matRdID;
    }

    @Override
    public String toString() {
        return "Fifo{" +
                "price=" + price +
                ", reQTy=" + reQTy +
                ", totalvalue=" + totalvalue +
                ", id=" + id +
                ", matRdID='" + matRdID + '\'' +
                '}';
    }
}
