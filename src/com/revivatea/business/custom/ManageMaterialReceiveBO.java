package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.MaterialReceiveDTO;
import com.revivatea.dto.MaterialReceiveDetailsDTO;

import java.util.ArrayList;
import java.util.List;

public interface ManageMaterialReceiveBO extends SuperBO {
    boolean saveMaterialReceive(MaterialReceiveDTO receiveDTO, ArrayList<MaterialReceiveDetailsDTO> dtos) throws Exception;
    boolean updateMaterialReceive(MaterialReceiveDTO receiveDTO, ArrayList<MaterialReceiveDetailsDTO> dtos) throws Exception;
    boolean deleteMaterialReceive(String matreceiveID)throws Exception;
    List<MaterialReceiveDTO>getAllMaterialRecievie()throws Exception;
    List<MaterialReceiveDetailsDTO>getAllMaterialRecievieDetails()throws Exception;
    MaterialReceiveDTO findMaterialRecievie(String matID)throws Exception;
    MaterialReceiveDetailsDTO findMaterialReceiveDetails(String matID,String matReceiveID)throws Exception;
    String getMatrialReceiveID()throws Exception;

}
