package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.ProductCategoryDAO;
import com.revivatea.entity.ProductCategory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAOImpl implements ProductCategoryDAO {

    @Override
    public boolean save(ProductCategory pc) throws Exception {
        int r= CrudUtil.execute("INSERT INTO productcategory VALUES (?,?,?)",pc.getProductCateID(),pc.getName(),pc.getDescription());
    if(r>0){return true;}return false;
    }

    @Override
    public boolean update(ProductCategory pc) throws Exception {
        int r= CrudUtil.execute("UPDATE productcategory SET name=?, description=? WHERE pCatNo=?",pc.getName(),pc.getDescription(),pc.getProductCateID());
        if(r>0){return true;}return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        int r= CrudUtil.execute("DELETE FROM productcategory WHERE pCatNo=?",s);
        if(r>0){return true;}return false;
    }

    @Override
    public List<ProductCategory> findAll() throws Exception {
     ResultSet rst= CrudUtil.execute("SELECT * FROM productcategory ");
     List<ProductCategory>temp =new ArrayList<>();
     if(rst.next()){
         temp.add(new ProductCategory(rst.getString(1),rst.getString(2),rst.getString(3)));
     }
     if(temp.size()>0){return temp;}
        return null;
    }

    @Override
    public ProductCategory find(String s) throws Exception {
        ResultSet rst =CrudUtil.execute("SELECT * FROM productcategory WHERE pCatNo=?",s);
        while (rst.next()){
          return new ProductCategory(rst.getString(1),rst.getString(2),rst.getString(3));
        }
        return null;
    }
    @Override
    public String getID() throws Exception {
        ResultSet rst =CrudUtil.execute("SELECT pCatNo FROM productcategory order BY pCatNo desc LIMIT 1");
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

}
