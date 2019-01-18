package com.revivatea.dao.custom;

import com.revivatea.dao.CurdDAO;
import com.revivatea.entity.User;

public interface UserDAO extends CurdDAO<User,String> {
    boolean passwordChange(String username,String pssword)throws Exception;
}
