package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ViewDetailsBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.*;
import com.revivatea.dto.*;
import com.revivatea.entity.*;

import java.util.List;

public class ViewDetailsBOImpl implements ViewDetailsBO {

    private static MaterialReceiveDAO materialReceiveDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MATERIALRECEIVE);
    private static MaterialReceiveDetailsDAO materialReceiveDetailsDAO =DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MATERIALRECEIVEDETAILS);
    private static MaterialReleaseDAO materialReleaseDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MaterialRELEASE);
    private static MaterialReleaseDetailsDAO materialReleaseDetailsDAO =DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MaterialRELEASEDETAILS);
    private static ProductionDetailsDAO productionDetailsDAO=DAOfactory.getInstance().getDAO(DAOfactory.DAOType.PRODUCTIONDETAILS);

    @Override
    public List<MaterialReceiveDTO> getAllMaterialReceiveDetails() throws Exception {
        List<MaterialReceive> all = materialReceiveDAO.findAll();
        if(all==null){return null;}
       return Converter.getDTOList(all);
    }

    @Override
    public List<MaterialReceiveDTO> getAllMaterialReceiveDetails(String receiveID) throws Exception {
        List<MaterialReceive> all = materialReceiveDAO.findAll(receiveID);
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    @Override
    public List<MaterialReceiveDetailsDTO> getAllMaterialReceiveDetailsDtoList(String rsID) throws Exception {
        List<MaterialReceiveDetails> all = materialReceiveDetailsDAO.findAll(rsID);
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    @Override
    public List<MaterialReleaseDTO> searchReleaseList(String rsID) throws Exception {
        List<MaterialRelease> all = materialReleaseDAO.findAll(rsID);
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    @Override
    public List<MaterialReleaseDTO> allresleasedList() throws Exception {
        List<MaterialRelease> all = materialReleaseDAO.findAll();
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    @Override
    public List<MaterialReleaseDetailsDTO> allresleasedDetailsList(String releaseID) throws Exception {
        List<MaterialReleaseDetails> all = materialReleaseDetailsDAO.findAll("MRNO01");
       // System.out.println(all);
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    @Override
    public List<ProductionDetailsDTO> findAllProductionDetails(String releaseID) throws Exception {
        List<ProductionDetails> all = productionDetailsDAO.findAll(releaseID);
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }
}
