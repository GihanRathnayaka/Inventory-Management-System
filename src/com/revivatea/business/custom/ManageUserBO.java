package com.revivatea.business.custom;

import com.revivatea.business.SuperBO;
import com.revivatea.dto.UserDTO;
import com.revivatea.entity.User;

import java.util.List;

public interface ManageUserBO extends SuperBO {
    boolean createUser(UserDTO userDTO) throws Exception;
    boolean updateUser(UserDTO userDTO) throws Exception;
    boolean deleteUser(String username) throws Exception;
    List<UserDTO>getAllUsers()throws Exception;
    UserDTO getUser(String username) throws Exception;
    boolean resetPassword(String username ,String pssword)throws Exception;

}
