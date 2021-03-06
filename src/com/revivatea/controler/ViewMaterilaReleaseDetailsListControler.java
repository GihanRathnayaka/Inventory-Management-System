package com.revivatea.controler;

import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ViewDetailsBO;
import com.revivatea.dto.MaterialReleaseDTO;
import com.revivatea.dto.MaterialReleaseDetailsDTO;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMaterilaReleaseDetailsListControler<T> {
    @FXML
    private TableView<MaterialReleaseDetailsDTO> tblDetails;
    @FXML
    private Button btnBack;

    public static String  releaseID;
    private static ViewDetailsBO viewDetailsBO = BOFactory.getInstance().getBO(BOFactory.BOType.VIEWDETAILS);

    public void initialize() {
        loadAll();
        tblDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("releaseID"));
        tblDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("matRdID"));
        tblDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("rowMatID"));
        tblDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("releaseqty"));
        tblDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/ViewMaterialReleaseDetails.fxml"));
        } catch (IOException e) {
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

    private void loadAll(){
        try {
            List<MaterialReleaseDetailsDTO> list = viewDetailsBO.allresleasedDetailsList("MRNO01");
            if(list==null){return;}
            ObservableList<MaterialReleaseDetailsDTO> dtos = FXCollections.observableArrayList(list);
            tblDetails.setItems(dtos);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }
}
