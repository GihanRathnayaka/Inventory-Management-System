package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ManageUserBO;
import com.revivatea.dto.UserDTO;
import com.revivatea.view.tm.UserTM;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddUserControler<T> {
    @FXML
    private JFXButton btnCrear;
    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtConfimPassword;
    @FXML
    private JFXComboBox cmbUserType;
    @FXML
    private JFXButton btnSave;
    @FXML
    private TableView<UserTM> tblUser;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDalete;
    @FXML
    private JFXTextField txtName;

    private static ManageUserBO manageUserBO = BOFactory.getInstance().getBO(BOFactory.BOType.UserBo);

    public void initialize(){
        tblUser.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblUser.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblUser.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));
        tblUser.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("userType"));
        loadAlluser();
        cmbUserType.getItems().addAll("Admin","User");
        btnSave.setDisable(false);
        btnDalete.setDisable(true);
        btnUpdate.setDisable(true);
        tblUser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserTM>() {
            @Override
            public void changed(ObservableValue<? extends UserTM> observable, UserTM oldValue, UserTM user) {
                if(user==null){return;}
                txtName.setText(user.getName());
                txtPassword.setText(user.getPassword());
                txtUserName.setText(user.getUserName());
                txtUserName.setEditable(false);
                btnSave.setDisable(true);
                btnUpdate.setDisable(false);
                btnDalete.setDisable(false);
                txtPassword.setEditable(true);
            }
        });


    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/HomePage.fxml"));
        } catch (IOException e) {
            System.out.println("Error");
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();
        primaryStage.setMaximized(false);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    @FXML
    private void btnSave_Onaction(ActionEvent actionEvent) {

        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Full Name Of the User !", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }
        if(txtUserName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter User Name !", ButtonType.OK).showAndWait();
            txtUserName.requestFocus();
            return;

        }
        if(txtPassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Password !", ButtonType.OK).showAndWait();
            txtPassword.requestFocus();
            return;
        }
        if(txtConfimPassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Confirm Your PassWord !", ButtonType.OK).showAndWait();
            txtConfimPassword.requestFocus();
            return;
        }

        if(cmbUserType.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select User type !", ButtonType.OK).showAndWait();
            cmbUserType.requestFocus();
            return;
        }

        if(!txtPassword.getText().trim().equals(txtConfimPassword.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"password Not Match !", ButtonType.OK).showAndWait();
            txtConfimPassword.requestFocus();
            return;
        }


        try {
            UserDTO user = manageUserBO.getUser(txtUserName.getText().trim());
            if(user!=null){
                new Alert(Alert.AlertType.ERROR,"Select different User Name !", ButtonType.OK).showAndWait();
                txtUserName.requestFocus();
                return;
            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }



        String name=txtName.getText().trim();
        String username =txtUserName.getText();
        String password =txtPassword.getText();
        String usrType =cmbUserType.getSelectionModel().getSelectedItem().toString();

        try {
            boolean result = manageUserBO.createUser(new UserDTO(username, password, name, usrType));
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully User Created !", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"There is Error User Account Not Created !", ButtonType.OK).showAndWait();
            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        reset();

    }

    @FXML
    private void btnUpdate_OnAction(ActionEvent actionEvent) {
        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Full Name Of the User !", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }
        if(txtUserName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter User Name !", ButtonType.OK).showAndWait();
            txtUserName.requestFocus();
            return;

        }
        if(txtPassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Password !", ButtonType.OK).showAndWait();
            txtPassword.requestFocus();
            return;
        }

        if(cmbUserType.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select User type !", ButtonType.OK).showAndWait();
            cmbUserType.requestFocus();
            return;
        }


        String name=txtName.getText().trim();
        String username =txtUserName.getText();
        String password =txtPassword.getText();
        String usrType =cmbUserType.getSelectionModel().getSelectedItem().toString();

        try {
            boolean result = manageUserBO.updateUser(new UserDTO(username, password, name, usrType));
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully User Update !", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"There is Error User Account Not Update !", ButtonType.OK).showAndWait();
            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        reset();
    }

    @FXML
    private void btnDalete_OnAction(ActionEvent actionEvent) {

        if(!txtUserName.isEditable()) {

            try {
                boolean result = manageUserBO.deleteUser(txtUserName.getText());
                if (result) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully User Deleted !", ButtonType.OK).showAndWait();
                } else {
                    new Alert(Alert.AlertType.ERROR, "There is Error User Account Not Deleted !", ButtonType.OK).showAndWait();
                }

            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            reset();
            return;

        }else {

            new Alert(Alert.AlertType.ERROR, "Select User to Delete !", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void btnCrear_OnAction(ActionEvent actionEvent) {
        reset();
    }

    private void loadAlluser(){
        try {
            List<UserDTO> allUsers = manageUserBO.getAllUsers();
            if(allUsers==null){return;}
            ArrayList<UserTM> tms = new ArrayList<>();
            for ( UserDTO u: allUsers ) {
                tms.add(new UserTM(u.getUserName(),u.getPassword(),u.getName(),u.getUserType()));
            }
            ObservableList<UserTM> observableList = FXCollections.observableArrayList(tms);
            tblUser.setItems(observableList);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

    }
    private void reset(){
        txtUserName.clear();
        txtName.clear();
        txtPassword.clear();
        txtConfimPassword.clear();
        loadAlluser();
        cmbUserType.getSelectionModel().clearSelection();
        btnSave.setDisable(false);
        btnDalete.setDisable(true);
        btnUpdate.setDisable(true);
        txtPassword.setEditable(true);
        txtUserName.setEditable(true);
        txtName.requestFocus();
    }


}
