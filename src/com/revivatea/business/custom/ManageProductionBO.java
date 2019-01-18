package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.ProductionDetailsDTO;
import com.revivatea.dto.SuperDTO;

import java.util.ArrayList;
import java.util.List;

public interface ManageProductionBO extends SuperBO {
    boolean saveProductionDetails(List<ProductionDetailsDTO> details)throws Exception;
    boolean updateProductionDetails(ProductionDetailsDTO details)throws Exception;
    boolean deleteProductionDetails(String proID ,String releaseID)throws Exception;
    List<ProductionDetailsDTO> viewAllDetails()throws Exception;
    ArrayList<ProductionDetailsDTO> searchDetails(String releaseID)throws Exception;
    boolean findMaterialRealseIsIn(String releaseID)throws Exception;
}
