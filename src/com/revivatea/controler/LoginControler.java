package com.revivatea.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ManageUserBO;
import com.revivatea.dto.UserDTO;
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

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControler {
    @FXML
    private JFXTextField txtUerName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Button btnExit;

    private static ManageUserBO manageUserBO = BOFactory.getInstance().getBO(BOFactory.BOType.UserBo);

    @FXML
    private void btnLogin_OnAction(ActionEvent actionEvent) {
        if(!txtUerName.getText().trim().isEmpty()||!txtPassword.getText().trim().isEmpty()){
            try {
                UserDTO user = manageUserBO.getUser(txtUerName.getText().trim());
                if(user==null){
                    new Alert(Alert.AlertType.ERROR,"Your User Name Invalid !", ButtonType.OK).showAndWait();
                    txtUerName.requestFocus();
                    return;
                }

                if(user.getUserName().equals(txtUerName.getText().trim())&&user.getPassword().equals(txtPassword.getText().trim())){

                    HomeControler.user=user;
                    Parent root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/HomePage.fxml"));

                    Scene subScene = new Scene(root);
                    Stage primaryStage = (Stage) btnExit.getScene().getWindow();
                    primaryStage.setScene(subScene);
                    primaryStage.centerOnScreen();

                    TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                    tt.setFromX(-subScene.getWidth());
                    tt.setToX(0);
                    tt.play();

                }else {

                    new Alert(Alert.AlertType.ERROR, "Your User Password Invalid !", ButtonType.OK).showAndWait();
                    txtPassword.requestFocus();
                    return;
                }
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

        }else {
            new Alert(Alert.AlertType.ERROR, "Please Enter Your User Name Password !", ButtonType.OK).showAndWait();
            txtUerName.requestFocus();
        }


    }

    @FXML
    private void btnExit_OnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
