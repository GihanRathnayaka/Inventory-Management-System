package com.revivatea.controler;

import com.revivatea.business.BOFactory;
import com.revivatea.business.custom.ViewDetailsBO;
import com.revivatea.dto.MaterialReceiveDetailsDTO;
import com.revivatea.dto.ProductionDetailsDTO;
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

import static com.revivatea.controler.ViewMatReceiveDetailsControler.materialID;

public class ViewProductionDetailsControler<T> {
    @FXML
    private Button btnBack;
    @FXML
    private TableView<ProductionDetailsDTO> tblDetails;

    public static String relaseId;
    private static ViewDetailsBO viewDetailsBO = BOFactory.getInstance().getBO(BOFactory.BOType.VIEWDETAILS);

    public void initialize() {

        loadAll();
        tblDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("proID"));
        tblDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bellSheetNo"));
        tblDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nofPackage"));
        tblDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("packagePrice"));
        tblDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("packageUnite"));
        tblDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("madeDate"));
        tblDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("expire"));
        tblDetails.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("packType"));
        tblDetails.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("releaseID"));
        tblDetails.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("recordDate"));
        tblDetails.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("description"));

    }

        @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
            Parent root = null;
            ViewMaterialReleaseDetailsControler.ID =2;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/revivatea/view/ViewMaterialReleaseDetails.fxml"));
            } catch (IOException e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) btnBack.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            System.out.print("y");
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
    }

    private void loadAll(){
        try {
            List<ProductionDetailsDTO> list = viewDetailsBO.findAllProductionDetails(relaseId);
          if(list==null){return;}
            ObservableList<ProductionDetailsDTO> dtos = FXCollections.observableArrayList(list);
            tblDetails.setItems(dtos);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }
}
