package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.SupplierDTO;
import com.revivatea.dto.UserDTO;

import java.util.List;

public interface ManageSupplierBO extends SuperBO {
    boolean createSupplier(SupplierDTO supplier) throws Exception;
    boolean updateSupplier(SupplierDTO supplier) throws Exception;
    boolean deleteSupplier(String username) throws Exception;
    List<SupplierDTO> getAllSupplier()throws Exception;
    SupplierDTO getSupplier(String sid) throws Exception;
    String getSupplierID()throws Exception;
}
