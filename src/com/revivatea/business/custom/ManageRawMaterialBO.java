package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.RawMaterialDTO;
import com.revivatea.dto.UserDTO;

import java.util.List;

public interface ManageRawMaterialBO extends SuperBO {
    boolean createRowmaterial(RawMaterialDTO rawMat) throws Exception;
    boolean updateRowmaterial(RawMaterialDTO rawMat) throws Exception;
    boolean deleteRowmaterial(String matId) throws Exception;
    List<RawMaterialDTO> getAllRowmaterial()throws Exception;
    RawMaterialDTO getRowmaterial(String matNumber) throws Exception;
    String getMatNumber()throws Exception;
}
