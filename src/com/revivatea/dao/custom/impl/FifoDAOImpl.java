package com.revivatea.dao.custom.impl;

import com.revivatea.dao.CrudUtil;
import com.revivatea.dao.custom.FifoDAO;
import com.revivatea.db.DBConnection;
import com.revivatea.entity.FifoFK;
import com.revivatea.entity.Fifo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FifoDAOImpl implements FifoDAO {
    @Override
    public boolean save(Fifo fifo) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("INSERT INTO fifo(price, reQTy, totalvalue, id, matRdID) VALUES (?,?,?,?,?)");
        String sql ="INSERT INTO fifo(price, reQTy, totalvalue, id, matRdID) VALUES (?,?,?,?,?)";
     int r=  CrudUtil.execute(sql,fifo.getPrice(),fifo.getReQTy(),fifo.getTotalvalue(),fifo.getFifoFK().getId(),fifo.getFifoFK().getMatRdID());
//        pst.setObject(1,fifo.getPrice());
//        pst.setObject(2,fifo.getReQTy());
//        pst.setObject(3,fifo.getTotalvalue());
//        pst.setObject(4,fifo.getFifoFK().getId());
//        pst.setObject(5,fifo.getFifoFK().getMatRdID());
        if(r>0){return true;}
        return false;
    }

    @Override
    public boolean update(Fifo fifo) throws Exception {
      //  Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE fifo SET  price=?, reQTy=?, totalvalue=? WHERE  id=? AND matRdID =? ");
          String sql ="UPDATE fifo SET  price=?, reQTy=?, totalvalue=? WHERE  id=? AND matRdID =? ";
         int r= CrudUtil.execute(sql,fifo.getPrice(),fifo.getReQTy(),fifo.getTotalvalue(),fifo.getFifoFK().getId(),fifo.getFifoFK().getMatRdID());
//        pst.setObject(1,fifo.getPrice());
//        pst.setObject(2,fifo.getReQTy());
//        pst.setObject(3,fifo.getTotalvalue());
//        pst.setObject(4,fifo.getFifoFK().getId());
//        pst.setObject(5,fifo.getFifoFK().getMatRdID());
        if(r>0){return true;}
        return false;
    }

    @Override
    public boolean delete(FifoFK fk ) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("UPDATE fifo SET  price=?, reQTy=?, totalvalue=? WHERE  id=? AND matRdID =? ");
        String sql ="DELETE FROM WHERE  id=? AND matRdID =? ";
        int r= CrudUtil.execute(sql,fk.getId(),fk.getMatRdID());
//        pst.setObject(1,fk.getId());
//        pst.setObject(2,fk.getMatRdID());
        if(r>0){return true;}

        return false;
    }

    @Override
    public List<Fifo> findAll() throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM fifo");
        ResultSet rst = CrudUtil.execute("SELECT * FROM fifo");
        List<Fifo> temp =new ArrayList<>();
        while (rst.next()){
             double price =rst.getDouble(2);
             double   reQTy =rst.getDouble(3);
             double  totalvalue =rst.getDouble(4);
             int   id =rst.getInt(5);
             String matRdID=rst.getString(6);
             temp.add(new Fifo(price,reQTy,totalvalue,new FifoFK(id,matRdID)));
        }
        if(temp.size()>0){return temp;}
        return null;
    }

    @Override
    public Fifo find(FifoFK fk) throws Exception {
//        Connection connection = DBConnection.getInstance();
//        PreparedStatement pst = connection.prepareStatement("SELECT * FROM fifo WHERE id=? AND matRdID=?");
        String sql="SELECT * FROM fifo WHERE id=? AND matRdID=?";

//        pst.setObject(1,fk.getId());
//        pst.setObject(2,fk.getMatRdID());
        ResultSet rst = CrudUtil.execute(sql,fk.getId(),fk.getMatRdID());
        Fifo temp =null;
        while (rst.next()){
            double price =rst.getDouble(2);
            double   reQTy =rst.getDouble(3);
            double  totalvalue =rst.getDouble(4);
            int   id =rst.getInt(5);
            String matRdID=rst.getString(6);
            temp=new Fifo(price,reQTy,totalvalue,new FifoFK(id,matRdID));
        }
        if(temp!=null){return temp;}
        return null;
    }
}
