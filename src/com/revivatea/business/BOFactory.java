package com.revivatea.business;

import com.revivatea.business.custom.ManageRawMaterialBO;
import com.revivatea.business.custom.ViewDetailsBO;
import com.revivatea.business.custom.impl.*;
import com.revivatea.dto.MaterialReleaseDetailsDTO;

public class BOFactory {
    private static BOFactory boFactory;
    public enum BOType{
        UserBo,MANAGERAWMATERIAL,ManageSUPPLIER,MANAGEMATERIALRECEIVE,MATERIALRELEASE,MANAGEPRODUCT,MANAGEPRODUCTIONDETAILS,
        VIEWDETAILS
    }
    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if(boFactory==null){
          boFactory=  new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO>T getBO(BOType boType){
        switch (boType){
            case MANAGEMATERIALRECEIVE:return (T) new ManageMaterialReceiveBOImpl();
            case UserBo: return (T) new ManageUserBOImpl();
            case MANAGERAWMATERIAL:return (T) new ManageRawMaterialBOImpl();
            case ManageSUPPLIER:return (T) new ManageSupplierBOImpl();
            case MATERIALRELEASE:return (T) new ManageMaterialReleaseBOImpl();
            case MANAGEPRODUCT: return (T) new ManageProductBOImpl();
            case MANAGEPRODUCTIONDETAILS:return (T) new ManageProductionBOImpl();
            case VIEWDETAILS: return (T) new ViewDetailsBOImpl();
            default:return null;
        }

    }


}
