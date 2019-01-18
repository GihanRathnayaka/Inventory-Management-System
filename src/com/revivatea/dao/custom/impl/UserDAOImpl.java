package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.CurdDAO;
import com.revivatea.dao.custom.UserDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.SuperEntity;
import com.revivatea.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public boolean save(User user) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
//        pst.setObject(1,user.getUserName());
//        pst.setObject(2,user.getPassword());
//        pst.setObject(3,user.getName());
//        pst.setObject(4,user.getUserType());

       int r= CrudUtil.execute("INSERT INTO users VALUES (?,?,?,?)",user.getUserName(),user.getPassword(),user.getName(),user.getUserType());

      if( r>0){
          return true;
      }
      return false;
    }

    @Override
    public boolean update(User user) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE users set  password=?, name=?,userType=? WHERE userName=? ");
//        pst.setObject(4,user.getUserName());
//        pst.setObject(1,user.getPassword());
//        pst.setObject(2,user.getName());
//        pst.setObject(3,user.getUserType());
        String sql ="UPDATE users set  password=?, name=?,userType=? WHERE userName=? ";
      int r = CrudUtil.execute(sql,user.getPassword(),user.getName(),user.getUserType(),user.getUserName());
        if(  r>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("DELETE FROM users WHERE userName=? ");
        int r= CrudUtil.execute("DELETE FROM users WHERE userName=? ",s);
        if(r>0){
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT *FROM users");
        ResultSet rst = CrudUtil.execute("SELECT *FROM users");
        ArrayList<User> users= new ArrayList<>();
        while (rst.next()){
            users.add(new User(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        if(users.size()>0){return users;}
        return null;
    }

    @Override
    public User find(String s) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT *FROM users WHERE userName=?");
//        pst.setObject(1,s);
        ResultSet rst = CrudUtil.execute("SELECT *FROM users WHERE userName=?",s);
        User user;
        while (rst.next()){
           return user=new User(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean passwordChange(String username,String pssword) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE users set  password=?  WHERE userName=? ");
//        pst.setObject(1,pssword);
//        pst.setObject(2,username);

       int r= CrudUtil.execute("UPDATE users set  password=?  WHERE userName=? ",pssword,username);

        if( r>0){
            return true;
        }
        return false;
    }
}
