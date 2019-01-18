package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ManageMaterialReceiveBO;
import com.revivatea.business.custom.ManageSupplierBO;
import com.revivatea.dto.MaterialReceiveDTO;
import com.revivatea.dto.SupplierDTO;
import com.revivatea.view.tm.SupplierTM;
import com.revivatea.view.tm.ValidationClass;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuplierControler<T> {
    @FXML
    private JFXTextField txtContactPerson;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtTele;
    @FXML
    private JFXTextField txtFax;
    @FXML
    private JFXTextField txtMobile;
    @FXML
    private JFXDatePicker dtpRegDate;
    @FXML
    private JFXTextField txtDebitLimit;
    @FXML
    private JFXTextField txtBalance;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDalete;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<SupplierTM> tblSupplier;
    @FXML
    private JFXTextField txtSuplierId;

    private static ManageSupplierBO manageSupplierBO = BOFactory.getInstance().getBO(BOFactory.BOType.ManageSUPPLIER);
    private static ManageMaterialReceiveBO manageMaterialReceiveBO=BOFactory.getInstance().getBO(BOFactory.BOType.MANAGEMATERIALRECEIVE);

    public void initialize(){
        setID();
        loadAll();
        btnDalete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        tblSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("suplierID"));
        tblSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("companyName"));
        tblSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("tele"));
        tblSupplier.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("fax"));
        tblSupplier.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("conatctPerson"));
        tblSupplier.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("Mobile"));
        tblSupplier.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("regDate"));
        tblSupplier.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("debitLimit"));
        tblSupplier.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("balance"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierTM>() {
            @Override
            public void changed(ObservableValue<? extends SupplierTM> observable, SupplierTM oldValue, SupplierTM sup) {
                if(sup==null){return;}
                btnDalete.setDisable(false);
                btnUpdate.setDisable(false);
                btnSave.setDisable(true);
                txtAddress.setText(sup.getAddress());
                txtBalance.setText(sup.getBalance()+"");
                txtMobile.setText(sup.getMobile());
                txtTele.setText(sup.getTele());
                txtDebitLimit.setText(sup.getDebitLimit()+"");
                txtFax.setText(sup.getFax());
                txtSuplierId.setText(sup.getSuplierID());
                txtEmail.setText(sup.getEmail());
                txtContactPerson.setText(sup.getConatctPerson());
                txtName.setText(sup.getCompanyName());
                dtpRegDate.setValue(sup.getRegDate());
            }
        });

    }


    @FXML
    private void btnSave_Onaction(ActionEvent actionEvent) {
        if(txtSuplierId.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Supplier ID !", ButtonType.OK).showAndWait();
            txtSuplierId.requestFocus();
            return;
        }
        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Name!", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }
        if(txtAddress.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Address !", ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        }
        if(txtEmail.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Email Address !", ButtonType.OK).showAndWait();
            txtEmail.requestFocus();
            return;
        }
        if(txtContactPerson.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Name Contact Person !", ButtonType.OK).showAndWait();
            txtContactPerson.requestFocus();
            return;
        }
        if(txtTele.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter  Tele Phone Number !", ButtonType.OK).showAndWait();
            txtTele.requestFocus();
            return;
        }
        if(txtMobile.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Contact Number Of Contact Persion !", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }

        if(!ValidationClass.isValidPhoneNumber(txtTele.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Please Enter Tele Phone Number Is Invalid !", ButtonType.OK).showAndWait();
            txtTele.requestFocus();
            return;
        }

        if(!ValidationClass.isValidPhoneNumber(txtMobile.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Invalid Mobile Number !", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }

        if(!ValidationClass.isValidEmailAddress(txtEmail.getText().trim())){
            new Alert(Alert.AlertType.CONFIRMATION,"Invalid  Email Address  !", ButtonType.OK).showAndWait();
            txtEmail.requestFocus();
            return;
        }
        
        if(!ValidationClass.isDouble(txtDebitLimit.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Invalid Number !", ButtonType.OK).showAndWait();
            txtDebitLimit.requestFocus();
            return;  
        }


        String sid =txtSuplierId.getText().trim();
        String name =txtName.getText().trim();
        String address =txtAddress.getText().trim();
        String email =txtEmail.getText().trim();
        String tele =txtTele.getText().trim();
        String fax =txtFax.getText().trim();
        String cPerson =txtContactPerson.getText().trim();
        String Cmobile =txtMobile.getText();
        LocalDate regDate=dtpRegDate.getValue();
        double debitLimit =Double.parseDouble(txtDebitLimit.getText().trim());
        double balance = Double.parseDouble(txtBalance.getText().trim());

        try {
            boolean result = manageSupplierBO.createSupplier(new SupplierDTO(sid, name, address, email, tele, fax, cPerson, Cmobile, regDate, debitLimit, balance));

            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Created Supplier Account !", ButtonType.OK).showAndWait();

            }else {
                new Alert(Alert.AlertType.ERROR,"Not Created Supplier Account !", ButtonType.OK).showAndWait();

            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }


        reset();

    }

    @FXML
    private void btnCrear_OnAction(ActionEvent actionEvent) {
        reset();
    }

    @FXML
    private void btnDalete_OnAction(ActionEvent actionEvent) {
        if(btnSave.isDisable()){
            try {

                List<MaterialReceiveDTO> all = manageMaterialReceiveBO.getAllMaterialRecievie();
                for (MaterialReceiveDTO dto:all) {
                    if(dto.getSupplierID().equals(txtSuplierId.getText().trim())){
                        new Alert(Alert.AlertType.ERROR,"Can't Delete In a Relation !", ButtonType.OK).showAndWait();
                        return;
                    }
                }

                boolean result = manageSupplierBO.deleteSupplier(txtSuplierId.getText().trim());
                if(result){
                    new Alert(Alert.AlertType.CONFIRMATION,"Successfully Delete Supplier Account !", ButtonType.OK).showAndWait();

                }else {
                    new Alert(Alert.AlertType.ERROR,"Not Deleted Supplier Account !", ButtonType.OK).showAndWait();

                }
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        }

        reset();
    }

    @FXML
    private void btnUpdate_OnAction(ActionEvent actionEvent) {
        if(txtSuplierId.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Supplier ID !", ButtonType.OK).showAndWait();
            txtSuplierId.requestFocus();
            return;
        }
        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Name!", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }
        if(txtAddress.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Address !", ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        }
        if(txtEmail.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Email Address !", ButtonType.OK).showAndWait();
            txtEmail.requestFocus();
            return;
        }
        if(txtContactPerson.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Name Contact Person !", ButtonType.OK).showAndWait();
            txtContactPerson.requestFocus();
            return;
        }
        if(txtTele.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter  Tele Phone Number !", ButtonType.OK).showAndWait();
            txtTele.requestFocus();
            return;
        }
        if(txtMobile.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Contact Number Of Contact Persion !", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }

        if(!ValidationClass.isValidPhoneNumber(txtTele.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Please Enter Tele Phone Number Is Invalid !", ButtonType.OK).showAndWait();
            txtTele.requestFocus();
            return;
        }

        if(!ValidationClass.isValidPhoneNumber(txtMobile.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Invalid Mobile Number !", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }

        if(!ValidationClass.isValidEmailAddress(txtEmail.getText().trim())){
            new Alert(Alert.AlertType.CONFIRMATION,"Invalid  Email Address  !", ButtonType.OK).showAndWait();
            txtEmail.requestFocus();
            return;
        }

        if(!ValidationClass.isDouble(txtDebitLimit.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Invalid Number !", ButtonType.OK).showAndWait();
            txtDebitLimit.requestFocus();
            return;
        }


        String sid =txtSuplierId.getText().trim();
        String name =txtName.getText().trim();
        String address =txtAddress.getText().trim();
        String email =txtEmail.getText().trim();
        String tele =txtTele.getText().trim();
        String fax =txtFax.getText().trim();
        String cPerson =txtContactPerson.getText().trim();
        String Cmobile =txtMobile.getText();
        LocalDate regDate=dtpRegDate.getValue();
        double debitLimit =Double.parseDouble(txtDebitLimit.getText().trim());
        double balance = Double.parseDouble(txtBalance.getText().trim());

        try {
            boolean result = manageSupplierBO.updateSupplier(new SupplierDTO(sid, name, address, email, tele, fax, cPerson, Cmobile, regDate, debitLimit, balance));

            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Update Supplier Account !", ButtonType.OK).showAndWait();

            }else {
                new Alert(Alert.AlertType.ERROR,"Not Updated Supplier Account !", ButtonType.OK).showAndWait();

            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }


        reset();
    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
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

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    private void setID(){
        try {
            txtSuplierId.setText(manageSupplierBO.getSupplierID());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    private void loadAll(){
        try {
            List<SupplierDTO> all = manageSupplierBO.getAllSupplier();
            if(all==null){tblSupplier.setItems(null);return;}
            ArrayList<SupplierTM> temp =new ArrayList<>();
            for (SupplierDTO s:all) {
               temp.add(new SupplierTM(s.getSuplierID(),s.getCompanyName(),s.getAddress(),s.getEmail(),s.getTele(),s.getFax(),s.getConatctPerson(),s.getcMobile(),s.getRegDate(),s.getDebitLimit(),s.getBalance()));
            }
            //System.out.println(temp);
            ObservableList<SupplierTM> list = FXCollections.observableArrayList(temp);
            tblSupplier.setItems(list);


        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

    }

    private void reset(){
        btnDalete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        txtBalance.setText("0");
        txtDebitLimit.setText("");
        txtMobile.clear();
        txtEmail.clear();
        txtContactPerson.clear();
        txtTele.clear();txtFax.clear();
        txtName.clear();
        txtAddress.clear();
        txtSuplierId.clear();
        loadAll();
        setID();

    }

}
