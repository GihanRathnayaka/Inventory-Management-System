package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.ProductCategoryDTO;

import java.util.List;

public interface ManageProductCategoryBO extends SuperBO {
    boolean saveManageProductCategory(ProductCategoryDTO pc)throws Exception;
    boolean updateManageProductCategory(ProductCategoryDTO pc)throws Exception;
    boolean deleteManageProductCategory(String Id)throws Exception;
    List<ProductCategoryDTO> allProductCategory()throws Exception;
    ProductCategoryDTO searchCaterory()throws Exception;
    String getCategoryNumber()throws Exception;
}
