package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.MaterialReleaseDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.MaterialRelease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaterialReleaseDAOImpl implements MaterialReleaseDAO {
    @Override
    public boolean save(MaterialRelease mr) throws Exception {
        //Connection connection = DBConnection.getInstance();
        //PreparedStatement pst = connection.prepareStatement("INSERT INTO materialrelease(releaseID, releaseDate, totalValue, description) VALUES (?,?,?,?)");

        String sql ="INSERT INTO materialrelease(releaseID, releaseDate, totalValue, description) VALUES (?,?,?,?)";
        int r=  CrudUtil.execute(sql,mr.getReleaseID(),mr.getReleaseDate(),mr.getTotalValue(),mr.getDescription());

       // pst.setObject(1,mr.getReleaseID());
       // pst.setObject(2,mr.getReleaseDate());
      //  pst.setObject(3,mr.getTotalValue());
       // pst.setObject(4,mr.getDescription());
        if(r>0){return true;}

        return false;
    }

    @Override
    public boolean update(MaterialRelease mr) throws Exception {
      //  Connection connection = DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("UPDATE materialrelease SET  releaseDate=?, totalValue=?, description=? WHERE releaseID=?");
        String sql ="UPDATE materialrelease SET  releaseDate=?, totalValue=?, description=? WHERE releaseID=?";
//        pst.setObject(4,mr.getReleaseID());
//        pst.setObject(1,mr.getReleaseDate());
//        pst.setObject(2,mr.getTotalValue());
       // pst.setObject(3,mr.getDescription());

       int r= CrudUtil.execute(sql,mr.getReleaseDate(),mr.getTotalValue(),mr.getDescription(),mr.getReleaseID());

        if(r>0){return true;}
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("DELETE FROM materialrelease WHERE releaseID=? ");
//        pst.setObject(1,s);
        int r= CrudUtil.execute("DELETE FROM materialrelease WHERE releaseID=? ",s);
        if(r>0){return true;}
        return false;
    }

    @Override
    public List<MaterialRelease> findAll() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM materialrelease");
        ArrayList<MaterialRelease> temp =new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM materialrelease");
        while (rst.next()){
            String matrID =rst.getString(1);
            LocalDate regDate =rst.getDate(2).toLocalDate();
            double total =rst.getDouble(3);
            String description =rst.getString(4);
            temp.add(new MaterialRelease(matrID,regDate,total,description));
        }
        if(temp.size()>0){
            return temp;
        }
        return null;
    }

    @Override
    public MaterialRelease find(String s) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM materialrelease WHERE releaseID=?");
//        pst.setObject(1,s);
        MaterialRelease temp =null;
        ResultSet rst = CrudUtil.execute("SELECT * FROM materialrelease WHERE releaseID=?",s);
        while (rst.next()){
            String matrID =rst.getString(1);
            LocalDate regDate =rst.getDate(2).toLocalDate();
            double total =rst.getDouble(3);
            String description =rst.getString(4);
            temp=new MaterialRelease(matrID,regDate,total,description);
        }
        if(temp!=null){
            return temp;
        }
        return null;
    }

    @Override
    public String getID() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT releaseID FROM materialrelease ORDER BY releaseID DESC LiMIT 1");
        ResultSet rst = CrudUtil.execute("SELECT releaseID FROM materialrelease ORDER BY releaseID DESC LiMIT 1");
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<MaterialRelease> findAll(String rid) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM materialrelease WHERE releaseID LIKE ? ");
//        pst.setObject(1,rid);
        ArrayList<MaterialRelease> temp =new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM materialrelease WHERE releaseID LIKE ? ",rid);
        while (rst.next()){
            String matrID =rst.getString(1);
            LocalDate regDate =rst.getDate(2).toLocalDate();
            double total =rst.getDouble(3);
            String description =rst.getString(4);
            temp.add(new MaterialRelease(matrID,regDate,total,description));
            System.out.print("");
        }
        if(temp.size()>0){
            return temp;
        }
        return null;
    }
}
