package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageRawMaterialBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.RawMaterialDAO;
import com.revivatea.dto.RawMaterialDTO;
import com.revivatea.dto.UserDTO;
import com.revivatea.entity.RawMaterial;

import java.util.List;



public class ManageRawMaterialBOImpl implements ManageRawMaterialBO {

    private static RawMaterialDAO materialDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.ROWMATERIAL);

    @Override
    public boolean createRowmaterial(RawMaterialDTO rawMat) throws Exception {
        return materialDAO.save(Converter.getEntity(rawMat));
    }

    @Override
    public boolean updateRowmaterial(RawMaterialDTO rawMat) throws Exception {
        return materialDAO.update(Converter.getEntity(rawMat));
    }

    @Override
    public boolean deleteRowmaterial(String matId) throws Exception {
        return materialDAO.delete(matId);
    }

    @Override
    public List<RawMaterialDTO> getAllRowmaterial() throws Exception {
        List<RawMaterial> all = materialDAO.findAll();
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    @Override
    public RawMaterialDTO getRowmaterial(String matNumber) throws Exception {
        RawMaterial material = materialDAO.find(matNumber);
        if(material==null){return null;}
        return Converter.getDTO(material);
    }

    @Override
    public String getMatNumber() throws Exception {
        String txtPart =materialDAO.rowMatNumber();
        if(txtPart==null){
            return "RMAT01";
        }

        List<RawMaterial> all = materialDAO.findAll();
            int x=0;
        for ( RawMaterial r:all) {
           if( Integer.parseInt(r.getRowMatID().substring(5))>x){
               x=Integer.parseInt(r.getRowMatID().substring(5));
           }
        }

        return txtPart.substring(0,5)+(1+x);
    }
}
