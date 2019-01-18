package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageSupplierBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.SupplierDAO;
import com.revivatea.dto.SupplierDTO;
import com.revivatea.dto.UserDTO;
import com.revivatea.entity.Supplier;

import java.util.List;

public class ManageSupplierBOImpl implements ManageSupplierBO {
    private static SupplierDAO supplierDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.SUPPLIER);
    @Override
    public boolean createSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.save(Converter.getEntity(supplier));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.update(Converter.getEntity(supplier));
    }

    @Override
    public boolean deleteSupplier(String sid) throws Exception {
        return supplierDAO.delete(sid);
    }

    @Override
    public List<SupplierDTO> getAllSupplier() throws Exception {
        List<Supplier> all = supplierDAO.findAll();
        if(all==null){return null; }
        return Converter.getDTOList(all);

    }

    @Override
    public SupplierDTO getSupplier(String sid) throws Exception {
        Supplier supplier = supplierDAO.find(sid);
        if(supplier==null){return null;}
       // System.out.println(supplier);
        return Converter.getDTO(supplier);

    }

    @Override
    public String getSupplierID() throws Exception {
        String txtPart = supplierDAO.getID();
        if(txtPart==null){return "SNO01";}
        return txtPart.substring(0,4)+(1+(Integer.parseInt(txtPart.substring(4))));
    }
}
