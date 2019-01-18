package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.dto.MaterialReceiveDetailsDTO;
import com.revivatea.entity.MaterialReceiveDetails;
import com.revivatea.entity.MaterialReceiveDetailsFK;

import java.util.List;

public interface MaterialReceiveDetailsDAO extends CurdDAO<MaterialReceiveDetails ,MaterialReceiveDetailsFK> {
   // MaterialReceiveDetailsDTO findMaterialReceiveDetails(MaterialReceiveDetailsFK fk)throws Exception;

    List<MaterialReceiveDetails> findReceiveDetailsMatID(String MatID)throws Exception;
    boolean aviableQty(int id ,String matID ,double qty)throws Exception;
    //List<MaterialReceiveDetails>materialDetailsListMatNumber(String matId)throws Exception;
      List<MaterialReceiveDetails> findAll(String receiveID) throws Exception;
}
