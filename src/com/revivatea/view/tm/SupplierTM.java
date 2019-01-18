package com.revivatea.view.tm;

import com.revivatea.dto.SuperDTO;

import java.time.LocalDate;

public class SupplierTM extends SuperDTO {

    private String suplierID;
    private String companyName;
    private String address;
    private String email;
    private String tele;
    private String fax;
    private String conatctPerson;
    private String Mobile;
    private LocalDate regDate;
    private double debitLimit;
    private double balance;

    public SupplierTM() {
    }

    public SupplierTM(String suplierID, String companyName, String address, String email, String tele, String fax, String conatctPerson, String mobile, LocalDate regDate, double debitLimit, double balance) {
        this.suplierID = suplierID;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.tele = tele;
        this.fax = fax;
        this.conatctPerson = conatctPerson;
        Mobile = mobile;
        this.regDate = regDate;
        this.debitLimit = debitLimit;
        this.balance = balance;
    }

    public String getSuplierID() {
        return suplierID;
    }

    public void setSuplierID(String suplierID) {
        this.suplierID = suplierID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getConatctPerson() {
        return conatctPerson;
    }

    public void setConatctPerson(String conatctPerson) {
        this.conatctPerson = conatctPerson;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public double getDebitLimit() {
        return debitLimit;
    }

    public void setDebitLimit(double debitLimit) {
        this.debitLimit = debitLimit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "SupplierTM{" +
                "suplierID='" + suplierID + '\'' +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", tele='" + tele + '\'' +
                ", fax='" + fax + '\'' +
                ", conatctPerson='" + conatctPerson + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", regDate=" + regDate +
                ", debitLimit=" + debitLimit +
                ", balance=" + balance +
                '}';
    }
}
