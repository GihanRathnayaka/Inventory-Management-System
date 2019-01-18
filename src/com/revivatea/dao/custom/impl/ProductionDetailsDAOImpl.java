package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.ProductionDetailsDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.ProductionDetails;
import com.revivatea.entity.ProductionDetailsFK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductionDetailsDAOImpl implements ProductionDetailsDAO {
    @Override
    public boolean save(ProductionDetails pd) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO productiondetails(recordDate, bellSheetNo, nofPackage, packagePrice, " +
//                "packageUnite, total, madeDate, Expire, packType, Description, proID, releaseID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

      String sql ="INSERT INTO productiondetails(recordDate, bellSheetNo, nofPackage, packagePrice, " +
              "packageUnite, total, madeDate, Expire, packType, Description, proID, releaseID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        int r= CrudUtil.execute(sql,pd.getRecordDate(),pd.getBellSheetNo(),pd.getNofPackage()
                ,pd.getPackagePrice(),pd.getPackageUnite(),pd.getTotal(),pd.getMadeDate(),pd.getExpire()
                 ,pd.getPackType(),pd.getDescription(),pd.getProductionDetailsFK().getProID(),pd.getProductionDetailsFK().getReleaseID());
//       pst.setObject(1,pd.getRecordDate());
//        pst.setObject(2,pd.getBellSheetNo());
//        pst.setObject(3,pd.getNofPackage());
//        pst.setObject(4,pd.getPackagePrice());
//        pst.setObject(5,pd.getPackageUnite());
//        pst.setObject(6,pd.getTotal());
//        pst.setObject(7,pd.getMadeDate());
//        pst.setObject(8,pd.getExpire());
//        pst.setObject(9,pd.getPackType());
//        pst.setObject(10,pd.getDescription());
//        pst.setObject(11,pd.getProductionDetailsFK().getProID());
//        pst.setObject(12,pd.getProductionDetailsFK().getReleaseID());
       if(r>0){return true;}

        return false;
    }

    @Override
    public boolean update(ProductionDetails pd) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE productiondetails SET recordDate=?, bellSheetNo=?, nofPackage=?, packagePrice=?, " +
//                "packageUnite=?, total=?, madeDate=?, Expire=?, packType=?, Description=?,WHERE proID=?, releaseID=?");
      String sql ="UPDATE productiondetails SET recordDate=?, bellSheetNo=?, nofPackage=?, packagePrice=?, " +
              "packageUnite=?, total=?, madeDate=?, Expire=?, packType=?, Description=?,WHERE proID=?, releaseID=?";
        int r= CrudUtil.execute(sql,pd.getRecordDate(),pd.getBellSheetNo(),pd.getNofPackage()
                ,pd.getPackagePrice(),pd.getPackageUnite(),pd.getTotal(),pd.getMadeDate(),pd.getExpire()
                ,pd.getPackType(),pd.getDescription(),pd.getProductionDetailsFK().getProID(),pd.getProductionDetailsFK().getReleaseID());

//        pst.setObject(1,pd.getRecordDate());
//        pst.setObject(2,pd.getBellSheetNo());
//        pst.setObject(3,pd.getNofPackage());
//        pst.setObject(4,pd.getPackagePrice());
//        pst.setObject(5,pd.getPackageUnite());
//        pst.setObject(6,pd.getTotal());
//        pst.setObject(7,pd.getMadeDate());
//        pst.setObject(8,pd.getExpire());
//        pst.setObject(9,pd.getPackType());
//        pst.setObject(10,pd.getDescription());
//        pst.setObject(11,pd.getProductionDetailsFK().getProID());
//        pst.setObject(12,pd.getProductionDetailsFK().getReleaseID());
        if(r>0){return true;}

        return false;
    }

    @Override
    public boolean delete(ProductionDetailsFK fk) throws Exception {
      //  Connection connection = DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("DELETE FROM productiondetails WHERE proID=? and releaseID=?");
      int r=  CrudUtil.execute("DELETE FROM productiondetails WHERE proID=? and releaseID=?",fk.getProID(), fk.getReleaseID());

//        pst.setObject(1, fk.getProID());
//        pst.setObject(2, fk.getReleaseID());

        if (r > 0) {
            return true;
        }

        return false;
    }
    @Override
    public List<ProductionDetails> findAll() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM productiondetails");
        ResultSet rst = CrudUtil.execute("SELECT * FROM productiondetails");
        List<ProductionDetails>temp =new ArrayList<>();
        while (rst.next()){
            LocalDate date=rst.getDate(2).toLocalDate();
            String bellsheet =rst.getString(3);
            double nofPack=rst.getDouble(4);
            double packPrice=rst.getDouble(5);
            double packUnit=rst.getDouble(6);
            double total =rst.getDouble(7);
            LocalDate madeDate=rst.getDate(8).toLocalDate();
            LocalDate expiry=rst.getDate(9).toLocalDate();
            String packType=rst.getString(10);
            String description=rst.getString(11);
            String productID =rst.getString(12);
            String releaseID =rst.getString(13);

            temp.add(new ProductionDetails(1,date,bellsheet,nofPack,packPrice,nofPack,total,madeDate,expiry,packType,description,productID,releaseID));
        }

            if(temp.size()>0){return temp;}
            return null;
    }

    @Override
    public ProductionDetails find(ProductionDetailsFK fk) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM productiondetails WHERE proID=? and releaseID=?");
//        pst.setObject(1, fk.getProID());
//        pst.setObject(2, fk.getReleaseID());
        ResultSet rst = CrudUtil.execute("SELECT * FROM productiondetails WHERE proID=? and releaseID=?",fk.getProID(),fk.getReleaseID());
         ProductionDetails temp =null;
        while (rst.next()){
            LocalDate date=rst.getDate(2).toLocalDate();
            String bellsheet =rst.getString(3);
            double nofPack=rst.getDouble(4);
            double packPrice=rst.getDouble(5);
            double packUnit=rst.getDouble(6);
            double total =rst.getDouble(7);
            LocalDate madeDate=rst.getDate(8).toLocalDate();
            LocalDate expiry=rst.getDate(9).toLocalDate();
            String packType=rst.getString(10);
            String description=rst.getString(11);
            String productID =rst.getString(12);
            String releaseID =rst.getString(13);
            temp=new ProductionDetails(1,date,bellsheet,nofPack,packPrice,nofPack,total,madeDate,expiry,packType,description,productID,releaseID);
        }

        if(temp!=null){return temp;}
        return null;
    }


    @Override
    public List<ProductionDetails> findAll(String relaseId) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM productiondetails WHERE releaseID=?");
//        pst.setObject(1,relaseId);
        ResultSet rst = CrudUtil.execute("SELECT * FROM productiondetails WHERE releaseID=?",relaseId);
        List<ProductionDetails>temp =new ArrayList<>();
        while (rst.next()){
            LocalDate date=rst.getDate(2).toLocalDate();
            String bellsheet =rst.getString(3);
            double nofPack=rst.getDouble(4);
            double packPrice=rst.getDouble(5);
            double packUnit=rst.getDouble(6);
            double total =rst.getDouble(7);
            LocalDate madeDate=rst.getDate(8).toLocalDate();
            LocalDate expiry=rst.getDate(9).toLocalDate();
            String packType=rst.getString(10);
            String description=rst.getString(11);
            String productID =rst.getString(12);
            String releaseID =rst.getString(13);

            temp.add(new ProductionDetails(1,date,bellsheet,nofPack,packPrice,nofPack,total,madeDate,expiry,packType,description,productID,releaseID));
        }

        if(temp.size()>0){return temp;}
        return null;
    }
}
