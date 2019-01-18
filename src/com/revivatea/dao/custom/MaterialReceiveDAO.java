package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.entity.MaterialReceive;

import java.util.List;

public interface MaterialReceiveDAO extends CurdDAO<MaterialReceive,String> {
    String getMatrecieveID()throws Exception;
    public List<MaterialReceive> findAll(String releaseID) throws Exception;
}
