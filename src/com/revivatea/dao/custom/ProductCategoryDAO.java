package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.entity.ProductCategory;

public interface ProductCategoryDAO extends CurdDAO<ProductCategory,String> {
    String getID()throws Exception;
}
