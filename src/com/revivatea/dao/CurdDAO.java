package com.revivatea.dao;

import com.revivatea.entity.SuperEntity;

import java.util.List;

public interface CurdDAO<T extends SuperEntity,Key> extends SuperDAO {
    boolean save(T t)throws Exception;
    boolean update(T t)throws Exception;
    boolean delete(Key key)throws Exception;
    List<T>findAll()throws Exception;
    T find(Key key) throws Exception;
}
