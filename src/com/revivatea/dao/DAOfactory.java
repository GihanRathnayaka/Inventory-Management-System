package com.revivatea.dao;

import com.revivatea.dao.custom.impl.ProductionDetailsDAOImpl;
import com.revivatea.dao.custom.impl.*;

public class DAOfactory {
  private   static DAOfactory daOfactory;
    private DAOfactory(){

    }
    public enum DAOType{
        USER,ROWMATERIAL,SUPPLIER,MATERIALRECEIVE,MATERIALRECEIVEDETAILS,MaterialRELEASE,MaterialRELEASEDETAILS,
        FIFO,PRODUCT,PRODUCTIONDETAILS,PRODUCTCATEGORY
    }
    public static DAOfactory getInstance() {
        if (daOfactory == null) {
            daOfactory = new DAOfactory();
        }
        return daOfactory;
    }
    public <T extends SuperDAO>T getDAO(DAOType daoType){
        switch (daoType){
            case USER: return (T) new UserDAOImpl();
            case ROWMATERIAL: return (T) new RawMaterialDAOImpl();
            case SUPPLIER: return (T) new SupplierDAOImpl();
            case MATERIALRECEIVE:return (T) new MaterialReceiveDAOImpl();
            case MATERIALRECEIVEDETAILS:return (T) new MaterialReceiveDetailsDAOImpl();
            case FIFO:return (T) new FifoDAOImpl();
            case MaterialRELEASE:return (T) new MaterialReleaseDAOImpl();
            case MaterialRELEASEDETAILS:return (T) new MaterialReleaseDetailsDAOImpl();
            case PRODUCT:return (T) new ProductDAOImpl();
            case PRODUCTIONDETAILS:return (T) new ProductionDetailsDAOImpl();
            case PRODUCTCATEGORY: return (T) new ProductCategoryDAOImpl();
            default:return null;
        }
    }

}
