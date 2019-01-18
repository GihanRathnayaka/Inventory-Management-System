package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ManageUserBO;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePasswordControler {
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtConfimPassword;
    @FXML
    private JFXPasswordField txtExistingPassword;
    @FXML
    private JFXButton btnChangePassword;
    @FXML
    private Button btnBack;

    private static ManageUserBO manageUserBO = BOFactory.getInstance().getBO(BOFactory.BOType.UserBo);


    public void initialize(){
        //System.out.println(HomeControler.user);
    }


    @FXML
    private void btnChangePassword_OnAction(ActionEvent actionEvent) {

        if(txtUserName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Enter Your User Name ! ", ButtonType.OK).showAndWait();
            txtUserName.requestFocus();
            return;
        }
        if(txtExistingPassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Enter Your User Name ! ", ButtonType.OK).showAndWait();
            txtExistingPassword.requestFocus();
            return;
        }
        if(txtPassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Enter Your User Name ! ", ButtonType.OK).showAndWait();
            txtPassword.requestFocus();
            return;
        }
        if(txtConfimPassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Enter Your User Name ! ", ButtonType.OK).showAndWait();
            txtConfimPassword.requestFocus();
            return;
        }

        if(!txtPassword.getText().trim().equals(txtExistingPassword.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Password is not Match ! ", ButtonType.OK).showAndWait();
            txtPassword.requestFocus();
            return;
        }

        if(!txtExistingPassword.getText().trim().equals(HomeControler.user.getPassword())){
            new Alert(Alert.AlertType.ERROR,"Existing Password Not Match ! ", ButtonType.OK).showAndWait();
            txtExistingPassword.requestFocus();
            return;
        }


        if(!HomeControler.user.getUserName().equals(txtUserName.getText())){
            new Alert(Alert.AlertType.ERROR,"Your User Name Is Invalid  ! ", ButtonType.OK).showAndWait();
            txtUserName.requestFocus();
            return;
        }


        String userName =txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        try {
            boolean result = manageUserBO.resetPassword(userName, password);
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Password Changed Successfully ! ", ButtonType.OK).showAndWait();
            }else{
                new Alert(Alert.AlertType.ERROR,"Pssword Not Changed Error! ", ButtonType.OK).showAndWait();

            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        txtExistingPassword.clear();
        txtPassword.clear();
        txtUserName.clear();
        txtConfimPassword.clear();
    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/HomePage.fxml"));
        } catch (IOException e) {
            System.out.println("Errors");
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

}
