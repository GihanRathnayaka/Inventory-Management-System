package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.MaterialReleaseDTO;
import com.revivatea.dto.MaterialReleaseDetailsDTO;
import com.revivatea.entity.MaterialRelease;

import java.util.ArrayList;

public interface ManageMaterialReleaseBO extends SuperBO {
    boolean saveMaterialRelease(MaterialReleaseDTO materialReleaseDTO, ArrayList<MaterialReleaseDetailsDTO>releaseDetailsDTOS)throws Exception;
    boolean updateMaterialRelease(MaterialReleaseDTO materialReleaseDTO, ArrayList<MaterialReleaseDetailsDTO>releaseDetailsDTOS)throws Exception;
    String materialReleaseNumber()throws Exception;
    double getQuantiyValue(String matID,double qty)throws Exception;
    String materialReleaseDetailNumber()throws Exception;

}
