package com.revivatea.entity;

import java.time.LocalDate;

public class MaterialRelease extends SuperEntity {
    private String releaseID ;
    private LocalDate releaseDate ;
    private double totalValue ;
    private String description ;

    public MaterialRelease() {
    }

    public MaterialRelease(String releaseID, LocalDate releaseDate, double totalValue, String description) {
        this.releaseID = releaseID;
        this.releaseDate = releaseDate;
        this.totalValue = totalValue;
        this.description = description;
    }

    public String getReleaseID() {
        return releaseID;
    }

    public void setReleaseID(String releaseID) {
        this.releaseID = releaseID;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MaterialRelease{" +
                "releaseID='" + releaseID + '\'' +
                ", releaseDate=" + releaseDate +
                ", totalValue=" + totalValue +
                ", description='" + description + '\'' +
                '}';
    }
}
