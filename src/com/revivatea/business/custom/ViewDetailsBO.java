package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.*;

import java.util.List;

public interface ViewDetailsBO extends SuperBO {
    List<MaterialReceiveDTO>getAllMaterialReceiveDetails()throws Exception;
    List<MaterialReceiveDTO>getAllMaterialReceiveDetails(String receiveID)throws Exception;
    List<MaterialReceiveDetailsDTO>getAllMaterialReceiveDetailsDtoList(String rsID)throws Exception;
    List<MaterialReleaseDTO>searchReleaseList(String rsID)throws Exception;
    List<MaterialReleaseDTO>allresleasedList()throws Exception;
    List<MaterialReleaseDetailsDTO>allresleasedDetailsList(String releaseID)throws Exception;
    List<ProductionDetailsDTO>findAllProductionDetails(String releaseID)throws Exception;

}
