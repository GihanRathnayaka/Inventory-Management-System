package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.ProductDTO;

import java.util.List;

public interface ManageProductBO extends SuperBO {
    boolean createNewProduct(ProductDTO product)throws Exception;
    boolean UpdateProduct(ProductDTO product)throws Exception;
    boolean DeleteProduct(String PID)throws Exception;
    List<ProductDTO> getAllProduct()throws Exception;
    ProductDTO getProduct(String Pid)throws Exception;
    String  getProductID()throws Exception;

}
