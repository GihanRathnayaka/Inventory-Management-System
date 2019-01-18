package com.revivatea.controler;

import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ViewDetailsBO;
import com.revivatea.dto.MaterialReceiveDTO;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMaterialReceiveDetailsControler<T> {
    @FXML
    private TableView<MaterialReceiveDTO> tblDetails;
    @FXML
    private JFXTextField txtReceiveID;
    @FXML
    private Button btnBack;

    private static ViewDetailsBO viewDetailsBO = BOFactory.getInstance().getBO(BOFactory.BOType.VIEWDETAILS);

    public void initialize() {
        loadAll();
        tblDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("receiveID"));
        tblDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("receiveDate"));
        tblDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    @FXML
    private void tblDetails_On_Mouse_Clicked(MouseEvent mouseEvent) {
        if(tblDetails.getSelectionModel().getSelectedItem()!=null){
           ViewMatReceiveDetailsControler.materialID= tblDetails.getSelectionModel().getSelectedItem().getReceiveID();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/ViewMatReceiveDetails.fxml"));
            } catch (IOException e) {
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
    }

    @FXML
    private void txtReceiveID_On_Key_Released(KeyEvent keyEvent) {
        if(!txtReceiveID.getText().trim().isEmpty()) {
            try {
                List<MaterialReceiveDTO> all = viewDetailsBO.getAllMaterialReceiveDetails(txtReceiveID.getText().trim()+"%");
                if(all==null){return;}
                ObservableList<MaterialReceiveDTO> dtos = FXCollections.observableArrayList(all);
                tblDetails.setItems(dtos);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        }else {
            loadAll();
        }
    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/ViewDetails.fxml"));
        } catch (IOException e) {
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


    private void loadAll(){
        try {
            List<MaterialReceiveDTO> list = viewDetailsBO.getAllMaterialReceiveDetails();
            if(list==null){return;}
            ObservableList<MaterialReceiveDTO> dtos = FXCollections.observableArrayList(list);
            tblDetails.setItems(dtos);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }
}
