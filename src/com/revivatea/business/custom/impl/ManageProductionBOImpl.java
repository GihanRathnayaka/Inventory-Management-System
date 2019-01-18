package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageProductionBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.MaterialReleaseDAO;
import com.revivatea.dao.custom.ProductDAO;
import com.revivatea.dao.custom.ProductionDetailsDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.dto.ProductionDetailsDTO;
import com.revivatea.dto.SuperDTO;
import com.revivatea.entity.MaterialRelease;
import com.revivatea.entity.Product;
import com.revivatea.entity.ProductionDetails;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ManageProductionBOImpl implements ManageProductionBO {
    private static ProductionDetailsDAO productionDetailsDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.PRODUCTIONDETAILS);
    private static MaterialReleaseDAO  materialReleaseDAO =DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MaterialRELEASE);
    private static ProductDAO productDAO =DAOfactory.getInstance().getDAO(DAOfactory.DAOType.PRODUCT);
    @Override
    public boolean saveProductionDetails(List<ProductionDetailsDTO> details) throws Exception {
        Connection connection = DBConnection.getInstance();
        connection.setAutoCommit(false);
        for (ProductionDetailsDTO dto:details ) {
            boolean result = productionDetailsDAO.save(Converter.getEntity(dto));
            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
            }

          result=  productDAO.updateAqty(dto.getProID(),dto.getNofPackage());
            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
            }

        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public boolean updateProductionDetails(ProductionDetailsDTO details) throws Exception {
        return false;
    }

    @Override
    public boolean deleteProductionDetails(String proID, String releaseID) throws Exception {
        return false;
    }

    @Override
    public List<ProductionDetailsDTO> viewAllDetails() throws Exception {
        List<ProductionDetails> all = productionDetailsDAO.findAll();
         if (all==null){return null;}
       return Converter.getDTOList(all);

    }

    @Override
    public ArrayList<ProductionDetailsDTO> searchDetails(String releaseID) throws Exception {
        return null;
    }

    @Override
    public boolean findMaterialRealseIsIn(String releaseID) throws Exception {
        MaterialRelease materialRelease = materialReleaseDAO.find(releaseID.toUpperCase());
        if(materialRelease==null){return false;}
        return true;
    }
}
