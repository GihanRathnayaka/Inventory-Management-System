package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.entity.MaterialRelease;

import java.util.List;

public interface MaterialReleaseDAO extends CurdDAO<MaterialRelease,String> {
    String getID()throws Exception;
    List<MaterialRelease> findAll(String rid) throws Exception;
}
