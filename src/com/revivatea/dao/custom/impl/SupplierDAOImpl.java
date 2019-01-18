package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.SupplierDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean save(Supplier supplier) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO  supplier(supplierID, cName, address, Email, tele, fax, cPerson, Cmobile, regDate, debitLimit) VALUES (?,?,?,?,?,?,?,?,?,?)");
//        pst.setObject(1,supplier.getSuplierID());
//        pst.setObject(2,supplier.getCompanyName());
//        pst.setObject(3,supplier.getAddress());
//        pst.setObject(4,supplier.getEmail());
//        pst.setObject(5,supplier.getTele());
//        pst.setObject(6,supplier.getFax());
//        pst.setObject(7,supplier.getConatctPerson());
//        pst.setObject(8,supplier.getcMobile());
//        pst.setObject(9,supplier.getRegDate());
//        pst.setObject(10,supplier.getDebitLimit());

        String sql ="INSERT INTO  supplier(supplierID, cName, address, Email, tele, fax, cPerson, Cmobile, regDate, debitLimit) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int r = CrudUtil.execute(sql,supplier.getSuplierID(),supplier.getCompanyName(),supplier.getAddress(),supplier.getEmail(),supplier.getTele(),
                supplier.getFax(),supplier.getConatctPerson(),supplier.getcMobile(),supplier.getRegDate(),supplier.getDebitLimit() );
        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Supplier supplier) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE supplier SET  cName=?, address=?, Email=?, tele=?, fax=?, cPerson=?, Cmobile=?, regDate=?, debitLimit=? WHERE supplierID=?");
//        pst.setObject(10,supplier.getSuplierID());
//        pst.setObject(1,supplier.getCompanyName());
//        pst.setObject(2,supplier.getAddress());
//        pst.setObject(3,supplier.getEmail());
//        pst.setObject(4,supplier.getTele());
//        pst.setObject(5,supplier.getFax());
//        pst.setObject(6,supplier.getConatctPerson());
//        pst.setObject(7,supplier.getcMobile());
//        pst.setObject(8,supplier.getRegDate());
//        pst.setObject(9,supplier.getDebitLimit());

        String sql="UPDATE supplier SET  cName=?, address=?, Email=?, tele=?, fax=?, cPerson=?, Cmobile=?, regDate=?, debitLimit=? WHERE supplierID=?";

       int r= CrudUtil.execute(sql,supplier.getCompanyName(),supplier.getAddress(),supplier.getEmail(),supplier.getTele(),
                supplier.getFax(),supplier.getConatctPerson(),supplier.getcMobile(),supplier.getRegDate(),supplier.getDebitLimit(),supplier.getSuplierID());

        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM supplier WHERE supplierID=?");
        pst.setObject(1,id);

        int r =CrudUtil.execute("DELETE FROM supplier WHERE supplierID=?",id);

        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Supplier> findAll() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT *FROM supplier");
        ResultSet rst = CrudUtil.execute("SELECT *FROM supplier");
        List<Supplier> suppliers =new ArrayList<>();
        while (rst.next()){
            String sid =rst.getString(1);
            String name =rst.getString(2);
            String address =rst.getString(3);
            String email =rst.getString(4);
            String tele =rst.getString(5);
            String fax =rst.getString(6);
            String cPerson =rst.getString(7);
            String Cmobile =rst.getString(8);
            LocalDate regDate=rst.getDate(9).toLocalDate();
            double debitLimit =rst.getDouble(10);
            double balance = rst.getDouble(11);
            suppliers.add(new Supplier(sid,name,address,email,tele,fax,cPerson,Cmobile,regDate,debitLimit,balance));
        }
        if(suppliers.size()>0){
            return suppliers;
        }
        return null;
    }

    @Override
    public Supplier find(String id) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM supplier WHERE supplierID =?");
//        pst.setObject(1,id);
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplier WHERE supplierID =?",id);
        Supplier  suppliers = null;
        while (rst.next()){
            String sid =rst.getString(1);
            String name =rst.getString(2);
            String address =rst.getString(3);
            String email =rst.getString(4);
            String tele =rst.getString(5);
            String fax =rst.getString(6);
            String cPerson =rst.getString(7);
            String Cmobile =rst.getString(8);
            LocalDate regDate=rst.getDate(9).toLocalDate();
            double debitLimit =rst.getDouble(10);
            double balance = rst.getDouble(11);
            System.out.println(rst);
            return new Supplier(sid,name,address,email,tele,fax,cPerson,Cmobile,regDate,debitLimit,balance);
        }

        return null;
    }
    @Override
    public String getID() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT supplierID FROM supplier order BY supplierID desc Limit 1");
        ResultSet rst = CrudUtil.execute("SELECT supplierID FROM supplier order BY supplierID desc Limit 1");
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean upateBalance(String supID,double balance) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE supplier SET balance=balance+? WHERE supplierID=?");
//        pst.setObject(1,balance);
//        pst.setObject(2,supID);

      int r = CrudUtil.execute("UPDATE supplier SET balance=balance+? WHERE supplierID=?",balance,supID);

        if(r>0){
            return true;
        }

        return false;

    }
}
