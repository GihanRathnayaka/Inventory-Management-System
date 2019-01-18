package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageProductCategoryBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.ProductCategoryDAO;
import com.revivatea.dto.ProductCategoryDTO;

import java.util.List;

public class ManageProductCategoryBOImpl implements ManageProductCategoryBO {

    private static ProductCategoryDAO productCategoryDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.PRODUCTCATEGORY);

    @Override
    public boolean saveManageProductCategory(ProductCategoryDTO pc) throws Exception {
        return productCategoryDAO.save( Converter.getEntity(pc));
    }

    @Override
    public boolean updateManageProductCategory(ProductCategoryDTO pc) throws Exception {
        return productCategoryDAO.update( Converter.getEntity(pc));
    }

    @Override
    public boolean deleteManageProductCategory(String Id) throws Exception {
        return productCategoryDAO.delete(Id);
    }

    @Override
    public List<ProductCategoryDTO> allProductCategory() throws Exception {
        return null;
    }

    @Override
    public ProductCategoryDTO searchCaterory() throws Exception {
        return null;
    }

    @Override
    public String getCategoryNumber() throws Exception {
        return null;
    }
}
