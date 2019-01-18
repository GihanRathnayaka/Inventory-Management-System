package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.MaterialReceiveDetailsDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.dto.MaterialReceiveDetailsDTO;
import com.revivatea.entity.MaterialReceiveDetails;
import com.revivatea.entity.MaterialReceiveDetailsFK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaterialReceiveDetailsDAOImpl implements MaterialReceiveDetailsDAO {
    @Override
    public boolean save(MaterialReceiveDetails mrd) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO materialreceivedetails(madeDate, expireDate, price, Qty, aviableQty, receiveID, rowMatID, packSize, unitType) VALUES (?,?,?,?,?,?,?,?,?)");
        String sql ="INSERT INTO materialreceivedetails(madeDate, expireDate, price, Qty, aviableQty, receiveID, rowMatID, packSize, unitType) VALUES (?,?,?,?,?,?,?,?,?)";
//        pst.setObject(1,mrd.getMadeDate());
//        pst.setObject(2,mrd.getExpireDate());
//        pst.setObject(3,mrd.getPrice());
//        pst.setObject(4,mrd.getQty());
//        pst.setObject(5,mrd.getAviableQty());
//        pst.setObject(6,mrd.getMaterialReceiveDetailsFK().getReceiveID());
//        pst.setObject(7,mrd.getMaterialReceiveDetailsFK().getRowMatID());
//        pst.setObject(8,mrd.getPackSize());
//        pst.setObject(9,mrd.getUnitType());

       int r= CrudUtil.execute(sql, mrd.getMadeDate(), mrd.getExpireDate(), mrd.getPrice(), mrd.getQty()
                , mrd.getAviableQty(), mrd.getMaterialReceiveDetailsFK().getReceiveID()
                , mrd.getMaterialReceiveDetailsFK().getRowMatID(), mrd.getPackSize(), mrd.getUnitType());


        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(MaterialReceiveDetails mrd) throws Exception {
        //Connection connection = DBConnection.getInstance();
        //PreparedStatement pst = connection.prepareStatement("UPDATE materialreceivedetails SET madeDate=?, expireDate=?, price=?, Qty=?, aviableQty=? ,packSize=?,unitType =? WHERE receiveID=? AND rowMatID=? ");
        String sql ="UPDATE materialreceivedetails SET madeDate=?, expireDate=?, price=?, Qty=?, aviableQty=? ,packSize=?,unitType =? WHERE receiveID=? AND rowMatID=? ";
        int r=  CrudUtil.execute(sql,mrd.getMadeDate(),mrd.getExpireDate(),mrd.getPrice(),mrd.getQty(),mrd.getAviableQty(),mrd.getMaterialReceiveDetailsFK().getReceiveID(),
                mrd.getMaterialReceiveDetailsFK().getRowMatID() ,mrd.getPackSize(),mrd.getUnitType());

//        pst.setObject(1,mrd.getMadeDate());
//        pst.setObject(2,mrd.getExpireDate());
//        pst.setObject(3,mrd.getPrice());
//        pst.setObject(4,mrd.getQty());
//        pst.setObject(5,mrd.getAviableQty());
//        pst.setObject(6,mrd.getMaterialReceiveDetailsFK().getReceiveID());
//        pst.setObject(7,mrd.getMaterialReceiveDetailsFK().getRowMatID());
//        pst.setObject(8,mrd.getPackSize());
//        pst.setObject(9,mrd.getUnitType());
        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(MaterialReceiveDetailsFK FK) throws Exception {
       // Connection connection = DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("DELETE  FROM materialreceivedetails WHERE receiveID=? AND rowMatID=? ");
        String sql ="DELETE  FROM materialreceivedetails WHERE receiveID=? AND rowMatID=? ";
        int r =CrudUtil.execute(sql,FK.getReceiveID(),FK.getRowMatID());
//        pst.setObject(1,FK.getReceiveID());
//        pst.setObject(2,FK.getRowMatID());

        if(r>0){
            return true;
        }
        return false;

    }

    @Override
    public List<MaterialReceiveDetails> findAll() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM materialreceivedetails");
        ResultSet rst =CrudUtil.execute("SELECT * FROM materialreceivedetails");
        ArrayList<MaterialReceiveDetails>temp = new ArrayList<>();
        while (rst.next()){
            int id =rst.getInt(1);
            LocalDate madeDate=rst.getDate(2).toLocalDate();
            LocalDate expierDate =rst.getDate(3).toLocalDate();
            double price =rst.getDouble(4);
            double Qty =rst.getDouble(5);
            double aviableQty =rst.getDouble(6);
            String receiveID =rst.getString(7);
            String rowMatID =rst.getString(8);
            int packSize =rst.getInt(9);
            String packType =rst.getString(10);
           temp.add(new MaterialReceiveDetails(id,madeDate,expierDate,price,Qty,aviableQty,new MaterialReceiveDetailsFK(receiveID,rowMatID),packSize,packType)) ;
        }
        if(temp.size()>0){return temp;}
        return null;
    }

    @Override
    public MaterialReceiveDetails find(MaterialReceiveDetailsFK fk) throws Exception {
        //Connection connection = DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("SELECT * FROM materialreceivedetails WHERE receiveID=? AND rowMatID=?");
        String sql ="SELECT * FROM materialreceivedetails WHERE receiveID=? AND rowMatID=?";
//
//        pst.setObject(1,fk.getReceiveID());
//        pst.setObject(2,fk.getRowMatID());
        ResultSet rst = CrudUtil.execute(sql,fk.getReceiveID(),fk.getRowMatID());
        MaterialReceiveDetails temp = null;
        while (rst.next()){
            int id =rst.getInt(1);
            LocalDate madeDate=rst.getDate(2).toLocalDate();
            LocalDate expierDate =rst.getDate(3).toLocalDate();
            double price =rst.getDouble(4);
            double Qty =rst.getDouble(5);
            double aviableQty =rst.getDouble(6);
            String receiveID =rst.getString(7);
            String rowMatID =rst.getString(8);
            int packSize =rst.getInt(9);
            String packType =rst.getString(10);
            return new MaterialReceiveDetails(id,madeDate,expierDate,price,Qty,aviableQty,new MaterialReceiveDetailsFK(receiveID,rowMatID),packSize,packType) ;
        }
        return null;
    }


    @Override
    public List<MaterialReceiveDetails> findReceiveDetailsMatID(String MatID) throws Exception {
        //Connection  connection =DBConnection.getInstance();
        //PreparedStatement pst = connection.prepareStatement("SELECT * FROM materialreceivedetails WHERE rowMatID=? and aviableQty>0 ORDER BY id ASC ");
        String sql ="SELECT * FROM materialreceivedetails WHERE rowMatID=? and aviableQty>0 ORDER BY id ASC ";
       // pst.setObject(1,MatID);
        ResultSet rst = CrudUtil.execute(sql,MatID);
        List<MaterialReceiveDetails>temp = new ArrayList<>();
        while (rst.next()){
            int id =rst.getInt(1);
            LocalDate madeDate=rst.getDate(2).toLocalDate();
            LocalDate expierDate =rst.getDate(3).toLocalDate();
            double price =rst.getDouble(4);
            double Qty =rst.getDouble(5);
            double aviableQty =rst.getDouble(6);
            String receiveID =rst.getString(7);
            String rowMatID =rst.getString(8);
            int packSize =rst.getInt(9);
            String packType =rst.getString(10);
            temp.add(new MaterialReceiveDetails(id,madeDate,expierDate,price,Qty,aviableQty,new MaterialReceiveDetailsFK(receiveID,rowMatID),packSize,packType)) ;
            System.out.println("");
        }
        if(temp.size()>0){return temp;}
        return null;
    }

    @Override
    public boolean aviableQty(int id, String matID, double qty) throws Exception {
        //Connection  connection =DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("UPDATE materialreceivedetails SET aviableQty=aviableQty-? WHERE id=? and rowMatID=?");
        String sql ="UPDATE materialreceivedetails SET aviableQty=aviableQty-? WHERE id=? and rowMatID=? ";
        int r= CrudUtil.execute(sql,qty,id,matID);
//        pst.setObject(1,qty);
//        pst.setObject(2,id);
//        pst.setObject(3,matID);
        if(r>0){return true;}
        return false;
    }

    @Override
    public List<MaterialReceiveDetails> findAll(String receivID) throws Exception {
      //  Connection  connection =DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("SELECT * FROM materialreceivedetails WHERE receiveID =?  ORDER BY id ASC ");
        String sql ="SELECT * FROM materialreceivedetails WHERE receiveID =?  ORDER BY id ASC ";
        ResultSet rst = CrudUtil.execute(sql,receivID);
        List<MaterialReceiveDetails>temp = new ArrayList<>();
        while (rst.next()){
            int id =rst.getInt(1);
            LocalDate madeDate=rst.getDate(2).toLocalDate();
            LocalDate expierDate =rst.getDate(3).toLocalDate();
            double price =rst.getDouble(4);
            double Qty =rst.getDouble(5);
            double aviableQty =rst.getDouble(6);
            String receiveID =rst.getString(7);
            String rowMatID =rst.getString(8);
            int packSize =rst.getInt(9);
            String packType =rst.getString(10);
            temp.add(new MaterialReceiveDetails(id,madeDate,expierDate,price,Qty,aviableQty,new MaterialReceiveDetailsFK(receiveID,rowMatID),packSize,packType)) ;
            System.out.println("");
        }
        if(temp.size()>0){return temp;}
        return null;
    }


}
