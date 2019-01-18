package com.revivatea.entity;

public class ProductionDetailsFK {
    private String proID ;
    private String releaseID ;

    public ProductionDetailsFK() {
    }

    public String getProID() {
        return proID;
    }

    public ProductionDetailsFK(String proID, String releaseID) {
        this.proID = proID;
        this.releaseID = releaseID;
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
        return "ProductionDetailsFK{" +
                "proID='" + proID + '\'' +
                ", releaseID='" + releaseID + '\'' +
                '}';
    }
}
