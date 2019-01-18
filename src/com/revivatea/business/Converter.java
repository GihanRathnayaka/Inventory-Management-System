package com.revivatea.business;

import com.revivatea.dto.*;
import com.revivatea.entity.*;

import java.util.List;
import java.util.stream.Collectors;


public class Converter {

    public static <T extends SuperDTO> T getDTO(SuperEntity entity) {
        if (entity instanceof User) {
            User u = (User) entity;
            return (T) new UserDTO(u.getUserName(), u.getPassword(), u.getName(),u.getUserType());

        } else if(entity instanceof RawMaterial){
            RawMaterial m = (RawMaterial) entity;
            return (T) new RawMaterialDTO(m.getRowMatID(),m.getName(),m.getRegDate(),m.getAviableQTY(),m.getDescriptiion());
        }else if(entity instanceof Supplier){
            Supplier s = (Supplier) entity;
            return (T) new SupplierDTO(s.getSuplierID(),s.getCompanyName(),s.getAddress(),s.getEmail(),s.getTele(),s.getFax(),s.getConatctPerson(),s.getcMobile(),s.getRegDate(),s.getDebitLimit(),s.getBalance());
        }else if(entity instanceof MaterialReceive){
            MaterialReceive m = (MaterialReceive) entity;
            return (T) new MaterialReceiveDTO(m.getReceiveID(),m.getReceiveDate(),m.getDescription(),m.getTotal(),m.getSupplierID());
        }else if(entity instanceof MaterialReceiveDetails){
            MaterialReceiveDetails md = (MaterialReceiveDetails) entity;
            return (T) new MaterialReceiveDetailsDTO(md.getMadeDate(),md.getExpireDate(),md.getPrice(),md.getQty(),md.getAviableQty(),md.getMaterialReceiveDetailsFK().getRowMatID(),(md.getPrice()*md.getQty()),md.getPackSize(),md.getUnitType());
        }else if(entity instanceof MaterialRelease){
            MaterialRelease mrd = (MaterialRelease) entity;
            return (T) new MaterialReleaseDTO(mrd.getReleaseID(),mrd.getReleaseDate(),mrd.getTotalValue(),mrd.getDescription());
        }else if(entity instanceof MaterialReleaseDetails){
            MaterialReleaseDetails  mrd = (MaterialReleaseDetails) entity;
            return (T) new MaterialReleaseDetailsDTO(mrd.getMatRdID(),mrd.getReleaseqty(),mrd.getTotal(),mrd.getReleaseID(),mrd.getRowMatID());
        }else if(entity instanceof Fifo){
            Fifo  f = (Fifo) entity;
            return (T) new FifoDTO(f.getPrice(),f.getReQTy(),f.getTotalvalue(),f.getFifoFK().getId(),f.getFifoFK().getMatRdID());
        }else if(entity instanceof Product){
            Product  p = (Product) entity;
            return (T) new ProductDTO(p.getProID(),p.getName(),p.getReq_Date(),p.getDescription(),p.getAviQty());
        }else if(entity instanceof ProductionDetails){
            ProductionDetails p = (ProductionDetails) entity;
            return (T) new ProductionDetailsDTO(p.getPdID(),p.getRecordDate(),p.getBellSheetNo(),p.getNofPackage(),p.getPackagePrice(),p.getPackageUnite(),p.getTotal(),p.getMadeDate(),p.getExpire(),p.getPackType(),p.getDescription(),p.getProductionDetailsFK().getProID(),p.getProductionDetailsFK().getReleaseID());
        }else if(entity instanceof ProductCategory){
            ProductCategory p = (ProductCategory) entity;
            return (T) new ProductCategoryDTO(p.getProductCateID(),p.getName(),p.getDescription());
        }
        else {
            throw new RuntimeException("This entity can't be converted to a DTO");
        }
    }

    public static <T extends SuperEntity>T getEntity(SuperDTO dto){
        if(dto instanceof UserDTO){
            UserDTO u = (UserDTO) dto;
            return (T) new User(u.getUserName(), u.getPassword(), u.getName(),u.getUserType());
        }else if(dto instanceof RawMaterialDTO){
            RawMaterialDTO m= (RawMaterialDTO) dto;
            return (T) new RawMaterial(m.getRowMatID(),m.getName(),m.getRegDate(),m.getAviableQTY(),m.getDescriptiion());
        }else if(dto instanceof SupplierDTO){
            SupplierDTO s = (SupplierDTO) dto;
            return (T) new Supplier(s.getSuplierID(),s.getCompanyName(),s.getAddress(),s.getEmail(),s.getTele(),s.getFax(),s.getConatctPerson(),s.getcMobile(),s.getRegDate(),s.getDebitLimit(),s.getBalance());
        }else if(dto instanceof MaterialReceiveDTO){
            MaterialReceiveDTO m = (MaterialReceiveDTO) dto;
            return (T) new MaterialReceive(m.getReceiveID(),m.getReceiveDate(),m.getDescription(),m.getTotal(),m.getSupplierID());
        }else if(dto instanceof MaterialReceiveDetailsDTO){
            MaterialReceiveDetailsDTO md = (MaterialReceiveDetailsDTO) dto;
           return  (T) new MaterialReceiveDetails(1,md.getMadeDate(),md.getExpireDate(),md.getPrice(),md.getQty(),md.getAviableQty(),new MaterialReceiveDetailsFK(),md.getPackSize(),md.getUnitType());
        }else if(dto instanceof MaterialReleaseDTO){
            MaterialReleaseDTO mr = (MaterialReleaseDTO) dto;
            return (T) new MaterialRelease(mr.getReleaseID(),mr.getReleaseDate(),mr.getTotalValue(),mr.getDescription());
        }else if(dto instanceof MaterialReleaseDetailsDTO){
            MaterialReleaseDetailsDTO mrd = (MaterialReleaseDetailsDTO) dto;
            return (T) new MaterialReleaseDetails(mrd.getMatRdID(),mrd.getReleaseqty(),mrd.getTotal(),mrd.getReleaseID(),mrd.getRowMatID());
        }else if(dto instanceof FifoDTO){
            FifoDTO f = (FifoDTO) dto;
            return (T) new Fifo(f.getPrice(),f.getReQTy(),f.getTotalvalue(),new FifoFK(f.getId(),f.getMatRdID()));
        }else if(dto instanceof ProductDTO){
            ProductDTO  p = (ProductDTO) dto;
            return (T) new Product(p.getProID(),p.getName(),p.getReq_Date(),p.getDescription(),p.getAviQty());
        }else if(dto instanceof ProductionDetailsDTO){
            ProductionDetailsDTO p = (ProductionDetailsDTO) dto;
            return (T) new ProductionDetails(1,p.getRecordDate(),p.getBellSheetNo(),p.getNofPackage(),p.getPackagePrice(),p.getPackageUnite(),p.getTotal(),p.getMadeDate(),p.getExpire(),p.getPackType(),p.getDescription(),p.getProID(),p.getReleaseID());
        }else if(dto instanceof ProductCategoryDTO){
            ProductCategoryDTO p = (ProductCategoryDTO) dto;
            return (T) new ProductCategory(p.getProductCateID(),p.getName(),p.getDescription());
        }
        else {
            throw  new RuntimeException("Dto Can't Convert Entity");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        return entities.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }

    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        return dtos.stream().map(Converter::<T>getEntity).collect(Collectors.toList());

    }



}
