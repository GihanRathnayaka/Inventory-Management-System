package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.entity.MaterialReleaseDetails;

import java.util.List;

public interface MaterialReleaseDetailsDAO extends CurdDAO<MaterialReleaseDetails,String> {
    String getID()throws Exception;
    List<MaterialReleaseDetails> findAll(String relaseId) throws Exception;
}
