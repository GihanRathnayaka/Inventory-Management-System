package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.ProductDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public boolean save(Product product) throws Exception {
        //Connection connection = DBConnection.getInstance();
        //PreparedStatement pst = connection.prepareStatement("INSERT INTO product(proID, name, req_Date, description, AviQty) VALUES (?,?,?,?,?)");
        String sql ="INSERT INTO product(proID, name, req_Date, description, AviQty) VALUES (?,?,?,?,?)";
        int r=CrudUtil.execute(sql,product.getProID(),product.getName(),product.getReq_Date(),product.getDescription(),product.getAviQty());
//
//        pst.setObject(1,product.getProID());
//        pst.setObject(2,product.getName());
//        pst.setObject(3,product.getReq_Date());
//        pst.setObject(4,product.getDescription());
//        pst.setObject(5,product.getAviQty());
        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Product product) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("UPDATE product SET  name=?, req_Date=?, description=? WHERE proID=?");
        String sql ="UPDATE product SET  name=?, req_Date=?, description=? WHERE proID=?";
       int r= CrudUtil.execute(sql,product.getName(),product.getReq_Date(),product.getDescription(),product.getProID());
//        pst.setObject(4,product.getProID());
//        pst.setObject(1,product.getName());
//        pst.setObject(2,product.getReq_Date());
//        pst.setObject(3,product.getDescription());

        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM product WHERE proID=?");
        pst.setObject(1,s);
       int r= CrudUtil.execute("DELETE FROM product WHERE proID=?",s);


        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findAll() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM product");
        ResultSet rst =CrudUtil.execute("SELECT * FROM product");
        ArrayList<Product>list = new ArrayList<>();
        while (rst.next()){
            String proID=rst.getString(1);
            String name =rst.getString(2);
            LocalDate regDate =rst.getDate(3).toLocalDate();
            String description =rst.getString(4);
            double qty =rst.getDouble(5);
            list.add(new Product(proID,name,regDate,description,qty));
        }

        if(list.size()>0){return list;}
        return null;
    }

    @Override
    public Product find(String s) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM product WHERE proID=?");
//        pst.setObject(1,s);
        ResultSet rst = CrudUtil.execute("SELECT * FROM product WHERE proID=?",s);
       Product temp = null;
        while (rst.next()){
            String proID=rst.getString(1);
            String name =rst.getString(2);
            LocalDate regDate =rst.getDate(3).toLocalDate();
            String description =rst.getString(4);
            double qty =rst.getDouble(5);
            temp=new Product(proID,name,regDate,description,qty);
        }

        if(temp!=null){return temp;}
        return null;
    }

    @Override
    public String getID() throws Exception {
        //Connection connection = DBConnection.getInstance();
       // PreparedStatement pst = connection.prepareStatement("SELECT proID FROM product ORDER BY proID DESC Limit 1");
        ResultSet rst = CrudUtil.execute("SELECT proID FROM product ORDER BY proID DESC Limit 1");
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean updateAqty(String id, double Qty) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE product SET AviQty=? WHERE proID=?");
//        pst.setObject(1,Qty);
//        pst.setObject(2,id);
        int r= CrudUtil.execute("UPDATE product SET AviQty=? WHERE proID=?",Qty,id);
        if(r>0){
            return true;
        }
        return false;
    }
}
