package com.revivatea.dto;

import java.time.LocalDate;

public class ProductionDetailsDTO extends SuperDTO {
    private int pdID ;
    private LocalDate recordDate ;
    private String bellSheetNo ;
    private double nofPackage ;
    private double packagePrice ;
    private double packageUnite ;
    private double total ;
    private LocalDate madeDate ;
    private LocalDate Expire ;
    private String packType;
    private String Description ;
    private String proID ;
    private String releaseID ;

    public ProductionDetailsDTO() {
    }

    public ProductionDetailsDTO(int pdID, LocalDate recordDate, String bellSheetNo, double nofPackage, double packagePrice, double packageUnite, double total, LocalDate madeDate, LocalDate expire, String packType, String description, String proID, String releaseID) {
        this.pdID = pdID;
        this.recordDate = recordDate;
        this.bellSheetNo = bellSheetNo;
        this.nofPackage = nofPackage;
        this.packagePrice = packagePrice;
        this.packageUnite = packageUnite;
        this.total = total;
        this.madeDate = madeDate;
        Expire = expire;
        this.packType = packType;
        Description = description;
        this.proID = proID;
        this.releaseID = releaseID;
    }

    public int getPdID() {
        return pdID;
    }

    public void setPdID(int pdID) {
        this.pdID = pdID;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getBellSheetNo() {
        return bellSheetNo;
    }

    public void setBellSheetNo(String bellSheetNo) {
        this.bellSheetNo = bellSheetNo;
    }

    public double getNofPackage() {
        return nofPackage;
    }

    public void setNofPackage(double nofPackage) {
        this.nofPackage = nofPackage;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public double getPackageUnite() {
        return packageUnite;
    }

    public void setPackageUnite(double packageUnite) {
        this.packageUnite = packageUnite;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(LocalDate madeDate) {
        this.madeDate = madeDate;
    }

    public LocalDate getExpire() {
        return Expire;
    }

    public void setExpire(LocalDate expire) {
        Expire = expire;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getReleaseID() {
        return releaseID;
    }

    public void setReleaseID(String releaseID) {
        this.releaseID = releaseID;
    }

    @Override
    public String toString() {
        return "ProductionDetailsDTO{" +
                "pdID=" + pdID +
                ", recordDate=" + recordDate +
                ", bellSheetNo='" + bellSheetNo + '\'' +
                ", nofPackage=" + nofPackage +
                ", packagePrice=" + packagePrice +
                ", packageUnite=" + packageUnite +
                ", total=" + total +
                ", madeDate=" + madeDate +
                ", Expire=" + Expire +
                ", packType='" + packType + '\'' +
                ", Description='" + Description + '\'' +
                ", proID='" + proID + '\'' +
                ", releaseID='" + releaseID + '\'' +
                '}';
    }
}
