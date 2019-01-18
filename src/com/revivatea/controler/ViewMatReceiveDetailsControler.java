package com.revivatea.controler;

import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ViewDetailsBO;
import com.revivatea.dto.MaterialReceiveDTO;
import com.revivatea.dto.MaterialReceiveDetailsDTO;
import com.revivatea.entity.MaterialReceive;
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
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMatReceiveDetailsControler<T> {
    @FXML
    private TableView<MaterialReceiveDetailsDTO> tblDetails;
    @FXML
    private Button btnBack;

    private static ViewDetailsBO viewDetailsBO = BOFactory.getInstance().getBO(BOFactory.BOType.VIEWDETAILS);
    public static String materialID;

    public void initialize() {
        loadAll();
        tblDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("matID"));
        tblDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("madeDate"));
        tblDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        tblDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("packSize"));
        tblDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("unitType"));
        tblDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblDetails.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("aviableQty"));
    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/ViewMaterialReceiveDetails.fxml"));
            } catch (IOException e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
                System.out.print("");
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
            List<MaterialReceiveDetailsDTO> list = viewDetailsBO.getAllMaterialReceiveDetailsDtoList(materialID);
            if(list==null){return;}
            ObservableList<MaterialReceiveDetailsDTO> dtos = FXCollections.observableArrayList(list);
            tblDetails.setItems(dtos);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

}
