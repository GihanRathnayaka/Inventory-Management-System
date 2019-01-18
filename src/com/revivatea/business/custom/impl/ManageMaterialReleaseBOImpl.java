package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageMaterialReleaseBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.*;
import com.revivatea.db.DBConnection;
import com.revivatea.dto.MaterialReleaseDTO;
import com.revivatea.dto.MaterialReleaseDetailsDTO;
import com.revivatea.entity.Fifo;
import com.revivatea.entity.MaterialReceiveDetails;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ManageMaterialReleaseBOImpl implements ManageMaterialReleaseBO {
    private static MaterialReleaseDAO materialReleaseDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MaterialRELEASE);
    private static MaterialReleaseDetailsDAO materialReleaseDetailsDAO= DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MaterialRELEASEDETAILS);
    private static FifoDAO fifoDAO= DAOfactory.getInstance().getDAO(DAOfactory.DAOType.FIFO);
    private static MaterialReceiveDetailsDAO receiveDetailsDAO =DAOfactory.getInstance().getDAO(DAOfactory.DAOType.MATERIALRECEIVEDETAILS);
    private static RawMaterialDAO rawMaterialDAO =DAOfactory.getInstance().getDAO(DAOfactory.DAOType.ROWMATERIAL);
    @Override
    public boolean saveMaterialRelease(MaterialReleaseDTO materialReleaseDTO, ArrayList<MaterialReleaseDetailsDTO> releaseDetailsDTOS) throws Exception {
        Connection connection = DBConnection.getInstance();
        connection.setAutoCommit(false);
        boolean result = materialReleaseDAO.save(Converter.getEntity(materialReleaseDTO));
        if(!result){
           connection.rollback();
           connection.setAutoCommit(true);
           return false;
        }
        for (MaterialReleaseDetailsDTO dto:releaseDetailsDTOS ) {
          result=  materialReleaseDetailsDAO.save(Converter.getEntity(dto));
          System.out.println(dto.getMatRdID());
            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            List<MaterialReceiveDetails> receiveDetailsList = receiveDetailsDAO.findReceiveDetailsMatID(dto.getRowMatID());

            double qty = dto.getReleaseqty();

            for (MaterialReceiveDetails mrd:receiveDetailsList) {

                if(mrd.getAviableQty()<qty){

                    result = fifoDAO.save(new Fifo(mrd.getPrice(), mrd.getAviableQty(), (mrd.getAviableQty() * mrd.getPrice()), mrd.getId(), dto.getMatRdID()));
                    if(!result){
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                    result = receiveDetailsDAO.aviableQty(mrd.getId(), mrd.getMaterialReceiveDetailsFK().getRowMatID(),mrd.getAviableQty());
                    if(!result){
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                   qty=qty-mrd.getAviableQty();

                }else {

                    result = fifoDAO.save(new Fifo(mrd.getPrice(),qty, (qty * mrd.getPrice()), mrd.getId(), dto.getMatRdID()));
                    if(!result){
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                    result = receiveDetailsDAO.aviableQty(mrd.getId(), mrd.getMaterialReceiveDetailsFK().getRowMatID(), qty);
                    if(!result){
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }

                    qty=0;
                    break;
                }

            }


           result= rawMaterialDAO.updateQty(-dto.getReleaseqty(),dto.getRowMatID());
            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        }


        connection.commit();
        connection.setAutoCommit(true);
        return true;

    }

    @Override
    public boolean updateMaterialRelease(MaterialReleaseDTO materialReleaseDTO, ArrayList<MaterialReleaseDetailsDTO> releaseDetailsDTOS) throws Exception {
        return false;
    }

    @Override
    public String materialReleaseNumber() throws Exception {
        String txtPart = materialReleaseDAO.getID();
        if(null==txtPart){return "MRNO01";}
        return txtPart.substring(0,4)+(1+(Integer.parseInt(txtPart.substring(4))));
    }

    @Override
    public double getQuantiyValue(String matID,double qty) throws Exception {
        List<MaterialReceiveDetails> receiveDetails = receiveDetailsDAO.findReceiveDetailsMatID(matID);
        if(receiveDetails==null){return 0 ;}
        double requestQty =qty;
        double value =0;
        double value2 =0;

        for (MaterialReceiveDetails mrd:receiveDetails) {
            if(requestQty>mrd.getAviableQty()){
                value=value+( mrd.getAviableQty()*mrd.getPrice());
                requestQty=requestQty-mrd.getAviableQty();
            }else {
                value2=value2+(mrd.getPrice()*requestQty);
                return value+value2;
            }
        }
        return value+value2;
    }

    @Override
    public String materialReleaseDetailNumber() throws Exception {
        String txtPart = materialReleaseDetailsDAO.getID();
        if(txtPart==null){return "MRDNO01";}
        return txtPart.substring(0,5)+(1+(Integer.parseInt(txtPart.substring(5))));
    }


}
