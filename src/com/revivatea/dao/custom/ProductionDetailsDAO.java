package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.dto.ProductionDetailsDTO;
import com.revivatea.entity.ProductionDetails;
import com.revivatea.entity.ProductionDetailsFK;

import java.util.List;

public interface ProductionDetailsDAO extends CurdDAO<ProductionDetails, ProductionDetailsFK> {
    //List<ProductionDetails>searchReleaseID(String releaseID)throws Exception;
    List<ProductionDetails> findAll(String relaseId) throws Exception;
}
