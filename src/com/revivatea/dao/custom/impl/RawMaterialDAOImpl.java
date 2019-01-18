package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.RawMaterialDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.RawMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialDAOImpl implements RawMaterialDAO {

    @Override
    public boolean save(RawMaterial rawMaterial) throws Exception {
//        Connection  connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO rawmaterial VALUES (?,?,?,?,?)");
        String sql ="INSERT INTO rawmaterial VALUES (?,?,?,?,?)";
//        pst.setObject(1,rawMaterial.getRowMatID());
//        pst.setObject(2,rawMaterial.getName());
//        pst.setObject(3,rawMaterial.getRegDate());
//        pst.setObject(4,rawMaterial.getAviableQTY());
//        pst.setObject(5,rawMaterial.getDescriptiion());

       int r= CrudUtil.execute(sql,rawMaterial.getRowMatID(),rawMaterial.getName(),rawMaterial.getRegDate(),rawMaterial.getAviableQTY(),rawMaterial.getDescriptiion());

        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(RawMaterial rawMaterial) throws Exception {
       // Connection  connection = DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("UPDATE rawmaterial SET  name=?, regDate=?, aviableQTY=?, description =? WHERE rowMatID=? ");
       String sql ="UPDATE rawmaterial SET  name=?, regDate=?, aviableQTY=?, description =? WHERE rowMatID=? ";
//        pst.setObject(5,rawMaterial.getRowMatID());
//        pst.setObject(1,rawMaterial.getName());
//        pst.setObject(2,rawMaterial.getRegDate());
//        pst.setObject(3,rawMaterial.getAviableQTY());
//        pst.setObject(4,rawMaterial.getDescriptiion());

       int r= CrudUtil.execute(sql,rawMaterial.getName(),rawMaterial.getRegDate(),rawMaterial.getAviableQTY(),rawMaterial.getDescriptiion(),rawMaterial.getRowMatID());

        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
//        Connection  connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("DELETE FROM rawmaterial WHERE rowMatID=? ");
//        pst.setObject(1,id);
       int r= CrudUtil.execute("DELETE FROM rawmaterial WHERE rowMatID=? ",id);
        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public List<RawMaterial> findAll() throws Exception {
//        Connection  connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM rawmaterial");
        ResultSet rst = CrudUtil.execute("SELECT * FROM rawmaterial");
        ArrayList<RawMaterial>temp = new ArrayList<>();
        while (rst.next()){
            String matId =rst.getString(1);
            String name =rst.getString(2);
            LocalDate regdate =rst.getDate(3).toLocalDate();
            double qty =rst.getDouble(4);
            String description=rst.getString(5);
           temp.add(new RawMaterial(matId,name,regdate,qty,description));
        }
        if(temp.size()>0){
            return temp;
        }
        return null;
    }

    @Override
    public RawMaterial find(String matID) throws Exception {
//        Connection  connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM rawmaterial WHERE rowMatID=?");
//        pst.setObject(1,s);
        ResultSet rst = CrudUtil.execute("SELECT * FROM rawmaterial WHERE rowMatID=?",matID);
        RawMaterial temp = null;
        while (rst.next()){
            String matId =rst.getString(1);
            String name =rst.getString(2);
            LocalDate regdate =rst.getDate(3).toLocalDate();
            double qty =rst.getDouble(4);
            String description=rst.getString(5);
           return temp=new RawMaterial(matId,name,regdate,qty,description);
        }

        return null;
    }
    @Override
    public String rowMatNumber() throws Exception {
        //Connection  connection = DBConnection.getInstance();
        //PreparedStatement pst = connection.prepareStatement("SELECT max(rowMatID) FROM rawmaterial ORDER BY rowMatID desc Limit 1");
        ResultSet rst =CrudUtil.execute("SELECT max(rowMatID) FROM rawmaterial ORDER BY rowMatID desc Limit 1");
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean updateQty(double qty, String matId) throws Exception {
//        Connection  connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE rawmaterial SET aviableQTY=aviableQTY+? WHERE rowMatID=?");
//        pst.setObject(1,qty);
//        pst.setObject(2,matId);
        int r=CrudUtil.execute("UPDATE rawmaterial SET aviableQTY=aviableQTY+? WHERE rowMatID=?",qty,matId);
      if(r>0){return true;}
      return false;
    }

}
