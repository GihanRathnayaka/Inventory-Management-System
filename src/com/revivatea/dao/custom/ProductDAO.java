package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.entity.Product;

public interface ProductDAO extends CurdDAO<Product,String > {
    String getID()throws Exception;
    boolean updateAqty(String id ,double Qty)throws Exception;
}
