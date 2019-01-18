package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageProductBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.ProductDAO;
import com.revivatea.dto.ProductDTO;
import com.revivatea.entity.Product;

import java.util.List;

public class ManageProductBOImpl implements ManageProductBO {

   private static ProductDAO productDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.PRODUCT);

    @Override
    public boolean createNewProduct(ProductDTO product) throws Exception {
        return productDAO.save(Converter.getEntity(product));
    }

    @Override
    public boolean UpdateProduct(ProductDTO product) throws Exception {
        return productDAO.update(Converter.getEntity(product));
    }

    @Override
    public boolean DeleteProduct(String PID) throws Exception {
        return productDAO.delete(PID);
    }

    @Override
    public List<ProductDTO> getAllProduct() throws Exception {
        List<Product> all = productDAO.findAll();
        if(all==null){return null;}
        return Converter.getDTOList(all);

    }

    @Override
    public ProductDTO getProduct(String Pid) throws Exception {
        Product product = productDAO.find(Pid);
        if(product==null){return null;}
        return Converter.getDTO(product);

    }

    @Override
    public String getProductID() throws Exception {
        String txtPart =productDAO.getID();
        if(txtPart==null){return "PRNO01";}
        return txtPart.substring(0,5)+(1+Integer.parseInt(txtPart.substring(5)));
    }
}
