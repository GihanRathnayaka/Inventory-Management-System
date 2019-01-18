package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.entity.RawMaterial;

public interface RawMaterialDAO extends CurdDAO<RawMaterial,String> {
    String rowMatNumber()throws Exception;
    boolean updateQty(double qty,String matId) throws Exception;
}
