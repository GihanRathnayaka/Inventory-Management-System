package com.revivatea.entity;

public class MaterialReceiveDetailsFK {

   private String receiveID ;
   private String rowMatID ;

    public MaterialReceiveDetailsFK() {
    }

    public MaterialReceiveDetailsFK(String receiveID, String rowMatID) {
        this.receiveID = receiveID;
        this.rowMatID = rowMatID;
    }

    public String getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(String receiveID) {
        this.receiveID = receiveID;
    }

    public String getRowMatID() {
        return rowMatID;
    }

    public void setRowMatID(String rowMatID) {
        this.rowMatID = rowMatID;
    }

    @Override
    public String toString() {
        return "MaterialReceiveDetailsFK{" +
                "receiveID='" + receiveID + '\'' +
                ", rowMatID='" + rowMatID + '\'' +
                '}';
    }
}
