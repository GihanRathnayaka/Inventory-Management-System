package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.MaterialReleaseDetailsDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.MaterialReleaseDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaterialReleaseDetailsDAOImpl implements MaterialReleaseDetailsDAO {
    @Override
    public boolean save(MaterialReleaseDetails mrd) throws Exception {
      //  Connection connection = DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("INSERT INTO materialreleasedetails(matRdID, releaseqty, total, releaseID, rowMatID) VALUES (?,?,?,?,?)");
        String sql ="INSERT INTO materialreleasedetails(matRdID, releaseqty, total, releaseID, rowMatID) VALUES (?,?,?,?,?)";
       int r= CrudUtil.execute(sql,mrd.getMatRdID(),mrd.getReleaseqty(),mrd.getTotal(),mrd.getReleaseID(),mrd.getRowMatID());
//
//        pst.setObject(1,mrd.getMatRdID());
//        pst.setObject(2,mrd.getReleaseqty());
//        pst.setObject(3,mrd.getTotal());
//        pst.setObject(4,mrd.getReleaseID());
//        pst.setObject(5,mrd.getRowMatID());
        if (r>0){return true;}
        return false;
    }

    @Override
    public boolean update(MaterialReleaseDetails mrd) throws Exception {
       // Connection connection = DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("UPDATE materialreleasedetails SET releaseqty=?, total=?, releaseID=?, rowMatID=? WHERE matRdID=?");
        String sql ="UPDATE materialreleasedetails SET releaseqty=?, total=?, releaseID=?, rowMatID=? WHERE matRdID=?";
        int r=CrudUtil.execute(sql,mrd.getReleaseqty(),mrd.getTotal(),mrd.getReleaseID(),mrd.getRowMatID(),mrd.getMatRdID());
//        pst.setObject(5,mrd.getMatRdID());
//        pst.setObject(1,mrd.getReleaseqty());
//        pst.setObject(2,mrd.getTotal());
//        pst.setObject(3,mrd.getReleaseID());
//        pst.setObject(4,mrd.getRowMatID());
        if (r>0){return true;}
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("DELETE FROM materialreleasedetails WHERE matRdID=?");
//        pst.setObject(1,s);
        int r= CrudUtil.execute("DELETE FROM materialreleasedetails WHERE matRdID=?",s);
        if (r>0){return true;}
        return false;
    }

    @Override
    public List<MaterialReleaseDetails> findAll() throws Exception {
       // Connection connection = DBConnection.getInstance();
        //PreparedStatement pst = connection.prepareStatement("SELECT *FROM materialreleasedetails");
        ResultSet rst =CrudUtil.execute("SELECT *FROM materialreleasedetails");
        List<MaterialReleaseDetails>temp = new ArrayList<>();
        if(rst.next()){
            String matRdID =rst.getString(1);
            double  releaseqty =rst.getDouble(2);
            double total =rst.getDouble(3);
            String releaseID =rst.getString(4);
            String rowMatID =rst.getString(5);
            temp.add(new MaterialReleaseDetails(matRdID,releaseqty,total,releaseID,rowMatID));
        }
        if (temp.size()>0){return temp;}
        return null;
    }

    @Override
    public MaterialReleaseDetails find(String s) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT* FROM materialreleasedetails WHERE matRdID=?");
//        pst.setObject(1,s);
        ResultSet rst = CrudUtil.execute("SELECT* FROM materialreleasedetails WHERE matRdID=?",s);
         MaterialReleaseDetails temp =null;
        if(rst.next()){
            String matRdID =rst.getString(1);
            double  releaseqty =rst.getDouble(2);
            double total =rst.getDouble(3);
            String releaseID =rst.getString(4);
            String rowMatID =rst.getString(5);
            temp=new MaterialReleaseDetails(matRdID,releaseqty,total,releaseID,rowMatID);
        }
        if (temp==null){return temp;}
        return null;
    }

    @Override
    public String getID() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT matRdID  FROM materialreleasedetails ORDER BY matRdID DESC LIMIT 1");
        ResultSet rst = CrudUtil.execute("SELECT matRdID  FROM materialreleasedetails ORDER BY matRdID DESC LIMIT 1");
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
    @Override
    public List<MaterialReleaseDetails> findAll(String relaseId) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM materialreleasedetails WHERE releaseID =? ");
//
//        pst.setObject(1,relaseId);
        ResultSet rst = CrudUtil.execute("SELECT * FROM materialreleasedetails WHERE releaseID =? ",relaseId);
        List<MaterialReleaseDetails>temp = new ArrayList<>();
        if(rst.next()){
            String matRdID =rst.getString(1);
            double  releaseqty =rst.getDouble(2);
            double total =rst.getDouble(3);
            String releaseID =rst.getString(4);
            String rowMatID =rst.getString(5);
            temp.add(new MaterialReleaseDetails(matRdID,releaseqty,total,releaseID,rowMatID));
            System.out.println(temp);
        }
        if (temp.size()>0){return temp;}
        return null;
    }
}
