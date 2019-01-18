package com.revivatea.business.custom.impl;

import com.revivatea.business.Converter;
import com.revivatea.business.custom.ManageUserBO;
import com.revivatea.dao.DAOfactory;
import com.revivatea.dao.custom.UserDAO;
import com.revivatea.dto.UserDTO;
import com.revivatea.entity.User;

import java.util.List;



public class ManageUserBOImpl implements ManageUserBO {
    private static UserDAO userDAO = DAOfactory.getInstance().getDAO(DAOfactory.DAOType.USER);
    @Override
    public boolean createUser(UserDTO userDTO) throws Exception {
       return userDAO.save(Converter.getEntity(userDTO));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return userDAO.update(Converter.getEntity(userDTO));
    }

    @Override
    public boolean deleteUser(String username) throws Exception {
       return userDAO.delete(username);
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
    return   Converter.getDTOList(  userDAO.findAll());
    }

    @Override
    public UserDTO getUser(String username) throws Exception {
        User user = userDAO.find(username);
        if(user==null){return null;}
        return Converter.getDTO( user);
    }

    @Override
    public boolean resetPassword(String username,String pssword) throws Exception {
        return userDAO.passwordChange(username,pssword);
    }
}
