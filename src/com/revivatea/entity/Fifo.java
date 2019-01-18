package com.revivatea.entity;

public class Fifo extends SuperEntity {

  private double   price ;
  private double   reQTy ;
  private double  totalvalue ;
  private FifoFK fifoFK ;


    public Fifo() {
    }

    public Fifo(double price, double reQTy, double totalvalue, FifoFK fifoFK) {
        this.price = price;
        this.reQTy = reQTy;
        this.totalvalue = totalvalue;
        this.fifoFK = fifoFK;
    }

    public Fifo(double price, double reQTy, double totalvalue, int id ,String mtRdID) {
        this.price = price;
        this.reQTy = reQTy;
        this.totalvalue = totalvalue;
        this.fifoFK = new FifoFK();
        fifoFK.setId(id);
        fifoFK.setMatRdID(mtRdID);
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

    public FifoFK getFifoFK() {
        return fifoFK;
    }

    public void setFifoFK(FifoFK fifoFK) {
        this.fifoFK = fifoFK;
    }

    @Override
    public String toString() {
        return "Fifo{" +
                "price=" + price +
                ", reQTy=" + reQTy +
                ", totalvalue=" + totalvalue +
                ", fifoFK=" + fifoFK +
                '}';
    }
}
