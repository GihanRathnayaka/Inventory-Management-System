package com.revivatea.entity;

public class MaterialReleaseDetails extends SuperEntity {

    private String matRdID ;
    private double releaseqty ;
    private double total;
    private String releaseID ;
    private String rowMatID ;

    public MaterialReleaseDetails() {
    }

    public MaterialReleaseDetails(String matRdID, double releaseqty, double total, String releaseID, String rowMatID) {
        this.matRdID = matRdID;
        this.releaseqty = releaseqty;
        this.total = total;
        this.releaseID = releaseID;
        this.rowMatID = rowMatID;
    }

    public String getMatRdID() {
        return matRdID;
    }

    public void setMatRdID(String matRdID) {
        this.matRdID = matRdID;
    }

    public double getReleaseqty() {
        return releaseqty;
    }

    public void setReleaseqty(double releaseqty) {
        this.releaseqty = releaseqty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getReleaseID() {
        return releaseID;
    }

    public void setReleaseID(String releaseID) {
        this.releaseID = releaseID;
    }

    public String getRowMatID() {
        return rowMatID;
    }

    public void setRowMatID(String rowMatID) {
        this.rowMatID = rowMatID;
    }

    @Override
    public String  toString() {
        return "MaterialReleaseDetails{" +
                "matRdID='" + matRdID + '\'' +
                ", releaseqty=" + releaseqty +
                ", total=" + total +
                ", releaseID='" + releaseID + '\'' +
                ", rowMatID='" + rowMatID + '\'' +
                '}';
    }
}
