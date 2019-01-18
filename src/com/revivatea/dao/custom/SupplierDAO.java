package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.entity.Supplier;

public interface SupplierDAO extends CurdDAO<Supplier,String> {
    String getID()throws Exception;
    boolean upateBalance(String supID,double balance) throws Exception;
}
