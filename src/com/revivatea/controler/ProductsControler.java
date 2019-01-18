package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ManageMaterialReceiveBO;
import com.revivatea.business.custom.ManageProductBO;
import com.revivatea.business.custom.ManageProductionBO;
import com.revivatea.dao.custom.MaterialReleaseDAO;
import com.revivatea.dao.custom.ProductionDetailsDAO;
import com.revivatea.dto.MaterialReceiveDTO;
import com.revivatea.dto.ProductDTO;
import com.revivatea.dto.ProductionDetailsDTO;
import com.revivatea.dto.SuperDTO;
import com.revivatea.entity.Product;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsControler<T> {
    @FXML
    private Button btnBack;
    @FXML
    private  JFXTextField txtProductID;
    @FXML
    private  JFXTextField txtName;
    @FXML
    private  JFXDatePicker dptRegDate;
    @FXML
    private  JFXTextField txtAviQty;
    @FXML
    private  JFXTextField txtDescription;
    @FXML
    private  TableView<ProductDTO> tblProduct;
    @FXML
    private  JFXButton btnSave;
    @FXML
    private  JFXButton btnUpdate;
    @FXML
    private  JFXButton btnDalete;
    @FXML
    private  JFXButton btnCrear;

    private static ManageProductBO manageProductBO = BOFactory.getInstance().getBO(BOFactory.BOType.MANAGEPRODUCT);
    private static ManageMaterialReceiveBO manageMaterialReceiveBO= BOFactory.getInstance().getBO(BOFactory.BOType.MANAGEMATERIALRECEIVE);
    private static ManageProductionBO manageProductionBO= BOFactory.getInstance().getBO(BOFactory.BOType.MANAGEPRODUCTIONDETAILS);

    public void initialize(){
        setProID();
        loadAllProduct();
        btnDalete.setDisable(true);
        btnUpdate.setDisable(true);
        tblProduct.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("proID"));
        tblProduct.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblProduct.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("req_Date"));
        tblProduct.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblProduct.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("aviQty"));

        tblProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductDTO>() {
            @Override
            public void changed(ObservableValue<? extends ProductDTO> observable, ProductDTO oldValue, ProductDTO p) {
                if(p==null){return;}
                txtProductID.setText(p.getProID());
                txtAviQty.setText(p.getAviQty()+"");
                txtDescription.setText(p.getDescription());
                txtName.setText(p.getName());
                dptRegDate.setValue(p.getReq_Date());
                btnDalete.setDisable(false);
                btnUpdate.setDisable(false);
                btnSave.setDisable(true);
            }
        });

    }

    @FXML
    private  void btnSave_Onaction(ActionEvent actionEvent) {

        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Name !", ButtonType.OK).showAndWait();
            txtName.requestFocus();
        }
        if(dptRegDate.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please Enter Reg Date !", ButtonType.OK).showAndWait();
            dptRegDate.requestFocus();
        }

        String proID =txtProductID.getText().trim();
        String name =txtName.getText().trim();
        String description =txtDescription.getText().trim();
        double avQty =Double.parseDouble(txtAviQty.getText().trim());
        LocalDate regdate =dptRegDate.getValue();

        try {
            boolean result = manageProductBO.createNewProduct(new ProductDTO(proID, name, regdate, description, avQty));
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Product Created Successfully !", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Product Not Created Error !", ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        reset();

    }

    @FXML
    private  void btnUpdate_OnAction(ActionEvent actionEvent) {

        if(tblProduct.getSelectionModel().getSelectedItem()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Product From Table !", ButtonType.OK).showAndWait();
            return;
        }

        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Name !", ButtonType.OK).showAndWait();
            txtName.requestFocus();
        }
        if(dptRegDate.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please Enter Reg Date !", ButtonType.OK).showAndWait();
            dptRegDate.requestFocus();
        }

        String proID =txtProductID.getText().trim();
        String name =txtName.getText().trim();
        String description =txtDescription.getText().trim();
        double avQty =Double.parseDouble(txtAviQty.getText().trim());
        LocalDate regdate =dptRegDate.getValue();

        try {
            boolean result = manageProductBO.UpdateProduct(new ProductDTO(proID, name, regdate, description, avQty));
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Product Update Successfully !", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Product Not  Update Error !", ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        reset();
    }

    @FXML
    private  void btnDalete_OnAction(ActionEvent actionEvent) {

        try {

            List<ProductionDetailsDTO> all = manageProductionBO.viewAllDetails();
            if(all!=null) {
                for (ProductionDetailsDTO dto : all) {
                    if (dto.getProID().equals(txtProductID.getText().trim())) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Can't Delete In a Relation  !", ButtonType.OK).showAndWait();
                        return;
                    }
                    System.out.println(dto.getProID());
                }
            }
            boolean result = manageProductBO.DeleteProduct(txtProductID.getText().trim());
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Product Delete Successfully !", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Product Not Delete Error !", ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        reset();
    }

    @FXML
    private  void btnCrear_OnAction(ActionEvent actionEvent) {
        reset();
    }

    @FXML
    private  void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/HomePage.fxml"));
        } catch (IOException e) {
            System.out.println("Error-Error");
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();
        System.out.print("");
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    private void setProID(){
        try {
            txtProductID.setText(manageProductBO.getProductID());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    private void reset(){
        txtName.setText("");
        txtProductID.clear();
        txtAviQty.setText("0.0");
        txtDescription.clear();
        dptRegDate.setValue(null);
        loadAllProduct();
        setProID();
        btnDalete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }

    private void loadAllProduct(){
        try {
            List<ProductDTO> list = manageProductBO.getAllProduct();
            if(list==null){tblProduct.setItems(null);return;}
            ObservableList<ProductDTO>temp = FXCollections.observableArrayList(list);
            tblProduct.setItems(temp);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

}
