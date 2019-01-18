package com.revivatea.controler;

import com.jfoenix.controls.JFXTextField;
import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ViewDetailsBO;
import com.revivatea.dto.MaterialReceiveDTO;
import com.revivatea.dto.MaterialReleaseDTO;
import com.revivatea.entity.MaterialRelease;
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

public class ViewMaterialReleaseDetailsControler<T> {
    public static  int ID  ;
    private static ViewDetailsBO viewDetailsBO = BOFactory.getInstance().getBO(BOFactory.BOType.VIEWDETAILS);

    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtReleaseId;
    @FXML
    private TableView<MaterialReleaseDTO> tblDetials;


    public void initialize() {
        loadAll();
        tblDetials.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("releaseID"));
        tblDetials.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        tblDetials.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        tblDetials.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/ViewDetails.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
            System.out.print("Error");
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
    @FXML
    private void txtReleaseId_On_Key_Released(KeyEvent keyEvent) {
        if(!txtReleaseId.getText().trim().isEmpty()) {
            try {
                List<MaterialReleaseDTO> all = viewDetailsBO.searchReleaseList(txtReleaseId.getText().trim()+"%");
                if(all==null){return;}
                ObservableList<MaterialReleaseDTO> dtos = FXCollections.observableArrayList(all);
                tblDetials.setItems(dtos);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
        }else {
            loadAll();
        }
    }

    @FXML
    private void tblDetials_On_Mouse_Clicked(MouseEvent mouseEvent) {
        if(tblDetials.getSelectionModel().getSelectedItem()!=null){
            Parent root = null;
            if(ID==2){
                    try {
                        root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/ViewProductionDetails.fxml"));
                        ViewProductionDetailsControler.relaseId= tblDetials.getSelectionModel().getSelectedItem().getReleaseID();

                    } catch (IOException e) {
                        Logger.getLogger("").log(Level.SEVERE, null, e);
                    }
            }else {
                 ViewMaterilaReleaseDetailsListControler.releaseID= tblDetials.getSelectionModel().getSelectedItem().getReleaseID();

                try {
                        root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/ViewMaterilaReleaseDetailsList.fxml"));
                    } catch (IOException e) {
                        Logger.getLogger("").log(Level.SEVERE, null, e);
                    }

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

    private void loadAll(){
        try {
            List<MaterialReleaseDTO> list = viewDetailsBO.allresleasedList();
            if(list==null){return;}
            ObservableList<MaterialReleaseDTO> dtos = FXCollections.observableArrayList(list);
            tblDetials.setItems(dtos);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }
}
