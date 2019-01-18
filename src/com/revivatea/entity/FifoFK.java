package com.revivatea.entity;

public class FifoFK {
    private int id;
    private String MatRdID;

    public FifoFK() {
    }

    public FifoFK(int id, String matRdID) {
        this.id = id;
        MatRdID = matRdID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatRdID() {
        return MatRdID;
    }

    public void setMatRdID(String matRdID) {
        MatRdID = matRdID;
    }

    @Override
    public String toString() {
        return "FifoFK{" +
                "id=" + id +
                ", MatRdID='" + MatRdID + '\'' +
                '}';
    }
}
