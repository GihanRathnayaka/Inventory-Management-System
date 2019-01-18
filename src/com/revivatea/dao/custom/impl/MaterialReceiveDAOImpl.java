package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.MaterialReceiveDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.MaterialReceive;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaterialReceiveDAOImpl implements MaterialReceiveDAO {

    @Override
    public boolean save(MaterialReceive matR) throws Exception {
        //Connection connection = DBConnection.getInstance();
        //PreparedStatement pst = connection.prepareStatement("INSERT INTO mterialreceive VALUES (?,?,?,?,?)");
        String sql ="INSERT INTO mterialreceive VALUES (?,?,?,?,?)";
        int r = CrudUtil.execute(sql, matR.getReceiveID(), matR.getReceiveDate(), matR.getDescription(), matR.getTotal(), matR.getSupplierID());
//
//
//        pst.setObject(1,matR.getReceiveID());
//        pst.setObject(2,matR.getReceiveDate());
//        pst.setObject(3,matR.getDescription());
//        pst.setObject(4,matR.getTotal());
//        pst.setObject(5,matR.getSupplierID());
        if(r>0){
            return true;
        }
        return false;


    }

    @Override
    public boolean update(MaterialReceive matR) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE mterialreceive set  receiveDate=?, description=?, total=?, supplierID=? WHERE receiveID=?");
        String sql ="UPDATE mterialreceive set  receiveDate=?, description=?, total=?, supplierID=? WHERE receiveID=?";
        int r = CrudUtil.execute(sql, matR.getReceiveDate(), matR.getDescription(), matR.getTotal(), matR.getSupplierID(),matR.getReceiveID());
//        pst.setObject(5,matR.getReceiveID());
//        pst.setObject(1,matR.getReceiveDate());
//        pst.setObject(2,matR.getDescription());
//        pst.setObject(3,matR.getTotal());
//        pst.setObject(4,matR.getSupplierID());
        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
       // Connection connection = DBConnection.getInstance();
      //  PreparedStatement pst = connection.prepareStatement("DELETE FROM mterialreceive  WHERE receiveID=?");
        String sql="DELETE FROM mterialreceive  WHERE receiveID=?";

        int r = CrudUtil.execute(sql, s);

       // pst.setObject(1,s);
        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public List<MaterialReceive> findAll() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT* FROM mterialreceive");
        String sql ="SELECT* FROM mterialreceive";
        ResultSet rst = CrudUtil.execute(sql);
        ArrayList<MaterialReceive>temp =new ArrayList<>();
        while (rst.next()){
            String matReceiveID =rst.getString(1);
            LocalDate date =rst.getDate(2).toLocalDate();
            String description =rst.getString(3);
            double total =rst.getDouble(4);
            String suplierID =rst.getString(5);
            temp.add(new MaterialReceive(matReceiveID,date,description,total,suplierID));
        }
        if(temp.size()>0){return temp;}
        return null;
    }

    @Override
    public MaterialReceive find(String s) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT *FROM mterialreceive WHERE supplierID=?");
//        pst.setObject(1,s);
        String sql ="SELECT *FROM mterialreceive WHERE supplierID=?";
        ResultSet rst = CrudUtil.execute(sql,s);
        MaterialReceive temp =null;
        while (rst.next()){
            String matReceiveID =rst.getString(1);
            LocalDate date =rst.getDate(2).toLocalDate();
            String description =rst.getString(3);
            double total =rst.getDouble(4);
            String suplierID =rst.getString(5);
             new MaterialReceive(matReceiveID,date,description,total,suplierID);
        }
        if(temp==null){return temp;}
        return null;
    }

    @Override
    public String getMatrecieveID() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT max( receiveID )FROM mterialreceive ORDER BY receiveID desc LIMIT 1");
       String sql ="SELECT max( receiveID )FROM mterialreceive ORDER BY receiveID desc LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<MaterialReceive> findAll(String releaseID) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT *FROM mterialreceive WHERE receiveID like ?");
        String sql ="SELECT *FROM mterialreceive WHERE receiveID like ?";
        //pst.setObject(1,releaseID);
        ResultSet rst = CrudUtil.execute(sql,releaseID);
        ArrayList<MaterialReceive>temp =new ArrayList<>();
        while (rst.next()){
            String matReceiveID =rst.getString(1);
            LocalDate date =rst.getDate(2).toLocalDate();
            String description =rst.getString(3);
            double total =rst.getDouble(4);
            String suplierID =rst.getString(5);
            temp.add(new MaterialReceive(matReceiveID,date,description,total,suplierID));
        }
        if(temp.size()>0){return temp;}
        return null;
    }
}
