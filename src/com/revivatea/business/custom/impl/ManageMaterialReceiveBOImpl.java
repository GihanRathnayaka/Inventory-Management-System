package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageMaterialReceiveBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.MaterialReceiveDAO;
import com.revivatea.dao.custom.MaterialReceiveDetailsDAO;
import com.revivatea.dao.custom.RawMaterialDAO;
import com.revivatea.dao.custom.SupplierDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.dto.MaterialReceiveDTO;
import com.revivatea.dto.MaterialReceiveDetailsDTO;
import com.revivatea.entity.MaterialReceive;
import com.revivatea.entity.MaterialReceiveDetails;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ManageMaterialReceiveBOImpl implements ManageMaterialReceiveBO {

    private MaterialReceiveDetailsDAO receiveDetailsDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MATERIALRECEIVEDETAILS);
    private MaterialReceiveDAO materialReceiveDAO=DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MATERIALRECEIVE);
    private RawMaterialDAO rawMaterialDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.ROWMATERIAL);
    private static SupplierDAO supplierDAO= DAOfactory.getInstance().getDAO(DAOfactory.DAOType.SUPPLIER);
    private static MaterialReceiveDetailsDAO materialReceiveDetailsDAO =DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MATERIALRECEIVEDETAILS);
    @Override
    public boolean saveMaterialReceive(MaterialReceiveDTO receiveDTO, ArrayList<MaterialReceiveDetailsDTO> dtos) throws Exception {
        Connection connection = DBConnection.getInstance();
        connection.setAutoCommit(false);
        boolean result = materialReceiveDAO.save(Converter.getEntity(receiveDTO));
        if(!result){
            connection.rollback();
            return false;

        }
        for (MaterialReceiveDetailsDTO dto:dtos) {
            MaterialReceiveDetails entity = Converter.getEntity(dto);
            entity.getMaterialReceiveDetailsFK().setReceiveID(receiveDTO.getReceiveID());
            entity.getMaterialReceiveDetailsFK().setRowMatID(dto.getMatID());
            result = receiveDetailsDAO.save(entity);

            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            result = rawMaterialDAO.updateQty(entity.getQty(), dto.getMatID());
            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        }

        result = supplierDAO.upateBalance(receiveDTO.getSupplierID(), receiveDTO.getTotal());
        if(!result){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public boolean updateMaterialReceive(MaterialReceiveDTO receiveDTO, ArrayList<MaterialReceiveDetailsDTO> dtos) throws Exception {
        return false;
    }

    @Override
    public boolean deleteMaterialReceive(String matreceiveID) throws Exception {
        return false;
    }

    @Override
    public List<MaterialReceiveDTO> getAllMaterialRecievie() throws Exception {
        List<MaterialReceive> all = materialReceiveDAO.findAll();
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    @Override
    public List<MaterialReceiveDetailsDTO> getAllMaterialRecievieDetails() throws Exception {
        List<MaterialReceiveDetails> all = materialReceiveDetailsDAO.findAll();
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    @Override
    public MaterialReceiveDTO findMaterialRecievie(String matID) throws Exception {
        List<MaterialReceiveDetails> all = materialReceiveDetailsDAO.findAll(matID);
        if(all==null){return null;}
        return  new MaterialReceiveDTO();
    }

    @Override
    public MaterialReceiveDetailsDTO findMaterialReceiveDetails(String matID, String matReceiveID) throws Exception {
        return null;
    }

    @Override
    public String getMatrialReceiveID() throws Exception {
        String txtPart = materialReceiveDAO.getMatrecieveID();
        if(txtPart==null){return "MRNO01";}
        return txtPart.substring(0,5)+(1+Integer.parseInt(txtPart.substring(4)));

    }
}
